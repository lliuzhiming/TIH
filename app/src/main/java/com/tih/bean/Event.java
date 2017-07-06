package com.tih.bean;

/**
 * Created by DELL on 2017/7/4.
 */


import java.util.List;

/**
 *{
 "reason": "success",
 "result": [
 {
 "day": "1/1",
 "date": "前45年01月01日",
 "title": "罗马共和国开始使用儒略历",
 "e_id": "1"
 }
 ],
 "error_code": 0
 }**/
public class Event {

    private String reason;

    private List<EventResult> result;

    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<EventResult> getResult() {
        return result;
    }

    public void setResult(List<EventResult> result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
