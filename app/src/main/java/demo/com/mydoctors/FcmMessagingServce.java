package demo.com.mydoctors;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FcmMessagingServce extends FirebaseMessagingService {

    private NotificationCompat.Builder mBuilder;
    private Context mContext;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        Log.e("test","fcm_token:"+s);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        String title=remoteMessage.getNotification().getTitle();
        String message=remoteMessage.getNotification().getBody();
        createNotificationChannel();

        Log.d("test","title:"+title);
        Log.d("test","message:"+message);

        Intent intent=new Intent(this, ActivityMain.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestId= (int) System.currentTimeMillis();
        PendingIntent pendingIntent=PendingIntent.getActivity(this,requestId,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBulder=new NotificationCompat.Builder(this,"mychannel");
        notificationBulder.setContentTitle(title);
        notificationBulder.setContentText(message);
        notificationBulder.setColor(getResources().getColor(R.color.colorPrimary));
        notificationBulder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));

        notificationBulder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //notificationBulder.setVisibility(NotificationCompat.PRIORITY_DEFAULT);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d("test29","Lolipop and above version");
            notificationBulder.setSmallIcon(R.drawable.ic_splash);
            notificationBulder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_splash));
        }

        notificationBulder.setAutoCancel(true);
        notificationBulder.setSmallIcon(R.drawable.ic_splash);
        notificationBulder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_splash));
        notificationBulder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
        notificationBulder.setColor(getResources().getColor(R.color.colorPrimary));
        notificationBulder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        notificationBulder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        notificationBulder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("mychannel", "Channel human readable title", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);


            Intent intent1=new Intent(this, ActivityMain.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            NotificationCompat.Builder notificationBulder1=new NotificationCompat.Builder(this,"mychannel");
            notificationBulder1.setContentTitle(title);
            notificationBulder1.setContentText(message);
            notificationBulder1.setSmallIcon(R.drawable.ic_splash);
            notificationBulder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_splash));
            notificationBulder1.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
            notificationBulder1.setVisibility(Notification.VISIBILITY_PUBLIC);
            notificationBulder1.setChannelId("mychannel");
            notificationBulder1.build();


        }
        notificationManager.notify(requestId,notificationBulder.build());
    }

    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Notification";
            String description ="include personal notification";
            int importance=NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }
}
