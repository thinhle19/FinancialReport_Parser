/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import constants.Constant;
import java.util.TreeMap;

/**
 *
 * @author letie
 */
public class ReportFieldsMap extends TreeMap<String, String> {

    public ReportFieldsMap() {
        for (int i = 0; i < Constant.FIELD_CODE_LIST.length ; i++) {
            put(Constant.FIELD_CODE_LIST[i], null);
        }
    }

}
