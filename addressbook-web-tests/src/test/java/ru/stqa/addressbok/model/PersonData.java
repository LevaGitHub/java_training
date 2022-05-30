package ru.stqa.addressbok.model;

public class PersonData {

    private int id=Integer.MAX_VALUE;;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String phone2;
    private String allPhones;
    private String email;
    private String email2;
    private String email3;
    private String allEmails;


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

    public String getHomePhone() {return homePhone; }

    public String getMobilePhone() {return mobilePhone; }

    public String getWorkPhone() {return workPhone; }

    public String getPhone2() {return phone2; }

    public void setPhone2(String phone2) { this.phone2 = phone2; }

    public String getAllPhones() {return allPhones; }

    public PersonData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {return email2; }

    public void setEmail2(String email2) { this.email2 = email2; }

    public String getEmail3() { return email3; }

    public void setEmail3(String email3) { this.email3 = email3;}

    public String getAllEmails() {return allEmails; }

    public PersonData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
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
        this.homePhone = phone;
        return this;
    }

    public PersonData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }
    public PersonData withWork(String work) {
        this.workPhone = work;
        return this;
    }

    public PersonData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public PersonData withEmail(String email) {
        this.email = email;
        return this;
    }

    public PersonData withEmail2(String mail2) {
        this.email2 = mail2;
        return this;
    }

    public PersonData withEmail3(String mail3) {
        this.email3 = mail3;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonData that = (PersonData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }


}
