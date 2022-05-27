package demo.com.mydoctors.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;

public class VideoModel  {

    String videoTitle;
    String videoDuration;
    Drawable videoDrwable;
    String videoLink;

    public Drawable getVideoDrwable() {
        return videoDrwable;
    }

    public void setVideoDrwable(Drawable videoDrwable) {
        this.videoDrwable = videoDrwable;
    }

    Uri videoUri;

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public Uri getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(Uri videoUri) {
        this.videoUri = videoUri;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String mVideoLink) {
        this.videoLink = mVideoLink;
    }
}