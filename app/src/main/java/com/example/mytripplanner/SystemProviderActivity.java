/*
 *   NAME    : SystemProviderActivity.java
 *   Project: Mobile Application Development - Assignment 3
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: April 18, 2020
 *   PURPOSE : The SystemProviderActivity class is access the gallery using the system content provider.
 *             It is going to check the permission first, then get the photo data from the system.
 */

package com.example.mytripplanner;

//import android.Manifest;
//import android.app.Activity;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.GridView;
//import android.widget.TextView;
//import android.widget.Toast;



import androidx.core.content.ContextCompat;
//
//public class SystemProviderActivity extends Activity {
//    private static final int PERMISSIONS_REQUEST_READ_CONTACTS=1;
//
//    public TextView ScreenOutput;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_system_provider);
//        ScreenOutput = findViewById(R.id.textView1);
//
//
//        // Check the permission
//        int permissionCheck = ContextCompat.checkSelfPermission(SystemProviderActivity.this,
//                Manifest.permission.READ_CONTACTS);
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissionCheck != PackageManager.PERMISSION_GRANTED)
//        {
//            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
//        }
//        else
//        {
//            loadContacts();
//        }
//    }
//
//    private void loadContacts()
//    {
//        StringBuffer output = new StringBuffer();
//        Uri uri = ContactsContract.Contacts.CONTENT_URI;
//        String[] projection = new String[] {
//                ContactsContract.Contacts._ID,
//                ContactsContract.Contacts.DISPLAY_NAME,
//                ContactsContract.Contacts.HAS_PHONE_NUMBER,
//        };
//        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
//
//        if (cursor.getCount() > 0) {
//            while (cursor.moveToNext()) {
//                if (cursor.getInt(2) == 1)
//                {
//                    output.append("\nName:" + cursor.getString(1));
//                    output = loadPhoneNumbers(cursor.getString(0), output);
//                }
//            }
//            ScreenOutput.setText(output);
//        }
//    }
//
//
//    private StringBuffer loadPhoneNumbers(String id, StringBuffer output)
//    {
//        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        String[] projection = new String[] {
//                ContactsContract.CommonDataKinds.Phone.TYPE,
//                ContactsContract.CommonDataKinds.Phone.NUMBER
//        };
//        String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?";
//        Cursor cursor = getContentResolver().query(uri, projection, selection, new String[] {id}, null);
//        while (cursor.moveToNext())
//        {
//            output.append(getNumberType(Integer.parseInt(cursor.getString(0))) + "Phone Number:" + cursor.getString(1));
//        }
//        return output;
//    }
//
//
//    private String getNumberType(int type)
//    {
//        String numberType = null;
//
//        switch (type)
//        {
//            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
//                numberType = "\nMobile ";
//                break;
//            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
//                numberType = "\nHome ";
//                break;
//        }
//
//        return numberType;
//    }


////////////////////////////////////////
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSIONS_REQUEST_READ_CONTACTS: {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    LoadContacts();
//                } else {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                    Log.d("My App", "permission denied");
//                }
//                return;
//            }
//        }
//    }
////////////////////////////////////////
//}



//==================================================================================================================
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class SystemProviderActivity extends Activity {
    private static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    //	Name	: onCreate
    //	Purpose : create system provider
    //          	to access the gallery using the system content provider.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_provider);

        // Check the permission to read from external storage
        int permissionCheck = ContextCompat.checkSelfPermission(SystemProviderActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        // It does not have permission problem if SDK is lower than 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            // If permission is not acceptable, request to accept
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
        else
        {
            // Get and display the photo
            getPhoto();
        }
    }

    //	Name	: getPhoto
    //	Purpose : Get photo data from EXTERNAL_CONTENT_URI
    public void getPhoto()
    {
        // Get photo data cursor
        Cursor cursor = getContentResolver().query
                (MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        null,null,null,
                        MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");// Order By

        // Set the view
        GridView photoListView = findViewById(R.id.photo_list);

        // Get the adapter
        MyPhotoAdapter adapter = new MyPhotoAdapter(this, cursor);
        photoListView.setAdapter(adapter);

        // Process the click event
        photoListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // Cursor data which is clicked photo
                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                String path = cursor.getString
                        (cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                Toast.makeText(SystemProviderActivity.this, "File path : " + path,
                        Toast.LENGTH_SHORT).show();

            }
        });
    }


    //	Name	: onRequestPermissionsResult
    //	Purpose : Check permission and call getphoto method
    @Override
    public void onRequestPermissionsResult(int code, String[] permissions, int[] result)
    {
        switch (code)
        {
            case PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                {
                if (result[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // Get the photo, if permission is accept
                    getPhoto();
                }
                else
                {
                    // Permission is denied
                    Log.d("Get Photo from gallery", "permission denied");
                }

                return;
            }
        }
    }
}