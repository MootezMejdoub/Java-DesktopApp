/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.email.durgesh.Email;

/**
 *
 * @author eyaou
 */
public class Mail {
    
    
    public Mail() {
        
    }
    
    public  static void sendMail(String username,String gr)
    {
         try
        {

            System.out.println(gr);
        Email email= new Email("eyawarteni68@gmail.com","191JFT1082");
       email.setFrom("eyawarteni68@gmail.com", "verification code");
    email.setSubject("User Email Verification");
    email.setContent("Registered successfully.Please verify your account using this code: " +gr,"text/html");
    email.addRecipient(username);
    email.send();
     
    
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    
    }
    
    
}
