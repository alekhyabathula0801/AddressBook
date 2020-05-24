package addressbook;

import java.util.Comparator;
import java.util.EnumMap;

public class AddressBookDataComparator {

    static EnumMap<AddressBook.CompareType, Comparator<Person>> addressBookComparator =
                                                                new EnumMap<>(AddressBook.CompareType.class);

    static {
        addressBookComparator.put(AddressBook.CompareType.FIRST_NAME,
                                                          Comparator.comparing(iplData -> iplData.getFirstName()));
        addressBookComparator.put(AddressBook.CompareType.LAST_NAME,
                                                          Comparator.comparing(iplData ->iplData.getLastName()));
        addressBookComparator.put(AddressBook.CompareType.ZIP,
                                                          Comparator.comparing(iplData ->iplData.getZip()));
        addressBookComparator.put(AddressBook.CompareType.STATE,
                                                          Comparator.comparing(iplData ->iplData.getState()));
        addressBookComparator.put(AddressBook.CompareType.CITY,
                                                          Comparator.comparing(iplData ->iplData.getCity()));
    }

    public Comparator<Person> getComparator(AddressBook.CompareType compareType) {
        return addressBookComparator.get(compareType);
    }

}
