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
            if (input.charAt(0) == '(') {
                input = input.substring(1);
            }
            if (input.charAt(input.length() - 1) == ')') {
                input = input.substring(0, input.length() - 2);
            }
        }
        return input;
    }
}
