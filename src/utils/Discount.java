package utils;
import  server.models.Payment;
public class Discount {
    public  Payment TotalAmount(float discount,float totalAmount){
       float AmountAfterDiscount=totalAmount-(totalAmount*(discount/100));
       Payment payment=new Payment();
       payment.setTotalAmount(AmountAfterDiscount);
       payment.setDiscount(discount);
       return payment;
    }

}
