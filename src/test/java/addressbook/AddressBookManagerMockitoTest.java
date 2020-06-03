package addressbook;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookManagerMockitoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    AddressBook addressBook;

    @InjectMocks
    AddressBookManager addressBookManager;

    @Test
    public void givenAddressBookManagerToOpenCSVFile_whenDataAdded_shouldReturnSameSize() {
        try {
            when(addressBook.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890")).thenReturn(true);
            addressBookManager.open("AddressBookInCsv", AddressBookManager.FileType.CSV);
            int size = addressBookManager.getSize();
            addressBookManager.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890");
            int updatedSize = addressBookManager.getSize();
            addressBookManager.save();
            assertEquals(size,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookManagerToOpenCSVFile_whenDataAddedSaveInAnotherCSVFile_shouldReturnSameSize() {
        try {
            when(addressBook.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890")).thenReturn(true);
            addressBookManager.open("AddressBookInCsv", AddressBookManager.FileType.CSV);
            int size = addressBookManager.getSize();
            addressBookManager.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890");
            addressBookManager.saveAs("AddressBookInCsvSample", AddressBookManager.FileType.CSV);
            addressBookManager.open("AddressBookInCsvSample", AddressBookManager.FileType.CSV);
            int updatedSize = addressBookManager.getSize();
            assertEquals(size,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookManagerToCreateNewCSVFile_whenDataAdded_shouldReturnSize0() {
        try {
            when(addressBook.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890")).thenReturn(true);
            addressBookManager.newFile("AddressBookInCsv1", AddressBookManager.FileType.CSV);
            addressBookManager.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890");
            int size = addressBookManager.getSize();
            addressBookManager.save();
            assertEquals(0,size);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookManagerToOpenJsonFile_whenDataAdded_shouldReturnSameSize() {
        try {
            when(addressBook.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890")).thenReturn(true);
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            int size = addressBookManager.getSize();
            addressBookManager.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890");
            int updatedSize = addressBookManager.getSize();
            addressBookManager.save();
            assertEquals(size,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookManagerToOpenJsonFile_whenDataAddedSaveInAnotherJsonFile_shouldReturnSameSize() {
        try {
            when(addressBook.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890")).thenReturn(true);
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            int size = addressBookManager.getSize();
            addressBookManager.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890");
            addressBookManager.saveAs("AddressBookJsonSample", AddressBookManager.FileType.JSON);
            addressBookManager.open("AddressBookJsonSample", AddressBookManager.FileType.JSON);
            int updatedSize = addressBookManager.getSize();
            assertEquals(size,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookManagerToCreateNewJsonFile_whenDataAdded_shouldReturnSize0() {
        try {
            when(addressBook.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890")).thenReturn(true);
            addressBookManager.newFile("AddressBookJson1", AddressBookManager.FileType.JSON);
            addressBookManager.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890");
            int size = addressBookManager.getSize();
            addressBookManager.save();
            assertEquals(0,size);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookManagerToOpenJsonFile_whenDataAddedSaveInAnotherCSVFile_shouldReturnSameSize() {
        try {
            when(addressBook.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890")).thenReturn(true);
            addressBookManager.open("AddressBook", AddressBookManager.FileType.JSON);
            int size = addressBookManager.getSize();
            addressBookManager.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890");
            addressBookManager.saveAs("AddressBookCSVSample", AddressBookManager.FileType.CSV);
            addressBookManager.open("AddressBookCSVSample", AddressBookManager.FileType.CSV);
            int updatedSize = addressBookManager.getSize();
            assertEquals(size,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookManagerToOpenCSVFile_whenDataAddedSaveInAnotherJsonFile_shouldReturnSameSize() {
        try {
            when(addressBook.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890")).thenReturn(true);
            addressBookManager.open("AddressBookInCsv", AddressBookManager.FileType.CSV);
            int size = addressBookManager.getSize();
            addressBookManager.add("Alekhya","Bathula","Laxmi Nagar","Warangal","Telangana",506002,"+91-1234567890");
            addressBookManager.saveAs("AddressBookJsonSample1", AddressBookManager.FileType.JSON);
            addressBookManager.open("AddressBookJsonSample1", AddressBookManager.FileType.JSON);
            int updatedSize = addressBookManager.getSize();
            assertEquals(size,updatedSize);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

}
