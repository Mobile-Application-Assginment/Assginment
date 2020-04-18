
//
//  NAME        : MyPhotoAdapter.java
//  Project     : Mobile Application Development - Assignment 3
//  By          : Charng Gwon Lee, Hyungbum Kim, Younchul Cho
//  Date        : April 17, 2020
//  PURPOSE     : The MyPhotoAdapter class inheritance the CursorAdapter.
//                  It will get the photo from the gallery (System), then display in the image view.
//

package com.example.mytripplanner;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

public class MyPhotoAdapter extends CursorAdapter {
    public MyPhotoAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    //	Name	: newView
    //	Purpose : If it is a new one, make and pass to bindView
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false);
    }

    //	Name	: newView
    //	Purpose : Binding the image and view
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView imageView = view.findViewById(R.id.photo_image);

        // Get the photo file path (URI)
        String uri = cursor.getString (cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

        // Shows the photo on image view
        imageView.setImageURI(Uri.parse(uri));
    }
}
