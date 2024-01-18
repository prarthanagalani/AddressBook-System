import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
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
    private Map<String, List<AddContacts>> cityToPerson = new HashMap<>();
    private Map<String, List<AddContacts>> stateToPerson = new HashMap<>();

    public void addContact(AddContacts contact) {
        // use case 7
        if (!contacts.contains(contact)) {
            contacts.add(contact);
            updateCityAndStateMaps(contact);
            System.out.println("Contact added successfully.");
        } else {
            System.out.println("Duplicate entry! Contact with the same name already exists.");
        }
    }

    private void updateCityAndStateMaps(AddContacts contact) {
        // Update cityToPerson map
        String city = contact.getCity().toLowerCase();
        cityToPerson.computeIfAbsent(city, k -> new ArrayList<>()).add(contact);

        // Update stateToPerson map
        String state = contact.getState().toLowerCase();
        stateToPerson.computeIfAbsent(state, k -> new ArrayList<>()).add(contact);
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

    public List<AddContacts> searchByCity(String city) {
        return contacts.stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .toList();
    }

    public List<AddContacts> searchByState(String state) {
        return contacts.stream()
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .toList();
    }

    public List<AddContacts> getPersonsByCity(String city) {
        return cityToPerson.getOrDefault(city.toLowerCase(), new ArrayList<>());
    }

    public List<AddContacts> getPersonsByState(String state) {
        return stateToPerson.getOrDefault(state.toLowerCase(), new ArrayList<>());
    }

    public long getPersonCountByCity(String city) {
        return cityToPerson.getOrDefault(city, Collections.emptyList()).stream().count();
    }

    public long getPersonCountByState(String state) {
        return stateToPerson.getOrDefault(state, Collections.emptyList()).stream().count();
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

        // usecase8:
        // Assuming user wants to search by city
        System.out.print("Enter the city to search: ");
        String searchCity = sc.next();

        List<AddContacts> citySearchResults = addressBook.searchByCity(searchCity);

        if (!citySearchResults.isEmpty()) {
            System.out.println("\nSearch Results in City '" + searchCity + "':");
            citySearchResults.forEach(person -> System.out.println(person.toString()));
        } else {
            System.out.println("No results found in the city '" + searchCity + "'.");
        }

        // Assuming user wants to search by state
        System.out.print("\nEnter the state to search: ");
        String searchState = sc.next();

        List<AddContacts> stateSearchResults = addressBook.searchByState(searchState);

        if (!stateSearchResults.isEmpty()) {
            System.out.println("\nSearch Results in State '" + searchState + "':");
            stateSearchResults.forEach(person -> System.out.println(person.toString()));
        } else {
            System.out.println("No results found in the state '" + searchState + "'.");
        }

        // Usecase 9:
        System.out.print("\nEnter the state to view persons: ");
        String viewState = sc.next();

        List<AddContacts> stateViewResults = addressBook.getPersonsByState(viewState);

        if (!stateViewResults.isEmpty()) {
            System.out.println("\nPersons in State '" + viewState + "':");
            stateViewResults.forEach(person -> System.out.println(person.toString()));
        } else {
            System.out.println("No persons found in the state '" + viewState + "'.");
        }

        // Usecase 10:
        // Get count by city
        System.out.print("Enter the city to get the count of contact persons: ");
        String countByCity = sc.next();

        long personCountByCity = addressBook.getPersonCountByCity(countByCity);
        System.out.println("Number of contact persons in City '" + countByCity + "': " + personCountByCity);

        // Get count by state
        System.out.print("\nEnter the state to get the count of contact persons: ");
        String countByState = sc.next();

        long personCountByState = addressBook.getPersonCountByState(countByState);
        System.out.println("Number of contact persons in State '" + countByState + "': " + personCountByState);

        sc.close();

    }
}