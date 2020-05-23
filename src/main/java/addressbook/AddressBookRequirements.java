package addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookRequirements {

    String addressBookFileName;
    List<Person> personData = new ArrayList<>();

    enum CompareType {FIRST_NAME,LAST_NAME,ZIP,CITY,STATE}

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
        List<Person> sortedAddressBookData = personData.stream()
                                                       .sorted(addressBookComparator)
                                                       .collect(Collectors.toList());
        return sortedAddressBookData;
    }

    public int getIndexOfPerson(String firstName) {
        int index = 0;
        while (personData.size()>index) {
            if (firstName.equals(personData.get(index).getFirstName()))
                return index;
            index++;
        }
        return -1;
    }

    public void deletePersonData(String firstName) throws AddressBookException {
        int index = getIndexOfPerson(firstName);
        if(index == -1)
            throw new AddressBookException("Data not found", AddressBookException.ExceptionType.INVALID_DATA);
        personData.remove(index);
        jsonFileOperations.writeInJsonFile(personData,addressBookFileName);
    }

    public void editPersonData(String firstName, String address, String city, String state, int zip, String mobile) throws AddressBookException {
        int index = getIndexOfPerson(firstName);
        if(index == -1)
            throw new AddressBookException("Data not found", AddressBookException.ExceptionType.INVALID_DATA);
        personData.get(index).setAddress(address);
        personData.get(index).setCity(city);
        personData.get(index).setState(state);
        personData.get(index).setZip(zip);
        personData.get(index).setMobile(mobile);
        jsonFileOperations.writeInJsonFile(personData,addressBookFileName);
    }

    public int getSize() { return personData.size(); }

}
