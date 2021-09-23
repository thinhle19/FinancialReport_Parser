/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author letie
 */
public class Validator {

    public static String trimParentheses(String input) {
        if (input.length() != 0) {
            return input.replace("(", "").replace(")", "");
        }
        return input;
    }
    
    public static String getNonDotNumString(String input) {
        return input.replace(".", "").replace(",", "");
    }
}
