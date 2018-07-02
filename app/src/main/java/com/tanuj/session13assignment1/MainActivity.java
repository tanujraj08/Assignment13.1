package com.tanuj.session13assignment1;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;



// Extend Activity Class With AppWidget Provider Class
public class MainActivity extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        // Loop Through Available Widgets with in the app and create respective actions for each Widget.
        for (int widgetId : appWidgetIds) {
            // Convert URL String to URI which required to pass on to Intent
            Uri uri = Uri.parse("https://www.acadgild.com");

            // Create An Intent And Flag as New Task, Set URI String as Data.
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(uri);

            // Create a pending Intent for the activity based on the context and pass on the intent to pending activity.
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,intent,0);

            // Get Views From the Layout Resource Find. which will be used to set action listeners
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_main);

            // Set On Click Listener to Button which is in Main Activity Resource.
            // Pass pending intent to View. This will be used to perform action on button click
            remoteViews.setOnClickPendingIntent(R.id.button2, pendingIntent);


            // Finally Update Widget Based on the Widget ID and assign remote view.
            appWidgetManager.updateAppWidget(widgetId,remoteViews);

            // Display Toast on each time Widget Updated.
            Toast.makeText(context, "Widget Has been added", Toast.LENGTH_SHORT).show();
        }
    }
}
