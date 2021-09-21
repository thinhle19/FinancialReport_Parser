/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import constants.Constant;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;
import models.Report;

/**
 *
 * @author letie
 */
public class Test {

    public static void main(String[] args) {
        //yearmonth parser test
        YearMonth ym = YearMonth.parse("122015", DateTimeFormatter.ofPattern("MMyyyy"));
        System.out.println(ym.getMonth() + " " + ym.getYear());
        System.out.println(ym.format(DateTimeFormatter.ofPattern("MMyyyy")));
        Report r = new Report("PLP", "122015");
        
        r.parseData();
        
    }
}
class MyMap extends TreeMap<String,String> {

    public MyMap() {
        for(int i = 0; i < Constant.FIELD_CODE_LIST.length; i++) {
            
        }
    }
    
}
