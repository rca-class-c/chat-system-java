package server.services;

import client.interfaces.MessageResponseDataFormat;
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

public class  MessagesService {
    private final MessagesRepository messagesRepository = new MessagesRepository();
    public List<Messages> viewDirectMessagesBetweenTwo(ChatBetweenTwo members) throws SQLException {
        return messagesRepository.getDirectMessagesBetweenTwo(members.getFirstUser(),members.getLastUser());
    }
    public List<Messages> getMessageReplies(int message_id) throws SQLException {
        return messagesRepository.getMessageReplies(message_id);
    }
    public List<GroupMessage> viewGroupMessages(int groupId) throws SQLException{
        return messagesRepository.getGroupMessages(groupId);
    }
    public Set<ResultSet> viewUserNotifications(int user_id) throws Exception {
        return messagesRepository.getNotifications(user_id);
    }
    public List<GroupMessage> viewUserNotis(int user_id) throws SQLException {
        return  messagesRepository.getNotis(user_id);
    }
    public List<DirectMessage> viewDirUserNotis(int user_id) throws SQLException {
        return  messagesRepository.getDirNotis(user_id);
    }

    public String viewGroupName(int id)throws SQLException{
        return messagesRepository.getGroupName(id);
    }
    public Messages getMessageWithID(int id)throws SQLException{
        return messagesRepository.getMessageID(id);
    }
    public boolean sendInGroup(GroupMessage messages) throws SQLException{
        return messagesRepository.sendGroupMessage(messages);
    }

    public Boolean sendDirectly(Messages messages) throws SQLException{
        return messagesRepository.sendDirectMessage(messages);
    }
    public Messages SendReply(Messages messages) throws SQLException{
        return messagesRepository.SendReply(messages);
    }
    public List<Messages> GetReplies(ChatBetweenTwo members) throws SQLException{
        return messagesRepository.GetReplies(members.getFirstUser(), members.getLastUser());
    }
    //HEAD
    public boolean DeleteReply(int id) throws SQLException{
        return messagesRepository.DeleteMessages(id,3);
    }

    public boolean DeleteMessage(MessageResponseDataFormat data) throws SQLException{
        return messagesRepository.DeleteMessages(data.getUser(),data.getMessage_id());
    }

    public Boolean EditMessage(MessageResponseDataFormat data) throws SQLException {
        return messagesRepository.updateMessage(data.getUser(), data.getMessage_id(), data.getContent());
    }
}
