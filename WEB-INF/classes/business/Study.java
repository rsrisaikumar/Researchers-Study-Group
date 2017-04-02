/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author SriSaiKumar
 */

public class Study implements Serializable{
    private String studyName;
    private String studyCode;
    private String dateCreated;
    private String emailOfCreator;
    private String question;
    private int requestedParticipants;
    private int numOfParticipants;
    private String description;
    private String imageURL;
    private String status;
    private ArrayList<String> answerChoice;
    private ArrayList<Answer> answer;

    
    public Study() {
        studyName = "";
        studyCode = "";
        dateCreated = "";
        emailOfCreator= "";
        question= "";
        description= "";
        imageURL="";
        status= "";
       answerChoice=null;
       requestedParticipants=0;
       numOfParticipants=0;
       answer=null;
    }

    public Study(String studyName, String studyCode, String dateCreated, String emailOfCreator, String question, int requestedParticipants, int numOfParticipants, String description, String imageURL, String status, ArrayList<String> answerChoice, ArrayList<Answer> answer) {
        this.studyName = studyName;//study table
        this.studyCode = studyCode;//study table
        this.dateCreated = dateCreated;//study table
        this.emailOfCreator = emailOfCreator;//study table
        this.question = question;//question table
        this.requestedParticipants = requestedParticipants;//study table
        this.numOfParticipants = numOfParticipants;//study table
        this.description = description;//study table
        this.imageURL = imageURL;//study table
        this.status = status;//study table
        this.answerChoice = answerChoice;//answer table
        this.answer = answer;//answer table with question id and study id
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public String getStudyCode() {
        return studyCode;
    }

    public void setStudyCode(String studyCode) {
        this.studyCode = studyCode;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEmailOfCreator() {
        return emailOfCreator;
    }

    public void setEmailOfCreator(String emailOfCreator) {
        this.emailOfCreator = emailOfCreator;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getRequestedParticipants() {
        return requestedParticipants;
    }

    public void setRequestedParticipants(int requestedParticipants) {
        this.requestedParticipants = requestedParticipants;
    }

    public int getNumOfParticipants() {
        return numOfParticipants;
    }

    public void setNumOfParticipants(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public ArrayList<String> getAnswerChoice() {
        return answerChoice;
    }

    public void setAnswerChoice(ArrayList<String> answerChoice) {
        this.answerChoice = answerChoice;
    }

    public ArrayList<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Answer> answer) {
        this.answer = answer;
    }

 
}