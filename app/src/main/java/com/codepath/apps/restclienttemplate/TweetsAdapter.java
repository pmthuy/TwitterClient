package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by thuypm on 26/03/2016.
 */
public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName;
        public ImageView ivProfileImage;
        public TextView tvBody;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
        }
    }

    private List<Tweet> mTweets;
    private Context context;

    public TweetsAdapter(List<Tweet> tweets) {
        this.mTweets = tweets;
    }


    @Override
    public TweetsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_tweet, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TweetsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Tweet tweet = mTweets.get(position);
        // Set item views based on the data model
        TextView tvUN = viewHolder.tvUserName;
        tvUN.setText(tweet.getUser().getScreenName());
        TextView tvBD = viewHolder.tvBody;
        tvBD.setText(tweet.getBody());
        ImageView ivImage = viewHolder.ivProfileImage;
        ivImage.setImageResource(0);
        Picasso.with( context).load(tweet.getUser().getProfileImageUrl()).into(ivImage);
    }

    @Override
    public int getItemCount() {
        if(mTweets == null)
            return 0;
        return mTweets.size();
    }

}
