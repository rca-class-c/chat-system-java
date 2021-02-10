package  server.repositories;
import  server.config.Config;
import  server.models.UserLog;
import  server.models.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
/**
 * @author hortance
 * **/
public class userLogAction {
    String getAllUserLogsQuery="SELECT * FROM user_logs";
    public  List<Object> getAllUserLogs() throws Exception{
        Connection connection= Config.getConnection();
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery(getAllUserLogsQuery);
        List<Object> userLogList=new ArrayList<>();
        while (rs.next()){
            UserLog userLog=new UserLog();
            userLog.setId(rs.getInt("id"));
            userLog.setUser_id(rs.getInt("user_id"));
            userLog.setDateTimeLoggedIn(rs.getString("date_time_logged_in"));
            userLog.setAction(rs.getString("action"));
            userLog.setDateTimeLoggedOut(rs.getString("date_time_logged_out"));
            userLog.setTotalIn(rs.getInt("Total_in"));
            userLog.setTotalOut(rs.getInt("Total_out"));
            userLogList.add(userLog);
        }
        return  userLogList;
    }
    public  static  String dateParser(){
        TimeZone tz=TimeZone.getTimeZone("UTC");
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        df.setTimeZone(tz);

        return df.format((new Date()));
    }

    public  Response recordUserLogs(UserLog userLog)  throws  Exception{
        String recordUserLogsQuery="INSERT into user_logs(user_id, date_Time_logged_In,Action, date_Time_logged_Out,Total_In, Total_out) values (?, ?, ?, ?, ?, ?)";
        String getPreviousRowQuery="SELECT Total_in,Total_out FROM user_logs ORDER by id DESC LIMIT 1";
        Connection connection= Config.getConnection();
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery(getPreviousRowQuery);
        while (rs.next()){
            if(userLog.getAction().equals("logIn")){
                System.out.println("Successful login");
                int currentTotalIn=rs.getInt("Total_in");
                int currentTotalOut=rs.getInt("Total_out");
                userLog.setTotalIn(currentTotalIn+1);
                userLog.setTotalOut(currentTotalOut);
            }else if(userLog.getAction().equals("logoUt")){
                int currentTotalIn = rs.getInt("Total_in");
                int currentTotalOut = rs.getInt("Total_out");
                System.out.println("date: "+dateParser());
                userLog.setDateTimeLoggedOut(dateParser());
                userLog.setTotalIn(currentTotalIn-1);
                userLog.setTotalOut(currentTotalOut+1);
            }else{
                userLog.setTotalIn(rs.getInt("Total_in"));
                userLog.setTotalOut(rs.getInt("Total_out"));
            }
        }
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(recordUserLogsQuery);
            preparedStatement.setInt(1,userLog.getUser_id());
            preparedStatement.setString(2,userLog.getDateTimeLoggedIn());
            preparedStatement.setString(3,userLog.getAction());
            preparedStatement.setString(4,userLog.getDateTimeLoggedOut());
            preparedStatement.setInt(5,userLog.getTotalIn());
            preparedStatement.setInt(6,userLog.getTotalOut());

            int inserted =preparedStatement.executeUpdate();

            if (inserted == 1){
                return new Response(200,"USER LOG ADDED","You have inserted the user log successfully");
            }

        }
        catch (Exception e){
            return new Response(400,"BAD REQUEST",e.getMessage());
        }

        return null;

    }
    }
