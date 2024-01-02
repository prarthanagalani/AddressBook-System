import java.util.HashMap;
import java.util.Map;

public class StoreAddressBook {
    private static Map<String, AddressBook> addressBooks = new HashMap<>();

    public static void addAddressBook(String name, AddressBook addressBook) {
        addressBooks.put(name, addressBook);
    }

}