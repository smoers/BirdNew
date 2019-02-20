package org.bird.db.models;

import org.bird.db.models.metadata.ModelMetaData;
import org.bson.types.ObjectId;
import xyz.morphia.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity("authors")
@Indexes({
        @Index(value = "firstname", fields = @Field("firstName")),
        @Index(value = "lastname", fields = @Field("lastName")),
        @Index(value = "firstname_lastname", fields = {@Field("firstName"), @Field("lastName")}),
})
public class Author extends ModelMetaData {

    @Id
    private ObjectId id = new ObjectId();
    private String firstName;
    private String lastName;
    private String bornFirstname;
    private String bornLastName;
    private boolean image;
    private String biography;
    private String comment;
    private Date bornDate;
    private Date deathDate;
    private byte[] picture;
    @Reference
    private List<Cycle> cycles = new ArrayList<>();

    private Author(){}

    public Author(String lastName) {
        this.lastName = lastName;
    }

    public ObjectId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBornFirstname() {
        return bornFirstname;
    }

    public void setBornFirstname(String bornFirstname) {
        this.bornFirstname = bornFirstname;
    }

    public String getBornLastName() {
        return bornLastName;
    }

    public void setBornLastName(String bornLastName) {
        this.bornLastName = bornLastName;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public List<Cycle> getCycles() {
        return cycles;
    }

    public void setCycles(List<Cycle> cycles) {
        this.cycles = cycles;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
