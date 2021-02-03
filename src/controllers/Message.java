package controllers;

import models.Messages;
import repositories.MessagesRepository;
import services.MessagesService;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class Message {
    private final MessagesService messagesService = new MessagesService();

    public List<DirectMessage> displayDirectMessages(Messages messages) throws SQLException {
        return this.messagesService.viewDirectMessages(messages);
    }

    public List<GroupMessage> displayGroupMessages(Messages messages) throws SQLException {
        return this.messagesService.viewGroupMessages(messages);
    }
    public Set<ResultSet> displayUserNotifications(int user_id) throws Exception{
        return messagesService.viewUserNotifications(user_id);
    }

//    public void sendInGroup(Messages messages) throws SQLException{
//        messagesService.sendInGroup(messages);
//    }
//
//    public void sendDirectly(Messages messages) throws SQLException{
//        messagesService.sendDirectly(messages);
//    }

}