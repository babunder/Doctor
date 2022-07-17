package demo.com.mydoctors.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.com.mydoctors.R;
import demo.com.mydoctors.TextUtils;


public class FragmentChestTrouble extends Fragment {
    private TextView ivGallery,textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        TextUtils.hideActionBar((AppCompatActivity) getActivity());
        super.onCreate(savedInstanceState);
        // My Check
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_chest_trouble, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        setHasOptionsMenu(true);


        String title = getArguments().getString("title");
        String data = getArguments().getString("data");

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);


        textView = view.findViewById(R.id.textWomens);
        textView.setText(data);



        ivGallery = view.findViewById(R.id.ivGallery);
    }
}