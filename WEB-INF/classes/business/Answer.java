/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import  java.io.Serializable;
/**
 *
 * @author SriSaiKumar
 */
public class Answer implements Serializable{
    private String emailOfParticipant;
    private String submissionDate;
    private String choice;

    public Answer(){
     emailOfParticipant = "";
     submissionDate = "";
     choice = "";    
    }

    public Answer(String emailOfParticipant, String submissionDate, String choice) {
        this.emailOfParticipant = emailOfParticipant;
        this.submissionDate = submissionDate;
        this.choice = choice;
    }

    public String getEmailOfParticipant() {
        return emailOfParticipant;
    }

    public void setEmailOfParticipant(String emailOfParticipant) {
        this.emailOfParticipant = emailOfParticipant;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }




}


