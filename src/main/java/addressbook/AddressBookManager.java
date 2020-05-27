package addressbook;

public class AddressBookManager extends AddressBook {

    String addressBookName;

    public void newFile(String addressBookName) throws AddressBookException {
        this.addressBookName = addressBookName;
        if(FileFactory.getFileOperations().getFileStatus(addressBookName))
            throw new AddressBookException("File Exists", AddressBookException.ExceptionType.FILE_EXISTS);
        FileFactory.getFileOperations().writeInFile(personData, addressBookName);
    }

    public void open(String addressBookName) throws AddressBookException {
        this.addressBookName = addressBookName;
        if(!FileFactory.getFileOperations().getFileStatus(addressBookName))
            throw new AddressBookException("File Not Present", AddressBookException.ExceptionType.FILE_DOESNT_EXISTS);
        personData = FileFactory.getFileOperations().loadDataFromFile(addressBookName);
    }

    public void save() throws AddressBookException {
        FileFactory.getFileOperations().writeInFile(personData, addressBookName);
    }

    public void saveAs(String addressBookName) throws AddressBookException {
        if(FileFactory.getFileOperations().getFileStatus(addressBookName))
            throw new AddressBookException("File Present", AddressBookException.ExceptionType.FILE_EXISTS);
        FileFactory.getFileOperations().writeInFile(personData, addressBookName);
    }

}
