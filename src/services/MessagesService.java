package services;

import models.Messages;
import repositories.MessagesRepository;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class MessagesService {
    private final MessagesRepository messagesRepository = new MessagesRepository();

    public List<DirectMessage> viewDirectMessages(Messages messages) throws SQLException {
        return messagesRepository.getDirectMessages(messages);
    }

    public List<GroupMessage> viewGroupMessages(Messages messages) throws SQLException{
        return messagesRepository.getGroupMessages(messages);
    }
    public Set<ResultSet> viewUserNotifications(int user_id) throws Exception{
        return messagesRepository.getNotifications(user_id);

    public void sendInGroup(Messages messages) throws SQLException{
        messagesRepository.sendGroupMessage(messages);
    }

    public void sendDirectly(Messages messages) throws SQLException{
        messagesRepository.sendDirectMessage(messages);
    }
}
