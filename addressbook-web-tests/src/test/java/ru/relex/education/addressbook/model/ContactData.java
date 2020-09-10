package ru.relex.education.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String first_name;
  private final String middle_name;
  private final String company;
  private final String address;
  private final String phone;
  private final String group;
  private int id;

  public ContactData(String first_name, String middle_name, String company, String address, String phone, String group) {
    this.first_name = first_name;
    this.middle_name = middle_name;
    this.company = company;
    this.address = address;
    this.phone = phone;
    this.group = group;
    this.id = 0;
  }

  public ContactData(String first_name, String middle_name, String company, String address, String phone, String group, int id) {
    this.first_name = first_name;
    this.middle_name = middle_name;
    this.company = company;
    this.address = address;
    this.phone = phone;
    this.group = group;
    this.id = id;
  }

  public String getFirst_name() {
    return first_name;
  }

  public String getMiddle_name() {
    return middle_name;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) { this.id = id; }

  @Override
  public String toString() {
    return "ContactData{" +
            " id='" + id + '\'' +
            ", first_name='" + first_name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(first_name, that.first_name) &&
            Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first_name, id);
  }
}
