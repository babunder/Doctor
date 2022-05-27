package demo.com.mydoctors;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import demo.com.mydoctors.model.VideoModel;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.viewHolder> {

    Context context;
    Activity activity;
    ArrayList<VideoModel> videoArrayList;
    public OnItemClickListener onItemClickListener;

    public VideoAdapter(Context context, Activity activity, ArrayList<VideoModel> videoArrayList) {
        this.context = context;
        this.activity = activity;
        this.videoArrayList = videoArrayList;
    }

    @Override
    public VideoAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_list, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VideoAdapter.viewHolder holder, final int i) {
        holder.title.setText(videoArrayList.get(i).getVideoLink());
        holder.duration.setText(videoArrayList.get(i).getVideoDuration());
        holder.image.setBackground(videoArrayList.get(i).getVideoDrwable());
    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, duration;
        LinearLayout llback;
        WebView webView;
        ImageView image;

        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            llback = (LinearLayout) itemView.findViewById(R.id.llback);
            duration = (TextView) itemView.findViewById(R.id.duration);
            image = (ImageView) itemView.findViewById(R.id.image);
            webView = (WebView) itemView.findViewById(R.id.webView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition(), v);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, View v);
    }
}