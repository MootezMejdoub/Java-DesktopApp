/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Tox
 */
public class Reponse {
     private String message;
     private Date datecreation;
     private int id,user_id;
     private String rec_ref;
     private String rec_reference;

    public String getRec_reference() {
        return rec_reference;
    }

    public void setRec_reference(String rec_reference) {
        this.rec_reference = rec_reference;
    }
     

    public Reponse(String message, Date datecreation, int id, int user_id, String rec_ref, String rec_reference) {
        this.message = message;
        this.datecreation = datecreation;
        this.id = id;
        this.user_id = user_id;
        this.rec_ref = rec_ref;
        this.rec_reference = rec_reference;
    }

    public Reponse() {
    }

    public Reponse(String message, String rec_ref) {
        this.message = message;
        this.rec_ref = rec_ref;
    }

    public Reponse(String message, int user_id, String rec_ref) {
        this.message = message;
        this.user_id = user_id;
        this.rec_ref = rec_ref;
    }

    public Reponse(String message, Date datecreation, int id, int user_id, String rec_ref) {
        this.message = message;
        this.datecreation = datecreation;
        this.id = id;
        this.user_id = user_id;
        this.rec_ref = rec_ref;
    }

    public Reponse(String message) {
        this.message = message;
    }

    public Reponse(String message, Date datecreation) {
        this.message = message;
        this.datecreation = datecreation;
    }
   

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRec_ref() {
        return rec_ref;
    }

    public void setRec_ref(String rec_ref) {
        this.rec_ref = rec_ref;
    }
    
}
