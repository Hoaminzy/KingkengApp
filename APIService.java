package com.Hoaminzf.fithou.mykengkingapp.Service;

public class APIService {
    private  static String base_url="https://kingkeng.000webhostapp.com/Server/";
    public  static DataService getService(){
        return API_retrofit.getCleint(base_url).create(DataService.class);
    }


}
