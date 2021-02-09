package utils;

import java.util.Random;

public class OTP {
// OTP GENERATOR
   public static char[] OTP(int len)
    {
        // Using numeric values
        String numbers = "0123456789";
        // Using random method
        Random rand_method = new Random();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++)
        {
            otp[i] =
                    numbers.charAt(rand_method.nextInt(numbers.length()));
        }
        return otp;
    }

}
