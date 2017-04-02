/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;

/**
 *
 * @author SriSaiKumar
 */
public class User implements Serializable{
    private String email;
    private String password;
    private String name;
    private String type;
    private int numCoins;
    private int numPostedStudies;
    private int numOfParticipants;
    private int numParticipation;

    public User(){
        email = "";
        password = "";
        name = "";
        type = "";
        numCoins = 0;
        numPostedStudies = 0;
        numOfParticipants=0;
        numParticipation = 0;
    }

    public User(String email, String password, String name, String type, int numCoins, int numPostedStudies, int numOfParticipants, int numParticipation) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
        this.numCoins = numCoins;
        this.numPostedStudies = numPostedStudies;
        this.numOfParticipants=numOfParticipants;
        this.numParticipation = numParticipation;
    }
    

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getNumCoins() {
        return numCoins;
    }

    public int getNumPostedStudies() {
        return numPostedStudies;
    }

    public int getNumOfParticipants() {
        return numOfParticipants;
    }

    public int getNumParticipation() {
        return numParticipation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumCoins(int numCoins) {
        this.numCoins = numCoins;
    }

    public void setNumPostedStudies(int numPostedStudies) {
        this.numPostedStudies = numPostedStudies;
    }

    public void setNumOfParticipants(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }

    public void setNumParticipation(int numParticipation) {
        this.numParticipation = numParticipation;
    }


}
