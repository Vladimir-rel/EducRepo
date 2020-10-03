package ru.relex.education.rest.test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ru.relex.education.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  protected Set<Issue> getIssues(int issueId) throws IOException {
    String json = null;
    if (issueId == 0) {
      json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json"))
              .returnContent()
              .asString();
    } else {
      json = getExecutor().execute(Request.Get(String.format("http://bugify.stqa.ru/api/issues/%s.json", issueId)))
              .returnContent()
              .asString();
    }
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  protected void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  protected Executor getExecutor() {
    Executor.newInstance().clearAuth();
    return Executor.newInstance().
            auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  protected boolean isIssueOpen(int issueId) throws IOException {
    Set<Issue> issues = getIssues(issueId);
    if ("Open".equals(issues.iterator().next().getStateName())) {
      return true;
    } else {
      return false;
    }
  }
}
