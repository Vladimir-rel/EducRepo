package ru.relex.education.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.relex.education.mantis.model.Issue;
import ru.relex.education.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {

  @Test
  public void testGetProjects () throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProject();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProject();
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description")
            .withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    Assert.assertEquals(issue.getSummary(), created.getSummary());
  }
}
