package ru.relex.education.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.relex.education.mantis.model.Issue;
import ru.relex.education.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
  private  ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProject() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL(System.getProperty("web.baseUrl")+ System.getProperty("soap.connect")));
    return mc;
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getDescription());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
    IssueData createdIssue = mc.mc_issue_get("administrator", "root", issueId);
    return new Issue().withId(createdIssue.getId().intValue())
            .withSummary(createdIssue.getSummary())
            .withDescription(createdIssue.getDescription())
            .withProject(new Project().withId(createdIssue.getProject().getId().intValue())
                                      .withName(createdIssue.getProject().getName()));
  }

  public List<Issue> getProjectIssues(int projectId) {
    MantisConnectPortType mc = getMantisConnect();
    IssueData[] issueData = MantisConnectPortType.mc_filter_get_issues("administrator", "root", projectId);
    mc.mc

  }

  boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData issuedata = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
    if (issuedata.getStatus().toString() == "open") {
      return true;
    } else {
      return  false;
    }
  }
}
