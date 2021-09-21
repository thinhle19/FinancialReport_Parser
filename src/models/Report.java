/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import constants.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Validator;

/**
 *
 * @author letie
 */
public final class Report {

    public final String stockSymbol;
    public final YearMonth monthYearPeriod;
    public final ReportFieldsMap detailData = new ReportFieldsMap();
    public final String dataPath;

    public Report(String stockSymbol, String yearMonthString) {
        this.monthYearPeriod
                = YearMonth.parse(yearMonthString, DateTimeFormatter.ofPattern("MMyyyy"));
        this.stockSymbol = stockSymbol;
        this.dataPath = getDataPath(stockSymbol);
    }

    public String getDataPath(String stockSymbol) {
        return Constant.ROOT_PATH + "/" + stockSymbol.toUpperCase() + "/"
                + stockSymbol.toUpperCase() + "_"
                + monthYearPeriod.format(DateTimeFormatter.ofPattern("MMyyyy"))
                + ".xlsx";
    }

    public void parseData() {
        File file = new File(dataPath);
        FileInputStream fis;
        final int startRow = 2;
        final int startCell = 8;

        try {
            fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet1 = wb.getSheetAt(0);

            for (int i = startRow; i < sheet1.getLastRowNum(); i++) {
                String fieldCode
                        = Validator.trimParentheses(sheet1.getRow(i).getCell(startCell).getStringCellValue());
                String fieldValue = Validator.trimParentheses(sheet1.getRow(i).getCell(startCell + 1).getStringCellValue());
                System.out.println("row " + i + " "
                        + fieldCode
                        + " "
                        + fieldValue);
                //in case special key like 01, 02, 03 => convert back to 1, 2, 3...
                if (fieldCode.length() == 2) {
                    fieldCode = String.valueOf(Integer.parseInt(fieldCode));
                }
                if (detailData.containsKey(fieldCode)) {
                    detailData.put(fieldCode, fieldValue);
                }
            }
            System.out.println(detailData.toString());
            System.out.println(detailData.size());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
