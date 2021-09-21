/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author letie
 */
public class Company {
    public final String name;
    public final String stockSymbol;
    public final ArrayList<Report> reportList = new ArrayList<>();

    public Company(String name, String stockSymbol) {
        this.name = name;
        this.stockSymbol = stockSymbol;
    }
}
