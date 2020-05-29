package addressbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFileOperations extends FileOperations implements IFileOperations {

    public List<Person> loadDataFromFile(String fileName) throws AddressBookException {
        List<Person> data = new ArrayList<>();
        try {
            String filePath = getFilePath(fileName, AddressBookManager.FileType.JSON);
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            data.addAll(Arrays.asList(gson.fromJson(reader, Person[].class)));
            reader.close();
        } catch (IOException | RuntimeException e) {
            throw  new AddressBookException("Invalid File", AddressBookException.ExceptionType.FILE_PROBLEM);
        }
        return data;
    }

    public void writeInFile(List<Person> data, String fileName) throws AddressBookException {
        try {
            String filePath = getFilePath(fileName, AddressBookManager.FileType.JSON);
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
