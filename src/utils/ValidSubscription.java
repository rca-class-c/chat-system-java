package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidSubscription {

    public boolean isValid(String expired){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expirationDate = LocalDate.parse(expired, dateFormatter);
        LocalDate today = LocalDate.now();

        if(today.isAfter(expirationDate)){
            return true;
        }
        return false;
    }
}
