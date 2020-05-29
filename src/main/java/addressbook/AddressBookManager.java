package addressbook;

import java.util.List;

public class AddressBookManager implements IAddressBookManager{

    enum FileType {CSV,JSON}

    String addressBookName;
    FileType fileType;

    AddressBook addressBook = new AddressBook();

    public void newFile(String addressBookName, FileType fileType) throws AddressBookException {
        this.addressBookName = addressBookName;
        this.fileType = fileType;
        if(FileFactory.getFileOperations(fileType).getFileStatus(addressBookName,fileType))
            throw new AddressBookException("File Exists", AddressBookException.ExceptionType.FILE_EXISTS);
        FileFactory.getFileOperations(fileType).writeInFile(addressBook.personData, addressBookName);
    }

    public void open(String addressBookName, FileType fileType) throws AddressBookException {
        this.addressBookName = addressBookName;
        this.fileType = fileType;
        if(!FileFactory.getFileOperations(fileType).getFileStatus(addressBookName,fileType))
            throw new AddressBookException("File Not Present", AddressBookException.ExceptionType.FILE_DOESNT_EXISTS);
        addressBook.personData =  FileFactory.getFileOperations(fileType).loadDataFromFile(addressBookName);
    }

    public void save() throws AddressBookException {
        FileFactory.getFileOperations(fileType).writeInFile(addressBook.personData, addressBookName);
    }

    public void saveAs(String addressBookName, FileType fileType) throws AddressBookException {
        if(FileFactory.getFileOperations(fileType).getFileStatus(addressBookName,fileType))
            throw new AddressBookException("File Present", AddressBookException.ExceptionType.FILE_EXISTS);
        FileFactory.getFileOperations(fileType).writeInFile(addressBook.personData, addressBookName);
    }

    public void add(String firstName, String lastName, String address, String city, String state, int zip,
                    String mobileNumber) throws AddressBookException {
        addressBook.add(firstName,lastName,address,city,state,zip,mobileNumber);
    }

    public List<Person> getSortedData(AddressBook.CompareType compareType) throws AddressBookException {
        return addressBook.getSortedData(compareType);
    }

    public void delete(String mobileNumber) throws AddressBookException {
        addressBook.delete(mobileNumber);
    }

    public void edit(String mobileNumberToEdit, String address, String city, String state, int zip, String mobileNumber)
                     throws AddressBookException {
        addressBook.edit(mobileNumberToEdit,address,city,state,zip,mobileNumber);
    }

    public int getSize() {
        return addressBook.getSize();
    }

}
