package org.bird.db.models;

import org.bird.db.models.metadata.ModelMetaData;
import org.bson.types.ObjectId;
import xyz.morphia.annotations.*;

@Entity("illustrators")
@Indexes({
        @Index(value = "firstname", fields = @Field("firstName")),
        @Index(value = "lastname", fields = @Field("lastName")),
        @Index(value = "lastname_firstname", fields = {@Field("lastName"), @Field("firstName")})
})
public class Illustrator extends ModelMetaData {

    @Id
    private ObjectId id = new ObjectId();
    private String firstName = null;
    private String lastName = null;

    private Illustrator(){}

    public Illustrator(String lastName) {
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
}
