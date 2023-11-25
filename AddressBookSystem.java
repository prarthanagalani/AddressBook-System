import java.util.Scanner;
import java.util.ArrayList;

class AddContacts {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

class AddressBook {

    // arrayList of class object
    // composition - one class contains object of another class
    private ArrayList<AddContacts> contacts = new ArrayList<>();

    public void addContact(AddContacts contact) {
        contacts.add(contact);
    }
    
    // function that will return AddContact object that matches which given firstname and lastname
    public AddContacts getContactByName(String firstName, String lastName)
    {
        for(AddContacts contact : contacts)
        {
            if(contact.getFirstName() == firstName && contact.getLastName() == lastName)
            {
                return contact;
            }
        }

        return null;
    }
    
    // function that will find index of oldcontact and update it will new contact
    public void editContact(AddContacts oldContact, AddContacts newContact) {
        int index = contacts.indexOf(oldContact);
        if (index != -1) {
            contacts.set(index, newContact);
        }
    }
}

public class AddressBookSystem {

    public static void main(String[] args) {
        // printing welcoe msg
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
        // add new_contact object in arrayList
        addressBook.addContact(new_contact);

        // Usecase 3: Editing an existing Contact
        System.out.println("Enter the name of the contact to edit:");
        System.out.print("First Name: ");
        String editFirstName = sc.next();
        System.out.print("Last Name: ");
        String editLastName = sc.next();
        
        // using firstname and lastname fetch whole info of that contact
        AddContacts old_contact = addressBook.getContactByName(editFirstName, editLastName);
        
        // if contact exist edit contact details
        if(old_contact != null)
        {
            System.out.println("Enter new details for the contact:");

            System.out.print("First Name: ");
             firstName = sc.next();
            System.out.print("Last Name: ");
             lastName = sc.next();
            System.out.print("Address: ");
             address = sc.next();
            System.out.print("City: ");
             city = sc.next();
            System.out.print("State: ");
             state = sc.next();
            System.out.print("Zip: ");
              zip = sc.next();
            System.out.print("Phone Number: ");
             phoneNumber = sc.next();
            System.out.print("Email: ");
             email = sc.next();

            AddContacts newContact = new AddContacts(firstName, lastName, address, city, state, zip, phoneNumber, email);
            
            // call editContact will update old_contact with newContact
            addressBook.editContact(old_contact, newContact);

        }
        else
        {
            System.out.println("Contact not found!");
        }

        sc.close();

    }
}