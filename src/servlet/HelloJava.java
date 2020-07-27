package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class HelloJava {  
	  
    public static void main(String[] args) {  
    	
        String url = "http://localhost:8080/SchoolAPP/HelloServlet";  
        String json = test(url);  
        System.out.println(json);  
        Person02 p = new Gson().fromJson(json, Person02.class);  
        System.out.println(p.getName() + "," + p.getEmail() + "," + p.getAge());  
    }  
  
    private static String test(String urlToRead) {  
        URL url;  
        HttpURLConnection conn;  
        BufferedReader rd;  
        String line;  
        String result = "";  
        try {  
            url = new URL(urlToRead);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setRequestMethod("GET");  
            rd = new BufferedReader(  
                    new InputStreamReader(conn.getInputStream()));  
            while ((line = rd.readLine()) != null) {  
                result += line;  
                System.out.println(line);
            }  
            rd.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
  
    }  
}  
