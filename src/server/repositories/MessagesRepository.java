package server.repositories;

import client.views.components.Component;
import server.models.Messages;
import server.config.PostegresConfig;
import server.models.User;
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

        Connection conn = PostegresConfig.getConnection();
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
    public List<GroupMessage> getGroupMessages(int groupid) throws SQLException {
        List<GroupMessage> allMessagesGrp = new ArrayList<GroupMessage>();

        Connection conn = PostegresConfig.getConnection();
        Statement statement = conn.createStatement();

        String readQuery = String.format(
                "SELECT * from messages where group_receiver = %d",groupid);

        ResultSet result = statement.executeQuery(readQuery);

        while (result.next()){

            Integer id = result.getInt(1);
            String content = result.getString(2);
            Integer sender = result.getInt(3);
            Integer group_receiver = result.getInt(4);
            Integer original_message = result.getInt(6);
            Date sent_at = result.getDate(7);
            GroupMessage message = new GroupMessage(content,sender,group_receiver,original_message,sent_at,id);
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

        Connection conn = PostegresConfig.getConnection();
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
        Connection conn = PostegresConfig.getConnection();
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
        statement.close();
        conn.close();
        return notis;
    }



    public List<GroupMessage> getNotis(int user_id)throws SQLException{
        List<GroupMessage>  notis = new ArrayList<GroupMessage>();
        Connection conn = PostegresConfig.getConnection();
        Statement statement = conn.createStatement();
        ResultSet groups;
        groups = statement.executeQuery("select * from user_group where user_id="+user_id);
        ResultSet group_message = null;
        while(groups.next()) {
//            group_message = statement.executeQuery("Select * from messages where user_receiver="+user_id+" and message_status='UNSEEN' and sender!="+user_id);
            group_message = statement.executeQuery("select username, messages.* from users join messages on user_id = sender where group_receiver = "+groups.getInt(1)+" and message_status= 'UNSEEN' and sender!="+user_id);
            while(group_message.next()){
                Integer id = group_message.getInt(2);
                String content = group_message.getString(3);
                Integer sender = group_message.getInt(4);
                Integer group_receiver = group_message.getInt(5);
                Integer original_message = group_message.getInt(7);
                Date sent_at = group_message.getDate(8);
                notis.add(new GroupMessage(content,sender,group_receiver,original_message,sent_at,id));
            }
//				grm = statement.executeQuery("select * from messages where group_receiver = "+gr.getInt(1)+" and isRead=false and sender!="+user_id);

        }
//        ResultSet direct_message;
//        direct_message = statement.executeQuery("select username, messages.* from users join messages on user_id = sender where message_status='UNSEEN' and user_receiver="+ user_id);
//        notis.add(direct_message);
        statement.close();
        conn.close();
        return notis;
    }

    public List<DirectMessage> getDirNotis(int user_id) throws SQLException {
        List<DirectMessage>  notis = new ArrayList<DirectMessage>();
        Connection conn = PostegresConfig.getConnection();
        Statement statement = conn.createStatement();
        ResultSet direct_message;
        direct_message = statement.executeQuery("select username, messages.* from users join messages on user_id = sender where message_status='UNSEEN' and user_receiver="+ user_id);
        while(direct_message.next()){
            Integer message_id = direct_message.getInt(2);
            String content = direct_message.getString(3);
            Integer sender = direct_message.getInt(4);
            Integer user_receiver = direct_message.getInt(5);
            Integer original_message = direct_message.getInt(7);
            Date sent_at = direct_message.getDate(8);
            notis.add(new DirectMessage(message_id, content, sender, user_receiver, original_message, sent_at));
        }

        statement.close();
        conn.close();
        return notis;
    }



    public String getGroupName(int id) throws  SQLException{
        Connection conn = PostegresConfig.getConnection();
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
    //author: Edine Noella
    public  boolean sendGroupMessage(GroupMessage message) throws SQLException {
        String sql= "insert into messages(content,sender,group_receiver) values (?,?,?)";
        Connection conn = PostegresConfig.getConnection();
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setString(1, message.getContent());
        statement.setInt(2, message.getSender());
        statement.setInt(3, message.getGroup_receiver() );
        boolean rowInsert= statement.executeUpdate()>0;
        statement.close();
        conn.close();
        return rowInsert;
    }

    public List<Messages> getDirectMessagesBetweenTwo(int first,int last) throws SQLException {
        Connection conn = PostegresConfig.getConnection();
        Statement statement = conn.createStatement();
        List <Messages> messages = new ArrayList<Messages>();

        String readQuery = String.format(
                "SELECT * from messages where sender = %d and user_receiver = %d or sender = %d and user_receiver = %d;",
                first, last,first, last);

        ResultSet result = statement.executeQuery(readQuery);

        while (result.next()){

            Integer id = result.getInt(1);
            String content = result.getString(2);
            Integer sender = result.getInt(3);
            Integer user_receiver = result.getInt(4);
            Integer group_receiver = result.getInt(5);
            Integer original_message = result.getInt(6);
            java.sql.Date date = result.getDate(7);
            Messages messages1 = new Messages(id,content,sender,user_receiver,group_receiver,original_message,date);
                messages.add(messages1);
        }
        String query = String.format("UPDATE messages SET message_status ='SEEN' WHERE user_receiver = %d",first);


        int k = statement.executeUpdate(query);
        statement.close();
        conn.close();
        return messages;
    }
    public List<Messages> GetReplies(int userId,int second) throws SQLException {
        Connection conn = PostegresConfig.getConnection();
        Statement statement = conn.createStatement();
        List <Messages> messages = new ArrayList<>();

        String readQuery = String.format(
                "SELECT * from messages where sender = %d and user_receiver = %d or sender = %d and user_receiver = %d;",
                userId, second,userId, second);

        ResultSet result = statement.executeQuery(readQuery);

        while (result.next()){

            Integer id = result.getInt(1);
            String content = result.getString(2);
            Integer sender = result.getInt(3);
            Integer user_receiver = result.getInt(4);
            Integer group_receiver = result.getInt(5);
            Integer original_message = result.getInt(6);
            Messages messages1 = new Messages(id,content,sender,user_receiver,group_receiver,original_message);
            if(messages1.getOriginal_message() != 0){
                messages.add(messages1);
            }
        }
        String query = String.format("UPDATE messages SET message_status ='SEEN' WHERE user_receiver = %d",userId);


        int k = statement.executeUpdate(query);
        statement.close();
        conn.close();
        return messages;
    }
    /**
     * Method for getting all user by id
     * */
    public Messages getMessageID(int messageID) throws SQLException{
        try{
            Connection connection = PostegresConfig.getConnection();


            String query = String.format("SELECT * FROM messages  WHERE id = '%d' ;",messageID);
            Statement statement =  connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if(rs.next()){

                Messages returnUser =  new Messages(rs.getInt("id"),rs.getString("content"),rs.getInt("sender"),rs.getInt("user_receiver"), rs.getInt("group_receiver"),rs.getInt("original_message"));
                return returnUser;
            }
            else{

            }
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }

    //sending a direct message
   //author: Edine Noella
    public  Boolean sendDirectMessage(Messages message) throws SQLException {
        String sql= "insert into messages(content,sender,user_receiver) values (?,?,?)";
        Connection conn = PostegresConfig.getConnection();
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

    public Messages SendReply(Messages message) throws SQLException {
        String sql= "insert into messages(content,sender,original_message,user_receiver) values (?,?,?,?)";
        Connection conn = PostegresConfig.getConnection();
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setString(1, message.getContent());
        statement.setInt(2, message.getSender());
        statement.setInt(3, message.getOriginal_message());
        statement.setInt(4, message.getUser_receiver());
        boolean rowInsert= statement.executeUpdate()>0;
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
        Connection connection = PostegresConfig.getConnection();
        try{
        Statement statement = connection.createStatement();
        String query = String.format("DELETE FROM messages WHERE id = '%d' and sender = '%d' ;",message_id,userid);
            System.out.println(query);
        affectedRows = statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {

            Component.showErrorMessage(e.getMessage());

        }
        if(affectedRows > 0) {
            return true;
        }
        return false;

    }
}
