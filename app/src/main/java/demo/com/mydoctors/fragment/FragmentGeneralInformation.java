package demo.com.mydoctors.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.com.mydoctors.R;

public class FragmentGeneralInformation extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_general_info, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("General Information");
    }


}
