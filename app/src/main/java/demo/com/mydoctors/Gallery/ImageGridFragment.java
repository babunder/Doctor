package demo.com.mydoctors.Gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import demo.com.mydoctors.R;
import demo.com.mydoctors.model.DiseasesDetails;
import demo.com.mydoctors.webutil.Webutil;

public class ImageGridFragment extends AbsListViewBaseFragment {

    public static final int INDEX = 1;
    // private static String[] IMAGE_URLS = null;
    String screenName;
    List<String> listOfImages = new ArrayList<>();
    String diseasesId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_image_grid, container, false);
        listView = (GridView) rootView.findViewById(R.id.grid);

        Bundle bundle = this.getArguments();
        screenName = bundle.getString("screen");

        diseasesId = Constants.getDiseasesId(screenName);

        listOfImages = Constants.ARRAY_LIST_IMAGES;
        ((GridView) listView).setAdapter(new ImageAdapter(getActivity()));
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startImagePagerActivity(position, screenName);
            }
        });

        return rootView;
    }

    public class ImageAdapter extends BaseAdapter {
        //  private static final String[] IMAGE_URLS = Constants.IMAGES;
        private final LayoutInflater inflater;
        private final DisplayImageOptions options;

        ImageAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.ic_stub)
                    .showImageForEmptyUri(R.drawable.ic_empty)
                    .showImageOnFail(R.drawable.ic_error)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }

        @Override
        public int getCount() {
            return listOfImages.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.item_grid_image, parent, false);
                holder = new ViewHolder();
                assert view != null;
                holder.imageView = (ImageView) view.findViewById(R.id.image);
                holder.progressBar = (ProgressBar) view.findViewById(R.id.progress);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            ImageLoader.getInstance()
                    .displayImage(listOfImages.get(position), holder.imageView, options, new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            holder.progressBar.setProgress(0);
                            holder.progressBar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    }, new ImageLoadingProgressListener() {
                        @Override
                        public void onProgressUpdate(String imageUri, View view, int current, int total) {
                            holder.progressBar.setProgress(Math.round(100.0f * current / total));
                        }
                    });

            return view;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    static class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
    }
}