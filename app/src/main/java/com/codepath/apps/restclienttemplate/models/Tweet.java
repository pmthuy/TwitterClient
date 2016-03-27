package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by thuypm on 26/03/2016.
 */
public class Tweet {

    private String body;
    private long uid;
    private User user;
    private String createAt;

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public User getUser() {
        return user;
    }

    public String getCreateAt() {
        return createAt;
    }

    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        try{
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        return tweet;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray json){
        ArrayList<Tweet> tweets = new ArrayList<>();
        Tweet tweet;
        for(int i = 0; i < json.length(); i++){
            try {
                JSONObject tweetJson = json.getJSONObject(i);
                tweet = Tweet.fromJSON(tweetJson);
                tweets.add(tweet);
            }catch (JSONException e){
                e.printStackTrace();
                continue;
            }

        }
        return tweets;
    }

}
