package addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {

    String addressBookFileName;
    List<Person> personData = new ArrayList<>();

    enum CompareType {FIRST_NAME,LAST_NAME,ZIP,CITY,STATE}

    JsonFileOperations jsonFileOperations = new JsonFileOperations();

    public void createNewAddressBook(String addressBookFileName) throws AddressBookException {
        this.addressBookFileName = addressBookFileName;
        if(jsonFileOperations.getFileStatus(addressBookFileName))
            throw new AddressBookException("File Exists", AddressBookException.ExceptionType.FILE_EXISTS);
    }

    public void openAddressBook(String addressBookFileName) throws AddressBookException {
        this.addressBookFileName = addressBookFileName;
        if(!jsonFileOperations.getFileStatus(addressBookFileName))
            throw new AddressBookException("File Not Present", AddressBookException.ExceptionType.FILE_DOESNT_EXISTS);
        personData = jsonFileOperations.loadAddressBookData(addressBookFileName);
    }

    public void save() {
        jsonFileOperations.writeInJsonFile(personData, addressBookFileName);
    }

    public void saveAs(String addressBookFileName) {
        jsonFileOperations.writeInJsonFile(personData, addressBookFileName);
    }

    public void addPersonData(String firstName, String lastName, String address, String city, String state,
                              int zip, String mobileNumber) throws AddressBookException {
        int index = getIndexOfPerson(mobileNumber);
        if(index != -1)
            throw new AddressBookException("Data not found", AddressBookException.ExceptionType.DATA_EXISTS);
        personData.add(new Person(firstName, lastName, address, city, state, zip, mobileNumber));
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

    public int getIndexOfPerson(String mobileNumber) {
        int index = 0;
        while (personData.size()>index) {
            if (mobileNumber.equals(personData.get(index).getMobileNumber()))
                return index;
            index++;
        }
        return -1;
    }

    public void deletePersonData(String mobileNumber) throws AddressBookException {
        int index = getIndexOfPerson(mobileNumber);
        if(index == -1)
            throw new AddressBookException("Data not found", AddressBookException.ExceptionType.INVALID_DATA);
        personData.remove(index);
    }

    public void editPersonData(String mobileNumberToEdit, String address, String city, String state, int zip,
                               String mobileNumber) throws AddressBookException {
        int index = getIndexOfPerson(mobileNumberToEdit);
        if(index == -1)
            throw new AddressBookException("Data not found", AddressBookException.ExceptionType.INVALID_DATA);
        personData.get(index).setAddress(address);
        personData.get(index).setCity(city);
        personData.get(index).setState(state);
        personData.get(index).setZip(zip);
        personData.get(index).setMobileNumber(mobileNumber);
    }

    public int getSize() { return personData.size(); }

}
