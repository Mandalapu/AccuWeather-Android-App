package com.example.manohar.weatherapp;

/**
 * Created by Manohar on 08-12-2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends Activity {
    String outputJSONData = "";
    JSONObject jsondata = null;
    String summary;
    String state;
    String units;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitydetails);

        /*Resources ressources = getResources();
        TabHost tabHost = getTabHost();*/
       /* Intent intent = getIntent();
        outputJSONData = intent.getStringExtra("RESULT_ACTIVITY_JSON_STRING");*/
/*

        String city=intent.getStringExtra("CITY");
        String state=intent.getStringExtra("STATE");
*/

        Intent intent = getIntent();
        int i;
        int j;
        TextView timetextview;
        TextView temptextview;
        ImageView imageiconview;
        outputJSONData = intent.getStringExtra("RESULT_ACTIVITY_JSON_STRING");
        city=intent.getStringExtra("CITY");
        state=intent.getStringExtra("STATE");
        units=intent.getStringExtra("UNITS");
        summary="More Details for ";
        summary=summary.concat(city);
        summary=summary.concat(", ");
        summary=summary.concat(state);
        TextView moredetailstext=(TextView)findViewById(R.id.moredetails);
        moredetailstext.setText(summary);
        TextView tempheader=(TextView)findViewById(R.id.tfheadertemp);
        if(units.equals("us"))
        tempheader.setText("Temp("+(char)0x00B0+"F");
        if(units.equals("si"))
            tempheader.setText("Temp("+(char)0x00B0+"C)");

        try {

            jsondata = (JSONObject) new JSONObject(outputJSONData);

            JSONArray array= jsondata.getJSONArray("dailydetails");
            JSONArray hourlyarray= jsondata.getJSONArray("hourlydetails");

            for(i=0;i<=23;i++)
            {
                JSONObject hourlyobject=hourlyarray.getJSONObject(i);
                String time=hourlyobject.getString("time");
                String imageicon=hourlyobject.getString("imageicon");
                String temp=hourlyobject.getString("temperature");
                j=i+1;

                String timeid="time"+j;
                int id=getResources().getIdentifier(timeid, "id", getPackageName());
                timetextview=(TextView) findViewById(id);
                timetextview.setText(time);

                String tempid="tftemp"+j;
                int id1=getResources().getIdentifier(tempid,"id", getPackageName() );
                temptextview=(TextView) findViewById(id1);
                temptextview.setText(temp);

                String imageid="simage"+j;
                int id2=getResources().getIdentifier(imageid,"id", getPackageName() );
                imageiconview=(ImageView) findViewById(id2);
                setImage(imageicon,imageiconview);

            }



            JSONObject object1 = array.getJSONObject(1);
            String day1=object1.getString("day");
            String month1=object1.getString("month");
            String dayandate1=day1.concat("," + month1);
            TextView temp1 = (TextView) findViewById(R.id.dayanddate1);
            temp1.setText(dayandate1);
            String imageicon=object1.getString("imageicon");
            String index=imageicon.substring(45, imageicon.length() - 4);
            ImageView imageIcon=(ImageView)findViewById(R.id.day1image);
            setImage(imageicon,imageIcon);
            TextView temperatureMin1 = (TextView) findViewById(R.id.minandmaxtemp1);
            temperatureMin1.setText("Min: "+object1.getString("temperatureMin")+" | Max:"+object1.getString("temperatureMax"));

            JSONObject object2 = array.getJSONObject(2);
            String day2=object2.getString("day");
            String month2=object2.getString("month");
            String dayandate2=day2.concat("," + month2);
            TextView temp2 = (TextView) findViewById(R.id.dayanddate2);
            temp2.setText(dayandate2);
            String imageicon2=object2.getString("imageicon");
            String index2=imageicon2.substring(45, imageicon2.length() - 4);
            ImageView imageIcon2=(ImageView)findViewById(R.id.day2image);
            setImage(imageicon2,imageIcon2);
            TextView temperatureMin2 = (TextView) findViewById(R.id.minandmaxtemp2);
            temperatureMin2.setText("Min: "+object2.getString("temperatureMin")+" | Max:"+object2.getString("temperatureMax"));


            JSONObject object3 = array.getJSONObject(3);
            String day3=object3.getString("day");
            String month3=object3.getString("month");
            String dayandate3=day3.concat("," + month3);
            TextView temp3 = (TextView) findViewById(R.id.dayanddate3);
            temp3.setText(dayandate3);
            String imageicon3=object3.getString("imageicon");
            String index3=imageicon3.substring(45, imageicon3.length() - 4);
            ImageView imageIcon3=(ImageView)findViewById(R.id.day3image);
            setImage(imageicon3, imageIcon3);
            TextView temperatureMin3 = (TextView) findViewById(R.id.minandmaxtemp3);
            temperatureMin3.setText("Min: "+object3.getString("temperatureMin")+" | Max:"+object3.getString("temperatureMax"));



            JSONObject object4 = array.getJSONObject(4);
            String day4=object4.getString("day");
            String month4=object4.getString("month");
            String dayandate4=day4.concat("," + month4);
            TextView temp4 = (TextView) findViewById(R.id.dayanddate4);
            temp4.setText(dayandate4);
            String imageicon4=object4.getString("imageicon");
            String index4=imageicon4.substring(45, imageicon4.length() - 4);
            ImageView imageIcon4=(ImageView)findViewById(R.id.day4image);
            setImage(imageicon4, imageIcon4);
            TextView temperatureMin4 = (TextView) findViewById(R.id.minandmaxtemp4);
            temperatureMin4.setText("Min: "+object4.getString("temperatureMin")+" | Max:"+object4.getString("temperatureMax"));



            JSONObject object5 = array.getJSONObject(5);
            String day5=object5.getString("day");
            String month5=object5.getString("month");
            String dayandate5=day5.concat("," + month5);
            TextView temp5 = (TextView) findViewById(R.id.dayanddate5);
            temp5.setText(dayandate5);
            String imageicon5=object5.getString("imageicon");
            String index5=imageicon5.substring(45, imageicon5.length() - 4);
            ImageView imageIcon5=(ImageView)findViewById(R.id.day5image);
            setImage(imageicon5, imageIcon5);
            TextView temperatureMin5 = (TextView) findViewById(R.id.minandmaxtemp5);
            temperatureMin5.setText("Min: "+object5.getString("temperatureMin")+" | Max:"+object5.getString("temperatureMax"));


            JSONObject object6 = array.getJSONObject(6);
            String day6=object6.getString("day");
            String month6=object6.getString("month");
            String dayandate6=day6.concat("," + month6);
            TextView temp6 = (TextView) findViewById(R.id.dayanddate6);
            temp6.setText(dayandate6);
            String imageicon6=object6.getString("imageicon");
            String index6=imageicon6.substring(45, imageicon6.length() - 4);
            ImageView imageIcon6=(ImageView)findViewById(R.id.day6image);
            setImage(imageicon6, imageIcon6);
            TextView temperatureMin6 = (TextView) findViewById(R.id.minandmaxtemp6);
            temperatureMin6.setText("Min: "+object6.getString("temperatureMin")+" | Max:"+object6.getString("temperatureMax"));



            JSONObject object7 = array.getJSONObject(7);
            String day7=object7.getString("day");
            String month7=object7.getString("month");
            String dayandate7=day7.concat("," + month7);
            TextView temp7 = (TextView) findViewById(R.id.dayanddate7);
            temp7.setText(dayandate7);
            String imageicon7=object7.getString("imageicon");
            String index7=imageicon7.substring(45, imageicon7.length() - 4);
            ImageView imageIcon7=(ImageView)findViewById(R.id.day7image);
            setImage(imageicon7,imageIcon7); //function call to set image in 7 days activity
            TextView temperatureMin7 = (TextView) findViewById(R.id.minandmaxtemp7);
            temperatureMin7.setText("Min: "+object7.getString("temperatureMin")+" | Max:"+object7.getString("temperatureMax"));


        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        onbuttonClick();

    }


    public void setImage(String imageicon,ImageView imageIcon)
    {
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(125, 125);
        imageIcon.setLayoutParams(layoutParams);
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


    }

    private void onbuttonClick() {


        Button button = (Button) findViewById(R.id.next24hours);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                LinearLayout dayslayout1 = (LinearLayout) findViewById(R.id.dayslayout);
                LinearLayout hourslayout1 = (LinearLayout) findViewById(R.id.hourslayout);
                hourslayout1.setVisibility(LinearLayout.VISIBLE);
                dayslayout1.setVisibility(LinearLayout.GONE);

            }

        });

        Button button1 = (Button) findViewById(R.id.next7days);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                LinearLayout dayslayout1 = (LinearLayout) findViewById(R.id.dayslayout);
                LinearLayout hourslayout1 = (LinearLayout) findViewById(R.id.hourslayout);
                dayslayout1.setVisibility(LinearLayout.VISIBLE);
                hourslayout1.setVisibility(LinearLayout.GONE);

            }

        });


        Button button2 = (Button) findViewById(R.id.morebutton);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                expandTable();

            }

        });
    }

    public void expandTable(){

        TextView ntextview,ntextview1;
        ImageView nimageview;
        int k,p;
        try {
            TableRow buttonrow = (TableRow) findViewById(R.id.buttonrow);
            buttonrow.setVisibility(TableRow.GONE);
            TableLayout tl = (TableLayout) findViewById(R.id.hourlytable);

            jsondata = (JSONObject) new JSONObject(outputJSONData);
            JSONArray hourlyarray = jsondata.getJSONArray("hourlydetails");

            for (k = 24; k <= 47; k++)

            {

                TableRow row = new TableRow(this);
                TableRow.LayoutParams LP = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);



                LP.gravity=Gravity.CENTER_HORIZONTAL;
                if(k%2==0) {
                    row.setBackgroundColor(Color.rgb(203, 203, 203));
                }
                else {
                    row.setBackgroundColor(Color.rgb(255,255,255));
                }

                ntextview = new TextView(this);
                nimageview = new ImageView(this);
                ntextview.setLayoutParams(LP);

                ntextview1 = new TextView(this);

                ntextview1.setLayoutParams(LP);
                ntextview.setTextSize(21);

                ntextview.setTextColor(Color.rgb(0, 0, 0));



                ntextview1.setTextSize(21);
                ntextview1.setPadding(50, 40, 50, 40);
                ntextview1.setTextColor(Color.rgb(0, 0, 0));


                JSONObject hourlyobject = hourlyarray.getJSONObject(k);
                String time = hourlyobject.getString("time");
                String imageicon = hourlyobject.getString("imageicon");
                String temp = hourlyobject.getString("temperature");
                //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
                //nimageview.setLayoutParams(layoutParams);
                setImage(imageicon, nimageview);


                ntextview.setText(time);
                ntextview.setPadding(50, 40, 50, 40);
                ntextview1.setText(temp);
                row.addView(ntextview);
                row.addView(nimageview);
                row.addView(ntextview1);
                LP.gravity= Gravity.CENTER_HORIZONTAL;
                row.setLayoutParams(LP);
                p=k+2;
                tl.addView(row,p);


            }
        }
        catch (JSONException e1) {
            e1.printStackTrace();
        }




    }

}

