package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.*;

public class SoapTests extends TestBase{

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project: projects){
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException{
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue()
                .withSummary("Test issue")
                .withDescription("Test issue description")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());

    }

    @Test
    public void testSkipIssue() throws MalformedURLException, ServiceException, RemoteException{
        int openedIssueId = 4;
        skipIfNotFixed(openedIssueId);
        System.out.println("The line is not printed because there is an open bug");
    }

    @Test
    public void testNotSkipIssue() throws MalformedURLException, ServiceException, RemoteException{
        int openedIssueId = 3;
        skipIfNotFixed(openedIssueId);
        System.out.println("The string is printed because there is a closed bug");
    }
}
