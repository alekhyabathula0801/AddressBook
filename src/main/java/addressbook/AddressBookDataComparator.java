package addressbook;

import java.util.Comparator;
import java.util.EnumMap;

public class AddressBookDataComparator {

    static EnumMap<AddressBookRequirements.CompareType, Comparator<Person>> addressBookComparator =
                                                            new EnumMap<>(AddressBookRequirements.CompareType.class);

    static {
        addressBookComparator.put(AddressBookRequirements.CompareType.FIRST_NAME,Comparator.comparing(iplData -> iplData.getFirstName()));

    }

    public Comparator<Person> getComparator(AddressBookRequirements.CompareType compareType) {
        return addressBookComparator.get(compareType);
    }
}
