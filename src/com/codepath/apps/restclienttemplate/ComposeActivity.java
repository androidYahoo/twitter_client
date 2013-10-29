package com.codepath.apps.restclienttemplate;

import org.json.JSONObject;
import com.loopj.android.http.JsonHttpResponseHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ComposeActivity extends Activity{

	public EditText composeTweet;
	RestClient client;
	Intent i;
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			setContentView(R.layout.compose_tweet);
			composeTweet = (EditText) findViewById(R.id.composeTweet);
			client = RestClientApp.getRestClient();
			i = new Intent(this, TweetsActivity.class);
	}
	
	 public void composeTweet(View v){
	  		String tweet = null;
	    	tweet = composeTweet.getText().toString();
		    switch(v.getId()) {	
		    	case R.id.cancelButton:
		    			startActivity(i);
		    			break;
		    	case R.id.doneButton:
		    			client.postTweet(tweet,new JsonHttpResponseHandler() { 
		    				public void onSuccess(JSONObject jsonTweets) {
		    		  			startActivity(i);
		    		  		}
		    			});
		    			break;	
		    	default:	
		    		throw new RuntimeException("Unknown Button");
		    	}	
	    	}
	    }

