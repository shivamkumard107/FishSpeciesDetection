package com.myntra.android.speciesdetectionandroid.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.myntra.android.speciesdetectionandroid.R;

import java.util.Objects;

public class LoginOption extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login_option, container, false);
        Button user=view.findViewById(R.id.btn_user);
        Button admin=view.findViewById(R.id.btn_admin);



        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences=getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("user","admin");
                editor.commit();

                phoneNumberFragment phoneNumberFragment = new phoneNumberFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, phoneNumberFragment).addToBackStack(null).commit();


            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* SharedPreferences preferences=getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("user","not admin");
                editor.commit();*/
                phoneNumberFragment phoneNumberFragment = new phoneNumberFragment();
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container, phoneNumberFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}