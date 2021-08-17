package com.example.emptytest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {
    private String stringJson;
    public static ArrayList<Restaurant> list = new ArrayList<>();

    public Parser(String jason) throws JSONException {
        this.stringJson = jason;
        JSONArray array = new JSONArray(stringJson); //String name, String offer, String descrp, URL url
        for(int i = 0; i < array.length(); i++){
            JSONObject restaurant = array.getJSONObject(i);
            list.add(new Restaurant(restaurant.getString("name"),restaurant.getString("offer"),restaurant.getString("description"),restaurant.getString("image_url")));
        }

    }

    public void print(){
        for (Restaurant rest: list)
        {
            System.out.println(rest.getDescrp() + "\n");
        }
    }



}
