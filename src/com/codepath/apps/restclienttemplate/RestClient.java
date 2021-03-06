package com.codepath.apps.restclienttemplate;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class RestClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    public static final String REST_URL = "http://api.twitter.com/1.1/";
    public static final String REST_CONSUMER_KEY = "m1ZVkLCJb7vT1bXCJcjv1A";
    public static final String REST_CONSUMER_SECRET = "JMcj2r2bqSQPVv66vzg3TLpHdGxMv8EDZ53jx5f5J68";
    public static final String REST_CALLBACK_URL = "oauth://codepathtweets";
    
    public RestClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
        
    }

    public void getHomeTimeline(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        //RequestParams params = new RequestParams();
        //params.put("page", String.valueOf(page));
        client.get(apiUrl, null, handler);
      }
    
    public void getMentionsTimeline(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/mentions_timeline.json");
        //RequestParams params = new RequestParams();
        //params.put("page", String.valueOf(page));
        client.get(apiUrl, null, handler);
      }
    
    public void getFriends(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("friends/ids.json");
        //RequestParams params = new RequestParams();
        //params.put("page", String.valueOf(page));
        client.get(apiUrl, null, handler);
      }
    
    public void postTweet(String tweet,AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", String.valueOf(tweet));
        client.post(apiUrl, params, handler);
      }
   
    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */

	public void getUserDetails(String name,AsyncHttpResponseHandler handler) {
		// TODO Auto-generated method stub
		String apiUrl = getApiUrl("statuses/user_timeline.json");
		RequestParams params = new RequestParams();
        params.put("screen_name", String.valueOf(name));
		if(name !=null){
			client.get(apiUrl, params,handler);
		}
		else{
			client.get(apiUrl, null,handler);
		}
		
	}
}