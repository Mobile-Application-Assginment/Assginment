package com.example.mytripplanner;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContentProviderActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);


        // The button which is back to the Home screen
        Button btn = findViewById(R.id.btnHome);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Intent intent = new Intent(ContentProviderActivity.this,PerInfoActivity.class );
                                       startActivity(intent);
                                   }
                               }
        );
    }


    // Add new airport when user insert
    public void onClickAdd(View view) {

        String airportName = ((EditText) findViewById(R.id.txtName)).getText().toString();
        TextView resultView= (TextView) findViewById(R.id.res);

        if(!airportName.isEmpty())
        {
            boolean existed = false;

            Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);
            if(cursor != null)
            {
                while(cursor.moveToNext()) {
                    String str = cursor.getString(cursor.getColumnIndex("airport_name"));

                    if(airportName.equals(str))
                    {
                        existed = true;
                        break;
                    }
                }
            }

            if(existed == false)
            {
                ContentValues values = new ContentValues();

                // Get the name of airport from the edit text box
                values.put(MyContentProvider.name, airportName);

                // Insert the new airport name
                getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

                // Show a Toast
                Toast.makeText(getBaseContext(), "New Airport Inserted", Toast.LENGTH_LONG).show();
            }
            else
            {
                resultView.setText(airportName + " is already existed.");
            }
        }
        else
        {
            resultView.setText("You have to put the name of airport.");
        }


    }

    // Get the all airport and display
    public void onClickShow(View view) {
        // Retrieve employee records
        TextView resultView= (TextView) findViewById(R.id.res);
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);

        // Get the all airport name
        if(cursor != null)
        {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("ID" + "        " + "Name\n");
            while(cursor.moveToNext()) {
                String str = cursor.getString(cursor.getColumnIndex("_id")) + "     " + cursor.getString(cursor.getColumnIndex("airport_name"));
                stringBuilder.append(str + "\n");
            }
            cursor.close();

            // Print the result on the Text view
            resultView.setText(stringBuilder);
        }
        else {
            resultView.setText("Could not found the name of airport");
        }
    }

}


