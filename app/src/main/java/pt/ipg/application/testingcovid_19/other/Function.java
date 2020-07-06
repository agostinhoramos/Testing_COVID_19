package pt.ipg.application.testingcovid_19.other;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Function {

    public Function() {}

    public JSONObject parseJSON(String string){
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String parseString(JSONObject jsonObject, String field){
        try {
            return jsonObject.get(field).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public ArrayList<String> strToArray(String string){
        string = string.replace("[\"","");
        string = string.replace("\"]","");
        string = string.replace("\",\"",",");
        return new ArrayList<String>(Arrays.asList(string.split(",")));
    }

}
