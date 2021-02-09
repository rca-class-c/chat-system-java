package server.models;

import java.util.Date;

/**
 *
 * @description: this is a model that represents Messages entity or table
 * @author: Loraine Mukezwa Irakoze
 * @date: 29-1-2021
 *
 */

public class Messages {
    public Integer id;
    public String content;
    public Integer sender;
    public Integer user_receiver;
    public Integer group_receiver;
    public Integer original_message;
    public Date sent_at;

    //Message class constructor
    public Messages(String content, Integer sender, Integer user_receiver, Integer group_receiver, Integer original_message
    ){
        this.content = content;
        this.sender = sender;
        this.user_receiver = user_receiver;
        this.group_receiver = group_receiver;
        this.original_message = original_message;
    }

    public Messages(Integer id, String content, Integer sender, Integer user_receiver, Integer group_receiver, Integer original_message, Date sent_at) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.user_receiver = user_receiver;
        this.group_receiver = group_receiver;
        this.original_message = original_message;
        this.sent_at = sent_at;
    }

    //Setters and getters
    public int getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getContent(){ return content;}

    public void setContent(String content) { this.content = content; }

    public int getSender(){ return sender; }

    public void setSender(Integer sender) { this.sender = sender; }

    public int getUser_receiver(){ return user_receiver; }

    public void setUser_receiver(Integer user_receiver) { this.user_receiver = user_receiver; }

    public int getGroup_receiver(Integer group_receiver) { return group_receiver; }

    public void setGroup_receiver(Integer group_receiver) { this.group_receiver = group_receiver; }

    public int getOriginal_message(){ return original_message; }

    public void setOriginal_message(Integer original_message){ this.original_message = original_message; }

    public Date getSent_at(){ return sent_at; }

    public void setSent_at(Date sent_at) { this.sent_at = sent_at; }

    public Messages(String content, Integer sender, Integer user_receiver) {
        this.content = content;
        this.sender = sender;
        this.user_receiver = user_receiver;
    }
}
