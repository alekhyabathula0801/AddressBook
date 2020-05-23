package addressbook;

import org.junit.Assert;
import org.junit.Test;

public class AddressBookRequirementsTest {

    String ADDRESS_BOOK_FILE_PATH = "./src/test/resources/AddressBook.json";

    @Test
    public void givenData_shouldReturn() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements(ADDRESS_BOOK_FILE_PATH);
            int file = addressBookRequirements.getSize();
            addressBookRequirements.addPersonData("Anu", "Pandey", "Rama Colony", "Anantapur", "Gujarat", 8765432, "91+1234456678");
            int updatedFile = addressBookRequirements.getSize();
            Assert.assertEquals(file+1,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
