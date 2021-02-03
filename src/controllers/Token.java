package controllers;

import java.util.TreeMap;

public class Token {

    private TreeMap<String,String> payload;

    public Token(TreeMap<String,String> payload){
        this.payload = payload;
    }


    public TreeMap<String, String> getPayload() {
        return payload;
    }

    public void setPayload(TreeMap<String, String> payload) {
        this.payload = payload;
    }
}

