package utils;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


/**
 *Description: This util class is functioning part that sends email to a user to join the system
 *@author Didier Munezero
 */
public class SendInvitationEmail {
    int verificationCode;
    String email;
    String from = "oclassc@gmail.com";
    String password = "!C$l2a%s0s#2^1C&(";
    String host = "localhost";

    public SendInvitationEmail(int verificationCode, String email) {
        this.verificationCode = verificationCode;
        this.email = email;
    }
    public boolean SendEmail(){
        // Recipient's email ID needs to be mentioned.
        String to = email;


        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from,password);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Verification Code to join Classc");

            // Now set the actual message
            message.setText("Hello this is your verification code to join classC: "+verificationCode);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return false;
    }
}
