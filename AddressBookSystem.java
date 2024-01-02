import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

        boolean istrue = false;

        System.out.println("do you want to edit contact? (y or n)");
        char ch = sc.next().charAt(0);

        if (ch == 'y')
            istrue = true;

        if (istrue) {

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
        }

        istrue = false;

        System.out.println("do you want to delete contact? (y or n)");
        ch = sc.next().charAt(0);

        if (ch == 'y')
            istrue = true;

        if (istrue) {
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
        }

        istrue = false;

        System.out.println("do you want to add multiple addressbook? (y or n)");
        ch = sc.next().charAt(0);

        if (ch == 'y')
            istrue = true;

        if (istrue) {
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
        }

        istrue = false;

        System.out.println("do you want to search user by city? (y or n)");
        ch = sc.next().charAt(0);

        if (ch == 'y')
            istrue = true;
        if (istrue) {
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
        }

        istrue = false;

        System.out.println("do you want to search user by state? (y or n)");
        ch = sc.next().charAt(0);

        if (ch == 'y')
            istrue = true;
        if (istrue) {
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
        }

        Usecase 9:
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

        int personCountByCity = addressBook.getPersonCountByCity(countByCity);
        System.out.println("Number of contact persons in City '" + countByCity + "': " + personCountByCity);

        // Get count by state
        System.out.print("\nEnter the state to get the count of contact persons: ");
        String countByState = sc.next();

        int personCountByState = addressBook.getPersonCountByState(countByState);
        System.out.println("Number of contact persons in State '" + countByState + "': " + personCountByState);

        // Use case11:
        // Sort entries by name
        List<AddContacts> sortedContacts = addressBook.sortByName();

        if (!sortedContacts.isEmpty()) {
            System.out.println("\nSorted Entries by Name:");
            sortedContacts.forEach(person -> System.out.println(person.toString()));
        } else {
            System.out.println("No entries to sort.");
        }

        // Usecase 13:

        String filepath = "address_book.txt";

        addressBook.writeToFile(filepath);
        // Read Address Book from file
        AddressBook newAddressBook = new AddressBook();
        newAddressBook.readFromFile(filepath);

        // Display contacts from the newly read Address Book
        System.out.println("\nContacts from the newly read Address Book:");
        ArrayList<AddContacts> allContactss = newAddressBook.getAllContacts();
        for (AddContacts contact : allContactss) {
            System.out.println("\n" + contact.toString());
        }

        sc.close();

    }

}