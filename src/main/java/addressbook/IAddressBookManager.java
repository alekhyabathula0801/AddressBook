package addressbook;

import java.util.List;

public interface IAddressBookManager {

    public void newFile(String addressBookName, AddressBookManager.FileType fileType) throws AddressBookException;

    public void open(String addressBookName, AddressBookManager.FileType fileType) throws AddressBookException;

    public void save() throws AddressBookException;

    public void saveAs(String addressBookName, AddressBookManager.FileType fileType) throws AddressBookException;

    public int getSize();

    public void edit(String mobileNumberToEdit, String address, String city, String state, int zip,
                     String mobileNumber) throws AddressBookException;

    public void delete(String mobileNumber) throws AddressBookException;

    public List<Person> getSortedData(AddressBook.CompareType compareType) throws AddressBookException;

    public void add(String firstName, String lastName, String address, String city, String state, int zip,
                    String mobileNumber) throws AddressBookException;

}