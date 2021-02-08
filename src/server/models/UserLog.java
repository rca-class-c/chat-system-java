package server.models;
import  java.io.Serializable;

public  class UserLog  implements Serializable{
    private Integer id;
    private Integer user_id;
    private String  dateTimeLoggedIn;
    private String  Action;
    private String  DateTimeLoggedOut;
    private Integer TotalIn;
    private Integer TotalOut;

    public  UserLog(){}

    public UserLog(Integer id, Integer user_id, String dateTimeLoggedIn, String action, String  dateTimeLoggedOut, Integer totalIn, Integer totalOut){
        this.id=id;
        this.user_id=user_id;
        this.dateTimeLoggedIn=dateTimeLoggedIn;
        Action=action;
        DateTimeLoggedOut=dateTimeLoggedOut;
        TotalIn=totalIn;
        TotalOut=totalOut;
    }

    public  Integer getId(){
        return  id;
    }
    public  Integer getUser_id(){
        return  user_id;
    }
    public  String getDateTimeLoggedIn(){
        return  dateTimeLoggedIn;
    }
    public  String getAction(){
        return  Action;
    }
    public  String getDateTimeLoggedOut(){
        return  DateTimeLoggedOut;
    }
    public  Integer getTotalIn(){
        return  TotalIn;
    }
    public  Integer getTotalOut(){
        return  TotalOut;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setDateTimeLoggedIn(String dateTimeLoggedIn) {
        this.dateTimeLoggedIn = dateTimeLoggedIn;
    }

    public void setAction(String action) {
        Action = action;
    }

    public void setDateTimeLoggedOut(String dateTimeLoggedOut) {
        DateTimeLoggedOut = dateTimeLoggedOut;
    }

    public void setTotalIn(Integer totalIn) {
        TotalIn = totalIn;
    }

    public void setTotalOut(Integer totalOut) {
        TotalOut = totalOut;
    }
}
