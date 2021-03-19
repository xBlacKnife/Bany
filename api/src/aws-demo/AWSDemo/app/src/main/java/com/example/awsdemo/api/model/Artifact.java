package com.example.awsdemo.api.model;

import java.io.Serializable;

public class Artifact implements Serializable {

    // Attributes
    // ------------------------------------------------------
    private String name;        // Artifact name
    private String description; // Artifact description filename
    private String image;       // Artifact image filename
    private String audio;       // Artifact audio filename

    // Constructors
    // ------------------------------------------------------
    public Artifact(String n, String d, String i, String a) {
        this.name = n;
        this.description = d;
        this.image = i;
        this.audio = a;
    }

    // Getters
    // ------------------------------------------------------
    public String name() { return this.name; }

    public String descriptionFile() { return this.description; }

    public String imageFile() { return this.image; }

    public String audioFile() { return this.audio; }

    // Overrides (DEBUG)
    // ------------------------------------------------------
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("\t Name: " + this.name        + "\n");
        sb.append("\t Desc: " + this.description + "\n");
        sb.append("\t  Img: " + this.image       + "\n");
        sb.append("\tAudio: " + this.audio       + "\n");
        return sb.toString();
    }
}
