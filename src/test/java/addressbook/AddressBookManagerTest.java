package addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookManagerTest {

    @Test
    public void givenEmpty_shouldThrowException() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenFile_whenInvalidSyntax_shouldThrowException() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("InvalidFile");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenFile_whenInvalidColon_shouldThrowException() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("InvalidColon");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenFile_whenInvalidDelimiter_shouldThrowException() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("InvalidDelimiter");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenNull_shouldThrowException() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open(null);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenLoaded_shouldReturnSize() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook1");
            int size = addressBookManager.getSize();
            Assert.assertEquals(1,size);
        } catch (AddressBookException e) { }
    }

    @Test
    public void givenAddressBookToCreateNewFile_whenDataAdded_shouldReturnSizeAs1() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.newFile("NewAddressBook");
            addressBookManager.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            addressBookManager.save();
            int updatedSize = addressBookManager.getSize();
            Assert.assertEquals(1,updatedSize);
        } catch (AddressBookException e) { }
    }

    @Test
    public void givenAddressBookToCreateNewFile_whenFileExists_shouldThrowException() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.newFile("AddressBook");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_EXISTS,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToFirstName_shouldReturnSortedResults() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook");
            List<Person> sortedData = addressBookManager.getSortedData(AddressBook.CompareType.FIRST_NAME);
            Assert.assertEquals("Abhi",sortedData.get(0).getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmptyAddressBookToSort_shouldThrowException() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("Empty");
            addressBookManager.getSortedData(AddressBook.CompareType.FIRST_NAME);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToLastName_shouldReturnSortedResults() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook");
            List<Person> sortedData = addressBookManager.getSortedData(AddressBook.CompareType.LAST_NAME);
            Assert.assertEquals("Chowdary",sortedData.get(0).getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToZip_shouldReturnSortedResults() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook");
            List<Person> sortedData = addressBookManager.getSortedData(AddressBook.CompareType.ZIP);
            Assert.assertEquals(144462,sortedData.get(0).getZip());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToState_shouldReturnSortedResults() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook");
            List<Person> sortedData = addressBookManager.getSortedData(AddressBook.CompareType.STATE);
            Assert.assertEquals("Gujarat",sortedData.get(0).getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToCity_shouldReturnSortedResults() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook");
            List<Person> sortedData = addressBookManager.getSortedData(AddressBook.CompareType.CITY);
            Assert.assertEquals("Anantapur",sortedData.get(0).getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenDataDeleted_shouldDecreaseSizeBy1() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook");
            int file = addressBookManager.getSize();
            addressBookManager.delete("+91-8715610681");
            int updatedFile = addressBookManager.getSize();
            addressBookManager.save();
            Assert.assertEquals(file-1,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenDataEdited_shouldReturnSameSize() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook");
            int file = addressBookManager.getSize();
            addressBookManager.edit("+91-6545610251","Sri Nagar Colony", "Hanvar", "Maharastra", 234564, "+91-6545610240");
            int updatedFile = addressBookManager.getSize();
            addressBookManager.save();
            Assert.assertEquals(file,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookToOpenFile_whenDataAdded_shouldIncreaseSizeBy1() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook");
            int size = addressBookManager.getSize();
            addressBookManager.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            int updatedSize = addressBookManager.getSize();
            addressBookManager.save();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) { }
    }

    @Test
    public void givenAddressBookToOpenFile_whenFileNotFound_shouldThrowException() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("NoAddressBook");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_DOESNT_EXISTS,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSavedInAnotherFile_shouldIncreaseSizeBy1() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook");
            int size = addressBookManager.getSize();
            addressBookManager.add("Varun", "Shastri", "Sai Reddy Colony", "Noida", "UP", 768365, "+91-7021610251");
            addressBookManager.saveAs("UpdatedAddressBook1");
            addressBookManager.open("UpdatedAddressBook1");
            int updatedSize = addressBookManager.getSize();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) { e.printStackTrace(); }
    }

    @Test
    public void givenAddressBook_whenSavedInAnotherFileAlreadyExists_shouldThrowException() {
        try {
            AddressBookManager addressBook = new AddressBookManager();
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
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook");
            addressBookManager.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.DATA_EXISTS,e.type);
        }
    }

}
