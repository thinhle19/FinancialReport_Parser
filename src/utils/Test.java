/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

<<<<<<< HEAD
import java.io.File;
import java.io.FileNotFoundException;
import models.ParseManager;
//import java.time.YearMonth;
//import java.time.format.DateTimeFormatter;
=======
import constants.Constant;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

>>>>>>> 221a46e (Gradle but not understand anything, I'm gonna roll back)
import models.Report;

/**
 * @author letie
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        //yearmonth parser test
<<<<<<< HEAD
//        YearMonth ym = YearMonth.parse("122015", DateTimeFormatter.ofPattern("MMyyyy"));
//        System.out.println(ym.getMonth() + " " + ym.getYear());
//        System.out.println(ym.format(DateTimeFormatter.ofPattern("MMyyyy")));
//        Report r = new Report("PLP", "122015", "PLP_122015.xlsx");
//        System.out.println(r.dataPath);
//        System.out.println(r.detailData.keySet());
        XLSXParser.parseToMap("D:\\Programming\\#Java\\Projects\\FinancialAutoReport\\financial_data\\PLP\\csv\\PLP_122020.xlsx");
        ParseManager pm = new ParseManager();
        pm.writeAllCompanyReport();
        pm.writeDataToMainFile("D:\\Programming\\#Java\\Projects\\FinancialAutoReport\\financial_data\\result.xlsx");
    }

//Validator test
//        System.out.println(Validator.trimParentheses("(hihifef.)"));
//        System.out.println(JSONParser.parseToMap(""));
=======
        YearMonth ym = YearMonth.parse("122015", DateTimeFormatter.ofPattern("MMyyyy"));
        System.out.println(ym.getMonth() + " " + ym.getYear());
        System.out.println(ym.format(DateTimeFormatter.ofPattern("MMyyyy")));
        Report r = new Report("PLP", "122015");
    }
>>>>>>> 221a46e (Gradle but not understand anything, I'm gonna roll back)
}
