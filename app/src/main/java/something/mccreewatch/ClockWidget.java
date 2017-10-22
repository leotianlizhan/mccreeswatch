package something.mccreewatch;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ClockWidget extends AppWidgetProvider{
    RemoteViews views;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        //find out the action
        String action = intent.getAction();
        //is it time to update
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
        {
            views = new RemoteViews(context.getPackageName(), R.layout.clock_widget_layout);
            AppWidgetManager.getInstance(context).updateAppWidget(intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS), views);
        }
    }
}
