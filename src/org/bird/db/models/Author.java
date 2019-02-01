package org.bird.db.models;

import java.util.ArrayList;
import java.util.Date;

public class Author {

    private String firstName;
    private String lastName;
    private String bornFirstname;
    private String bornLastName;
    private boolean image;
    private String biography;
    private String comment;
    private Date bornDate;
    private Date deathDate;
    private Date createDate;
    private ArrayList<Cycle> cycles;

}
