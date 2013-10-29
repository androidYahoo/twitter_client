package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;

import org.json.JSONArray;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SecondFragment extends Fragment {
 
	RestClient client;
	ArrayList<Tweet> tweets;
	ListView timeLine;
	TimelineAdapter adapter;

	public SecondFragment(){
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			View v =  inflater.inflate(R.layout.fragment_second, container, false);
			ListView lv = (ListView) v.findViewById(R.id.lvMentionTweets);
			client = RestClientApp.getRestClient();
			loadTweets(v);
			lv.setAdapter(adapter);
			return v;
	}

   public void loadTweets(final View v) {
		client.getMentionsTimeline(new JsonHttpResponseHandler() { 
  		public void onSuccess(JSONArray jsonTweets) {
  				tweets = new ArrayList<Tweet>();
  				tweets = Tweet.fromJson(jsonTweets);
  				timeLine = (ListView) v.findViewById(R.id.lvMentionTweets);
  				adapter = new TimelineAdapter(v.getContext(), tweets);
  				timeLine.setAdapter(adapter);
              	
          }
  	});
	}

  
}