package addressbook;

public class AddressBookException extends Exception {
    enum ExceptionType {NO_DATA,ADDRESS_BOOK_FILE_PROBLEM,INVALID_DATA}

    ExceptionType type;

    public AddressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
