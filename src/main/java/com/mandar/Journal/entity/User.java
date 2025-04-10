package com.mandar.Journal.entity;

import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class User {

    public User() {
        password = "";
        username = "";
        roles = "";
        mail = null;

    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Id
    ObjectId userId;
    @Indexed(unique = true)
    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    String roles;

    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    @DBRef
    List<Journal> journals = new ArrayList<>();

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @NonNull
    private String mail ;

    public boolean isSentimentAnalysis() {
        return sentimentAnalysis;
    }

    public void setSentimentAnalysis(boolean sentimentAnalysis) {
        this.sentimentAnalysis = sentimentAnalysis;
    }
    private boolean sentimentAnalysis;
}
