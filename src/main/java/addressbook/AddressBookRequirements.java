package addressbook;

import java.util.*;

public class AddressBookRequirements {

    String addressBookFileName;
    List<Person> personData = new ArrayList<>();

    enum CompareType {FIRST_NAME}

    JsonFileOperations jsonFileOperations = new JsonFileOperations();

    public AddressBookRequirements(String addressBookFileName) throws AddressBookException {
        this.addressBookFileName = addressBookFileName;
        if(jsonFileOperations.getFileStatus(addressBookFileName))
            personData = jsonFileOperations.loadAddressBookData(addressBookFileName);
    }

    public void addPersonData(String firstName, String lastName, String address, String city, String state,
                                                                                              int zip, String mobile) {
        personData.add(new Person(firstName, lastName, address, city, state, zip, mobile));
        jsonFileOperations.writeInJsonFile(personData, addressBookFileName);
    }

    public List<Person> getSortedData(CompareType compareType) throws AddressBookException {
        if (personData == null || personData.size() == 0) {
            throw new AddressBookException("No Data", AddressBookException.ExceptionType.NO_DATA);
        }
        Comparator<Person> addressBookComparator = new AddressBookDataComparator().getComparator(compareType);
        personData.stream()
                  .sorted(addressBookComparator);
        return personData;
    }

    public int getSize() { return personData.size(); }

}
