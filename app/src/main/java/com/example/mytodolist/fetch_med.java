package com.example.mytodolist;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("ALL")
public class fetch_med extends AsyncTask <Void,Void,Void> {
    String data = "";
    String mediparsed = "";
    String mparsed = "";


    @Override

    protected Void doInBackground(Void...voids){

        try {
            URL url = new URL ("https://api.myjson.com/bins/x4lds");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null ){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for(int i =0 ;i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                mparsed =  "Name:" + JO.get("name") + "\n"+
                        "Uses:" + JO.get("uses") + "\n"+
                        "Warnings:" + JO.get("warnings") + "\n";

                mediparsed = mediparsed + mparsed +"\n" ;


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override

    protected void onPostExecute (Void aVoid){
        super.onPostExecute(aVoid);

        MainActivity.medicine.setText(this.data);
    }
}
