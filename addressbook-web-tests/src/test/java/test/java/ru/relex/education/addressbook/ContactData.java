package test.java.ru.relex.education.addressbook;

public class ContactData {
  private final String first_name;
  private final String middle_name;
  private final String company;
  private final String address;
  private final String phone;

  public ContactData(String first_name, String middle_name, String company, String address, String phone) {
    this.first_name = first_name;
    this.middle_name = middle_name;
    this.company = company;
    this.address = address;
    this.phone = phone;
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
}
