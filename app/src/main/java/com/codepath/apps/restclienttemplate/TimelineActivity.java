package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity {
    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsAdapter tweetAdpter;
    private RecyclerView rvTweets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvTweets = (RecyclerView)findViewById(R.id.rvTweets);
        tweets = new ArrayList<>();
        tweetAdpter = new TweetsAdapter(tweets);
        rvTweets.setAdapter(tweetAdpter);
        rvTweets.setLayoutManager(new LinearLayoutManager(this));

        client = TwitterApplication.getRestClient();
        populateTimeline();
    }

    private void populateTimeline(){
        client.getHomeTimeline(new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
//                Toast.makeText(getApplicationContext(), json.toString(), Toast.LENGTH_LONG);
                tweets.addAll(Tweet.fromJSONArray(json));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(getApplicationContext(), "getHomeTimeLine fail" + errorResponse.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
