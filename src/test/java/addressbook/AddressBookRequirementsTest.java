package addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookRequirementsTest {

    String ADDRESS_BOOK_FILE_PATH = "./src/test/resources/AddressBook.json";
    String EMPTY_ADDRESS_BOOK_FILE_PATH = "./src/test/resources/AddressBookWithEmpty.json";
    String WRONG_TYPE_ADDRESS_BOOK_FILE_PATH = "./src/test/resources/AddressBook.txt";

    @Test
    public void givenAddressBookData_whenDataAdded_shouldIncreaseSizeBy1() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements(ADDRESS_BOOK_FILE_PATH);
            int file = addressBookRequirements.getSize();
            addressBookRequirements.addPersonData("Snehita", "Naidu", "Anapurna Colony", "Dharmeshwaram", "Karnataka", 5065432, "+91-8356456613");
            int updatedFile = addressBookRequirements.getSize();
            Assert.assertEquals(file+1,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmptyFile_shouldThrowException() {
        try {
            new AddressBookRequirements(EMPTY_ADDRESS_BOOK_FILE_PATH);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ADDRESS_BOOK_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenWrongTypeFile_shouldThrowException() {
        try {
            new AddressBookRequirements(WRONG_TYPE_ADDRESS_BOOK_FILE_PATH);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ADDRESS_BOOK_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenAddressBookData_whenSorted_shouldReturnSortedResults() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements(ADDRESS_BOOK_FILE_PATH);
            List<Person> sortedData = addressBookRequirements.getSortedData(AddressBookRequirements.CompareType.FIRST_NAME);
            Assert.assertEquals("Anu",sortedData.get(0).getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
