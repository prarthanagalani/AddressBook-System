import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddressBook {

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

    public int getPersonCountByCity(String city) {
        return cityToPerson.getOrDefault(city.toLowerCase(), new ArrayList<>()).size();
    }

    public int getPersonCountByState(String state) {
        return stateToPerson.getOrDefault(state.toLowerCase(), new ArrayList<>()).size();
    }

    public List<AddContacts> sortByName() {
        return contacts.stream()
                .sorted(Comparator.comparing(AddContacts::getFirstName).thenComparing(AddContacts::getLastName))
                .collect(Collectors.toList());
    }

    public List<AddContacts> sortByCity() {
        return contacts.stream()
                .sorted(Comparator.comparing(AddContacts::getCity))
                .collect(Collectors.toList());
    }

    public List<AddContacts> sortByState() {
        return contacts.stream()
                .sorted(Comparator.comparing(AddContacts::getState))
                .collect(Collectors.toList());
    }

    public List<AddContacts> sortByZip() {
        return contacts.stream()
                .sorted(Comparator.comparing(AddContacts::getZip))
                .collect(Collectors.toList());
    }

    public void writeToFile(String filepath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            for (AddContacts contact : contacts) {
                writer.println(contact.toFileString());
            }
            System.out.println("Address Book written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void readFromFile(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                AddContacts contact = AddContacts.fromFileString(line);
                addContact(contact);
            }
            System.out.println("Address Book read from file successfully.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    
}