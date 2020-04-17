/*
 *   NAME    : PerInfoActivity.java
 *   Project: Mobile Application Development - Assignment 14
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The PerInfoActivity class has been created to provide a method for user input
 *             such as a user name and city of departure. The PerinfoActivity also has the
 *             ability to show a list of departure cities in a form of dropdown. When a city
 *             of departure is selected, the class shows a toast message of the selected city.
 *             In addition, it provides with a menu to jump to other screens.
 */

package com.example.mytripplanner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import java.util.Locale;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import static com.example.mytripplanner.RequestItemServiceTask.strTTS;


public class PerInfoActivity extends Activity implements TextToSpeech.OnInitListener {
    // properties receive from previous activity
    private Spinner mSpinner = null;
    private ArrayAdapter<String> mSpinnerAdapter = null;
    private TextToSpeech tts;                               // TTS(Text-to-Speech) for Voice Guide
	
    String mDeparture;
    // create Database for store travel information
    ListDB db = new ListDB(this);
    Button btnTTS;                                          // for Voice Guide

    // BGM - Intent for service
    Intent intentBgm;
    Button btnStartBGM;
    Button btnStopBGM;
    // END

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perinfo);
        new RequestItemServiceTask(this).execute();         // for download a JSON file of TTS
        tts = new TextToSpeech(this, this);                 // instantiating TTS(Text-to-Speech)

        // BGM - instantiate Intent for service
        intentBgm = new Intent(this,BackgroundMusicService.class);
        // BGM - start backgroud music service
        // startService(intentBgm);
        // END

        mSpinner = (Spinner) findViewById(R.id.spinner);

        // Adapter for spinner of airport information
        // Adapters are needed to place data intelligently within the list
        // and in order to handle list selection
        mSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                db.getAirportList());

        // In case of dropdown
        mSpinnerAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapter);

        // When a user selects one item in spinner, this handler will receive the action.
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {             // in case of selecting a city
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                mDeparture = adapterView.getItemAtPosition(position).toString();
                // Pop up a toast message to inform a departure information to the user.
                Toast.makeText(PerInfoActivity.this, mDeparture+" selected",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });


        final EditText etName = findViewById(R.id.et_name);

        Button btn = findViewById(R.id.btn_perinfo);
        // When push the Next button put data into intent and send it to next Activity
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       String name = etName.getText().toString();
                                       Data data = new Data(name,mDeparture,"","","","");

                                       Intent intent = new Intent(PerInfoActivity.this,TripInfoActivity.class );
                                       intent.putExtra("data",data);                        // the user name

                                       startActivity(intent);
                                   }
                               }
        );

        btnTTS = findViewById(R.id.btn_TTS);                      // for Voice Guide
        btnTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakOut();
            }
        });

        //BGM - Start Background music service
        btnStartBGM = findViewById(R.id.btn_startbgm);
        btnStartBGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intentBgm);
            }
        });

        //BGM - Stop Background music service
        btnStopBGM = findViewById(R.id.btn_stopbgm);
        btnStopBGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intentBgm);
            }
        });
    }

    // Make a menu option
    // In order to display a menu, use inflate
    // This method is a part of the parent Activity class
    // and must be overridden
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tripmenu, menu);
        return true;
    }

    // Response the activity which is selected by user
    // Menu is defined in the menu file under res/menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = false;
        Intent intent = null;
        switch(item.getItemId()) {
            // When select fiight info menu move into FlightInfoActivity
            case R.id.menu_flightinfo:
                intent = new Intent(this,FlightInfoActivity.class );
                startActivity(intent);
                result = true;
                break;
            // When select home menu move into PerInfoActivity
            case R.id.menu_home:
                intent = new Intent(this, PerInfoActivity.class);
                startActivity(intent);
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }

	
	@Override
    public void onInit(int status) {                                     // for Text-to-Speech
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(getApplicationContext(), "Language not supported", Toast.LENGTH_SHORT).show();
            } else {
                btnTTS.setEnabled(true);
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Init failed", Toast.LENGTH_SHORT).show();
        }
    }


    private void speakOut() {                                                  // for Text-to-Speech
        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), strTTS, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onDone(String s) {
            }

            @Override
            public void onError(String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Error: TTS", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Bundle params = new Bundle();
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
        tts.speak(strTTS, TextToSpeech.QUEUE_FLUSH, params, "Dummy String");
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
