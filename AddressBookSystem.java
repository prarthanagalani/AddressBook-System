import java.util.Scanner;
import java.util.ArrayList;

class AddContacts {

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

}

class AddressBook {

    //arrayList of class object
    //composition - one class contains object of another class
    private ArrayList<AddContacts> contacts = new ArrayList<>();

    public void addContact(AddContacts contact) {
        contacts.add(contact);
    }
}

public class AddressBookSystem {

    public static void main(String[] args) {
        System.out.println("Welcome to address book system program!!!");

        // Usecase 2: Adding a new Contact
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter details for a new contact:");
        System.out.print("First Name: ");
        String firstName = sc.next();
        System.out.print("Last Name: ");
        String lastName = sc.next();
        System.out.print("Address: ");
        String address = sc.next();
        System.out.print("City: ");
        String city = sc.next();
        System.out.print("State: ");
        String state = sc.next();
        System.out.print("Zip: ");
        String zip = sc.next();
        System.out.print("Phone Number: ");
        String phoneNumber = sc.next();
        System.out.print("Email: ");
        String email = sc.next();
        
        // calling contructor to create new contact
        AddContacts new_contact = new AddContacts(firstName, lastName, address, city, state, zip, phoneNumber, email);
        
        // object of AddressBook class 
        AddressBook addressBook = new AddressBook();
        //add new_contact object in arrayList
        addressBook.addContact(new_contact);

        

        sc.close();

    }
}