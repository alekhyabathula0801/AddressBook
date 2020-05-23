package addressbook;

import java.util.Comparator;
import java.util.EnumMap;

public class AddressBookDataComparator {

    static EnumMap<AddressBookRequirements.CompareType, Comparator<Person>> addressBookComparator =
                                                            new EnumMap<>(AddressBookRequirements.CompareType.class);

    static {
        addressBookComparator.put(AddressBookRequirements.CompareType.FIRST_NAME,
                                                          Comparator.comparing(iplData -> iplData.getFirstName()));
        addressBookComparator.put(AddressBookRequirements.CompareType.LAST_NAME,
                                                          Comparator.comparing(iplData ->iplData.getLastName()));
        addressBookComparator.put(AddressBookRequirements.CompareType.ZIP,
                                                          Comparator.comparing(iplData ->iplData.getZip()));
        addressBookComparator.put(AddressBookRequirements.CompareType.STATE,
                                                          Comparator.comparing(iplData ->iplData.getState()));
        addressBookComparator.put(AddressBookRequirements.CompareType.CITY,
                                                          Comparator.comparing(iplData ->iplData.getCity()));
    }

    public Comparator<Person> getComparator(AddressBookRequirements.CompareType compareType) {
        return addressBookComparator.get(compareType);
    }

}
