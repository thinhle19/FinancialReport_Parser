/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Report;
import models.ReportFieldsMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author letie
 */
public class XLSXParser {

    //Format fileName: XXX_MMyyyy.xlsx
    public static Report reportFromFileName(String fileName) {
        String stockSymbol = fileName.substring(0, 3);
        String ymString = fileName.substring(4, 10);
        return new Report(stockSymbol, ymString, fileName);
    }

    public static ReportFieldsMap parseToMap(String dataPath) {
        File file = new File(dataPath);
        FileInputStream fis;
        ReportFieldsMap detailData = new ReportFieldsMap();
        try {
            fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            int codeColumnIndex = (int) sheet.getRow(0).getCell(0).getNumericCellValue();
            int valueColumnIndex = (int) sheet.getRow(0).getCell(1).getNumericCellValue();
            System.out.println(codeColumnIndex + " " + valueColumnIndex);
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                String fieldCode
                        = Validator.trimParentheses(sheet.getRow(i).getCell(codeColumnIndex - 1).getStringCellValue());
//                System.out.println("raw " + fieldCode);
                String fieldValue = Validator.trimParentheses(sheet.getRow(i).getCell(valueColumnIndex - 1).getStringCellValue());
                //in case special key like 01, 02, 03 => convert back to 1, 2, 3...
                if (fieldCode.equals("411")) {
                    fieldCode = "411a";
                } else if (fieldCode.equals("01")) {
                    fieldCode = "1";
                } else if (fieldCode.equals("02")) {
                    fieldCode = "2";
                }
                if (detailData.containsKey(fieldCode.toLowerCase())) {
                    detailData.put(fieldCode, Validator.getNonDotNumString(fieldValue));
                }
            }
            System.out.println(detailData);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XLSXParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XLSXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detailData;
    }
}
