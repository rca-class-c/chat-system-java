package server.repositories;

import server.models.Messages;
import server.config.Config;
import utils.DirectMessage;
import utils.GroupMessage;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class MessagesRepository {


    //-------------------------------View Messages-----------------------------------------

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
    public List<Messages> getNotifications(int user_id)throws Exception{
		List<Messages>  notis = new ArrayList<>();
		Connection conn = Config.getConnection();
        Statement statement = conn.createStatement();
		ResultSet gr;
		gr = statement.executeQuery("select * from user_group where user_id="+user_id);
		ResultSet result = null;
		while(gr.next()) {
            result = statement.executeQuery("select * from messages where group_receiver = "+gr.getInt(1)+" and message_status='UNSEEN' and sender!="+user_id);
				//notis.add(grm);
                while (result.next()){

                    Integer id = result.getInt(1);
                    String content = result.getString(2);
                    Integer sender = result.getInt(3);
                    Integer user_receiver = result.getInt(4);
                    Integer group_receiver = result.getInt(4);
                    Integer original_message = result.getInt(6);
                    Date sent_at = result.getDate(7);
                    notis.add(new Messages(id,content,sender,user_receiver,group_receiver,original_message,sent_at));
                }
            }
		ResultSet rs;
		rs = statement.executeQuery("Select * from messages where user_receiver="+user_id+" and message_status='UNSEEN' and sender!="+user_id);
        while(rs.next()) {
            result = statement.executeQuery("select * from messages where group_receiver = "+gr.getInt(1)+" and message_status='UNSEEN' and sender!="+user_id);
            //notis.add(grm);
            while (result.next()){

                Integer id = result.getInt(1);
                String content = result.getString(2);
                Integer sender = result.getInt(3);
                Integer user_receiver = result.getInt(4);
                Integer group_receiver = result.getInt(4);
                Integer original_message = result.getInt(6);
                Date sent_at = result.getDate(7);
                notis.add(new Messages(id,content,sender,user_receiver,group_receiver,original_message,sent_at));
            }
        }
		//notis.add(rs);
		
		statement.close();
        conn.close();
		return notis;
	}


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
    public  Messages sendDirectMessage(Messages message) throws SQLException {
        String sql= "insert into messages(content,sender,user_receiver,sent_at) values (?,?,?,?)";
        Connection conn = Config.getConnection();
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setString(1, message.getContent());
        statement.setInt(2, message.getSender());
        statement.setInt(3, message.getUser_receiver() );
        statement.setDate(4, (java.sql.Date) message.getSent_at());
        boolean rowInsert= statement.executeUpdate()>1;
        statement.close();
        conn.close();
        if(rowInsert){
            return message;
        }
        return null;
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
