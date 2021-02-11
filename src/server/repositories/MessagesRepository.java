package server.repositories;

import server.models.Messages;
import server.config.Config;
import utils.DirectMessage;
import utils.GroupMessage;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class MessagesRepository {


    //-------------------------------View Direct Messages-----------------------------------------
    // author : Loraine
    public List<DirectMessage> getDirectMessages(int first, int last) throws SQLException {
        List<DirectMessage> allMessagesDM = new ArrayList<DirectMessage>();

        Connection conn = Config.getConnection();
        Statement statement = conn.createStatement();

        String readQuery = String.format(
                "SELECT * from messages where sender = %d && user_receiver = %d or sender = %d && user_receiver = %d;",
                first, last, first, last
        );

        ResultSet result = statement.executeQuery(readQuery);

        while (result.next()){

            Integer id = result.getInt(1);
            String content = result.getString(2);
            Integer sender = result.getInt(3);
            Integer user_receiver = result.getInt(4);
            Integer original_message = result.getInt(6);
            Date sent_at = result.getDate(7);

            DirectMessage message = (DirectMessage) result;
            allMessagesDM.add(message);
        }
        statement.close();
        conn.close();
        return allMessagesDM;
    }
    //-------------------------------------------------------------------------------

    //-------------------------------View Group Messages-----------------------------------------
    // author : Loraine
    public List<GroupMessage> getGroupMessages(int first, int last) throws SQLException {
        List<GroupMessage> allMessagesGrp = new ArrayList<GroupMessage>();

        Connection conn = Config.getConnection();
        Statement statement = conn.createStatement();

        String readQuery = String.format(
                "SELECT * from messages where sender = %d && group_receiver = %d or sender = %d && group_receiver = %d;",
                first, last, first, last);

        ResultSet result = statement.executeQuery(readQuery);

        while (result.next()){

            Integer id = result.getInt(1);
            String content = result.getString(2);
            Integer sender = result.getInt(3);
            Integer group_receiver = result.getInt(4);
            Integer original_message = result.getInt(6);
            Date sent_at = result.getDate(7);

            GroupMessage message = (GroupMessage) result;
            allMessagesGrp.add(message);
        }
        statement.close();
        conn.close();
        return allMessagesGrp;
    }
    //-------------------------------------------------------------------------------

    //-------------------------------Edit Direct Messages-----------------------------------------
    // author : Loraine
    public Messages updateMessage (Messages message) throws Exception{

        Connection conn = Config.getConnection();
        String query = String.format("UPDATE messages SET content = ? WHERE id = ?;");
        PreparedStatement statement =  conn.prepareStatement(query);

        statement.setString(1, message.getContent());
        statement.setInt(2, message.getId());

        boolean rowUpdated = statement.executeUpdate() > 1;
        statement.close();
        conn.close();
        if(rowUpdated){
            return message;
        }
        return null;
    }

    //-------------------------------------Getting Notifications------------------------------------------
    //author : Souvede & Chanelle

    public Set<ResultSet> getNotifications(int user_id)throws SQLException{
        Set<ResultSet>  notis = new HashSet<>();
        Connection conn = Config.getConnection();
        Statement statement = conn.createStatement();
        ResultSet groups;
        groups = statement.executeQuery("select * from user_group where user_id="+user_id);
        ResultSet group_message = null;
        while(groups.next()) {
//            group_message = statement.executeQuery("Select * from messages where user_receiver="+user_id+" and message_status='UNSEEN' and sender!="+user_id);
            group_message = statement.executeQuery("select username, messages.* from users join messages on user_id = sender where group_receiver = "+groups.getInt(1)+" and message_status= 'UNSEEN' and sender!="+user_id);
//				grm = statement.executeQuery("select * from messages where group_receiver = "+gr.getInt(1)+" and isRead=false and sender!="+user_id);
            notis.add(group_message);
        }
        ResultSet direct_message;
        direct_message = statement.executeQuery("select username, messages.* from users join messages on user_id = sender where message_status='UNSEEN' and user_receiver="+ user_id);
        notis.add(direct_message);
        return notis;
    }
    public String getGroupName(int id) throws  SQLException{
        Connection conn = Config.getConnection();
        Statement statement = conn.createStatement();
        ResultSet grn;
        grn = statement.executeQuery("select group_name from groups where group_id="+id);
        String gr_name;
        grn.next();
        gr_name = grn.getString(1);
        return  gr_name;
    }
    //                    Statement statement = connect.createStatement();
//                    ResultSet grn;
//                    grn = statement.executeQuery("select group_name from groups where group_id="+g_rec);
//                    grn.next();
//                    String gr_name = grn.getString(1);
    //-------------------------------sending messages--------------------------
    //sending group message
    public  Messages sendGroupMessage(Messages message) throws SQLException {
        String sql= "insert into messages(content,sender,group_receiver,sent_at) values (?,?,?,?)";
        Connection conn = Config.getConnection();
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setString(1, message.getContent());
        statement.setInt(2, message.getSender());
       // statement.setInt(3, message.getGroup_receiver());
        statement.setDate(4, (java.sql.Date) message.getSent_at());
        boolean rowInsert= statement.executeUpdate()>1;
        statement.close();
        conn.close();
        if(rowInsert){
            return message;
        }
        return null;
    }

    public List<DirectMessage> getDirectMessagesBetweenTwo(int first,int last) throws SQLException {
        Connection conn = Config.getConnection();
        Statement statement = conn.createStatement();
        List <DirectMessage> messages = new ArrayList<DirectMessage>();

        String readQuery = String.format(
                "SELECT * from messages where sender = %d and user_receiver = %d or sender = %d and user_receiver = %d;",
                first, last,first, last);

        ResultSet result = statement.executeQuery(readQuery);

        while (result.next()){

            Integer id = result.getInt(1);
            String content = result.getString(2);
            Integer sender = result.getInt(3);
            Integer user_receiver = result.getInt(4);
            Integer original_message = result.getInt(6);
            Date sent_at = result.getDate(7);
            messages.add(new DirectMessage(id,content,sender,user_receiver,original_message));
        }
        statement.close();
        conn.close();
        return messages;
    }
   //sending a direct message
    public  Boolean sendDirectMessage(Messages message) throws SQLException {
        String sql= "insert into messages(content,sender,user_receiver) values (?,?,?)";
        Connection conn = Config.getConnection();
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setString(1, message.getContent());
        statement.setInt(2, message.getSender());
        statement.setInt(3, message.getUser_receiver() );
        boolean rowInsert= statement.executeUpdate()>0;
        statement.close();
        conn.close();
        return rowInsert;
    }
    //----------------------------Reply direct messages-----------------------------
    // author : Melissa

    public Messages ReplyDirectMessage(Messages message) throws SQLException {
        String sql= "insert into messages(content,sender,group_receiver,original_message,sent_at) values (?,?,?,?)";
        Connection conn = Config.getConnection();
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setString(1, message.getContent());
        statement.setInt(2, message.getSender());
        statement.setInt(3, message.getGroup_receiver(null));
        statement.setDate(4, (java.sql.Date) message.getSent_at());
        boolean rowInsert= statement.executeUpdate()>1;
        statement.close();
        conn.close();
        if(rowInsert){
            return message;
        }
        return null;
    }



    //----------------------------Reply group messages--------------------------------
    // author : Melissa


    public Messages ReplyGroupMessage(Messages message) throws SQLException {
        String sql= "insert into messages(content,sender,group_receiver,original_message,sent_at) values (?,?,?,?)";
        Connection conn = Config.getConnection();
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setString(1, message.getContent());
        statement.setInt(2, message.getSender());
        statement.setInt(3, message.getGroup_receiver(null));
        statement.setDate(4, (java.sql.Date) message.getSent_at());
        boolean rowInsert= statement.executeUpdate()>1;
        statement.close();
        conn.close();
        if(rowInsert){
            return message;
        }
        return null;
    }

    //Deleting a message


    public boolean DeleteMessages(int userid,int message_id) throws SQLException {
        int affectedRows = 0;

        Connection connection = Config.getConnection();
        String query = String.format("DELETE FROM messages WHERE id = ? and sender = ? ;");
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userid);
        statement.setInt(1, message_id);
        if (affectedRows > 0) {
            return  true;
        }
        return false;

    }
}
