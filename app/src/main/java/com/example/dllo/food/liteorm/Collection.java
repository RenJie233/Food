package com.example.dllo.food.liteorm;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Ren on 16/11/12.
 */
public class Collection {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String title;
    private String link;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
