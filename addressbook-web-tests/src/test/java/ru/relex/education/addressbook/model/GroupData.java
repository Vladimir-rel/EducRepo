package ru.relex.education.addressbook.model;

import java.util.Objects;

public class GroupData {

  private int id;
  private String name;
  private String header;
  private String fooret;

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

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooret(String fooret) {
    this.fooret = fooret;
    return this;
  }

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
