package server.services;

import server.repositories.ReportsManagementRepository;

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
   public List<List> getMessageReport(){
       return new ReportsManagementRepository().getReport("message:");
    }
    public List<List> getGroupReport(){
        return new ReportsManagementRepository().getReport("group:");
    }
    public List<List> getUserReport(){
        return new ReportsManagementRepository().getReport("user:");
    }
}