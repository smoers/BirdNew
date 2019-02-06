package org.bird.db.models;

import org.bson.types.ObjectId;
import xyz.morphia.annotations.*;

import java.util.Date;

@Entity("users")
@Indexes({
        @Index(value = "login", fields = @Field("login")),
        @Index(value = "firstname", fields = @Field("firstName")),
        @Index(value = "lastname", fields = @Field("lastName"))
})
public class User {

    @Id
    private ObjectId id = new ObjectId();
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date lastLogin;
    private Date createDate;

    private User(){}

    public User(String login) {
        this.login = login;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
