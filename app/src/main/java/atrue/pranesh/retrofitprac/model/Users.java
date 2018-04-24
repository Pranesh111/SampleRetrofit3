package atrue.pranesh.retrofitprac.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Users implements Serializable {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("username")
    public String username;
    @SerializedName("email")
    public String email;
    @SerializedName("address")
    public Address address;
    @SerializedName("phone")
    public String phone;
    @SerializedName("website")
    public String website;
    @SerializedName("company")
    public Company company;

    public static class Geo {
        @SerializedName("lat")
        public String lat;
        @SerializedName("lng")
        public String lng;
    }

    public static class Address {
        @SerializedName("street")
        public String street;
        @SerializedName("suite")
        public String suite;
        @SerializedName("city")
        public String city;
        @SerializedName("zipcode")
        public String zipcode;
        @SerializedName("geo")
        public Geo geo;
    }

    public static class Company {
        @SerializedName("name")
        public String name;
        @SerializedName("catchPhrase")
        public String catchPhrase;
        @SerializedName("bs")
        public String bs;
    }
}
