package ThirdHomework;

import com.alibaba.fastjson.JSONReader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetData {
    // use url to get Json data
    public static String getJson(String url){
            StringBuffer json=new StringBuffer();
            BufferedReader bufferedReader=null;
            try {
                URL urlInstance=new URL(url);
                URLConnection urlConnection=urlInstance.openConnection();
                bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine =null;
                while((inputLine=bufferedReader.readLine())!=null){
                    json.append(inputLine);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                if(bufferedReader!=null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return json.toString();
            }


    }


    public static void main(String[] args) {
        //得到中国数据
        System.out.println("中国数据："+"\n"+getJson("https://covid-api.mmediagroup.fr/v1/cases?country=China"));

        //得到日本数据

        System.out.println("日本数据："+"\n"+getJson("https://covid-api.mmediagroup.fr/v1/cases?country=Japan"));
        //得到美国数据
        System.out.println("美国数据："+"\n"+getJson("https://covid-api.mmediagroup.fr/v1/cases?country=US"));
        //得到英国数据
        System.out.println("英国数据："+"\n"+getJson("https://covid-api.mmediagroup.fr/v1/cases?country=United%20Kingdom"));

    }

}
