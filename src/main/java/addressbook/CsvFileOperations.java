package addressbook;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvFileOperations extends FileOperations implements IFileOperations {

    public List<Person> loadDataFromFile(String fileName) throws AddressBookException {
        List<Person> data;
        try (Reader reader = Files.newBufferedReader(Paths.get(getFilePath(fileName, AddressBookManager.FileType.CSV)))) {
            CsvToBean<Person> csvToBean = new CsvToBeanBuilder(reader).withType(Person.class)
                                                                      .withIgnoreLeadingWhiteSpace(true)
                                                                      .build();
            data = csvToBean.parse();
        } catch (IOException e){
            throw  new AddressBookException("Invalid File", AddressBookException.ExceptionType.FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw  new AddressBookException("Invalid Parse", AddressBookException.ExceptionType.INVALID_PARSE);
        }
        return data;
    }

    public void writeInFile(List<Person> data, String fileName) throws AddressBookException{
        try (Writer writer = Files.newBufferedWriter(Paths.get(getFilePath(fileName, AddressBookManager.FileType.CSV)))) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer)
                                                      .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                                                      .build();
            beanToCsv.write(data);
        } catch (Exception e) {
            throw  new AddressBookException("Invalid File", AddressBookException.ExceptionType.FILE_PROBLEM);
        }
    }

}
