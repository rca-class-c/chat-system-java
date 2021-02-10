package  server.services;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ArrayList;
import utils.Mailing;
import utils.OTP;
import server.repositories.sendInvitationRepositories;

/**
 * @author Cyusa Munezero Keny
 */
public class  sendInvitations{
    public static  int sendingInvitations(final String email,final  String password,ArrayList<String> to) throws MessagingException, SQLException, ClassNotFoundException {
        ArrayList<Integer> verificationCodes= new ArrayList<Integer>();
        // REPOSITORY
        sendInvitationRepositories repositories =new sendInvitationRepositories();
        // VARIABLES OF TOTAL NUMBER OF  EMAILS SELECTED , ID OF THE USER , STATUS OF THE EMAIL
        int numberOfEmails= to.size();
        int id=0;
        int ok;
        // OTP
        for (int i = 0; i < numberOfEmails; i++) {
            char [] otp= OTP.OTP(5);
            int sentOtp=Integer.parseInt(String.valueOf(otp));
            verificationCodes.add(sentOtp);
        }
        // SEARCH FOR THE ADMIN
     id=repositories.searchForAdmin(email);
// SEND INVITATIONS TO THE SELECTED EMAILS
        if (id!=0) {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(email, password);
                        }
                    });
            for (int i = 0; i < numberOfEmails; i++) {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.setRecipients(
                        Message.RecipientType.TO,
                        new InternetAddress[]{new InternetAddress(to.get(i))}
                );
                message.setSubject("Invitation to the chat System");
                message.setText("Dear,"
                        + "\n\n Here is your Invitation Token  to the System : " + verificationCodes.get(i));

                Transport.send(message);
                // INSERT INTO DB INVITATIONS
                repositories.InsertAnInvitation(id,to.get(i),verificationCodes.get(i));
            }
        }
        ok=1;
        return ok;
    }

}