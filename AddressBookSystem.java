import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

class AddressBook {

    // arrayList of class object
    // composition - one class contains object of another class
    private ArrayList<AddContacts> contacts = new ArrayList<>();

    public void addContact(AddContacts contact) {
        // use case 7
        if (!contacts.contains(contact)) {
            contacts.add(contact);
            System.out.println("Contact added successfully.");
        } else {
            System.out.println("Duplicate entry! Contact with the same name already exists.");
        }
    }

    // function that will return AddContact object that matches which given
    // firstname and lastname
    public AddContacts getContactByName(String firstName, String lastName) {
        for (AddContacts contact : contacts) {
            if (contact.getFirstName() == firstName && contact.getLastName() == lastName) {
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

    // function to delete contact
    public void deleteContact(AddContacts contactTodelete) {
        contacts.remove(contactTodelete);
    }

    // function to get all contacts

    public ArrayList<AddContacts> getAllContacts() {
        return contacts;
    }
}

class StoreAddressBook {
    private static Map<String, AddressBook> addressBooks = new HashMap<>();

    public static void addAddressBook(String name, AddressBook addressBook) {
        addressBooks.put(name, addressBook);
    }

}

public class AddressBookSystem {

    public static void main(String[] args) {
        // printing welcome msg
        System.out.println("Welcome to address book system program!!!");

        Scanner sc = new Scanner(System.in);

        // object of AddressBook class
        AddressBook addressBook = new AddressBook();

        // Use case5: creating loop to add multiple contacts
        System.out.println("Add multiple contacts to Address Book");
        char addAnother;
        do {
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

            AddContacts new_contact = new AddContacts(firstName, lastName, address, city, state, zip, phoneNumber,
                    email);
            addressBook.addContact(new_contact);

            System.out.print("Do you want to add another person? (y/n): ");
            addAnother = sc.next().charAt(0);

        } while (addAnother == 'y');

        // Display all contacts in the Address Book
        System.out.println("\nAll Contacts in Address Book:");
        ArrayList<AddContacts> allContacts = addressBook.getAllContacts();
        for (AddContacts contact : allContacts) {
            System.out.println("\n" + contact.toString());
        }

        // Usecase 3: Editing an existing Contact
        System.out.println("Enter the name of the contact to edit:");
        System.out.print("First Name: ");
        String editFirstName = sc.next();
        System.out.print("Last Name: ");
        String editLastName = sc.next();

        // using firstname and lastname fetch whole info of that contact
        AddContacts old_contact = addressBook.getContactByName(editFirstName, editLastName);

        // if contact exist edit contact details
        if (old_contact != null) {
            System.out.println("Enter new details for the contact:");

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

            AddContacts newContact = new AddContacts(firstName, lastName, address, city, state, zip, phoneNumber,
                    email);

            // call editContact will update old_contact with newContact
            addressBook.editContact(old_contact, newContact);

        } else {
            System.out.println("Contact not found!");
        }

        // Usecase 4: Deleting a person
        System.out.println("Enter the name of the contact to delete:");
        System.out.print("First Name: ");
        String deleteFirstName = sc.next();
        System.out.print("Last Name: ");
        String deleteLastName = sc.next();

        // using firstname and lastname fetch whole info of that contact
        AddContacts contactTodelete = addressBook.getContactByName(deleteFirstName, deleteLastName);

        if (contactTodelete != null) {
            addressBook.deleteContact(contactTodelete);

        } else {
            System.out.println("contact not found!");
        }

        // Usecase 6: Refactor to add multiple Address Books to the System
        System.out.println("Add multiple Address Books to the System");
        char addAnotherBook;
        do {
            System.out.print("Enter a unique name for the new Address Book: ");
            String addressBookName = sc.next();

            // Create a new Address Book and add it to the system
            AddressBook newAddressBook = new AddressBook();
            StoreAddressBook.addAddressBook(addressBookName, newAddressBook);
            // printing the name of address book
            System.out.println("Name of addressBook: " + addressBookName);

            System.out.print("Do you want to add another Address Book? (y/n): ");
            addAnotherBook = sc.next().charAt(0);
        } while (addAnotherBook == 'y');

        sc.close();

    }
}