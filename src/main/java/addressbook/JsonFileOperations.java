package addressbook;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFileOperations {

    public boolean getFileStatus(String addressBookFileName) {
        String addressBookFilePath = "./src/test/resources/jsonfiles/"+addressBookFileName+".json";
        if(new File(addressBookFilePath).exists())
            return true;
        return false;
    }

    public List<Person> loadAddressBookData(String addressBookFileName) throws AddressBookException {
        List<Person> personData = new ArrayList<>();
        try {
            String addressBookFilePath = "./src/test/resources/jsonfiles/"+addressBookFileName+".json";
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(addressBookFilePath));
            personData.addAll(Arrays.asList(gson.fromJson(reader, Person[].class)));
            reader.close();
        } catch (Exception e) {
            throw  new AddressBookException("Invalid File", AddressBookException.ExceptionType.ADDRESS_BOOK_FILE_PROBLEM);
        }
        return personData;
    }

    public void writeInJsonFile (List<Person> personData, String addressBookFileName) {
        try {
            String addressBookFilePath = "./src/test/resources/jsonfiles/"+addressBookFileName+".json";
            Gson gson = new Gson();
            String json = gson.toJson(personData);
            FileWriter writer = new FileWriter(addressBookFilePath);
            writer.write(json);
            writer.close();
        } catch (IOException e) { }
    }

}
