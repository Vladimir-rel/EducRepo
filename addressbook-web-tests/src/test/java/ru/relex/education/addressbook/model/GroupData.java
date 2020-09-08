package ru.relex.education.addressbook.model;

import java.util.Objects;

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

  @Override
  public String toString() {
    return "GroupData{" +
            "name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return Objects.equals(name, groupData.name) &&
            Objects.equals(header, groupData.header) &&
            Objects.equals(fooret, groupData.fooret);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, header, fooret);
  }
}
