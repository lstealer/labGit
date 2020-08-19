package com.kshrd.khracer_api.Repository.Model;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class BaseApiResponse<T> {

    private String message;
    private T data;
    private boolean success=true;
    private HttpStatus status;
    private Timestamp time=new Timestamp(System.currentTimeMillis());
    //private int code=200;
    private int count=1;

    public BaseApiResponse() {}

    public BaseApiResponse(String message, T data, HttpStatus status, Timestamp time) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.time = time;
    }

    public BaseApiResponse(String message, T data, boolean success, HttpStatus status, Timestamp time, int count) {
        this.message = message;
        this.data = data;
        this.success = success;
        this.status = status;
        this.time = time;
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BaseApiResponse{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", status=" + status +
                ", time=" + time +
//                ", code=" + code +
                '}';
    }
    public void setResponse(String message, boolean success, HttpStatus status, T data) {
        this.message = message;
        this.success = success;
        this.status=status;
        this.data = data;
    }
    public void setResponse(String message, boolean success, HttpStatus status, T data,int count) {
        this.message = message;
        this.success = success;
        this.status=status;
        this.count = count;
        this.data = data;
    }

}
