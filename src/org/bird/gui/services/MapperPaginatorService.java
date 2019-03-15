package org.bird.gui.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.bird.db.mapper.MapperFactory;
import org.bird.db.mapper.MapperPaginator;
import org.bird.db.query.Paginator;
import org.bird.gui.events.OnProcessEvent;
import org.bird.gui.listeners.OnProcessListener;

import java.util.ArrayList;
import java.util.List;

public class MapperPaginatorService extends Service<Paginator> {

    private Paginator paginator;
    private List<OnProcessListener> listeners = new ArrayList<>();

    public MapperPaginatorService(Paginator paginator) {
        this.paginator = paginator;
        setOnSucceeded(succeeded -> {
            notifyOnProcessListener(new OnProcessEvent(this,true));
        });
        setOnFailed(fail ->{
            notifyOnProcessListener(new OnProcessEvent(this,false));
        });
        setOnCancelled(canceled ->{
            notifyOnProcessListener(new OnProcessEvent(this,false));
        });
    }

    @Override
    protected Task<Paginator> createTask() {
        return new Task<Paginator>(){

            @Override
            protected Paginator call() throws Exception {
                MapperPaginator mapper = new MapperPaginator();
                mapper = MapperFactory.getInstance().getMapper(mapper);
                return mapper.loadPaginator(paginator);
            }
        };
    }

    public void addOnProcessListener(OnProcessListener listener){
        listeners.add(listener);
    }

    protected void notifyOnProcessListener(OnProcessEvent evt){
        for (OnProcessListener listener : listeners){
            listener.onProcess(evt);
        }
    }
}
