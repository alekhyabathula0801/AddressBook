package addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookManagerTest {

    @Test
    public void givenEmptyName_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("", AddressBookManager.FileType.JSON);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenJsonFile_whenInvalidSyntax_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("InvalidFile", AddressBookManager.FileType.JSON);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenJsonFile_whenInvalidColon_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("InvalidColon", AddressBookManager.FileType.JSON);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenJsonFile_whenInvalidDelimiter_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("InvalidDelimiter", AddressBookManager.FileType.JSON);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenNullName_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open(null, AddressBookManager.FileType.JSON);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenAddressBookOfJsonType_whenLoaded_shouldReturnSize() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook1", AddressBookManager.FileType.JSON);
            int size = addressBookManager.getSize();
            Assert.assertEquals(1,size);
        } catch (AddressBookException e) { }
    }

    @Test
    public void givenAddressBookToCreateNewFile_whenDataAdded_shouldReturnSizeAs1() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.newFile("NewAddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            addressBookManager.save();
            int updatedSize = addressBookManager.getSize();
            Assert.assertEquals(1,updatedSize);
        } catch (AddressBookException e) { }
    }

    @Test
    public void givenAddressBookToCreateNewFile_whenFileExists_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.newFile("AddressBook", AddressBookManager.FileType.JSON);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_EXISTS,e.type);
        }
    }

    @Test
    public void givenData_whenAdded_shouldIncreaseSizeBy2() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            int file = addressBookManager.getSize();
            addressBookManager.add("Sam", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "+91-8715610681");
            addressBookManager.add("Karan", "Shetty", "Yamuna Colony", "Mangua", "Jammu", 781164, "+91-6545610251");
            addressBookManager.save();
            int updatedFile = addressBookManager.getSize();
            Assert.assertEquals(file+2,updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNullDataToAdd_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.add(null, "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, null);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenEmptyDataToAdd_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.add("", "Reddy", "Ganga Colony", "Mehar", "Kerala", 871164, "");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToFirstName_shouldReturnSortedResults() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            List<Person> sortedData = addressBookManager.getSortedData(AddressBook.CompareType.FIRST_NAME);
            Assert.assertEquals("Abhi",sortedData.get(0).getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmptyAddressBookToSort_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("Empty", AddressBookManager.FileType.JSON);
            addressBookManager.getSortedData(AddressBook.CompareType.FIRST_NAME);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToLastName_shouldReturnSortedResults() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            List<Person> sortedData = addressBookManager.getSortedData(AddressBook.CompareType.LAST_NAME);
            Assert.assertEquals("Chowdary",sortedData.get(0).getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToZip_shouldReturnSortedResults() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            List<Person> sortedData = addressBookManager.getSortedData(AddressBook.CompareType.ZIP);
            Assert.assertEquals(144462,sortedData.get(0).getZip());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToState_shouldReturnSortedResults() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            List<Person> sortedData = addressBookManager.getSortedData(AddressBook.CompareType.STATE);
            Assert.assertEquals("Gujarat",sortedData.get(0).getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSortedAccordingToCity_shouldReturnSortedResults() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            List<Person> sortedData = addressBookManager.getSortedData(AddressBook.CompareType.CITY);
            Assert.assertEquals("Anantapur",sortedData.get(0).getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenDataDeleted_shouldDecreaseSizeBy1() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
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
    public void givenNullDataToDelete_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.delete( null);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenEmptyDataToDelete_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.delete( "");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenData_whenDataToBeDeletedIsNotFound_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.delete("+91-8912346543");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.INVALID_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenDataEdited_shouldReturnSameSize() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
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
    public void givenNullDataToEdit_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.edit( null,"SR Estates","rameshwaram","Kerala",987654,"+91-9876543976");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenEmptyDataToEdit_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.edit( "","SR Estates","rameshwaram","Kerala",987654,"+91-9876543976");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenData_whenDataToEditIsNotFound_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.edit("+91-9875610250","Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.INVALID_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBookToOpenFile_whenDataAdded_shouldIncreaseSizeBy1() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            int size = addressBookManager.getSize();
            addressBookManager.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
            int updatedSize = addressBookManager.getSize();
            addressBookManager.save();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookToOpenFile_whenFileNotFound_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("NoAddressBook", AddressBookManager.FileType.JSON);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_DOESNT_EXISTS,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSavedInAnotherFile_shouldIncreaseSizeBy1() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            int size = addressBookManager.getSize();
            addressBookManager.add("Varun", "Shastri", "Sai Reddy Colony", "Noida", "UP", 768365, "+91-7021610251");
            addressBookManager.saveAs("UpdatedAddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.open("UpdatedAddressBook", AddressBookManager.FileType.JSON);
            int updatedSize = addressBookManager.getSize();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenSavedInAnotherFileAlreadyExists_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.add("Sanju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-98756164");
            addressBookManager.saveAs("NewAddressBook", AddressBookManager.FileType.JSON);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.FILE_EXISTS,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenAddDataAlreadyExists_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            addressBookManager.add("Anju", "Sharma", "Sai Ram Colony", "Kazipet", "Kashmir", 876365, "+91-9875610251");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.DATA_EXISTS,e.type);
        }
    }

    @Test
    public void givenAddressBook_whenSavedInCsvFile_shouldIncreaseSizeBy1() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            int size = addressBookManager.getSize();
            addressBookManager.add("Varun", "Shastri", "Sai Reddy Colony", "Noida", "UP", 768365, "+91-7021610251");
            addressBookManager.saveAs("UpdatedAddressBookInCsv", AddressBookManager.FileType.CSV);
            addressBookManager.open("UpdatedAddressBookInCsv", AddressBookManager.FileType.CSV);
            int updatedSize = addressBookManager.getSize();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCSVFile_whenInvalidDelimiter_shouldThrowException() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("InvalidDelimiterCsv", AddressBookManager.FileType.CSV);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.INVALID_PARSE,e.type);
        }
    }

    @Test
    public void givenAddressBookOfCSVFile_whenSaved_shouldIncreaseSizeBy1() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBookInCsv", AddressBookManager.FileType.CSV);
            int size = addressBookManager.getSize();
            addressBookManager.add("Varun Kumar", "Shastri", "Sai Reddy Colony", "Noida", "UP", 768365, "+91-7021610251");
            addressBookManager.save();
            int updatedSize = addressBookManager.getSize();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookOfCSVFile_whenSavedInJson_shouldIncreaseSizeBy1() {
        try {
            IAddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.open("AddressBookInCsv", AddressBookManager.FileType.CSV);
            int size = addressBookManager.getSize();
            addressBookManager.add("Shivani", "Shastri", "Sai Reddy Colony", "Noida", "UP", 768365, "+91-7021698512");
            addressBookManager.saveAs("AddressBookInJson", AddressBookManager.FileType.JSON);
            addressBookManager.open("AddressBookInJson", AddressBookManager.FileType.JSON);
            int updatedSize = addressBookManager.getSize();
            Assert.assertEquals(size+1,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

}
