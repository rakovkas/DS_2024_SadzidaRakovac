package homework3;

public class Entry implements Comparable<Entry>{
    // implement the relevant data attributes
    private String name;
    private String streetAddress;
    private String city;
    private String postcode;
    private String country;
    private String phone;

    public Entry(String name, String streetAddress, String city, String postcode, String country, String phone) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phone;
    }

    @Override
    public int compareTo(Entry other) {

        return this.name.compareTo(other.name);
    }
    @Override
    public String toString() {
        return "Name: " + this.name + "\n" +
                "Street Address: " + this.streetAddress + "\n" +
                "City: " + this.city + "\n" +
                "Postal code: " + this.postcode + "\n" +
                "Country: " + this.country + "\n" +
                "Phone Number: " + this.phone + "\n";
    }
}
