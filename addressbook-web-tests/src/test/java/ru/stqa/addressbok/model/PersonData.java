package ru.stqa.addressbok.model;

public class PersonData {

    private int id=Integer.MAX_VALUE;;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String phone;
    private String mail;

    public int getId() {
        return id;
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

    public PersonData withId(int id) {
        this.id = id;
        return this;
    }

    public PersonData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public PersonData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonData withAddress(String address) {
        this.address = address;
        return this;
    }

    public PersonData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PersonData withMail(String mail) {
        this.mail = mail;
        return this;
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
