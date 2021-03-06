package org.bird.sandbox;

import com.mongodb.MongoClient;
import org.bird.db.models.*;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class _Create_Data_Mas {

    //Set default data User
    private static String u_login = "smoers_";
    private static String u_firstName = "Serge_";
    private static String u_lastName = "Moers_";
    private static String u_email = "serge.moers@gmail.com";
    private static String u_password = "password";
    private static Date u_lastLogin = Calendar.getInstance().getTime();
    private static Date u_createDate = Calendar.getInstance().getTime();
    //Set default data Illustrator
    private static String i_firstName = "Illustrateur_";
    private static String i_lastName = "Nom Illustrator_";
    //Set Default Collection
    private static String o_name = "Collection Name ";
    private static Boolean o_active = true;
    //set Default Editor
    private static String e_name = "Editor Name ";
    private static Boolean e_active = true;
    //Set default data Book
    private static String b_title = "Livre titre ";
    private int b_volume = 0;
    private static String b_presentation = "presentation";
    private static String b_cover = "cover";
    private static String b_isbn_10 = "isbn_10";
    private static String b_isbn_13 = "isbn_13";
    //Set default Cycle
    private static String c_title = "Cycle title ";
    private static String c_comments = "comments";
    //Set default Author
    private static String a_firstName = "firstname_";
    private static String a_lastName = "lastname_";
    private static String a_bornFirstname = "bornfisrtname_";
    private static String a_bornLastName = "bornlastname_";
    private static boolean a_image = false;
    private static String a_biography = "biography";
    private static String a_comment = "comments";
    private static Date a_bornDate = Calendar.getInstance().getTime();
    private static Date a_deathDate = Calendar.getInstance().getTime();
    

    public static void main(String[] args) throws IOException {

        Morphia morphia = new Morphia();
        Datastore datastore = morphia.createDatastore(new MongoClient(), "bird");

        //USER01
        User user01 = new User(u_login+"01");
        user01.setFirstName(u_firstName+"01");
        user01.setLastName(u_lastName+"01");
        user01.setEmail(u_email);
        user01.setPassword(u_password);
        user01.setLastLogin(u_lastLogin);
        user01.setCreateDate(u_createDate);

        //USER01
        User user02 = new User(u_login+"02");
        user02.setFirstName(u_firstName+"02");
        user02.setLastName(u_lastName+"02");
        user02.setEmail(u_email);
        user02.setPassword(u_password);
        user02.setLastLogin(u_lastLogin);
        user02.setCreateDate(u_createDate);
            
        //Save user
        datastore.save(user01);
        datastore.save(user02);
        
        //ILLUSTRATOR01
        Illustrator illustrator01 = new Illustrator(i_lastName+"01");
        illustrator01.setFirstName(i_firstName+"01");

        //ILLUSTRATOR02
        Illustrator illustrator02 = new Illustrator(i_lastName+"02");
        illustrator02.setFirstName(i_firstName+"02");
        
        //Save Illustrator
        datastore.save(illustrator01);
        datastore.save(illustrator02);

        //COLLECTION
        Collection collection01 = new Collection(o_name+01, o_active);
        Collection collection02 = new Collection(o_name+02,o_active);
        datastore.save(collection01);
        datastore.save(collection02);

        //EDITOR
        Editor editor01 = new Editor(e_name+01, e_active);
        Editor editor02 = new Editor(e_name+02, e_active);
        datastore.save(editor01);
        datastore.save(editor02);

        int count = 200;
        for(int i=1;i<=count;i++){
            Book book = new Book(b_title+String.valueOf(i));
            book.setVolume(1);
            book.setPresentation(b_presentation);
            book.setCover(b_cover);
            book.setIsbn_10(b_isbn_10);
            book.setIsbn_13(b_isbn_13);
            book.setCreateDate(Calendar.getInstance().getTime());
            book.setModificationDate(Calendar.getInstance().getTime());

            Cycle cycle = new Cycle(c_title+String.valueOf(i));
            cycle.setCycle(false);
            cycle.setVolumeNumber(1);
            cycle.setComments(c_comments);
            cycle.setBooks(List.of(book));
            book.setCycle(cycle);
            cycle.setCreateDate(Calendar.getInstance().getTime());
            cycle.setModificationDate(Calendar.getInstance().getTime());

            Author author = new Author(a_lastName+String.valueOf(i));
            author.setFirstName(a_firstName+String.valueOf(i));
            author.setBornFirstname(a_bornFirstname+String.valueOf(i));
            author.setBornLastName(a_bornLastName+String.valueOf(i));
            author.setBiography(a_biography);
            author.setComment(a_comment);
            author.setBornDate(a_bornDate);
            author.setDeathDate(a_deathDate);
            author.setImage(a_image);
            author.setCreateDate(Calendar.getInstance().getTime());
            author.setModificationDate(Calendar.getInstance().getTime());


            cycle.setAuthors(List.of(author));
            author.setCycles(List.of(cycle));

            if(i<count/2){

                System.out.println(FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
                File filea = new File("src/images/authors/tolkien.jpg");
                File fileb = new File("src/images/books/001.jpg");
                book.setUser(user01);
                book.setPicture(new FileInputStream(fileb).readAllBytes());
                book.setIllustrators(List.of(illustrator01));
                book.setEditor(editor01);
                book.setCollection(collection01);
                cycle.setUser(user01);
                author.setPicture(new FileInputStream(filea).readAllBytes());
                author.setUser(user01);

            } else {
                File filea = new File("src/images/authors/martin.jpg");
                File fileb = new File("src/images/books/004.jpg");
                book.setUser(user02);
                book.setPicture(new FileInputStream(fileb).readAllBytes());
                book.setIllustrators(List.of(illustrator01, illustrator02));
                book.setEditor(editor02);
                book.setCollection(collection02);
                cycle.setUser(user02);
                author.setPicture(new FileInputStream(filea).readAllBytes());
                author.setUser(user02);
            }

            datastore.save(book);
            datastore.save(cycle);
            datastore.save(author);

        }


    }
    
}
