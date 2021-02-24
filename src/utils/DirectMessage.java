package utils;

import java.util.Date;

public class DirectMessage {
  Integer message_id;
  String content;
  Integer sender;
  Integer user_receiver;
  Integer original_message;
  Date sentAt;

  public DirectMessage(Integer message_id,String content, Integer sender, Integer user_receiver, Integer original_message, Date sentAt) {
    this.content = content;
    this.sender = sender;
    this.user_receiver = user_receiver;
    this.original_message = original_message;
    this.sentAt = sentAt;
    this.message_id = message_id;;
  }

  public Date getSentAt() {
    return sentAt;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getSender() {
    return sender;
  }

  public void setSender(Integer sender) {
    this.sender = sender;
  }

  public Integer getUser_receiver() {
    return user_receiver;
  }

  public void setUser_receiver(Integer user_receiver) {
    this.user_receiver = user_receiver;
  }

  public Integer getOriginal_message() {
    return original_message;
  }

  public void setOriginal_message(Integer original_message) {
    this.original_message = original_message;
  }
}
