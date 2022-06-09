package ru.stqa.addressbok.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")
@XStreamAlias("persons")
public class PersonData {

    @Id
    @XStreamOmitField()
    @Column(name="id")
    private int id=Integer.MAX_VALUE;

    @Expose
    @Column(name="firstname")
    private String firstName;

    @Expose
    @Column(name="middlename")
    private String middleName;

    @Expose
    @Column(name="lastname")
    private String lastName;

    @Transient
    private String group;

    @Expose
    @Column(name="address")
    @Type(type="text")
    private String address;

    @Expose
    @Column(name="home")
    @Type(type="text")
    private String homePhone;

    @Column(name="mobile")
    @Type(type="text")
    private String mobilePhone;

    @Column(name="work")
    @Type(type="text")
    private String workPhone;

    @Column(name="phone2")
    @Type(type="text")
    private String phone2;

    @Transient
    private String allPhones;

    @Expose
    @Column(name="email")
    @Type(type="text")
    private String email;

    @Override
    public String toString() {
        return "PersonData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Column(name="email2")
    @Type(type="text")
    private String email2;

    @Column(name="email3")
    @Type(type="text")
    private String email3;

    @Transient
    private String allEmails;

    @Column(name="photo")
    @Type(type="text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public PersonData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

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
