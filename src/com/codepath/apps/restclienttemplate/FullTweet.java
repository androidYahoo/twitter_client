package com.codepath.apps.restclienttemplate;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FullTweet extends Activity{
	Intent i;
	String name;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_tweet);
		Intent myIntent = getIntent();
		String body = myIntent.getStringExtra("body");
		name = myIntent.getStringExtra("name");
		String image = myIntent.getStringExtra("image");
		i = new Intent(this,ProfileActivity.class);
		
		ImageView imageView = (ImageView)findViewById(R.id.ivProfile);
        ImageLoader.getInstance().displayImage(image, imageView);
	    imageView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					i.putExtra("name", name);
					startActivity(i);
				}
	        });
        TextView nameView = (TextView) findViewById(R.id.tvName);
        String formattedName = "<b>" + name+ "</b>" + " <small><font color='#777777'>@" +
                name + "</font></small>";
        nameView.setText(Html.fromHtml(formattedName));

        TextView bodyView = (TextView) findViewById(R.id.tvBody);
        bodyView.setText(Html.fromHtml(body));
			
	  }
}
