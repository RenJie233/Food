package com.example.dllo.food.entity;

/**
 * Created by Ren on 16/11/1.
 */
public class FoodCardBean {
    /**
     * id : 7264
     * title : 香煎鲳鱼
     * image_url : http://one.boohee.cn/food/2016/10/15/85F2A209-8BA3-4184-8812-E182EC1F94F2.jpg?
     * description : 作为减脂餐 还不错
     * food_code : xiangjianchangyu
     * post_date : 2016-10-15T17:42:03.000+08:00
     * user_key : 40df8fdc-f58d-4161-9a37-51fdd0453680
     * user_name : 艾米82808
     * user_avatar : http://wx.qlogo.cn/mmopen/brsKjTrsruV0XF0fEribr3ayR1I4H3wNbI400UORicyyVeU6wcNXJRWhkHibEqiaBZGBsmZSa2ZMiaLZI8WmRRDYVZ82iaFeTLbzH6/0
     * like_ct : 69
     */

    private int id;
    private String title;
    private String image_url;
    private String description;
    private String food_code;
    private String post_date;
    private String user_key;
    private String user_name;
    private String user_avatar;
    private int like_ct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFood_code() {
        return food_code;
    }

    public void setFood_code(String food_code) {
        this.food_code = food_code;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public int getLike_ct() {
        return like_ct;
    }

    public void setLike_ct(int like_ct) {
        this.like_ct = like_ct;
    }
}
