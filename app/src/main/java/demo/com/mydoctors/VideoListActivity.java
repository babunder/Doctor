package demo.com.mydoctors;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.com.mydoctors.Gallery.Constants;
import demo.com.mydoctors.model.VideoModel;
import demo.com.mydoctors.webutil.Webutil;

public class VideoListActivity extends AppCompatActivity {

    public static ArrayList<VideoModel> videoArrayList;
    RecyclerView recyclerView;
    public static final int PERMISSION_READ = 0;
    String SCREEN_VIDEO_ACTIVITY = "Video";
    public  String frScreen = "";
    public  String diseasesId = "";

    List<String> listOfVideo = new ArrayList<>();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setTitle(SCREEN_VIDEO_ACTIVITY);
        frScreen = getIntent().getStringExtra(Constants.FRAGMENT_SCREEN);
        diseasesId = Constants.getDiseasesId(frScreen);

        Webutil.getVideos(this, diseasesId, new HandlerLogin());

    }

    /**
     * Method to get the Feedback api response
     */
    class HandlerLogin extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("error_code").equalsIgnoreCase("0")) {

                    JSONArray jsonArray = jsonObject.getJSONArray("img_path");

                    listOfVideo = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        listOfVideo.add(jsonArray.getString(i));
                    }

                    videoList();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void videoList() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        videoArrayList = new ArrayList<>();
        getVideos();
    }

    public void getVideos() {
        for (int i = 0; i < listOfVideo.size(); i++) {
            videoArrayList.add(getVideoModel(listOfVideo.get(i), getResources().getDrawable(R.drawable.ic_video_default)));
        }

       /* VideoModel videoModel = getVideoModel("android.resource://" + getPackageName() + "/raw/ic_video1", getResources().getDrawable(R.drawable.iv_vid1));
        VideoModel videoModel2 = getVideoModel("android.resource://" + getPackageName() + "/raw/ic_video2", getResources().getDrawable(R.drawable.iv_vid2));
        VideoModel videoModel3 = getVideoModel("android.resource://" + getPackageName() + "/raw/ic_video3", getResources().getDrawable(R.drawable.iv_vid3));
        videoArrayList.add(videoModel);
        videoArrayList.add(videoModel2);
        videoArrayList.add(videoModel3);*/

        VideoAdapter adapter = new VideoAdapter(this, VideoListActivity.this, videoArrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View v) {
               /* Intent intent = new Intent(getApplicationContext(), VideoPlayActivity.class);
                intent.putExtra("pos", pos);
                startActivity(intent);*/
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listOfVideo.get(pos)));
                startActivity(intent);
            }
        });

    }

    @NotNull
    private VideoModel getVideoModel(String link, Drawable drawable) {
        VideoModel videoModel = new VideoModel();
        videoModel.setVideoTitle("");
        //Uri uri = Uri.parse(uriPath);
        videoModel.setVideoDrwable(drawable);
        videoModel.setVideoLink(link);
        //videoModel2.setVideoDuration(timeConversion(Long.parseLong(getDuration("android.resource://" + getPackageName() + "/" + "R.raw.ic_video1"))));
        videoModel.setVideoDuration("");
        return videoModel;
    }

    public String getDuration(String video) {
        String time;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(video, new HashMap<String, String>());
        time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        return time;
    }


    //time conversion
    public String timeConversion(long value) {
        String videoTime;
        int dur = (int) value;
        int hrs = (dur / 3600000);
        int mns = (dur / 60000) % 60000;
        int scs = dur % 60000 / 1000;

        if (hrs > 0) {
            videoTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
        } else {
            videoTime = String.format("%02d:%02d", mns, scs);
        }
        return videoTime;
    }
}