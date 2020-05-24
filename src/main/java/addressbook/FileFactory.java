package addressbook;

public class FileFactory {

    public static IFileOperations getFileOperations() {
        return new JsonFileOperations<>();
    }

}
