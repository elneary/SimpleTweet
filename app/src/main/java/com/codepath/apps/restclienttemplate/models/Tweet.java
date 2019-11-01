package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
    public String body;
    public String createdAt;
    public User user;

    //Take json and turn into tweet object
    public static Tweet fromJson(JSONObject json) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = json.getString("text");
        tweet.createdAt =json.getString("created_at");
        tweet.user = User.fromJson(json.getJSONObject("user"));
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray json) throws JSONException {
        List<Tweet> list = new ArrayList<>();
        for (int i = 0; i < json.length(); i++){
            list.add(fromJson(json.getJSONObject(i)));
        }
        return list;
    }
}
