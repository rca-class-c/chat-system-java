package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidEmail {
    String email;
    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    Pattern pattern;

    public ValidEmail(String email) {
        this.pattern = Pattern.compile(regex);
        this.email = email;
    }

    public boolean checkEmail(){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
