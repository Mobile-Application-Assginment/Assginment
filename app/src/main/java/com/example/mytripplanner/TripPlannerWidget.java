/*
 *   NAME    : TripPlannerWidget.java
 *   Project: Mobile Application Development - Assignment 3
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Apr. 17, 2020
 *   PURPOSE : The TripPlannerWidget class has been created to App widget for
 *             the most recent travel information
 *             such as a name, a departure, a destination . The TripPlannerWidget also has the
 *             ability to refresh information using broadcast technic.
 */

package com.example.mytripplanner;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class TripPlannerWidget extends AppWidgetProvider {

    //Define action to broadcast and receive between app and widget
    private  static  final String ACTION_UPDATE = "com.example.mytripplanner.BUTTON_UPDATE";
    private static final String TAG = "MyTripPlannerWidget";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);

        // connect DB to get recent travel information.
        ListDB db = new ListDB(context);
        // Construct the RemoteViews object that provides access to widget view
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.trip_planner_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // get the most recent travel data from database.
        Task recentInfo = db.getLastTask();
        int recentUserId = recentInfo.getUserId();
        int recentDepId = recentInfo.getDepatureAirportId();
        int recentDestId = recentInfo.getDestinationAirportId();

        // set widget text with data fetched from database
        views.setTextViewText(R.id.wztxt_name,db.getUserName(recentUserId));
        views.setTextViewText(R.id.wztxt_departure,db.getAirportName(recentDepId));
        views.setTextViewText(R.id.wztxt_destination,db.getAirportName(recentDestId));

        // create intent to use broadcast for update widget data.
        Intent intent = new Intent(context,TripPlannerWidget.class);
        // set action to intent for pushing refresh button.
        intent.setAction(ACTION_UPDATE);
        // after pushing update button, callback onRecieve method.
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0 );
        // set pendingIntent for widget update button
        views.setOnClickPendingIntent(R.id.btn_update,pendingIntent);
        Log.d(TAG, "pendingIntent");
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    //Receive action when update button pushed in app widget
    //Make data for onUpdate method and call it again to update screen
    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        Log.d(TAG, "onReceive() action = " + action);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidget = new ComponentName(context.getPackageName(),
                TripPlannerWidget.class.getName());
        int[] appWidgets = appWidgetManager.getAppWidgetIds(thisAppWidget);
        // if action is ACTION_UPDATE for update button on widget
        if(action.equals(ACTION_UPDATE)){
            Toast.makeText(context,"Refresh Widget",Toast.LENGTH_LONG).show();
            onUpdate(context, appWidgetManager, appWidgets);
        }
        super.onReceive(context,intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

