package addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {

    String addressBookFileName;
    List<Person> personData = new ArrayList<>();

    enum CompareType {FIRST_NAME,LAST_NAME,ZIP,CITY,STATE}

    public void newFile(String addressBookFileName) throws AddressBookException {
        this.addressBookFileName = addressBookFileName;
        if(FileFactory.getFileOperations().getFileStatus(addressBookFileName))
            throw new AddressBookException("File Exists", AddressBookException.ExceptionType.FILE_EXISTS);
    }

    public void open(String addressBookFileName) throws AddressBookException {
        this.addressBookFileName = addressBookFileName;
        if(!FileFactory.getFileOperations().getFileStatus(addressBookFileName))
            throw new AddressBookException("File Not Present", AddressBookException.ExceptionType.FILE_DOESNT_EXISTS);
        personData = FileFactory.getFileOperations().loadDataFromFile(addressBookFileName);
    }

    public void save() {
        FileFactory.getFileOperations().writeInFile(personData, addressBookFileName);
    }

    public void saveAs(String addressBookFileName) throws AddressBookException {
        if(FileFactory.getFileOperations().getFileStatus(addressBookFileName))
            throw new AddressBookException("File Present", AddressBookException.ExceptionType.FILE_EXISTS);
        FileFactory.getFileOperations().writeInFile(personData, addressBookFileName);
    }

    public void add(String firstName, String lastName, String address, String city, String state, int zip,
                    String mobileNumber) throws AddressBookException {
        try {
            if (firstName == "" | lastName == "" | address == "" | city == "" | state == "" | mobileNumber == "")
                throw new AddressBookException("Entered Empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            int index = getIndex(mobileNumber);
            if (index != -1)
                throw new AddressBookException("Data Exists", AddressBookException.ExceptionType.DATA_EXISTS);
            personData.add(new Person(firstName, lastName, address, city, state, zip, mobileNumber));
        } catch (NullPointerException e) {
            throw new AddressBookException("Entered Null", AddressBookException.ExceptionType.ENTERED_NULL);
        }
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

    private int getIndex(String mobileNumber) {
        int index = 0;
        while (personData.size()>index) {
            if (mobileNumber.equals(personData.get(index).getMobileNumber()))
                return index;
            index++;
        }
        return -1;
    }

    public void delete(String mobileNumber) throws AddressBookException {
        try {
            if (mobileNumber == "")
                throw new AddressBookException("Entered Empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            int index = getIndex(mobileNumber);
            if(index == -1)
                throw new AddressBookException("Data not found", AddressBookException.ExceptionType.INVALID_DATA);
            personData.remove(index);
        } catch (NullPointerException e) {
            throw new AddressBookException("Entered Null", AddressBookException.ExceptionType.ENTERED_NULL);
        }
    }

    public void edit(String mobileNumberToEdit, String address, String city, String state, int zip,
                     String mobileNumber) throws AddressBookException {
        try {
            if (mobileNumberToEdit == "" | address == "" | city == "" | state == "" | mobileNumber == "")
                throw new AddressBookException("Entered Empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            int index = getIndex(mobileNumberToEdit);
            if(index == -1)
                throw new AddressBookException("Data not found", AddressBookException.ExceptionType.INVALID_DATA);
            personData.get(index).setAddress(address);
            personData.get(index).setCity(city);
            personData.get(index).setState(state);
            personData.get(index).setZip(zip);
            personData.get(index).setMobileNumber(mobileNumber);
        } catch (NullPointerException e) {
            throw new AddressBookException("Entered Null", AddressBookException.ExceptionType.ENTERED_NULL);
        }
    }

    public int getSize() {
        return personData.size();
    }

}
