package ru.relex.education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.relex.education.addressbook.model.GroupData;

import java.util.HashMap;
import java.util.Map;

public class GroupsHelperr {

  private WebDriver wd;
  JavascriptExecutor js;
  private Map<String, Object> vars;

  GroupsHelperr(WebDriver wd){
    js = (JavascriptExecutor) wd;
    vars = new HashMap<String, Object>();
    this.wd = wd;
  }

  public void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  public void submitGroupCreate() {
    wd.findElement(By.name("submit")).click();
  }

  public void fillGroupForm(GroupData groupData) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooret());
  }

  public void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  public void deleteSelectedGroups() {
    wd.findElement(By.name("delete")).click();
  }

  public void seectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }
}
