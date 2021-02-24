package client.views;

import server.services.sendInvitations;
import utils.CommonUtil;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public
class AdminInput {
public  static  void InviteUser() throws ClassNotFoundException, MessagingException, SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Click 1  To Stop: ");
    String to = null;
    String[] sentTo=new String[]{};
    int i=0;
    while (to != "1") {
        to = scanner.next();
         sentTo[i]=to;
         i++;
    }
   sendInvitations.sendingInvitations(sentTo);
}
}