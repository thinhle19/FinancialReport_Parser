package utils;

import java.util.Scanner;
import models.Company;
import models.ParseManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author letie
 */
public class Main {

    public static void main(String[] args) {
        ParseManager parseManager = new ParseManager();
        String input;
        int companyNum = 1;
        Scanner sc = new Scanner(System.in);

//        do {
//            System.out.println("Enter stock symbol of company (ex. HAG, PLL,...|x to exit) " + companyNum++ + ": ");
//            input = sc.nextLine();
//            input = input.toUpperCase();
//            if (!input.equals("X")) {
//                parseManager.companies.add(new Company(input, input));
//            }
//        } while (!input.toLowerCase().equals("x"));
//        System.out.println("Done!");
        parseManager.getAllComapnyReports();
        parseManager.writeDataToMainFile("D:\\Programming\\#Java\\Projects\\FinancialAutoReport\\financial_data\\result.xlsx");
    }
}
