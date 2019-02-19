package org.bird.sandbox;

import org.bird.db.exceptions.DBException;
import org.bird.db.mapper.Mapper;
import org.bird.db.mapper.MapperFactory;
import org.bird.db.models.Author;
import org.bird.db.query.Paginator;

import java.util.List;

public class _Paginator_Test_01 {

    public static void main(String[] args) throws DBException {
        Paginator<Author> paginator = new Paginator<>(2,15, Author.class);
        Mapper mapper = MapperFactory.getInstance().getMapper();
        paginator = mapper.<Author>Paginator(paginator);
        List<Author> list = paginator.getList();
        for (Author author : list){
            System.out.println(author.getLastName());
        }
    }
}
