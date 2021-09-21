/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import constants.Constant;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
<<<<<<< HEAD
import utils.XLSXParser;
=======
>>>>>>> 221a46e (Gradle but not understand anything, I'm gonna roll back)

/**
 *
 * @author letie
 */
public final class Report  {

    public final String stockSymbol;
    public final YearMonth monthYearPeriod;
    public final ReportFieldsMap detailData;
    public final String dataPath;

    public Report(String stockSymbol, String yearMonthString, String fileName) {
        this.monthYearPeriod
                = YearMonth.parse(yearMonthString, DateTimeFormatter.ofPattern("MMyyyy"));
        this.stockSymbol = stockSymbol;
<<<<<<< HEAD
        this.dataPath = getFullPath(fileName);
        detailData = XLSXParser.parseToMap(dataPath);
=======
        this.dataPath = getDataPath(stockSymbol);
        detailData = new ReportFieldsMap();
>>>>>>> 221a46e (Gradle but not understand anything, I'm gonna roll back)
    }

    private String getFullPath(String fileName) {
        return String.format("%s/%s/csv/%s", Constant.ROOT_PATH, stockSymbol, fileName);
    }
<<<<<<< HEAD

//    @Override
//    public int compare(Report o1, Report o2) {
//        return o1.monthYearPeriod.compareTo(o2.monthYearPeriod);
//    }

=======
>>>>>>> 221a46e (Gradle but not understand anything, I'm gonna roll back)
}
