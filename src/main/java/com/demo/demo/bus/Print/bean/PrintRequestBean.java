package com.demo.demo.bus.Print.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintRequestBean implements Serializable{
    String type;
    ArrayList<Integer> id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

    public void setId(ArrayList<Integer> id) {
        this.id = id;
    }
}
