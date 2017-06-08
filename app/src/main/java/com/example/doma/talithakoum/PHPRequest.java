package com.example.doma.talithakoum;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by doma on 2017-04-21.
 */

public class PHPRequest {
    private URL url;

    public PHPRequest(String url) throws MalformedURLException { this.url = new URL(url); }



    private String readStream(InputStream in) throws IOException {
        StringBuilder jsonHtml = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line = null;

        while((line = reader.readLine()) != null)
            jsonHtml.append(line);

        reader.close();
        return jsonHtml.toString();
    }

    public String PhPtest(ArrayList<String> DBFieldNameList, ArrayList<String> DBDataList) {

        try {
            String postData = "";

            for(int i =0; i < DBFieldNameList.size(); i++){
                if(i != DBFieldNameList.size()-1){
                postData = postData + DBFieldNameList.get(i)+ "=" +DBDataList.get(i) +"&";}
                else{
                    postData = postData + DBFieldNameList.get(i)+ "=" +DBDataList.get(i);
                }

            }
            Log.d("PHPRequest", postData);

          //  String postData = "email=" + data1 + "&" + "nickname=" + data2 + "&" + "password=" + data3;
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postData.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            String result = readStream(conn.getInputStream());
            conn.disconnect();
            return result;
        }
        catch (Exception e) {
            Log.i("PHPRequest", "request was failed.");
            return null;
        }
    }

}

