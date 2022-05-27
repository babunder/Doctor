package demo.com.mydoctors.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.com.mydoctors.R;
import demo.com.mydoctors.TextUtils;

public class FragmentHeadAndNeck extends Fragment {
    private TextView ivGallery,textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        TextUtils.hideActionBar((AppCompatActivity) getActivity());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_head_neck, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        setHasOptionsMenu(true);


        String title = getResources().getString(R.string.uncommon_wisdom);
        String data = getResources().getString(R.string.uncommon_wisdom_data);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);


        textView = view.findViewById(R.id.textWomens);
        textView.setText(data);

        ivGallery = view.findViewById(R.id.ivGallery);
    }
}