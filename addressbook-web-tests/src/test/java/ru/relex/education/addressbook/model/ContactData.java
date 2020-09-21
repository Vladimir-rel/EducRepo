package ru.relex.education.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id = 0;

  @Expose
  @Column(name = "firstName")
  private String firstName;

  @Expose
  @Column(name = "lastName")
  private String lastName;

  @Column(name = "company")
  private String company;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;
  @Transient
  private String allPhone;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String eMail1;

  @Column(name = "email2")
  @Type(type = "text")
  private String eMail2;

  @Column(name = "email3")
  @Type(type = "text" )
  //@ManyToOne(optional=false)
  //@JoinColumn(name = "candidate_id", insertable=false, updatable=false)
  private String eMail3;

  @Transient
  private String eMailAll;

  @Transient
  private String group;

  @Column(name = "photo")
  @Type(type = "text" )
  private String photo;

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }
  public ContactData withAllPhone(String allPhone) {
    this.allPhone = allPhone;
    return this;
  }

  public ContactData withEmail1(String eMail1) {
    this.eMail1 = eMail1;
    return this;
  }

  public ContactData withEmail2(String eMail2) {
    this.eMail2 = eMail2;
    return this;
  }

  public ContactData withEmail3(String eMail3) {
    this.eMail3 = eMail3;
    return this;
  }

  public ContactData withAllEmail(String eMailAll) {
    this.eMailAll = eMailAll;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAllPhone() {
    return allPhone;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public String getAllEmail() { return eMailAll; }

  public String getEmail3() { return eMail3; }

  public String getEmail2() { return eMail2; }

  public String getEmail1() { return eMail1; }

  public File getPhoto() { return new File(photo); }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, id);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}
