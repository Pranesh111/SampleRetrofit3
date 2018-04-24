package atrue.pranesh.retrofitprac.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Adminitrator on 4/24/2018.
 * Copyright IMDSTAR Technologies
 */

public class Weather {

    @SerializedName("cod")
    public String cod;
    @SerializedName("message")
    public double message;
    @SerializedName("cnt")
    public int cnt;
    @SerializedName("list")
    public java.util.List<List> list;
    @SerializedName("city")
    public City city;

    public static class List {
    }

    public static class Coord {
        @SerializedName("lat")
        public double lat;
        @SerializedName("lon")
        public double lon;
    }

    public static class City {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("coord")
        public Coord coord;
        @SerializedName("country")
        public String country;
        @SerializedName("population")
        public int population;
    }
}
