package ru.stqa.addressbok.model;

public class PersonData {
    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String mail;

    public PersonData(
            int id,
            String lastName,
            String middleName,
            String firstName,
            String address,
            String phone,
            String mail) {

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
    }

    public PersonData(
            int id,
            String lastName,
            String firstName) {

        this.id = id;
        this.firstName = firstName;
        this.middleName = null;
        this.lastName = lastName;
        this.address = null;
        this.phone = null;
        this.mail = null;
    }

    public PersonData(String firstName, String middleName, String lastName, String address, String phone, String mail) {
        this.id = Integer.MAX_VALUE;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonData that = (PersonData) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
