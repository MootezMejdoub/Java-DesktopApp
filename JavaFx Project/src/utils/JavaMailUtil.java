/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tox
 */
public class JavaMailUtil {
    public static void sendEmail(String recepient,String s) throws Exception
    {
        System.out.println("Prepare");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccountEmail="mootez.mejdoub@esprit.tn";
        String password="213JMT1330";
        Session session=Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(myAccountEmail,password);
                
            }
        });
        Message message = prepareMessage(session,myAccountEmail,recepient,s);
        Transport.send(message);
        System.out.println("Email envoyee avec succes");
        
        

    
    }
    

   
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient,String msg) {
        try {
            Message message=new MimeMessage(session);
        message.setFrom(new InternetAddress (myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress (recepient));
        message.setSubject("Reclamation♥");
        message.setText(msg);
        message.reply(false);
        return message;
            
        }
        catch(Exception e)
        {
           e.getMessage();
        }
        return null;
    }
    
}
