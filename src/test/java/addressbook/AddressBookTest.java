package addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookTest {

    @Test
    public void givenAddressBook_whenDataAdded_shouldIncreaseSizeBy2() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            int file = addressBook.getSize();
            addressBook.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBook.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            int updatedFile = addressBook.getSize();
            addressBook.save();
            Assert.assertEquals(file+2,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmpty_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenNull_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open(null);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenAddressBookAndNullDataToAdd_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            addressBook.add(null, "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, null);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenAddressBookAndEmptyDataToAdd_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            addressBook.add("", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToFirstName_shouldReturnSortedResults() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.FIRST_NAME);
            Assert.assertEquals("Abhi",sortedData.get(0).getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmptyAddressBookToSort_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("Empty");
            addressBook.getSortedData(AddressBook.CompareType.FIRST_NAME);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToLastName_shouldReturnSortedResults() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.LAST_NAME);
            Assert.assertEquals("Chowdary",sortedData.get(0).getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToZip_shouldReturnSortedResults() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.ZIP);
            Assert.assertEquals(144462,sortedData.get(0).getZip());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToState_shouldReturnSortedResults() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.STATE);
            Assert.assertEquals("Gujarat",sortedData.get(0).getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToCity_shouldReturnSortedResults() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            List<Person> sortedData = addressBook.getSortedData(AddressBook.CompareType.CITY);
            Assert.assertEquals("Anantapur",sortedData.get(0).getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenDataDeleted_shouldDecreaseSizeBy1() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            int file = addressBook.getSize();
            addressBook.delete("+91-8715610681");
            int updatedFile = addressBook.getSize();
            addressBook.save();
            Assert.assertEquals(file-1,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookAndNullDataToDelete_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            addressBook.delete( null);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenAddressBookAndEmptyDataToDelete_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            addressBook.delete( "");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenDataToBeDeletedIsNotFound_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            addressBook.delete("+91-8912346543");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.INVALID_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenDataEdited_shouldReturnSameSize() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            int file = addressBook.getSize();
            addressBook.edit("+91-6545610251","Sri Nagar Colony", "Hanvar", "Maharastra", 234564, "+91-6545610240");
            int updatedFile = addressBook.getSize();
            addressBook.save();
            Assert.assertEquals(file,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookAndNullDataToEdit_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            addressBook.edit( null,"SR Estates","rameshwaram","Kerala",987654,"+91-9876543976");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenAddressBookAndEmptyDataToEdit_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            addressBook.edit( "","SR Estates","rameshwaram","Kerala",987654,"+91-9876543976");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenDataToEditIsNotFound_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            addressBook.edit("+91-9875610250","Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.INVALID_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBookToCreateNewFile_whenDataAdded_shouldReturnSizeAs1() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.newFile("NewAddressBook");
            addressBook.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            addressBook.save();
            int updatedSize = addressBook.getSize();
            Assert.assertEquals(1,updatedSize);
        } catch (AddressBookException e) { }
    }

    @Test
    public void givenAddressBookToCreateNewFile_whenFileExists_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.newFile("AddressBook");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_EXISTS,e.type);
        }
    }

    @Test
    public void givenAddressBookToOpenFile_whenDataAdded_shouldIncreaseSizeBy1() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            int size = addressBook.getSize();
            addressBook.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            int updatedSize = addressBook.getSize();
            addressBook.save();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) { }
    }

    @Test
    public void givenAddressBookToOpenFile_whenFileNotFound_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("NoAddressBook");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_DOESNT_EXISTS,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSavedInAnotherFile_shouldIncreaseSizeBy1() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            int size = addressBook.getSize();
            addressBook.add("Varun", "Shastri", "Sai Reddy Colony", "Noida", "UP", 768365, "+91-7021610251");
            addressBook.saveAs("UpdatedAddressBook");
            addressBook.open("UpdatedAddressBook");
            int updatedSize = addressBook.getSize();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) { e.printStackTrace(); }
    }

    @Test
    public void givenAddressBook_whenSavedInAnotherFileAlreadyExists_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            addressBook.add("Sanju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-98756164");
            addressBook.saveAs("NewAddressBook");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_EXISTS,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenAddDataAlreadyExists_shouldThrowException() {
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.open("AddressBook");
            addressBook.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.DATA_EXISTS,e.type);
        }
    }

}
