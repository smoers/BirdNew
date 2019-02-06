package org.bird.db.models;

import org.bird.db.models.metadata.ModelMetaData;
import org.bson.types.ObjectId;
import xyz.morphia.annotations.*;

import java.util.ArrayList;

@Entity("cycles")
@Indexes({
        @Index(value = "title", fields = @Field("title"))
})
public class Cycle extends ModelMetaData {

    @Id
    private ObjectId id = new ObjectId();
    private String title;
    private boolean isCycle;
    private int volumeNumber;
    private String comments;
    @Reference
    private ArrayList<Author> authors = new ArrayList<>();
    @Reference
    private ArrayList<Book> books = new ArrayList<>();

    private Cycle(){}

    public Cycle(String title) {
        this.title = title;
    }

    public ObjectId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCycle() {
        return isCycle;
    }

    public void setCycle(boolean cycle) {
        isCycle = cycle;
    }

    public int getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
