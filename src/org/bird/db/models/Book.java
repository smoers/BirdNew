package org.bird.db.models;

import org.bird.db.models.metadata.ModelMetaData;
import org.bson.types.ObjectId;
import xyz.morphia.annotations.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Entity("books")
@Indexes({
        @Index(value = "title", fields = @Field("title")),
        @Index(value = "collection", fields = @Field("collection")),
        @Index(value = "editor", fields = @Field("editor"))
})
public class Book extends ModelMetaData {

    @Id
    private ObjectId id = new ObjectId();
    private String title = null;
    private int volume = 0;
    private String presentation = null;
    private String cover = null;
    private String editor = null;
    private String collection =null;
    private String isbn_10 = null;
    private String isbn_13 = null;
    private byte[] picture;
    private List<Illustrator> illustrators = new ArrayList<>();

    private Book(){}

    public Book(String title) {
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getIsbn_10() {
        return isbn_10;
    }

    public void setIsbn_10(String isbn_10) {
        this.isbn_10 = isbn_10;
    }

    public String getIsbn_13() {
        return isbn_13;
    }

    public void setIsbn_13(String isbn_13) {
        this.isbn_13 = isbn_13;
    }

    public List<Illustrator> getIllustrators() {
        return illustrators;
    }

    public void setIllustrators(List<Illustrator> illustrators) {
        this.illustrators = illustrators;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
