package com.myntra.android.speciesdetectionandroid.ui.catcharea;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.myntra.android.speciesdetectionandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HeatMapDemoFragment extends HeatMapFragment implements DiseaseCountSheet.BottomSheetListener {

    /**
     * Alternative radius for convolution
     */
    private static final int ALT_HEATMAP_RADIUS = 29;

    /**
     * Alternative opacity of heatmap overlay
     */
    private static final double ALT_HEATMAP_OPACITY = 0.9;

    /**
     * Alternative heatmap gradient (blue -> red)
     * Copied from Javascript version
     */
    public static ArrayList<ReportedCases> rc_mod;
    private static final int[] ALT_HEATMAP_GRADIENT_COLORS = {
            Color.argb(255, 255, 255, 255),// transparent
            Color.argb(255, 255, 255, 255),
            Color.rgb(255, 191, 255),
            Color.rgb(255, 255, 127),
            Color.rgb(255, 255, 255)
    };
    ArrayList<ReportedCases> rc = new ArrayList<>();
    public static final float[] ALT_HEATMAP_GRADIENT_START_POINTS = {
            0.60f, 0.70f, 0.80f, 0.90f, 1.0f
    };

    public static final Gradient ALT_HEATMAP_GRADIENT = new Gradient(ALT_HEATMAP_GRADIENT_COLORS,
            ALT_HEATMAP_GRADIENT_START_POINTS);
    private HeatmapTileProvider mProvider;
    private TileOverlay mOverlay;

    private boolean mDefaultGradient = true;
    private boolean mDefaultRadius = true;
    private boolean mDefaultOpacity = true;

    /**
     * Maps name of data set to data (list of LatLngs)
     * Also maps to the URL of the data set for attribution
     */
    private HashMap<String, DataSet> mLists = new HashMap<String, DataSet>();

    private double factor = 0.4;

    protected int getLayoutId() {
        return R.layout.fragment_heatmap_demo;
    }

    @Override
    protected void showBottomInfo(LatLng point) {
        rc_mod = new ArrayList<>();
        double lat1 = point.latitude - factor;
        double lat2 = point.latitude + factor;
        double lng1 = point.longitude - factor;
        double lng2 = point.longitude + factor;
        int count = 0;
        if (rc.size() != 0) {
            for (ReportedCases report : rc) {
                if (Double.valueOf(report.getAddresslat()) < lat2 && Double.valueOf(report.getAddresslat()) > lat1 && Double.valueOf(report.getAddresslng()) < lng2 && Double.valueOf(report.getAddresslng()) > lng1) {
                    count++;
                    rc_mod.add(report);
                }
            }
        }

        DiseaseCountSheet bottomSheet = new DiseaseCountSheet();

        bottomSheet.show(getActivity().getSupportFragmentManager(), "exampleBottomSheet");
    }

    @Override
    protected void startDemo() {
        float zoom = getMap().getCameraPosition().zoom;
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.8467, 80.9462), zoom));

        // Set up the spinner/dropdown list
//        Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);
//        spinner.setVisibility(View.GONE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.heatmaps_datasets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new SpinnerActivity());

        try {
            mLists.put(getString(R.string.medicare), new DataSet(getList(),
                    getString(R.string.medicare_url)));
            mLists.put(getString(R.string.police_stations), new DataSet(readItems(R.raw.police),
                    getString(R.string.police_stations_url)));
        } catch (JSONException e) {
            Toast.makeText(getContext(), "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
        // Make the handler deal with the map
        // Input: list of WeightedLatLngs, minimum and maximum zoom levels to calculate custom
        // intensity from, and the map to draw the heatmap on
        // radius, gradient and opacity not specified, so default are used
    }

    // Datasets from http://data.gov.au
    private ArrayList<LatLng> readItems(int resource) throws JSONException {
        ArrayList<LatLng> list = new ArrayList<LatLng>();
        InputStream inputStream = getResources().openRawResource(resource);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("lat");
            double lng = object.getDouble("lng");
            list.add(new LatLng(lat, lng));
        }
        return list;
    }

    private ArrayList<LatLng> getList() {
        ArrayList<LatLng> latLngs = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("cases");
        Log.d("here2", "cases");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ReportedCases post = postSnapshot.getValue(ReportedCases.class);
                    Log.d("CheckCoor", "onDataChange: " + post.toString());
                    rc.add(post);
                }
                for (int i = 0; i < rc.size(); i++) {
                    latLngs.add(new LatLng(Double.valueOf(rc.get(i).addresslat), Double.valueOf(rc.get(i).addresslng)));
                }

//                TextView attribution = ((TextView) .findViewById(R.id.attribution));

                // Check if need to instantiate (avoid setData etc twice)
                if (mProvider == null) {
                    mProvider = new HeatmapTileProvider.Builder().data(
                            mLists.get("Medicare Office").getData()).build();
                    mOverlay = getMap().addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
                    // Render links
//                    attribution.setMovementMethod(LinkMovementMethod.getInstance());
                } else {
                    mProvider.setData(latLngs);
                    mOverlay.clearTileCache();
                }
                // Update attribution
//                attribution.setText(Html.fromHtml(String.format(getString(R.string.attrib_format),
//                        "")));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tmz", "Failed to read value.", error.toException());
            }
        });
        return latLngs;
    }

    public void changeRadius(View view) {
        if (mDefaultRadius) {
            mProvider.setRadius(ALT_HEATMAP_RADIUS);
        } else {
            mProvider.setRadius(ALT_HEATMAP_RADIUS);
        }
        mOverlay.clearTileCache();
        mDefaultRadius = !mDefaultRadius;
    }

    public void changeGradient(View view) {
        if (mDefaultGradient) {
            mProvider.setGradient(ALT_HEATMAP_GRADIENT);
        } else {
            mProvider.setGradient(HeatmapTileProvider.DEFAULT_GRADIENT);
        }
        mOverlay.clearTileCache();
        mDefaultGradient = !mDefaultGradient;
    }

    public void changeOpacity(View view) {
        if (mDefaultOpacity) {
            mProvider.setOpacity(ALT_HEATMAP_OPACITY);
        } else {
            mProvider.setOpacity(HeatmapTileProvider.DEFAULT_OPACITY);
        }
        mOverlay.clearTileCache();
        mDefaultOpacity = !mDefaultOpacity;
    }

    @Override
    public void onButtonClicked(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onButtonClicked(String text) {
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//    }

    // Dealing with spinner choices
    public class SpinnerActivity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    /**
     * Helper class - stores data sets and sources.
     */
    private class DataSet {
        private ArrayList<LatLng> mDataset;
        private String mUrl;

        public DataSet(ArrayList<LatLng> dataSet, String url) {
            this.mDataset = dataSet;
            this.mUrl = url;
        }

        public ArrayList<LatLng> getData() {
            return mDataset;
        }

        public String getUrl() {
            return mUrl;
        }
    }
}
