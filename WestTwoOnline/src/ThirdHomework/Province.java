package ThirdHomework;

public class Province {
    private  String province_name;
    private int confirmed;
    private int recovered;
    private int deaths;
    private double latitude;
    private double longitude;
    private String updated_time;
    private String country_abbreviation;
    public Province(){}

    public Province(String province_name, int confirmed, int recovered, int deaths, double latitude, double longitude, String updated_time, String country_abbreviation) {
        this.province_name = province_name;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
        this.latitude = latitude;
        this.longitude = longitude;
        this.updated_time = updated_time;
        this.country_abbreviation = country_abbreviation;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getCountry_abbreviation() {
        return country_abbreviation;
    }

    public void setCountry_abbreviation(String country_abbreviation) {
        this.country_abbreviation = country_abbreviation;
    }

    @Override
    public String toString() {
        return "Province{" +
                "province_name='" + province_name + '\'' +
                ", confirmed=" + confirmed +
                ", recovered=" + recovered +
                ", deaths=" + deaths +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", updated_time='" + updated_time + '\'' +
                ", country_abbreviation='" + country_abbreviation + '\'' +
                '}';
    }
}
