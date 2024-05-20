package homework1;

public class Entry implements Comparable<Entry>{
    // implement the relevant data attributes
    String name;
    String streetAddress;
    String city;
    String postcode;
    String country;
    String phoneNumber;

    public Entry(String name, String streetAddress, String city, String postcode, String country, String phoneNumber) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Entry other) {
        // implement the actual compareTo logic
        return this.name.compareTo(other.name);
    }
}
