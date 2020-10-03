package ru.relex.education.rest.test;

import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.relex.education.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestRunFixedTests extends TestBase {

  @Test
  public void testRunFixedIssueTest() throws IOException {
    Set<Issue> issues = getIssues(0);
    for (Issue issue : issues) {
      if ("Open".equals(issue.getStateName())) {
        System.out.println("Skeep test, because bug " + issue.getId() + " is open.");
      }
      else {
        System.out.println("Run test for bug: " + issue.getId());
      }
    }
  }

  @Test
  public void testRunFixedIssueTest2() throws IOException {
    Set<Issue> issues = getIssues(0);
    for (Issue issue : issues) {
      try {
        skipIfNotFixed(issue.getId());
        System.out.println("Run test for bug: " + issue.getId());
      } catch (SkipException ex) {
        System.out.println(ex.getMessage());
      }
    }
  }
}
