package addressbook;

public class FileFactory {

    public static IFileOperations getFileOperations(AddressBookManager.FileType fileType) {
        if (fileType.equals(AddressBookManager.FileType.JSON))
            return new JsonFileOperations();
        return new CsvFileOperations();
    }

}
