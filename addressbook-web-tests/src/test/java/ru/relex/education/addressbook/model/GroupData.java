package ru.relex.education.addressbook.model;

import java.util.Objects;

public class GroupData {

  private int id;
  private final String name;
  private final String header;
  private final String fooret;

  public GroupData(String name, String header, String fooret, int id) {
    this.name = name;
    this.header = header;
    this.fooret = fooret;
    this.id = id;
  }

  public GroupData(String name, String header, String fooret) {
    this.name = name;
    this.header = header;
    this.fooret = fooret;
    this.id = 0;
    //this.id = Integer.MAX_VALUE;
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

  public int getId() { return id; }

  public void setId(int id) { this.id = id; }

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return Objects.equals(name, groupData.name)&&Objects.equals(id, groupData.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
