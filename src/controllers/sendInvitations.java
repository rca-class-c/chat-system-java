package  controllers;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import  config.Config;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.ArrayList;
public class  sendInvitations{
    public static  int sendingInvitations(final String email,final  String password) throws MessagingException, SQLException, ClassNotFoundException {
        //connection
        Connection conn=Config.getConnection();
        ArrayList<String> to= new ArrayList<String>();
        int sendlimit=0;
        Scanner scanner =new Scanner(System.in);
        System.out.println("\t \t Click 1 To Stop Inserting Emails"+"\t\tto:");

        while (sendlimit!=1){
            String sentTo=scanner.next();
            if (sentTo.equals("1")){
                sendlimit=1;
            }
            else {
                to.add(sentTo);
            }
        }
        int numberOfEmails= to.size();
        int id=0;
        int ok=0;
        //queries
        final  String query="SELECT user_id,email from users WHERE email=?";
        final  String sql="INSERT INTO sent_invitations(admin_id,sent_to) VALUES(?,?)";
        PreparedStatement preparedStatement= conn.prepareStatement(query);
        preparedStatement.setString(1,email);
        ResultSet resultSet= preparedStatement.executeQuery();
        while (resultSet.next()){
            String email1=resultSet.getString("email");
            id=resultSet.getInt("user_id");
            if (email1.equals(email)){
                System.out.println("Admin found");
                ok=1;
            }
        }

        if (ok==1){
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
            for (int i=0;i<numberOfEmails;i++) {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.setRecipients(
                        Message.RecipientType.TO,
                        new InternetAddress[]{new InternetAddress(to.get(i))}
                );
                message.setSubject("Invitation to the chat System");
                message.setText("Dear,"
                        + "\n\n Here is your invitation link to the system ");

                Transport.send(message);
                System.out.println("Message sent");
            }

//insert sent invitations

            PreparedStatement prepared=conn.prepareStatement(sql);
            for (int i = 0; i < numberOfEmails; i++) {
                prepared.setInt(1,id);
                prepared.setString(2,to.get(i));
                int row= prepared.executeUpdate();
                if (row!=-1){
                    System.out.println("Done");
                }
                else {
                    System.out.println("Failed");
                }
            }

            conn.close();


        }
        else{
            System.out.println("Not eligible");
        }
        return ok;
    }

}