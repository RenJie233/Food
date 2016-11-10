package com.example.dllo.food.liteorm;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Ren on 16/11/9.
 */
public class SearchHistory {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String history;

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
