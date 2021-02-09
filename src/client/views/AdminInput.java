package client.views;

import server.services.sendInvitations;
import utils.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public
class AdminInput {

    public static void inviteUser() {
        Scanner scan = new Scanner(System.in);
        CommonUtil.useColor("\u001b[0;33m");
        System.out.print(" Enter emails [space for more]: ");
        CommonUtil.resetColor();
        String emails = scan.nextLine();
        String[] email = emails.split(" ");
        ArrayList<String> emailLists = new ArrayList<String>(Arrays.asList(email).subList(0, emails.split(" ").length));
        sendInvitations.sendingInvitations(emailLists);
    }
}
