package com.tih.bean;

/**
 * Created by DELL on 2017/7/6.
 */

/**
 *  "picUrl": [
 {
 "pic_title": "儒略历",
 "id": 1,
 "url": "http://images.juheapi.com/history/1_1.jpg"
 },
 {
 "pic_title": "",
 "id": 2,
 "url": "http://images.juheapi.com/history/1_2.jpg"
 },
 {
 "pic_title": "",
 "id": 3,
 "url": "http://images.juheapi.com/history/1_3.jpg"
 },
 {
 "pic_title": "公式中的符号含义如下：",
 "id": 4,
 "url": "http://images.juheapi.com/history/1_4.jpg"
 },
 {
 "pic_title": "",
 "id": 5,
 "url": "http://images.juheapi.com/history/1_5.jpg"
 }
 ]
 }
 ]
 * */
public class PicUrl {

    private String pic_title;//图片标题

    private int id;//图片顺序

    private String url;//图片地址

    public String getPic_title() {
        return pic_title;
    }

    public void setPic_title(String pic_title) {
        this.pic_title = pic_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
