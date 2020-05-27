package addressbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFileOperations<E> implements IFileOperations<E> {

    private String getFilePath(String fileName) throws AddressBookException {
        try{
            if (fileName.equals(""))
                throw new AddressBookException("Entered Empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
             return  "./src/test/resources/jsonfiles/"+fileName+".json";
        } catch (NullPointerException  e) {
            throw new AddressBookException("Entered Null", AddressBookException.ExceptionType.ENTERED_NULL);
        }
    }

    public boolean getFileStatus(String fileName) throws AddressBookException {
        String filePath = getFilePath(fileName);
        if(new File(filePath).exists())
            return true;
        return false;
    }

    public List<Person> loadDataFromFile(String fileName) throws AddressBookException {
        List<Person> data = new ArrayList<>();
        try {
            String filePath = getFilePath(fileName);
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            data.addAll(Arrays.asList(gson.fromJson(reader, Person[].class)));
            reader.close();
        } catch (IOException | RuntimeException e) {
            throw  new AddressBookException("Invalid File", AddressBookException.ExceptionType.FILE_PROBLEM);
        }
        return data;
    }

    public <E> void writeInFile(List<E> data, String fileName) throws AddressBookException {
        try {
            String filePath = getFilePath(fileName);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(data);
            FileWriter writer = new FileWriter(filePath);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw  new AddressBookException("Invalid File", AddressBookException.ExceptionType.FILE_PROBLEM);
        }
    }

}
