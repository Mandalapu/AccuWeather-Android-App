package com.example.manohar.weatherapp;

/**
 * Created by Manohar on 08-12-2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;


public class ResultActivity extends Activity {
    String outputJSONData = "";
    JSONObject jsondata = null;
    String city;
    String state;
    String url;
    String dp;
    String summary;
    String symbattach;
    String units;
    String temperature;
    ShareDialog shareDialog;
    ImageButton share;
    String imageicon;
    private ShareButton shareButton;
    CallbackManager callbackManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activityresult);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        share = (ImageButton) findViewById(R.id.fbsharebutton);
        Intent intent = getIntent();

        city=intent.getStringExtra("city");
        state=intent.getStringExtra("state");
        outputJSONData = intent.getStringExtra("RESULT_ACTIVITY_JSON_STRING");
        units=intent.getStringExtra("UNITS");
        url=intent.getStringExtra("URL");
        if(units.equals("us"))
            {
                symbattach="F";
            }
        else
        {
            symbattach="C" ;
        }
        url=intent.getStringExtra("URL");
        try {
            jsondata = (JSONObject)new JSONObject(outputJSONData);
            TextView summarytext = (TextView) findViewById(R.id.summary);
            imageicon=jsondata.getString("imageicon");
            summary=jsondata.getString("summary");
            summary=summary.concat(" in");
            summary=summary.concat(" ");
            summary=summary.concat(city+ ","+state);
            ImageView imageIcon=(ImageView)findViewById(R.id.weatherImage);
           summarytext.setText(summary);

            if(imageicon.equals("http://cs-server.usc.edu:45678/hw/hw8/images/clear.png"))
            {
                imageIcon.setImageResource(R.drawable.clear);
            }
            if(imageicon.equals("http://cs-server.usc.edu:45678/hw/hw8/images/clear_night.png"))
            {
                imageIcon.setImageResource(R.drawable.clear_night);
            }
            if(imageicon.equals("http://cs-server.usc.edu:45678/hw/hw8/images/cloud_day.png"))
            {
                imageIcon.setImageResource(R.drawable.cloud_day);
            }
            if(imageicon.equals("http://cs-server.usc.edu:45678/hw/hw8/images/cloud_night.png"))
            {
                imageIcon.setImageResource(R.drawable.cloud_night);
            }
            if(imageicon.equals("http://cs-server.usc.edu:45678/hw/hw8/images/cloudy.png"))
            {
                imageIcon.setImageResource(R.drawable.cloudy);
            }
            if(imageicon.equals("http://cs-server.usc.edu:45678/hw/hw8/images/fog.png"))
            {
                imageIcon.setImageResource(R.drawable.fog);
            }
            if(imageicon.equals("http://cs-server.usc.edu:45678/hw/hw8/images/rain.png"))
            {
                imageIcon.setImageResource(R.drawable.rain);
            }
            if(imageicon.equals("http://cs-server.usc.edu:45678/hw/hw8/images/sleet.png"))
            {
                imageIcon.setImageResource(R.drawable.sleet);
            }
            if(imageicon.equals("http://cs-server.usc.edu:45678/hw/hw8/images/snow.png"))
            {
                imageIcon.setImageResource(R.drawable.snow);
            }




            TextView temp = (TextView) findViewById(R.id.temperature);
            temperature=jsondata.getString("temperature");

            temperature=temperature.substring(0,2);
            temp.setText(temperature+(char)0x00B0+symbattach);
//            temp.setText(city+","+state);
            TextView lowtemp = (TextView) findViewById(R.id.lowtemperature);
            lowtemp.setText("L:"+jsondata.getString("minTemperature")+(char)0x00B0);

            TextView hightemp = (TextView) findViewById(R.id.hightemperature);
            hightemp.setText("H:"+jsondata.getString("maxTemperature")+(char)0x00B0);

            TextView precipitation = (TextView) findViewById(R.id.precipitation);
            precipitation.setText(jsondata.getString("precipitation"));

            TextView chanceofrain = (TextView) findViewById(R.id.chanceofrain);
            chanceofrain.setText(jsondata.getString("precipProbability"));

            TextView windspeed = (TextView) findViewById(R.id.windspeed);
            windspeed.setText(jsondata.getString("windSpeed"));

            TextView dewPoint = (TextView) findViewById(R.id.dewPoint);
            dp=jsondata.getString("dewPoint");



            dp=dp.substring(0,2);

            dewPoint.setText(dp+(char)0x00B0+symbattach);
           // dewPoint.setText(jsondata.getString("dewPoint"));

            TextView humidity = (TextView) findViewById(R.id.humidity);
            humidity.setText(jsondata.getString("humidity"));

            TextView visibility = (TextView) findViewById(R.id.visibility);
            visibility.setText(jsondata.getString("visibility"));

            TextView sunriseTime = (TextView) findViewById(R.id.sunrise);
            sunriseTime.setText(jsondata.getString("sunriseTime"));

            TextView sunsetTime = (TextView) findViewById(R.id.sunset);
            sunsetTime.setText(jsondata.getString("sunsetTime"));



            share.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             ShareLinkContent linkContent = null;
                                             try {
                                                 linkContent = new ShareLinkContent.Builder()
                                                         .setContentTitle("Current Weather in " + city + " ," + state)
                                                         .setContentDescription(jsondata.getString("summary") + " , " + jsondata.getString("temperature") + (char) 0x00B0 + " ")

                                                         .setContentUrl(Uri.parse("http://forecast.io"))
                                                         .setImageUrl(Uri.parse(imageicon))
                                                         .build();
                                             } catch (JSONException e) {
                                                 e.printStackTrace();
                                             }

                                             shareDialog.show(linkContent);


                                         }
                                     }


            );





        } catch (JSONException e) {
            e.printStackTrace();
        }


        onMoreButtonClick();


    }

    public void onMoreButtonClick() {

        final Context context = this;

        Button button = (Button) findViewById(R.id.moredetails);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("RESULT_ACTIVITY_JSON_STRING", jsondata.toString());
                intent.putExtra("STATE",state);
                intent.putExtra("CITY",city);
                intent.putExtra("UNITS",units);
                //intent.putExtra("symbattach",symbattach);
                startActivity(intent);


            }

        });

        Button button1 = (Button) findViewById(R.id.viewmap);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent1 = getIntent();

                city=intent1.getStringExtra("city");
                state=intent1.getStringExtra("state");

                Intent intent = new Intent(context, AerisMaP.class);
                intent.putExtra("JSONString", jsondata.toString());
                intent.putExtra("state",state);
                intent.putExtra("city",city);
                intent.putExtra("units",units);
                startActivity(intent);


            }

        });








    }

      @Override
      protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
          super.onActivityResult(requestCode, resultCode, data);
          boolean a = callbackManager.onActivityResult(requestCode, resultCode, data);
          if (resultCode==RESULT_OK) {
              Toast.makeText(getApplicationContext(), "Facebook Post Successful", Toast.LENGTH_SHORT).show();
          }
          if(resultCode==RESULT_CANCELED)
          {
              Toast.makeText(getApplicationContext(), "Post Cancelled", Toast.LENGTH_SHORT).show();
          }
      }












}
