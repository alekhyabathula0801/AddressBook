package addressbook;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AddressBookRequirements {

    String addressBookFilePath;
    List<Person> personData = new ArrayList<>();

    public AddressBookRequirements(String addressBookFilePath) {
        this.addressBookFilePath = addressBookFilePath;
        this.loadAddressBookData();
    }

    public int loadAddressBookData() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(addressBookFilePath));
            personData.addAll(Arrays.asList(gson.fromJson(reader, Person[].class)));
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personData.size();
    }

    public void writeInJsonFile (List<Person> personData) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(personData);
            FileWriter writer = new FileWriter(addressBookFilePath);
            writer.write(json);
            writer.close();
        } catch (IOException e) { }
    }

    public void addPersonData(String firstName, String lastName, String address, String city, String state, int zip, String mobile) {
        personData.add(new Person(firstName,lastName,address,city,state,zip,mobile));
        writeInJsonFile(personData);
    }

    public int getSize(){
       return personData.size();
    }

}
