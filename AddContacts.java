public class AddContacts {

    // contact info
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    // Uscase1: constructor to initialize contacts
    public AddContacts(String firstName, String lastName, String address, String city, String state, String zip,
            String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String toString() {
        return "Name: " + firstName + " " + lastName +
                "\nAddress: " + address +
                "\nCity: " + city +
                "\nState: " + state +
                "\nZip: " + zip +
                "\nPhone Number: " + phoneNumber +
                "\nEmail: " + email;
    }

}