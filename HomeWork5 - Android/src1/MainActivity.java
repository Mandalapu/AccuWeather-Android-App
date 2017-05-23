package com.example.manohar.weatherapp;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.manohar.weatherapp.library.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity  {

    ImageButton imageButton;
    String url;
    String city;
    //String url = "http://webtechproject-env.elasticbeanstalk.com/index.php?completeaddress=2353+Portland+Street%2CLos+Angeles%2CCA&units=us";
    String units;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        aboutButton();
        addListenerOnButton();
        processData();
        cleardata();



    }



    private void aboutButton() {


        final Context context = this;

        Button button = (Button) findViewById(R.id.about);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, About.class);
                startActivity(intent);

            }

        });

    }

    private void processData() {

        Button button = (Button) findViewById(R.id.search);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //   url = "http://webtechproject-env.elasticbeanstalk.com/index.php?completeaddress=2353+Portland+Street%2CLos+Angeles%2CCA&units=us";
                EditText street1 = (EditText) findViewById(R.id.street_address);

                Spinner mySpinner = (Spinner) findViewById(R.id.states_spinner);
                String state = mySpinner.getSelectedItem().toString();





                String streetAddress = street1.getText().toString();
                EditText city1 = (EditText) findViewById(R.id.city);
                String city = city1.getText().toString();
                RadioButton rdbtn=(RadioButton)findViewById(R.id.radioFahrenheit);
                RadioButton rdbtn1=(RadioButton)findViewById(R.id.radioCelsius);

                if(rdbtn1.isChecked())
                {
                   units="si";
                }
                else
                {
                    units="us";
                }




                //url = "http://webtechproject-env.elasticbeanstalk.com/index.php?completeaddress=2353+Portland+Street%2CLos+Angeles%2CCA&units=us";

                url="http://webtechproject-env.elasticbeanstalk.com/index.php?";

                url=url.concat("completeaddress=");
                url=url.concat(Uri.encode(streetAddress));



                url=url.concat(Uri.encode(city));


                url=url.concat(Uri.encode(state));

                url=url.concat("&units=");
                url=url.concat(units);

                //url="http://weatherforecastwebapp-env.elasticbeanstalk.com/index.php?str=App+No%3A202%2C+1210+West+Adams&cityname=Los+Angeles&statename=CA&degree=Farenheit";

               // url = "http://weatherforecastwebapp-env.elasticbeanstalk.com/index.php?str=" + streetAddress + "&cityname=" + city + "&statename=" + state + "&degree=" + units;
               if(validateform()) {
                   new JSONParse().execute();
               }



        }


        });
    }


public boolean validateform() {

    TextView errortext = (TextView) findViewById(R.id.errorclasslabel);
    EditText streetid = (EditText) findViewById(R.id.street_address);
    EditText cityid = (EditText) findViewById(R.id.city);
    Spinner mySpinnerid = (Spinner) findViewById(R.id.states_spinner);
    String statevalue = mySpinnerid.getSelectedItem().toString();
    String streetaddvalue = streetid.getText().toString();
    String cityvalue = cityid.getText().toString();

    if (streetaddvalue.trim().isEmpty()) {
        errortext.setText("Please enter street address");
        errortext.setVisibility(View.VISIBLE);
        return false;

    }
    if (cityvalue.trim().isEmpty()) {
        errortext.setText("Please enter city");
        errortext.setVisibility(View.VISIBLE);
        return false;

    }
    if (statevalue.trim().contentEquals("Select")) {
        errortext.setText("Please enter state");
        errortext.setVisibility(View.VISIBLE);
        return false;

    }
    else {
        errortext.setVisibility(View.GONE);
        return true;
    }
}

    public void cleardata()
    {
        Button button = (Button) findViewById(R.id.clear);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView errortext1 = (TextView) findViewById(R.id.errorclasslabel);
                EditText streetid1 = (EditText) findViewById(R.id.street_address);
                EditText cityid1 = (EditText) findViewById(R.id.city);
                Spinner mySpinnerid1 = (Spinner) findViewById(R.id.states_spinner);
                RadioButton rdbtn=(RadioButton)findViewById(R.id.radioFahrenheit);
                rdbtn.setChecked(true);
                streetid1.setText("");
                cityid1.setText("");
                mySpinnerid1.setSelection(0);
                errortext1.setVisibility(View.GONE);
            }
        });
    }
    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        ProgressDialog pDialog;


        public JSONParse() {
            try {
                Class.forName("android.os.AsyncTask");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            pDialog = new ProgressDialog(MainActivity.this);
        }




        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            //pDialog.setMessage("do in background Data");


            JSONObject json = jParser.getJSONFromUrl(url);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            try {
                // Getting JSON Array\




                pDialog.setMessage(json.getString("latitude"));


                // Storing  JSON item in a Variable
                Spinner mySpinner = (Spinner) findViewById(R.id.states_spinner);
                String state = mySpinner.getSelectedItem().toString();
                EditText city1 = (EditText) findViewById(R.id.city);
                String city = city1.getText().toString();
                Intent intent_result_activity = new Intent(MainActivity.this, ResultActivity.class);
                intent_result_activity.putExtra("RESULT_ACTIVITY_JSON_STRING", json.toString());
                intent_result_activity.putExtra("city",city);
                intent_result_activity.putExtra("state", state);
                intent_result_activity.putExtra("UNITS",units);
                intent_result_activity.putExtra("URL",url);
                startActivity(intent_result_activity);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            pDialog.dismiss();
        }


    }

    public void addListenerOnButton() {

        imageButton = (ImageButton) findViewById(R.id.imageButton1);

        imageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Uri uri = Uri.parse("http://forecast.io");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
