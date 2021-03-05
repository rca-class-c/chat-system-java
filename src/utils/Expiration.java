package utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.sql.Date;


/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 *
 */

public class Expiration {
    public Date expirationCalculator(int period){
        int periodSec = 3600*24*period;
        long CurrentDate = System.currentTimeMillis();
        Timestamp subscribedAt = new Timestamp(CurrentDate);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(subscribedAt.getTime());
        cal.add(Calendar.SECOND, periodSec);
        Timestamp Expiration= new Timestamp(cal.getTime().getTime());
        Date ExpirationDate = new Date(Expiration.getTime());
        System.out.println(ExpirationDate);
        return ExpirationDate;
    }

    public static void main(String[] args) {
        Expiration exp = new Expiration();
        exp.expirationCalculator(30);
    }
}
