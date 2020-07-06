package pt.ipg.application.testingcovid_19.database.remote;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import pt.ipg.application.testingcovid_19.other.Function;

public class RemoteDB {

    private String URL;
    private String TOKEN;
    private Context context;
    private String value;
    private boolean ready;
    private Function fn;

    public RemoteDB(Context context){
        this.context = context;
        value = null;
        fn = new Function();

        // It's must to be https to work...
        URL = "https://coronavirus.goodshapecode.com/api/db.php";
        TOKEN = "4378265784657234657843265783426549837";
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public boolean isReady(){
        return ready;
    }

    public JSONObject getAllColumn() throws JSONException {
        JSONObject Obj = fn.parseJSON(this.value);
        return Obj;
    }

    public ArrayList<String> getColumn(String name) throws JSONException {
        JSONObject Obj = fn.parseJSON(this.value);
        ArrayList<String> list = new ArrayList<>();
        if(name != null && name.length() > 0){
            String str = Obj.get(name).toString();
            list = fn.strToArray(str);
        }
        return list;
    }

    public <T> void query(final String query, final Callable<T> callable ){
        ready = false;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.length() != 4){
                    setValue(response);
                    ready = true;
                }
                try {
                    callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", TOKEN);
                params.put("query", query);
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void query(final String query){
        query(query, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
    }

    public void delete(String table, String[] conditions){
        String query = "DELETE FROM `"+table+"`";
        int len = conditions.length;
        if(len > 0){
            query += " WHERE";
            for(int i=0; i<len; i++){
                query += " ";
                query += conditions[0];
            }
        }
        query(query);
    }

    public void update(String table, String[][] colAndVal, String[] conditions){
        //colAndVal : {{column1,value1},{column2,value2}}
        String query = "UPDATE `" + table + "`";
        int len = colAndVal.length;
        if(len > 0){
            query += " SET";
            for (int i=0;i<len;i++){
                query += " ";
                query += "`"+colAndVal[i][0]+"` = `"+colAndVal[i][1]+"`";
            }
        }
        len = conditions.length;
        if(len > 0){
            query += " WHERE";
            for(int i=0; i<len; i++){
                query += " ";
                query += conditions[0];
            }
        }
        query(query);
    }
}