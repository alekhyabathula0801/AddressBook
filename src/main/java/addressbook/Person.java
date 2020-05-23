package addressbook;

public class Person {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String mobile;

    public Person(String firstName, String lastName, String address, String city, String state, int zip, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.mobile = mobile;
    }

}
