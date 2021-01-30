package com.myntra.android.speciesdetectionandroid.ui.catcharea;

public class ReportedCases {
    Long isweb;
    String addresslat;
    String addresslng;
    int age;
    String disease;
    String gender;
    String h_name;
    String name;
    String pincode;
    String state;

    public Long isIsweb() {
        return isweb;
    }

    public String getAddresslat() {
        return addresslat;
    }

    public String getAddresslng() {
        return addresslng;
    }

    public int getAge() {
        return age;
    }

    public String getDisease() {
        return disease;
    }

    public String getGender() {
        return gender;
    }

    public String getH_name() {
        return h_name;
    }

    public String getName() {
        return name;
    }

    public String getPincode() {
        return pincode;
    }

    public String getState() {
        return state;
    }

    public String getWater_borne() {
        return water_borne;
    }

    String water_borne;

    public ReportedCases(Long isweb, String addresslat, String addresslng, int age, String disease, String gender, String h_name, String name, String pincode, String state, String water_borne) {
        this.isweb = isweb;
        this.addresslat = addresslat;
        this.addresslng = addresslng;
        this.age = age;
        this.disease = disease;
        this.gender = gender;
        this.h_name = h_name;
        this.name = name;
        this.pincode = pincode;
        this.state = state;
        this.water_borne = water_borne;
    }


    public ReportedCases() {

    }

}
