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


public class FragmentTroubleSomeDrug extends Fragment {
    private TextView ivGallery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        TextUtils.hideActionBar((AppCompatActivity) getActivity());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_trouble_some_drug, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        setHasOptionsMenu(true);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("Troublesome Drugs");

        ivGallery = view.findViewById(R.id.ivGallery);
    }
}