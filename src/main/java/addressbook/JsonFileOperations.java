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

    public boolean getFileStatus(String fileName) {
        String filePath = "./src/test/resources/jsonfiles/"+fileName+".json";
        if(new File(filePath).exists())
            return true;
        return false;
    }

    public List<Person> loadDataFromFile(String fileName) throws AddressBookException {
        List<Person> data = new ArrayList<>();
        try {
            String filePath = "./src/test/resources/jsonfiles/"+fileName+".json";
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            data.addAll(Arrays.asList(gson.fromJson(reader, Person[].class)));
            reader.close();
        } catch (Exception e) {
            throw  new AddressBookException("Invalid File", AddressBookException.ExceptionType.ADDRESS_BOOK_FILE_PROBLEM);
        }
        return data;
    }

    public <E> void writeInFile(List<E> data, String fileName) {
        try {
            String filePath = "./src/test/resources/jsonfiles/"+fileName+".json";
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(data);
            FileWriter writer = new FileWriter(filePath);
            writer.write(json);
            writer.close();
        } catch (IOException e) { }
    }

}
