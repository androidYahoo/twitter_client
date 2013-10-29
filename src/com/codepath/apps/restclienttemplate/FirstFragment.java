package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;
import org.json.JSONArray;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FirstFragment extends Fragment{
 
	RestClient client;
	ArrayList<Tweet> tweets;
	ListView timeLine;
	TimelineAdapter adapter;
	View v;

	public FirstFragment(){
		
	}
  @Override
  	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	  	v =  inflater.inflate(R.layout.fragment_first, container, false);
		ListView lv = (ListView) v.findViewById(R.id.rssFeed);
		client = RestClientApp.getRestClient();
		loadTweets(v);
		lv.setAdapter(adapter);
		return v;
  }

  public void loadTweets(final View v) {
		client.getHomeTimeline(new JsonHttpResponseHandler() { 
  		public void onSuccess(JSONArray jsonTweets) {
  				tweets = new ArrayList<Tweet>();
  				tweets = Tweet.fromJson(jsonTweets);
  				timeLine = (ListView) v.findViewById(R.id.rssFeed);
  				adapter = new TimelineAdapter(v.getContext(), tweets);
  				timeLine.setAdapter(adapter);
  				timeLine.setOnItemClickListener(new OnItemClickListener(){
  					public void onItemClick(AdapterView<?> parent, View view,
                      int position, long id) {
		        		  Intent i = new Intent(getActivity(),FullTweet.class);
		        		  Tweet tweet = adapter.getItem(position);
		        		  String body = tweet.getBody();
		        		  String profileImageUrl = tweet.getUser().getProfileImageUrl();
		        		  String userName = tweet.getUser().getScreenName();
		        		  i.putExtra("body", body);
		        		  i.putExtra("image",profileImageUrl);
		        		  i.putExtra("name", userName);
		        		  startActivity(i);
  						}
  					});	
          }
		});
	}
}