package addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookRequirementsTest {

    @Test
    public void givenAddressBook_whenDataAdded_shouldIncreaseSizeBy1() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements("AddressBook");
            int file = addressBookRequirements.getSize();
            addressBookRequirements.addPersonData("Snehita", "Naidu", "Anapurna Colony", "Dharmeshwaram", "Karnataka", 5065432, "+91-8356456613");
            int updatedFile = addressBookRequirements.getSize();
            Assert.assertEquals(file+1,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmptyAddressBook_whenDataAdded_shouldIncreaseSizeBy1() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements("AddressBook1");
            addressBookRequirements.addPersonData("Arjun", "Chowdary", "Laxmi Colony", "Narsampet", "Sikkim", 764432, "+91-9356576189");
            int updatedFile = addressBookRequirements.getSize();
            Assert.assertEquals(1,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToFirstName_shouldReturnSortedResults() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements("AddressBook");
            List<Person> sortedData = addressBookRequirements.getSortedData(AddressBookRequirements.CompareType.FIRST_NAME);
            Assert.assertEquals("Anu",sortedData.get(0).getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmptyAddressBookToSort_shouldThrowException() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements("AddressBookEmpty");
            addressBookRequirements.getSortedData(AddressBookRequirements.CompareType.FIRST_NAME);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.NO_DATA,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToLastName_shouldReturnSortedResults() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements("AddressBook");
            List<Person> sortedData = addressBookRequirements.getSortedData(AddressBookRequirements.CompareType.LAST_NAME);
            Assert.assertEquals("Naidu",sortedData.get(0).getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToZip_shouldReturnSortedResults() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements("AddressBook");
            List<Person> sortedData = addressBookRequirements.getSortedData(AddressBookRequirements.CompareType.ZIP);
            Assert.assertEquals(5065432,sortedData.get(0).getZip());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToState_shouldReturnSortedResults() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements("AddressBook");
            List<Person> sortedData = addressBookRequirements.getSortedData(AddressBookRequirements.CompareType.STATE);
            Assert.assertEquals("Gujarat",sortedData.get(0).getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToCity_shouldReturnSortedResults() {
        try {
            AddressBookRequirements addressBookRequirements = new AddressBookRequirements("AddressBook");
            List<Person> sortedData = addressBookRequirements.getSortedData(AddressBookRequirements.CompareType.CITY);
            Assert.assertEquals("Anantapur",sortedData.get(0).getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
