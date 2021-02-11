/**
 * Mailing is the class that will be used to send email
 * which allow an application to send email to any client
 * A Mailing object get the required information
 * for the various rendering operations that Java supports.  This
 * state information includes:
 * <ul>
 * <li> where email from
 * <li>how is the receiver of email
 * <li>The subject of the email
 * <li>The content of the email whether text all html content
 * <li>The file or attachment the sender want to sent
 *
 * </ul>
 * <p>
 *
 * @author      tuyishime jeandamour
 * @version     1, 0
 */
package utils;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

//construction
public class Mailing {
    private  String from;
    private String to;
    private String subject;
    private String content;
    private String port = "8080";
    private String host ="localhost";
    private String filepath;
    private String password ;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * this is used to set the requirement to send email without attachment
     * <h3 color="red">remember  to enable   this properties of (sender)email inorder to send email from untrusted</h5>
     *  <ul>
     *    <li><a href="https://myaccount.google.com/lesssecureapps">click to allow your sending email from non google app</a></li>
     *    <li><a href="https://accounts.google.com/DisplayUnlockCaptcha">click to allow to access your google account</a></li>
     *  * </ul>
     * @param  from  the sender of the email
     * @param  to  the receiver of the email
     * @param  subject  subject of the email
     * @param  content  this is the body of the email
     *
     */
    public Mailing(String from ,String password, String to, String subject , String content){
        this.setFrom(from);
        this.setTo(to);
        this.setSubject(subject);
        this.setContent(content);
        this.setPassword(password);
    }

    /**
     * these are setter and getter of instance variable
     *
     */
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //end of getter and setter functions

    /**
     * methods used to send text email
     * @option param  is  text|html
     */

    public void send(){
         sendImplement("text");
     }

    /**
     * methods used to send  whether message or html content to email
     * @param choice  text|html
     * @option () for sending text email
     */
     public  void send(String choice){
        sendImplement(choice);
     }

    /**
     * this is function used to send email to gmail account
     */
   public void sendImplement( String choice){
       Properties props = new Properties();
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.socketFactory.port", "465");
       props.put("mail.smtp.socketFactory.class",
               "javax.net.ssl.SSLSocketFactory");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.port", "465");
       //get Session
       Session session = Session.getDefaultInstance(props,
               new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(from,password);
                   }
               });
       //compose message
       if (choice.equals("text")){
           this.sendtext(session);
       }else if (choice.equals("html")){
           this.sendHtml(session);
       }

   }

    /**
     * method use to send text email
     * @param session
     */
   public void   sendtext(Session session){
       try {
           MimeMessage message = new MimeMessage(session);
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           message.setSubject(this.subject);
           message.setText(this.content);
           //send message
           Transport.send(message);
           System.out.println("message sent successfully");
       } catch (MessagingException e) {throw new RuntimeException(e);}
   }

    /**
     * method use to send html content in email
     * @param session
     */
   public void  sendHtml(Session session){
       try {
           MimeMessage message = new MimeMessage(session);
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           message.setSubject(this.subject);
           message.setContent(this.content, "text/html");
           //send message
           Transport.send(message);
           System.out.println("message sent successfully");
       } catch (MessagingException e) {throw new RuntimeException(e);}
   }
}
