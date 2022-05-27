package demo.com.mydoctors.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.com.mydoctors.ActivityMain;
import demo.com.mydoctors.R;

public class FragmentDoDontCoughCold extends Fragment implements View.OnClickListener {

    TextView txt_recurring;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_all_no,container,false);
        initView(view);
        return view;
    }

    private void initView(View view){
       // setHasOptionsMenu(true);
        txt_recurring=view.findViewById(R.id.txt_recurring);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Cough And Cold");

        txt_recurring.setOnClickListener(this);

    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // TODO Add your menu entries here
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_recurring:
                ((ActivityMain)getActivity()).replaceFragment(new FragmentRecurring());
                break;

        }
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.txt_recurring:
                ((ActivityMain)getActivity()).replaceFragment(new FragmentRecurring());
                break;
        }

    }
}
