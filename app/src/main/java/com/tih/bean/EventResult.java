package com.tih.bean;

/**
 * Created by DELL on 2017/7/6.
 */

/**
 *  "result": [
 {
 "day": "1/1",
 "date": "前45年01月01日",
 "title": "罗马共和国开始使用儒略历",
 "e_id": "1"
 }
 ]
 */

public class EventResult {

    private String day;

    private String date;

    private String title;

    private int e_id;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }
}
