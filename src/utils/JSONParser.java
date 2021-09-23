/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.ReportFieldsMap;

/**
 *
 * @author letie
 */
public class JSONParser {
    //This is parse method for Extract Table API
    public static ReportFieldsMap parseToMap(String dataPath, int codeIndex, int valueIndex) {
        String path = "C:\\Users\\letie\\Desktop\\Temp\\response.json";
        BufferedReader br;
        ReportFieldsMap parsedData = new ReportFieldsMap();
        try {
            br = new BufferedReader(new FileReader(path));
            Gson gson = new Gson();
            HashMap<String, Object> json = gson.fromJson(br, HashMap.class);
            Map tableObject = (LinkedTreeMap) ((ArrayList) json.get("Tables")).get(0);//        System.out.println(json.toString());
            Map tableJsonObject = (LinkedTreeMap) tableObject.get("TableJson");

            String fieldCodeIndex = Integer.toString(codeIndex);
            String fieldValueIndex = Integer.toString(valueIndex);

            System.out.println(tableJsonObject.size());
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) tableJsonObject).entrySet()) {
                String keyGot = ((String) ((Map) entry.getValue()).get(fieldCodeIndex));
                String valueGot = Validator.trimParentheses((String) ((Map) entry.getValue()).get(fieldValueIndex));
                if (keyGot.length() == 2) {
                    keyGot = String.valueOf(Integer.parseInt(keyGot));
                }
                if (parsedData.containsKey(keyGot)) {
                    parsedData.put(keyGot, valueGot);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSONParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsedData;
    }
}
