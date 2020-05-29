package addressbook;

import java.util.List;

public interface IFileOperations {

    public boolean getFileStatus(String fileName, AddressBookManager.FileType fileType) throws AddressBookException;
    public List<Person> loadDataFromFile(String fileName) throws AddressBookException;
    public void writeInFile(List<Person> data, String fileName) throws AddressBookException;

}
