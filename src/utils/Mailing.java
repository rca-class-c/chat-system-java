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


public class Mailing {
    private  String from;
    private String to;
    private String subject;
    private String content;
    private String port;
    private String host;
    private String filepath;
    /**
     * this is used to set the requirement to send email without attachment
     *
     * @param  from  the sender of the email
     * @param  to  the receiver of the email
     * @param  subject  subject of the email
     * @param  content  this is the body of the email
     * @return      void
     */
    public Mailing(String from, String to, String subject , String content){
        this.setFrom(from);
        this.setTo(to);
        this.setSubject(subject);
        this.setContent(content);
    }
    /**
     * this is used to set the requirement to send email without attachment
     *
     * @param  from  the sender of the email
     * @param  to  the receiver of the email
     * @param  subject  subject of the email
     * @param  content  this is the body of the email
     * @param  absolutePath  this is the body of the email
     * @return      void
     */
    public Mailing(String from, String to, String subject , String content, String absolutePath){
        this.setFrom(from);
        this.setTo(to);
        this.setSubject(subject);
        this.setContent(content);
        this.setFilepath(absolutePath);
    }
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

    /**
     * this is function tha will be used to send email ?? plain texts
     */
  public void sendMailText(String choice){
      // Getting system properties
      Properties properties = System.getProperties();

      // Setting up the mail server
      properties.setProperty("mail.smtp.host", host);
      properties.setProperty("mail.smtp.port", port);

      // Get default session object
      Session session = Session.getDefaultInstance(properties);

      switch(choice){
          case "text":
              System.out.println(this.sendText(session));
              break;
          case "html":
              System.out.println(this.sentHtml(session));
          case "attachment":
              System.out.println(this.sendAttachment(session));
              break;
          default:
              System.out.println("invalid choice");
      }

  }

    /**
     * Send the email with text only.
     */
   private  String sendText(Session session ){
       try
       {
           // Create MimeMessage object
           MimeMessage message = new MimeMessage(session);

           // Set the Senders mail to From
           message.setFrom(new InternetAddress(this.from));

           // Set the recipients email address
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));

           // Subject of the email
           message.setSubject(this.subject);

           // Body of the email
           message.setText(this.content);

           // Send email
           Transport.send(message);

       } catch (MessagingException me)
       {
           me.printStackTrace();
       }
       return "Mail sent successfully";
   }
    /**
     * Send the email with html content.
     */
   private String sentHtml(Session session){
       try {
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

           // Set Subject: header field
           message.setSubject(this.subject);

           // Send the actual HTML message, as big as you like
           message.setContent(this.content, "text/html");

           // Send message
           Transport.send(message);
       } catch (MessagingException mex) {
           mex.printStackTrace();
       }
       return "mail send successful ...";
   }
    /**
     * Send the email with content and even attachment.
     */
   private String sendAttachment(Session session){
       try {
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

           // Set Subject: header field
           message.setSubject(this.subject);

           // Create the message part
           BodyPart messageBodyPart = new MimeBodyPart();

           // Fill the message
           messageBodyPart.setText(this.content);

           // Create a multipar message
           Multipart multipart = new MimeMultipart();

           // Set text message part
           multipart.addBodyPart(messageBodyPart);

           // Part two is attachment
           messageBodyPart = new MimeBodyPart();
           String filename = this.filename(this.filepath);
           DataSource source = new FileDataSource(this.filepath);
           messageBodyPart.setDataHandler(new DataHandler(source));
           messageBodyPart.setFileName(filename);
           multipart.addBodyPart(messageBodyPart);

           // Send the complete message parts
           message.setContent(multipart );

           // Send message
           Transport.send(message);

       } catch (MessagingException mex) {
           mex.printStackTrace();
       }

       return  "Sent message successfully....";
   }
    /**
     * it is used to split file path given and return the last filename
     */
   private String filename(String filepath){
       String[] split = filepath.split("/");
       return split[split.length-1];
   }
}
