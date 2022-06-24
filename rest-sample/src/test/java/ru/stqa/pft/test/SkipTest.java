package ru.stqa.pft.test;

import org.testng.annotations.Test;

public class SkipTest extends TestBase {

    @Test
    public void testSkip() {
        // "id": 1699 -  "In Progress"
        int blockedIssueId = 1;
        skipIfNotFixed(blockedIssueId);
        System.out.println("The line is not printed because there is an open bug");
    }

}
