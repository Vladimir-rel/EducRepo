package test.java.ru.relex.education.addressbook;

public class GroupData {
  private final String name;
  private final String header;
  private final String fooret;

  public GroupData(String name, String header, String fooret) {
    this.name = name;
    this.header = header;
    this.fooret = fooret;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooret() {
    return fooret;
  }
}
