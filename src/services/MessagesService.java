package services;

import models.Messages;
import repositories.MessagesRepository;
import utils.*;

import java.sql.SQLException;
import java.util.List;

public class MessagesService {
    private final MessagesRepository messagesRepository = new MessagesRepository();

    public List<DirectMessage> viewDirectMessages(Messages messages) throws SQLException {
        return messagesRepository.getDirectMessages(messages);
    }

    public List<GroupMessage> viewGroupMessages(Messages messages) throws SQLException{
        return messagesRepository.getGroupMessages(messages);
    }

    public void sendInGroup(Messages messages) throws SQLException{
        messagesRepository.sendGroupMessage(messages);
    }

    public void sendDirectly(Messages messages) throws SQLException{
        messagesRepository.sendDirectMessage(messages);
    }
}
