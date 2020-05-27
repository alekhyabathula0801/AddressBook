package addressbook;

public class AddressBookException extends Exception {
    enum ExceptionType {NO_DATA, FILE_PROBLEM,INVALID_DATA,FILE_EXISTS,FILE_DOESNT_EXISTS,DATA_EXISTS,
                        ENTERED_EMPTY,ENTERED_NULL}

    ExceptionType type;

    public AddressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
