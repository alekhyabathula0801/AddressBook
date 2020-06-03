package addressbook;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AddressBookTest {

    AddressBook addressBook;

    @Before
    public void setUp() throws Exception {
        addressBook = new AddressBook();
    }

    @Test
    public void givenData_whenAdded_shouldIncreaseSizeBy2() {
        try {
            int file = addressBook.getSize();
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            int updatedFile = addressBook.getSize();
            Assert.assertEquals(file+2,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNullDataToAdd_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.add(null, "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, null);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenEmptyDataToAdd_shouldThrowException() {
        try {
            addressBook.add("", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenData_whenDataDeleted_shouldDecreaseSizeBy1() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            int file = addressBook.getSize();
            addressBook.delete("+91-8715610681");
            int updatedFile = addressBook.getSize();
            Assert.assertEquals(file-1,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNullDataToDelete_shouldThrowException() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBook.delete( null);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenEmptyDataToDelete_shouldThrowException() {
        try {
            addressBook.delete( "");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenData_whenDataToBeDeletedIsNotFound_shouldThrowException() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBook.delete("+91-8912346543");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.INVALID_DATA,e.type);
        }
    }

    @Test
    public void givenData_whenEdited_shouldReturnSameSize() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            int size = addressBook.getSize();
            addressBook.edit("+91-6545610251","Sri Nagar Colony", "Hanvar", "Maharastra", 234564, "+91-6545610240");
            int updatedSize = addressBook.getSize();
            Assert.assertEquals(size,updatedSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNullDataToEdit_shouldThrowException() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBook.edit( null,"SR Estates","rameshwaram","Kerala",987654,"+91-9876543976");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenEmptyDataToEdit_shouldThrowException() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBook.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            addressBook.edit( "","SR Estates","rameshwaram","Kerala",987654,"+91-9876543976");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenData_whenDataToEditIsNotFound_shouldThrowException() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBook.edit("+91-9875610250","Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.INVALID_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToFirstName_shouldReturnSortedResults() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBook.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.FIRST_NAME);
            Assert.assertEquals("Anju",sortedData.get(0).getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmptyAddressBookToSort_shouldThrowException() {
        try {
            addressBook.getSortedData(AddressBook.CompareType.FIRST_NAME);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToLastName_shouldReturnSortedResults() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBook.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.LAST_NAME);
            Assert.assertEquals("Reddy",sortedData.get(0).getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToZip_shouldReturnSortedResults() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBook.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.ZIP);
            Assert.assertEquals(781164,sortedData.get(0).getZip());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToState_shouldReturnSortedResults() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBook.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.STATE);
            Assert.assertEquals("Jammu",sortedData.get(0).getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToCity_shouldReturnSortedResults() {
        try {
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBook.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.CITY);
            Assert.assertEquals("Kazipet",sortedData.get(0).getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
