package demo.com.mydoctors.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.com.mydoctors.R;

public class FragmentRecurring extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recurring, container, false);
        initView(view);
        return view;
    }
    private void initView(View view){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Cough And Cold (Recurring)");

    }
}
