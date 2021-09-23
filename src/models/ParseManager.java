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
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.XLSXParser;

/**
 *
 * @author letie
 */
public class ParseManager {

    public ArrayList<Company> companies = new ArrayList<>();
    public Comparator<Report> byYearMonth = (Report r1, Report r2)
            -> r1.monthYearPeriod.compareTo(r2.monthYearPeriod);

    public ParseManager() {
        companies.add(new Company("PLP", "PLP"));
        companies.add(new Company("PLX", "PLX"));
    }

    public void writeAllCompanyReport() {
        for (int i = 0; i < companies.size(); i++) {
            //./financial_data\\PLP\\csv
            final File folder = new File(String.format("%s/%s/csv", Constant.ROOT_PATH, companies.get(i).stockSymbol));
            File[] list = folder.listFiles();
            for (File file : list) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    companies.get(i).reportList.add(XLSXParser.reportFromFileName(file.getName()));
                    System.out.println("");
                }
            }
//            System.out.println(companies.get(i).reportList);
            companies.get(i).reportList.sort(byYearMonth);
        }
    }

    public void writeDataToMainFile(String dataPath) {
        File file = new File(dataPath);
        FileInputStream fis;
        int startColumn = 2;
        int codeRowIndex = 1;
        ArrayList<String> listCodeInOrder = new ArrayList<>();

        try {
            fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(2);

            //get needed field in order
            for (int i = startColumn; i < Constant.FIELD_NUM + startColumn; i++) {
                XSSFCell cell = sheet.getRow(codeRowIndex).getCell(i);
                String value;
                if (cell.getCellType() == CellType.NUMERIC) {
                    value = Integer.toString((int) cell.getNumericCellValue());
                } else {
                    value = cell.getStringCellValue();
                }
                listCodeInOrder.add(value);
            }
            //list code tested, not wrong
//            System.out.println("list code " +  listCodeInOrder);
            fis.close();

            //write to result file
            wb = new XSSFWorkbook();
            sheet = wb.createSheet();
            Row row;
            Cell cell;
            row = sheet.createRow(2);
            int currentRow = 2;
            for (int i = 0; i < listCodeInOrder.size(); i++) {
                cell = row.createCell(i + 2);
                cell.setCellValue(listCodeInOrder.get(i));
            }

            for (int i = 0; i < companies.size(); i++) {
                List<Report> reportList = companies.get(i).reportList;
//                System.out.println(reportList);
                for (int k = 0; k < reportList.size(); k++) {
                    row = sheet.createRow(++currentRow);
                    cell = row.createCell(0);
                    cell.setCellValue(reportList.get(k).stockSymbol);
                    cell = row.createCell(1);
                    cell.setCellValue(reportList.get(k).monthYearPeriod.format(DateTimeFormatter.ofPattern("MMyyyy")));
                    for (int f = 0; f < listCodeInOrder.size() ; f++) {
                        String findingKey = listCodeInOrder.get(f);
                        cell = row.createCell(f + 2);
//                        System.out.println(reportList.get(k).detailData.get(findingKey));
                        if (reportList.get(k).detailData.get(findingKey) == null
                                || reportList.get(k).detailData.get(findingKey).equals("")) {
                            cell.setCellValue("0");
                        } else {
                            cell.setCellValue(reportList.get(k).detailData.get(findingKey));
                        }
                    }
                }
            }
            try (FileOutputStream outputStream = new FileOutputStream("test.xlsx")) {
                wb.write(outputStream);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParseManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
