package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
    public String body;
    public String createdAt;
    public User user;
    public long id;
    private boolean expanded;
    public int numRetweets;
    public boolean retweetedByUser;

    //Take json and turn into tweet object
    public static Tweet fromJson(JSONObject json) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = json.getString("text");
        tweet.createdAt =json.getString("created_at");
        tweet.id = json.getLong("id");
        tweet.user = User.fromJson(json.getJSONObject("user"));
        tweet.numRetweets = json.getInt("retweet_count");
        tweet.retweetedByUser = json.getBoolean("retweeted");
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray json) throws JSONException {
        List<Tweet> list = new ArrayList<>();
        for (int i = 0; i < json.length(); i++){
            list.add(fromJson(json.getJSONObject(i)));
        }
        return list;
    }

    public String getTimeDiff() {
        return TimeFormatter.getTimeDifference(createdAt);
    }


    public String getFormattedTime() {
        return TimeFormatter.getTimeStamp(createdAt);
    }

    public String getFormattedHandle() {
        return "@" + user.screenName;
    }


    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
