package server.repositories;

import server.components.Component;
import server.config.PostegresConfig;
import  server.models.Group;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GroupLogAction{
    /**
     * @author hortance
     * @description: This is class with methods which print all group actions
     * **/

    public  List<Object> viewAllGroups() throws Exception{
        List<Object> allGroups=new ArrayList<>();
        try{
            Connection connection= PostegresConfig.getConnection();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT group_creator,group_name,created_at,updated_at FROM groups");

            while(rs.next()){
                Group group=new Group(
                        rs.getInt("id"),
                        rs.getString("group_name"),
                        rs.getString("description"),
                        rs.getInt("group_creator"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at")
                );
                allGroups.add((Object)group);
            }
            connection.close();
        }catch (Exception e){
            System.out.println();
            CommonUtil.addTabs(10, false);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.RED_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

            System.out.print(e.getMessage());

            CommonUtil.resetColor();
        }
        return  allGroups;
    }

    public  static   void getGroupsByCreationDateInWeek(String date) throws  Exception{
        try{
            String query="SELECT * FROM GROUPS WHERE created_at=current_timestamp+7";
            Connection connection= PostegresConfig.getConnection();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            ArrayList<Group> allGroups=new ArrayList<Group>();

            while (rs.next()){
                Group group=new Group(
                        rs.getString("group_name"),
                        rs.getString("group_creator"),
                        rs.getInt("Created_at")
                );
                allGroups.add(group);
            }
            Iterator it=allGroups.iterator();
            while (it.hasNext()){
                Group group=(Group)it.next();
            }
            connection.close();
        }catch(Exception e){
            Component.showErrorMessage(e.getMessage());

            CommonUtil.resetColor();
        }
    }
    public  static void totalNumberOfCreatedGroups() throws Exception{
        try{
            Connection connection= PostegresConfig.getConnection();
            Statement stmt=connection.createStatement();
            String query="SELECT COUNT(*)  FROM groups";
            ResultSet rs=stmt.executeQuery(query);

            connection.close();
        }catch (Exception e){
            Component.showErrorMessage(e.getMessage());
        }
    }

    public void  getGroupByName() throws Exception{
        try{
            Scanner sc=new Scanner(System.in);
            Connection connection= PostegresConfig.getConnection();
            Statement stmt=connection.createStatement();
            System.out.println("Enter the group name: ");
            String gName=sc.nextLine();
            String query="SELECT group_creator,group_description,created_at FROM groups WHERE group_name=gName";
            ResultSet rs=stmt.executeQuery(query);
        }catch (Exception  e){

            Component.showErrorMessage(e.getMessage());

        }
    }
}