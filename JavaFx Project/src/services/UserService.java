/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Client;
import entities.CryptWithMD5;
import entities.Guide;
import entities.Utilisateur;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


import utils.MyDB;

/**
 *
 * @author macbook
 */
public class UserService implements IUser {

    Connection connexion;
    Statement stm;

    public UserService() {
        connexion = MyDB.getInstance().getConnexion();
    }

   
   

    public void ajouterGuide(Guide p)  {
        try {
            String req = "INSERT INTO `utilisateur` (`nom`, `prenom`,`date_naissance`,`adresse`, `num_tel`, `email`,`mdp`, `type`,`description`) "
                    + "VALUES ( ?, ?,?, ?,?, ?,?, ?,?) ";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(4, p.getAdresse());
            ps.setDate(3,  p.getDate_naissance());
            ps.setString(5, p.getNum_tel());
            ps.setString(6, p.getEmail());
            ps.setString(7, p.getMdp());
            ps.setString(8, p.getType());
            ps.setString(9, p.getDescription());
            
            
            ps.executeUpdate();
            System.out.println("aaaaaaaaa");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> readAllLogins() throws SQLException {
        List<String> users = new ArrayList<>();
        String req = "select * from utilisateur  ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        
            while (rst.next()) {
            String logins = "" + rst.getString("email");
            users.add(logins);
        }
        
        return users;
    }
    
    public List<String> readAllTels() throws SQLException {
        List<String> users = new ArrayList<>();
        String req = "select * from utilisateur  ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        
            while (rst.next()) {
            String num_tel = "" + rst.getString("num_tel");
            users.add(num_tel);
        }
        
        return users;
    }

    public List<Guide> afficherGuide() throws SQLException {
        List<Guide> users = new ArrayList<>();
        String req = "select * from utilisateur where type='guide' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Guide p = new Guide(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"),
                     rst.getDate("date_naissance"),
                    rst.getString("adresse"),
                    rst.getString("num_tel"),
                    rst.getString("email"),
                     rst.getString("mdp"),
                    rst.getString("type"),
                    rst.getString("description"),
                    rst.getInt("evaluation")
                    
                    
            
            
            );
            users.add(p);
        }
        return users;
    }
public void modifierGuidePst(Guide u) throws SQLException {
        String req = "update utilisateur set nom = ? , prenom = ? , date_naissance= ? ,adresse= ? ,num_tel= ? ,email= ? ,mdp= ? ,type= ?,description= ? ,evaluation= ?  where id = ?";

       
                    PreparedStatement pst = connexion.prepareStatement(req);

           
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setDate(3, u.getDate_naissance());
            pst.setString(4, u.getAdresse());
            pst.setString(5, u.getNum_tel());
            pst.setString(6, u.getEmail());
            pst.setString(7, u.getMdp());
            pst.setString(8, u.getType());
            pst.setString(9, u.getDescription());
            pst.setInt(10, u.getEvaluation());
            pst.setInt(11, u.getId());
            
            
            pst.executeUpdate();

        

}
 public void suppGuidePst(Guide u) throws SQLException {
        String req = "delete from utilisateur where id = ?";

    PreparedStatement pst = connexion.prepareStatement(req);

           
            pst.setInt(1, u.getId());
            pst.executeUpdate();

       
 }
 


    @Override
    public void ajouterClient(Client p)  {
        String req = "INSERT INTO `utilisateur` (`nom`, `prenom`,`date_naissance`,`adresse`, `num_tel`, `email`,`mdp`, `type`) "
                    + "VALUES ( ?, ?,?, ?,?, ?,?, ?) ";
            
        try {
            
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(4, p.getAdresse());
            ps.setDate(3,  p.getDate_naissance());
            ps.setString(5, p.getNum_tel());
            ps.setString(6, p.getEmail());
            ps.setString(7, p.getMdp());
            ps.setString(8, p.getType());
           
            
            ps.executeUpdate();
            System.out.println("client ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Client> afficherClient()  {
        
            List<Client> users = new ArrayList<>();
            String req = "select * from utilisateur where type='simpleUser' ";
            try {
            stm = connexion.createStatement();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
                Client p = new Client(rst.getInt("id"),//or rst.getInt(1)
                        rst.getString("nom"),
                        rst.getString("prenom"),
                        rst.getDate("date_naissance"),
                        rst.getString("adresse"),
                        rst.getString("num_tel"),
                        rst.getString("email"),
                        rst.getString("mdp"),
                        rst.getString("type")
                        
                        
                        
                        
                        
                );
                users.add(p);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
             return users;
    }

    @Override
    public void modifierClientPst(Client u)  {
        
            String req = "update utilisateur set nom = ? , prenom = ? , date_naissance= ? ,adresse= ? ,num_tel= ? ,email= ? ,mdp= ? ,type= ?  where id = ?";
            
            try {
            PreparedStatement pst = connexion.prepareStatement(req);
            
            
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setDate(3, u.getDate_naissance());
            pst.setString(4, u.getAdresse());
            pst.setString(5, u.getNum_tel());
            pst.setString(6, u.getEmail());
            pst.setString(7, u.getMdp());
            pst.setString(8, u.getType());
             
           
            pst.setInt(9, u.getId());
                System.out.println("modification effectuée");
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    @Override
    public void suppClientPst(Client u)  {
        
            String req = "delete from utilisateur where id = ?";
            try {
            PreparedStatement pst = connexion.prepareStatement(req);
            
            
            pst.setInt(1, u.getId());
            pst.executeUpdate();
                System.out.println("suppression effectuée");
                
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override 
   /* public boolean login(String username,String password) {
        boolean reponse = false;
String req = "select * from utilisateur where nom ='" +username+"' and mdp='"+password+"' ";
        try {
            stm = connexion.createStatement();
             ResultSet rst = stm.executeQuery(req);
             if(rst.next())
             {
                 
                 reponse=true;
                 
             }
             else 
             {
                 reponse=false;
             }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponse;

    }*/
    
    
  /* public boolean login(String username,String password) {
        boolean reponse = false;
List<String> users = new ArrayList<>();
users=afficherMdp(username);
String res=decrypt(users.get(0));
            System.out.println(res);
            
           String req = "select * from utilisateur where  mdp='"+res+"' and email ='" +username+"' ";
        try {
            stm = connexion.createStatement();
            
            
            
           ResultSet rst = stm.executeQuery(req);
            System.out.println(rst);
       
        

       
             if(rst.next())
             {
                 
                 reponse=true;
                 
             }
             else 
             {
                 reponse=false;
             }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponse;

    }*/
    
    public boolean login(String username,String password) {
        boolean reponse = false;
        CryptWithMD5 crypt=new CryptWithMD5();

String res= crypt.cryptWithMD5(password);
            
           String req = "select * from utilisateur where  mdp='"+res+"' and email ='" +username+"' ";
        try {
            stm = connexion.createStatement();
            
            
            
           ResultSet rst = stm.executeQuery(req);
            //System.out.println(rst);
       
        

       
             if(rst.next())
             {
                 
                 reponse=true;
                 
             }
             else 
             {
                 reponse=false;
             }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponse;

    }
    
    public List<String> afficherMdp(String username) 
    {
        
            List<String> users = new ArrayList<>();
            String req = "select mdp from utilisateur where email='" +username+"' ";
            try {
            stm = connexion.createStatement();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
                String p = new String(rst.getString("mdp"));
                
                
                
                
               
                
                users.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
return users;
    }

   public String encrypt(String password){
        String crypte="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            crypte=crypte+(char)c;
        }
        return crypte;
    }
   
      public String decrypt(String password){
        String aCrypter="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            aCrypter=aCrypter+(char)c;
        }
        return aCrypter;
    }

    @Override
    public List<Client>  afficherTout() {
 List<Client> users = new ArrayList<>();
            String req = "select * from utilisateur  ";
            try {
            stm = connexion.createStatement();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
                Client p = new Client(rst.getInt("id"),//or rst.getInt(1)
                        rst.getString("nom"),
                        rst.getString("prenom"),
                        rst.getDate("date_naissance"),
                        rst.getString("adresse"),
                        rst.getString("num_tel"),
                        rst.getString("email"),
                        rst.getString("mdp"),
                        rst.getString("type")
                        
                        
                        
                        
                        
                );
                users.add(p);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
             return users;
    }

    
    public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "eyawarteni@gmail.com";
        //Your gmail password
        String password = "zdrasti2014";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My First Email from Java App");
            String htmlCode = "<h1> WE LOVE JAVA </h1> <br/> <h2><b>Next Line </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
}
