package ru.stqa.addressbok;

public class PersonData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String mail;

    public PersonData(String firstName, String middleName, String lastName, String address, String phone, String mail) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }
}
