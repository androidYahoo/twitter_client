package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetsActivity extends FragmentActivity {
	
	RestClient client;
	ArrayList<Tweet> tweets;
	ListView timeLine;
	TimelineAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragments);
		client = RestClientApp.getRestClient();
		if(findViewById(R.id.fragment_container) !=null) {
			if(savedInstanceState != null) {
				return;
			}
		}
		setupTabs();
	}

	@SuppressLint("NewApi")
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
			.newTab()
			.setText("Home")
			
			.setTag("Home")
			.setTabListener(new FragmentTabListener<FirstFragment>(this, "first",FirstFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
			.newTab()
			.setText("@Mentions")
			
			.setTag("MentionsTimelineFragment")
			.setTabListener(
			    new FragmentTabListener<SecondFragment>(this, "second",
								SecondFragment.class));

		actionBar.addTab(tab2);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.photos, menu);
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	       
	        case R.id.action_compose:
	            composeMessage();
	            return true;
	            
	        case R.id.action_profile:
	        	displayProfile();
	        	return true;
	        	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void displayProfile() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this,ProfileActivity.class);
		startActivity(i);
	}

	private void composeMessage() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, ComposeActivity.class);
    	startActivity(i);
	}

	private void openSearch() {
		// TODO Auto-generated method stub
		
	}

}
