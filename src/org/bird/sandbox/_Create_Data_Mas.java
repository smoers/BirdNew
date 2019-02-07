package org.bird.sandbox;

import com.mongodb.MongoClient;
import org.bird.db.models.Book;
import org.bird.db.models.Illustrator;
import org.bird.db.models.User;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

import java.util.ArrayList;
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
    //Set default data Book
    private static String b_title = "Livre titre ";
    private int b_volume = 0;
    private static String b_presentation = "presentation";
    private static String b_cover = "cover";
    private static String b_editor = "editor";
    private static String b_collection = "collection";
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
    private Date a_bornDate = Calendar.getInstance().getTime();
    private Date a_deathDate = Calendar.getInstance().getTime();
    

    public static void main(String[] args) {

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

        int count = 200;
        for(int i=1;i<=count;i++){
            Book book = new Book(b_title+String.valueOf(i));
            book.setVolume(1);
            book.setPresentation(b_presentation);
            book.setCover(b_cover);
            book.setEditor(b_editor);
            book.setCollection(b_collection);
            book.setIsbn_10(b_isbn_10);
            book.setIsbn_13(b_isbn_13);
            if(i<count/2){
                book.setUser(user01);
                book.setIllustrators(List.of(illustrator01));
            } else {
                book.setUser(user02);
                book.setIllustrators(List.of(illustrator01, illustrator02));
            }

        }
    }
    
}
