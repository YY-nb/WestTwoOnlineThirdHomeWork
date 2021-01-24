package ThirdHomework;

public class Country {
    private  String country_abbreviation;
    private int iso;
    private  String country_name;
    private int population;
    private  int confirmed;
    private int recovered;
    private int deaths;
    private double sq_km_area;
    private double life_expectancy;
    private double elevation_in_meters;
    private String continent;
    private String location;
    private  String capital_city;

    public Country() {
    }

    public Country(String country_abbreviation, int iso, String country_name, int population, int confirmed, int recovered, int deaths, double sq_km_area, double life_expectancy, double elevation_in_meters, String continent, String location, String capital_city) {
        this.country_abbreviation = country_abbreviation;
        this.iso = iso;
        this.country_name = country_name;
        this.population = population;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
        this.sq_km_area = sq_km_area;
        this.life_expectancy = life_expectancy;
        this.elevation_in_meters = elevation_in_meters;
        this.continent = continent;
        this.location = location;
        this.capital_city = capital_city;
    }

    public String getCountry_abbreviation() {
        return country_abbreviation;
    }

    public void setCountry_abbreviation(String country_abbreviation) {
        this.country_abbreviation = country_abbreviation;
    }

    public int getIso() {
        return iso;
    }

    public void setIso(int iso) {
        this.iso = iso;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
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

    public double getSq_km_area() {
        return sq_km_area;
    }

    public void setSq_km_area(double sq_km_area) {
        this.sq_km_area = sq_km_area;
    }

    public double getLife_expectancy() {
        return life_expectancy;
    }

    public void setLife_expectancy(double life_expectancy) {
        this.life_expectancy = life_expectancy;
    }

    public double getElevation_in_meters() {
        return elevation_in_meters;
    }

    public void setElevation_in_meters(double elevation_in_meters) {
        this.elevation_in_meters = elevation_in_meters;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCapital_city() {
        return capital_city;
    }

    public void setCapital_city(String capital_city) {
        this.capital_city = capital_city;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country_abbreviation='" + country_abbreviation + '\'' +
                ", iso=" + iso +
                ", country_name='" + country_name + '\'' +
                ", population=" + population +
                ", confirmed=" + confirmed +
                ", recovered=" + recovered +
                ", deaths=" + deaths +
                ", sq_km_area=" + sq_km_area +
                ", life_expectancy=" + life_expectancy +
                ", elevation_in_meters=" + elevation_in_meters +
                ", continent='" + continent + '\'' +
                ", location='" + location + '\'' +
                ", capital_city='" + capital_city + '\'' +
                '}';
    }
}
