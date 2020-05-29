package addressbook;

import java.io.File;

public abstract class FileOperations implements IFileOperations {

    public String getFilePath(String fileName, AddressBookManager.FileType fileType) throws AddressBookException {
        try {
            if (fileName.equals(""))
                throw new AddressBookException("Entered Empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            if (fileType.equals(AddressBookManager.FileType.CSV))
                return "./src/test/resources/files/"+fileName+".csv";
            return  "./src/test/resources/files/"+fileName+".json";
        } catch (NullPointerException  e) {
            throw new AddressBookException("Entered Null", AddressBookException.ExceptionType.ENTERED_NULL);
        }
    }

    public boolean getFileStatus(String fileName, AddressBookManager.FileType fileType) throws AddressBookException {
        String filePath = getFilePath(fileName,fileType);
        if(new File(filePath).exists())
            return true;
        return false;
    }

}
