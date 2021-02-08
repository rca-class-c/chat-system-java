package server.services;

import server.models.Messages;
import server.repositories.MessagesRepository;
import utils.ChatBetweenTwo;
import utils.DirectMessage;
import utils.GroupMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * User Services provider
 * @author: Bella Mellissa  Ineza
 */

public class MessagesService {
    private final MessagesRepository messagesRepository = new MessagesRepository();

    public List<DirectMessage> viewDirectMessages(Messages messages) throws SQLException {
        return messagesRepository.getDirectMessages(messages);
    }
    public List<DirectMessage> viewDirectMessagesBetweenTwo(ChatBetweenTwo members) throws SQLException {
        return messagesRepository.getDirectMessagesBetweenTwo(members.getFirstUser(),members.getLastUser());
    }
    public List<GroupMessage> viewGroupMessages(Messages messages) throws SQLException{
        return messagesRepository.getGroupMessages(messages);
    }
    public Set<ResultSet> viewUserNotifications(int user_id) throws Exception {
        return messagesRepository.getNotifications(user_id);
    }
    public void sendInGroup(Messages messages) throws SQLException{
        messagesRepository.sendGroupMessage(messages);
    }

    public void sendDirectly(Messages messages) throws SQLException{
        messagesRepository.sendDirectMessage(messages);
    }
    public void ReplyInGroup(Messages messages) throws SQLException{
        messagesRepository.ReplyDirectMessage(messages);
    }

    public void ReplyDirectly(Messages messages) throws SQLException{
        messagesRepository.ReplyGroupMessage(messages);
    }
}
