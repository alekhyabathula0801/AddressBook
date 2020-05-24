package addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookTest {

    @Test
    public void givenAddressBook_whenDataAdded_shouldIncreaseSizeBy2() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            int file = addressBook.getSize();
            addressBook.addPersonData("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.addPersonData("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            int updatedFile = addressBook.getSize();
            Assert.assertEquals(file+2,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToFirstName_shouldReturnSortedResults() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.FIRST_NAME);
            Assert.assertEquals("Anu",sortedData.get(0).getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmptyAddressBookToSort_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook("AddressBookEmpty");
            addressBook.getSortedData(AddressBook.CompareType.FIRST_NAME);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.NO_DATA,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToLastName_shouldReturnSortedResults() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.LAST_NAME);
            Assert.assertEquals("Chowdary",sortedData.get(0).getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToZip_shouldReturnSortedResults() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.ZIP);
            Assert.assertEquals(144462,sortedData.get(0).getZip());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToState_shouldReturnSortedResults() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.STATE);
            Assert.assertEquals("Gujarat",sortedData.get(0).getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToCity_shouldReturnSortedResults() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.CITY);
            Assert.assertEquals("Anantapur",sortedData.get(0).getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenDataDeleted_shouldDecreaseSizeBy1() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            int file = addressBook.getSize();
            addressBook.deletePersonData("+91-8715610681");
            int updatedFile = addressBook.getSize();
            Assert.assertEquals(file-1,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenDataToBeDeletedIsNotFound_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            addressBook.deletePersonData("+91-8912346543");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.INVALID_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenDataEdited_shouldReturnSameSize() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            int file = addressBook.getSize();
            addressBook.editPersonData("+91-6545610251","Teachers Colony", "Magua", "Manyavar", 781564, "+91-7945610243");
            int updatedFile = addressBook.getSize();
            Assert.assertEquals(file,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenDataToEditIsNotFound_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            addressBook.editPersonData("+91-9875610250","Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.INVALID_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenNewFileOpenAddData_shouldIncreaseSizeBy1() {
        try {
            AddressBook addressBook = new AddressBook("NewAddressBook");
            addressBook.createNewAddressBook();
            addressBook.addPersonData("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            addressBook.save();
            int updatedSize = addressBook.getSize();
            Assert.assertEquals(1,updatedSize);
        } catch (AddressBookException e) { }
    }

    @Test
    public void givenAddressBookToCreateNewFile_whenFileExists_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            addressBook.createNewAddressBook();
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_EXISTS,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenOpenAddData_shouldIncreaseSizeBy1() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            addressBook.openAddressBook();
            int size = addressBook.getSize();
            addressBook.addPersonData("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            int updatedSize = addressBook.getSize();
            addressBook.save();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) { }
    }

    @Test
    public void givenAddressBookToOpenFile_whenFileDoesntExists_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook("NoAddressBook");
            addressBook.openAddressBook();
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_DOESNT_EXISTS,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSavedInAnotherFile_shouldIncreaseSizeBy1() {
        try {
            AddressBook addressBook = new AddressBook("AddressBook");
            addressBook.openAddressBook();
            int size = addressBook.getSize();
            addressBook.addPersonData("Varun", "Shastri", "Sai Reddy Colony", "Noida", "UP", 768365, "+91-7021610251");
            addressBook.saveAs("UpdatedAddressBook");
            int updatedSize = addressBook.getSize();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) { }
    }

}
