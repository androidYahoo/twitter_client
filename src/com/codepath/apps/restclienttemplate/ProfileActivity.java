package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileActivity extends Activity{
	RestClient client;
	ArrayList<Tweet> tweets;
	String name = null;
	 public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.profile_tweet);
		  client = RestClientApp.getRestClient();
		  Intent myIntent = getIntent();
		  name = myIntent.getStringExtra("name");
		  displayProfile(name);
	 }
	private void displayProfile(String name) {
		// TODO Auto-generated method stub
		client.getUserDetails(name,new JsonHttpResponseHandler() { 
	  		public void onSuccess(JSONArray jsonTweets) {
	  			 //i = new Intent(, TweetsActivity.class);
	  			tweets = new ArrayList<Tweet>();
  				tweets = Tweet.fromJson(jsonTweets);
  				ImageView imageView = (ImageView)findViewById(R.id.profileImage);
  		        ImageLoader.getInstance().displayImage(tweets.get(0).getUser().getProfileImageUrl(), imageView);
  		        
  		        TextView nameView = (TextView) findViewById(R.id.userName);
  		        String formattedName = "<b>" + tweets.get(0).getUser().getName()+ "</b>" + " <small><font color='#777777'>@" +
  		        		tweets.get(0).getUser().getName() + "</font></small>";
  		        nameView.setText(Html.fromHtml(formattedName));

  		        TextView descriptionView = (TextView) findViewById(R.id.description);
  		        descriptionView.setText(Html.fromHtml(tweets.get(0).getUser().getDescription()));
  		        
  		        TextView followersCount = (TextView)findViewById(R.id.followerCount);
  		        String followString = "<b>" + "Followers"+ "</b>" + " <small><font color='#777777'>@" +
		        		tweets.get(0).getUser().getFollowersCount() + "</font></small>";
		        followersCount.setText(Html.fromHtml(followString));
		        
		        TextView followingCount = (TextView)findViewById(R.id.followingCount);
  		        String followingString = "<b>" + "Following"+ "</b>" + " <small><font color='#777777'>@" +
		        		tweets.get(0).getUser().getFriendsCount() + "</font></small>";
		        followingCount.setText(Html.fromHtml(followingString));
		        
		        ListView timeLine = (ListView)findViewById(R.id.userTweets);
  				TimelineAdapter adapter = new TimelineAdapter(getBaseContext(), tweets);
  				timeLine.setAdapter(adapter);
	  		}
		});
	}
	 
}	
