package addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookRequirements {

    String addressBookFilePath;
    List<Person> personData;

    enum CompareType {FIRST_NAME}

    JsonFileOperations jsonFileOperations = new JsonFileOperations();

    public AddressBookRequirements(String addressBookFilePath) throws AddressBookException {
        this.addressBookFilePath = addressBookFilePath;
        personData = jsonFileOperations.loadAddressBookData(addressBookFilePath);
    }

    public void addPersonData(String firstName, String lastName, String address, String city, String state, int zip, String mobile) {
        personData.add(new Person(firstName,lastName,address,city,state,zip,mobile));
        jsonFileOperations.writeInJsonFile(personData,addressBookFilePath);
    }

    public List<Person> getSortedData(CompareType compareType) throws AddressBookException {
        if (personData == null || personData.size() == 0) {
            throw new AddressBookException("No Data", AddressBookException.ExceptionType.NO_DATA);
        }
        Comparator<Person> addressBookComparator = new AddressBookDataComparator().getComparator(compareType);
        List<Person> sortedAddressBookData = personData.stream()
                                                       .sorted(addressBookComparator)
                                                       .collect(Collectors.toList());
        return sortedAddressBookData;
    }

    public int getSize(){
       return personData.size();
    }

}
