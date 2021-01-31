package controllers;

import models.Messages;
import services.MessagesService;
import utils.*;

import java.sql.SQLException;
import java.util.List;

public class Message {
    private final MessagesService messagesService = new MessagesService();

    public List<DirectMessage> displayDirectMessages(Messages messages) throws SQLException {
        return this.messagesService.viewDirectMessages(messages);
    }

    public List<GroupMessage> displayGroupMessages(Messages messages) throws SQLException {
        return this.messagesService.viewGroupMessages(messages);
    }

}