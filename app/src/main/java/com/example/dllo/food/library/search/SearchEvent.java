package com.example.dllo.food.library.search;

/**
 * Created by Ren on 16/11/8.
 */
public class SearchEvent {
    private String text;

    public SearchEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
