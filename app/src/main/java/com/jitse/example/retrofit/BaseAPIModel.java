package com.jitse.example.retrofit;

import java.io.Serializable;

public abstract class BaseAPIModel implements Serializable {
    public int statusCode;
    public String error;
    public String message;
    public String messageId;
}

