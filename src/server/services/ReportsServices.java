package server.services;

import server.models.UserLog;
import server.repositories.ReportsManagementRepository;
import  server.repositories.userLogAction;

import java.util.List;

/**
 * service for all report which is stored in redis database
 *
 * @author damour
 * @version 0.0.1
 */
public class ReportsServices {
    /**
     * method to insert message report
     */
    public void insertMessageReport(){
        new ReportsManagementRepository().insertMessageReport();
    }

    /**
     * @author hortance
     * **/
    public  void addUserLog(UserLog userLog) throws Exception {
        new userLogAction().recordUserLogs(userLog);
    }

    /**
    * @author hortance
    * */
    public List getUserLogs() throws Exception {
        return new userLogAction().getAllUserLogs();
    }
    /**
     * method to insert message report with detail per user
     * @param userId
     */
   public void insertMessageDetails(int userId){
        new ReportsManagementRepository().insertMessageReport(userId);
   }

    /**
     * method to insert group registration report
     */
   public void insertGroupReport(){
       new ReportsManagementRepository().insertGroupReport();
   }

    /**
     * method to insert user registration report
     */
   public void insertUserReport(){
       new ReportsManagementRepository().insertUserReport();
   }

    /**
     * method that set the number of system visit
     * @return void
     */
    public  void  insertVisitsReport(){new ReportsManagementRepository().setNumberOfSystemVisit();}

    /**
     * method to get message statistics
     * @return List<list>
     */
   public List<List> getMessageReport(){
       return new ReportsManagementRepository().getReport("message:");
    }
    /**
     * method to get group statistics
     * @return List<list>
     */
    public List<List> getGroupReport(){
        return new ReportsManagementRepository().getReport("group:");
    }
    /**
     * method to get user statistics
     * @return List<list>
     */
    public List<List> getUserReport(){
        return new ReportsManagementRepository().getReport("user:");
    }
    /**
     * method to get visit statistics
     * @return List<list>
     */
    public List<List> getVisitReport(){
        return new ReportsManagementRepository().getReport("visits:");
    }


}
