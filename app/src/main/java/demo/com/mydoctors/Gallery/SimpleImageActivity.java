package demo.com.mydoctors.Gallery;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import demo.com.mydoctors.R;

public class SimpleImageActivity extends FragmentActivity {
    public static String frScreen = "";
    private List<String> listOfImages = new ArrayList<>();
    private Bundle mBundle = new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int frIndex = getIntent().getIntExtra(Constants.Extra.FRAGMENT_INDEX, 0);
        frScreen = getIntent().getStringExtra(Constants.FRAGMENT_SCREEN);
        listOfImages = getIntent().getStringArrayListExtra(Constants.ARGUMENT_IMAGE_LIST);

        Fragment fr;
        String tag;
        int titleRes;
        switch (frIndex) {
            default:
            case ImageGridFragment.INDEX:
                tag = ImageGridFragment.class.getSimpleName();
                fr = getSupportFragmentManager().findFragmentByTag(tag);
                if (fr == null) {
                    fr = new ImageGridFragment();
                }

                titleRes = R.string.ac_name_image_gallery;
                break;
            case ImagePagerFragment.INDEX:
                tag = ImagePagerFragment.class.getSimpleName();
                fr = getSupportFragmentManager().findFragmentByTag(tag);
                if (fr == null) {
                    fr = new ImagePagerFragment();
                    mBundle.putBundle(Constants.Extra.IMAGE_POSITION, getIntent().getExtras());
                }
                titleRes = R.string.ac_name_image_pager;
                break;
        }

        mBundle.putString("screen", frScreen);
        mBundle.putStringArrayList(Constants.ARGUMENT_IMAGE_LIST, (ArrayList<String>) listOfImages);
        fr.setArguments(mBundle);

        setTitle(titleRes);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, tag).commit();
    }
}