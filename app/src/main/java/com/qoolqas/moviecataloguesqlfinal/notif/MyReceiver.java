package com.qoolqas.moviecataloguesqlfinal.notif;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.qoolqas.moviecataloguesqlfinal.R;
import com.qoolqas.moviecataloguesqlfinal.activity.MainActivity;
import com.qoolqas.moviecataloguesqlfinal.api.Client;
import com.qoolqas.moviecataloguesqlfinal.api.Service;
import com.qoolqas.moviecataloguesqlfinal.data.Movie;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        String tahun = String.valueOf(calendar.get(Calendar.YEAR));
        String bulan = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String hari = String.valueOf(calendar.get(Calendar.DATE));
        final String release = tahun+"-"+bulan+"-"+hari;
        String client = Client.getApiKey();
        Service service = Client.getClient().create(Service.class);
        Call<Movie> movieCall = service.getRelease(client,release,release);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                for (int i=0; i<response.body().getResults().size();i++){
                    Intent intent1 = new Intent(context, MainActivity.class);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,10,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationManager daily = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context,response.body().getResults().get(i).getTitle())
                            .setContentIntent(pendingIntent)
                            .setContentTitle(context.getResources().getString(R.string.app_name))
                            .setContentText(response.body().getResults().get(i).getTitle())
                            .setSmallIcon(R.id.id_toolbar_container)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                            .setAutoCancel(true);

                    Notification notification = builder.build();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        NotificationChannel channel = new NotificationChannel(response.body().getResults().get(i).getTitle()
                        ,response.body().getResults().get(i).getTitle(),NotificationManager.IMPORTANCE_DEFAULT);

                        builder.setChannelId(response.body().getResults().get(i).getTitle());

                        if (daily != null){
                            daily.createNotificationChannel(channel);
                        }
                    }
                    if (daily != null){
                        daily.notify(i,notification);
                    }

                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

    }
}
