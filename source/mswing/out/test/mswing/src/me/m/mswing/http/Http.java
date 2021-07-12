package me.m.mswing.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {
    private URL url;
    private HttpURLConnection conn;
    private InputStream inputStream;
    private String urlstr;
    public Http(){
    }
    public Http(String urlstr){
        this.urlstr=urlstr;
        try {
            url=new URL(urlstr);
            conn= (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public InputStream get(String urlstr) {
        if(url==null){
            try {
                url=new URL(urlstr);
                conn= (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return get();
    }
    public InputStream get(){
        try {
            if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                inputStream=conn.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  inputStream;
    }
    public String getURL(){
        return  urlstr;
    }
    public void close(){
        conn.disconnect();
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getContentLength(){
       return conn.getContentLength();
    }
    public String getContentType(){
        return conn.getContentType();
    }
}
