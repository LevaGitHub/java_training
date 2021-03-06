package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase{
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws MessagingException, IOException {
        //Получение значений из БД
        Users users = app.db().getUsers();
        UserData user = users.iterator().next();
        String userName = user.getUserName();
        String newPassword = "password";
        // Действия админа
        app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.goTo().manageMenuItem();
        app.goTo().manageUsersTab();
        app.user().selectUser(user.getId());
        app.user().resetPassword();
        // Действия юзера
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        app.registration().finishChangingPassword(userName, confirmationLink, newPassword);
        long now = System.currentTimeMillis();
        assertTrue(app.newSession().login(userName, newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
