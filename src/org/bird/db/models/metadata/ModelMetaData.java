package org.bird.db.models.metadata;

import org.bird.db.models.User;

import java.util.Date;

public class ModelMetaData {

    private Date createDate;
    private Date modificationDate;
    private User user;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
