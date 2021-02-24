package  server.services;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ArrayList;

import server.models.User;
import server.repositories.UserRepository;
import utils.CommonUtil;
import utils.OTP;
import server.repositories.sendInvitationRepositories;
import utils.SendInvitationEmail;

public class  sendInvitations{
    public static boolean CheckIfVerificationCodeExist(int code){
        return new UserRepository().checkVerificationCode(code);
        //return true;
    };
    public static  boolean sendingInvitations(String[] to) throws  SQLException {
        ArrayList<Integer> verificationCodes= new ArrayList<Integer>();
        sendInvitationRepositories repositories =new sendInvitationRepositories();
        int id=0;
        for (String newEmail : to) {
            char [] otp= OTP.OTP(5);
            int sentOtp=Integer.parseInt(String.valueOf(otp));
            verificationCodes.add(sentOtp);
        }
        id=10;

            int i = 0;
            for (String newEmail : to) {

                if(new SendInvitationEmail(verificationCodes.get(i),newEmail).SendEmail()){
                    repositories.InsertAnInvitation(id,newEmail,verificationCodes.get(i));
                    i++;
                    System.out.println("Email sent");
            }
        }
        if(i == to.length){
            return true;
        }
        return false;
    }

}