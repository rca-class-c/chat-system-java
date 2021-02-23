package  server.services;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ArrayList;

import server.models.User;
import utils.CommonUtil;
import utils.OTP;
import server.repositories.sendInvitationRepositories;
import utils.SendInvitationEmail;

public class  sendInvitations{
    public static boolean CheckIfVerificationCodeExist(int code){
        return true;
    };
    public static  boolean sendingInvitations(String[] to) throws MessagingException, SQLException, ClassNotFoundException {
        String email = "oclassc@gmail.com";
        String password = "!C$l2a%s0s#2^1C&(";
        ArrayList<Integer> verificationCodes= new ArrayList<Integer>();
        sendInvitationRepositories repositories =new sendInvitationRepositories();
        int id=0;
        int ok;
        // OTP
        for (String newEmail : to) {
            char [] otp= OTP.OTP(5);
            int sentOtp=Integer.parseInt(String.valueOf(otp));
            verificationCodes.add(sentOtp);
        }
        // SEARCH FOR THE ADMIN
        id=10;
// SEND INVITATIONS TO THE SELECTED EMAILS
//        if (id!=0) {
//            Properties prop = new Properties();
//            prop.put("mail.smtp.host", "smtp.gmail.com");
//            prop.put("mail.smtp.port", "587");
//            prop.put("mail.smtp.auth", "true");
//            prop.put("mail.smtp.starttls.enable", "true");
//            Session session = Session.getInstance(prop,
//                    new javax.mail.Authenticator() {
//                        protected PasswordAuthentication getPasswordAuthentication() {
//                            return new PasswordAuthentication(email, password);
//                        }
//                    });
            //for (int i = 0; i < numberOfEmails; i++) {
            int i = 0;
            for (String newEmail : to) {
//                MimeMessage message = new MimeMessage(session);
//                message.setFrom(new InternetAddress(email));
//                message.setRecipients(
//                        Message.RecipientType.TO,
//                        new InternetAddress[]{new InternetAddress(newEmail)}
//                );
//                message.setSubject("Invitation to the chat System");
//                message.setText("Dear,"
//                        + "\n\n Here is your Invitation Token  to the System : " + verificationCodes.get(i));
//
//                Transport.send(message);
                if(new SendInvitationEmail(verificationCodes.get(i),newEmail).SendEmail()){
                    repositories.InsertAnInvitation(id,newEmail,verificationCodes.get(i));
                    i++;
                    System.out.println("Email sent");
                // INSERT INTO DB INVITATIONS
//
//                i++;
            }
        }
        if(i == to.length){
            return true;
        }
        return false;
    }

}