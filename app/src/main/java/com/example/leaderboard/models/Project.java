package com.example.leaderboard.models;

import com.google.gson.annotations.SerializedName;

public class Project {
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("link")
    private String link;

    public Project(String firstName, String lastName, String email, String link) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.link = link;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
