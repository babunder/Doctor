
package demo.com.mydoctors.Gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import demo.com.mydoctors.R;

public class SimpleImageActivity extends FragmentActivity {
    public static String frScreen = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int frIndex = getIntent().getIntExtra(Constants.Extra.FRAGMENT_INDEX, 0);
        frScreen = getIntent().getStringExtra(Constants.FRAGMENT_SCREEN);

        Bundle bundle = new Bundle();

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
                    bundle.putBundle(Constants.Extra.IMAGE_POSITION, getIntent().getExtras());
                }
                titleRes = R.string.ac_name_image_pager;
                break;
        }

        bundle.putString("screen", frScreen);
        fr.setArguments(bundle);

        setTitle(titleRes);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, tag).commit();
    }
}