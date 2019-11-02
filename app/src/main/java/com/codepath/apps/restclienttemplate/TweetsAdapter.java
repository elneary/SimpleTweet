package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;

    //Pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //For each row, inflate the tweet layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;


    }

    //Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //Get the data at position
        final Tweet tweet = tweets.get(position);

        holder.tweetContainer.setTag(position);

        //Bind the tweet with the viewholder param
        holder.bind(tweet);



    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }


    //Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        View extraDetails;
        TextView tvName;
        RelativeLayout tweetContainer;
        TextView timestamp;
        TextView retweetVal;
        TextView publishedDate;

        public ViewHolder(@NonNull View itemView) {
            //itemview parameter represents one row in the recyclerview
            super(itemView);

            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            extraDetails = itemView.findViewById(R.id.extraDetails);
            tweetContainer = itemView.findViewById(R.id.tweetContainer);
            tvName = itemView.findViewById(R.id.tvName);
            timestamp = itemView.findViewById(R.id.timestamp);
            publishedDate = itemView.findViewById(R.id.formattedDate);
            retweetVal = itemView.findViewById(R.id.retweets);



        }

        public void bind(final Tweet tweet){
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.getFormattedHandle());
            tvName.setText(tweet.user.name);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
            timestamp.setText(tweet.getTimeDiff());
            publishedDate.setText(tweet.getFormattedTime());
            retweetVal.setText("" + tweet.numRetweets);

            boolean expanded = tweet.isExpanded();

            tweetContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean expanded = tweet.isExpanded();
                    tweet.setExpanded(!expanded);
                    notifyItemChanged((int) v.getTag());
                }
            });

            extraDetails.setVisibility(expanded ? View.VISIBLE : View.GONE);
        }
    }



}
