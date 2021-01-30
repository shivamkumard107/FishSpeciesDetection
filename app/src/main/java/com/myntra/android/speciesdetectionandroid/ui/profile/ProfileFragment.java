package com.myntra.android.speciesdetectionandroid.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.myntra.android.speciesdetectionandroid.R;


public class ProfileFragment extends Fragment {
    MaterialCardView aboutus;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view=inflater.inflate(R.layout.fragment_profile, container, false);
//       aboutus=view.findViewById(R.id.cardAbout);
//       aboutus.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               startActivity(new Intent(getContext(),About_us.class));
//           }
//       });
       return view;
    }
}