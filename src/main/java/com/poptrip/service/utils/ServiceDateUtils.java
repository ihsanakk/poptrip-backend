package com.poptrip.service.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class ServiceDateUtils {

    public Timestamp getTimeStamp(){
        return new Timestamp(new Date().getTime());
    }
    public Date getDate(){return new Date();}

}
