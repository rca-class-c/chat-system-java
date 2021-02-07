package client.views;

import client.views.components.*;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.util.Scanner;

public class AdminAction {
    public  AdminAction(){
        this.starts();
    }
    public void starts(){
        Component.pageTitleView("ADMIN ACTIVITIES");
        CommonUtil.addTabs(10, true);
        System.out.println("1. Statistics");
        CommonUtil.addTabs(10, false);
        System.out.println("2. Invite user");
        CommonUtil.addTabs(10, false);
        System.out.println("3. Go back");
        CommonUtil.addTabs(10, false);
        System.out.println("4. Quit");
        this.insertChoice();
    }

    private void insertChoice(){
        do {
            try {
                CommonUtil.addTabs(10, false);
                CommonUtil.useColor(ConsoleColor.BackgroundColor.YELLOW_BACKGROUND);
                System.out.print("  ");
                CommonUtil.resetColor();
                CommonUtil.useColor(ConsoleColor.RegularColor.YELLOW);
                System.out.print(" Choose an option: ");
                CommonUtil.resetColor();
                int choice = this.insertAdminChoice();

                switch (choice) {
                    case 1 -> this.chooseStat();
                    //case 2 -> client.views.SendInvitationView.sendInvitations();
                    case 3 -> System.out.println("back to profile setting");
                    case 4 -> {
                        CommonUtil.addTabs(10, true);
                        CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                        System.out.println("SYSTEM CLOSED !");
                        System.exit(1);
                    }
                    default -> {
                        CommonUtil.addTabs(10, false);
                        CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                        System.out.println("Enter a valid choice (1,5): ");
                        CommonUtil.resetColor();
                    }
                }
            }catch (Exception e){
                CommonUtil.addTabs(10, false);
                CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                System.out.println("is incorrect input");
                CommonUtil.resetColor();
            }
        }while (true);
    }
  private void chooseStat(){
      Component.pageTitleView("VIEW STATISTICS OF THE APP");
      CommonUtil.addTabs(10, true);
      System.out.println("1. message reports");
      CommonUtil.addTabs(10, false);
      System.out.println("2. user reports");
      CommonUtil.addTabs(10, false);
      System.out.println("3. Go back");
      CommonUtil.addTabs(10, false);
      System.out.println("4. Quit");

      do {
          try {
              CommonUtil.addTabs(10, false);
              CommonUtil.useColor(ConsoleColor.BackgroundColor.YELLOW_BACKGROUND);
              System.out.print("  ");
              CommonUtil.resetColor();
              CommonUtil.useColor(ConsoleColor.RegularColor.YELLOW);
              System.out.print(" Choose an option: ");
              CommonUtil.resetColor();
              int choiceStatic = this.insertAdminChoice();

              switch (choiceStatic) {
                  case 1 -> {
                      CommonUtil.clearScreen();
                      this.choosePeriod();
                  }
                  case 2 ->{
                      System.out.flush();
                      CommonUtil.clearScreen();
                      this.choosePeriod();
                  }
                  case 3 -> this.starts();
                  case 4 -> {
                      CommonUtil.addTabs(10, true);
                      Component.byeBye();
                      CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                      System.out.println("SYSTEM CLOSED !");
                      System.exit(1);
                  }
                  default -> {
                      CommonUtil.addTabs(10, false);
                      CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                      System.out.print("Enter a valid choice (1,5): ");
                      CommonUtil.resetColor();
                  }
              }
          }catch (Exception e){
              CommonUtil.addTabs(10, false);
              CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
              System.out.println("is incorrect input");
              CommonUtil.resetColor();
          }
      }while (true);
  }

  private int insertAdminChoice(){
      int num;
      CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

      Scanner scanner = new Scanner(System.in);
      num = scanner.nextInt();
      CommonUtil.useColor(ConsoleColor.RESET);
      return num;
  }

  private  void choosePeriod(){

          Component.pageTitleView("CHOOSE THE REPORT");
          CommonUtil.addTabs(10, true);
          System.out.println("1. Daily");
          CommonUtil.addTabs(10, false);
          System.out.println("2. Monthly");
          CommonUtil.addTabs(10, false);
          System.out.println("3. Yearly");
          CommonUtil.addTabs(10, false);
          System.out.println("4. Go back");
          CommonUtil.addTabs(10, false);
          System.out.println("5. Quit");
      do {
                      try {
                          CommonUtil.addTabs(10, false);
                          CommonUtil.useColor(ConsoleColor.BackgroundColor.YELLOW_BACKGROUND);
                          System.out.print("  ");
                          CommonUtil.resetColor();
                          CommonUtil.useColor(ConsoleColor.RegularColor.YELLOW);
                          System.out.print(" Choose an option: ");
                          CommonUtil.resetColor();
                          int choicePeriod = this.insertAdminChoice();

                          switch (choicePeriod) {
                              case 1 -> System.out.println("choicePeriod = " + choicePeriod);
                              case 2 -> System.out.println("monthly");
                              case 3 -> System.out.println("yearly");
                              case 4 -> this.starts();
                              case 5 -> {
                                  CommonUtil.addTabs(10, true);
                                  Component.byeBye();
                                  CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                                  System.out.println("SYSTEM CLOSED !");
                                  System.exit(1);
                              }
                              default -> {
                                  CommonUtil.addTabs(10, false);
                                  CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                                  System.out.print("Enter a valid choice (1,5): ");
                                  CommonUtil.resetColor();
                              }
                          }
                      } catch (Exception e) {
                          CommonUtil.addTabs(10, false);
                          CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                          System.out.println("is incorrect input");
                          CommonUtil.resetColor();
                      }
                  } while (true);
      }



}
