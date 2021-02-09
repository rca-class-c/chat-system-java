package server.services;

import server.models.Messages;
import server.repositories.MessagesRepository;
import utils.ChatBetweenTwo;
import utils.DirectMessage;
import utils.GroupMessage;

import java.sql.SQLException;
import java.util.List;

/**
 * User Services provider
 * @author: Bella Mellissa  Ineza
 */

public class MessagesService {
    private final MessagesRepository messagesRepository = new MessagesRepository();

    public List<DirectMessage> viewDirectMessages(ChatBetweenTwo members) throws SQLException {
        return messagesRepository.getDirectMessages(members.getFirstUser(), members.getLastUser());
    }
    public List<DirectMessage> viewDirectMessagesBetweenTwo(ChatBetweenTwo members) throws SQLException {
        return messagesRepository.getDirectMessagesBetweenTwo(members.getFirstUser(),members.getLastUser());
    }
    public List<GroupMessage> viewGroupMessages(ChatBetweenTwo members) throws SQLException{
        return messagesRepository.getGroupMessages(members.getFirstUser(), members.getLastUser());
    }
    public List<Messages> viewUserNotifications(int user_id) throws Exception {
        return messagesRepository.getNotifications(user_id);
    }
    public Messages sendInGroup(Messages messages) throws SQLException{
        return messagesRepository.sendGroupMessage(messages);
    }

    public Messages sendDirectly(Messages messages) throws SQLException{
        return messagesRepository.sendDirectMessage(messages);
    }
    public Messages ReplyInGroup(Messages messages) throws SQLException{
        return messagesRepository.ReplyDirectMessage(messages);
    }

    public Messages ReplyDirectly(Messages messages) throws SQLException{
        return messagesRepository.ReplyGroupMessage(messages);
    }

    public boolean DeleteReply(int id) throws SQLException{
        return messagesRepository.DeleteReplies(id);
    }

    public boolean DeleteMessage(int id) throws SQLException {
        return messagesRepository.DeleteMessages(id);
    }
}
