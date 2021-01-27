package com.truscert.gambit.utils;

import java.util.Date;

public class Timestamp {
    private static Timestamp instance;
    public static Timestamp getInstance() {
        if (instance == null) instance = new Timestamp();
        return instance;
    }
    
    public String getTs() {
        String ts;
        Date d = new Date();
        ts = d.getTime() + "";
        return ts;
    }
}
