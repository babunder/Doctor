package demo.com.mydoctors.Gallery;

import java.util.ArrayList;
import java.util.List;

import demo.com.mydoctors.R;

public final class Constants {

    public static final String DISEASE_ID_BACK = "back001";
    public static final String DISEASE_ID_CHEST = "chest001";
    public static final String DISEASE_ID_COUGH = "cough002";
    public static final String DISEASE_ID_DENTAL = "dental001";
    public static final String DISEASE_ID_FEVER = "fever001";
    public static final String DISEASE_ID_KNEE = "knee001";
    public static final String DISEASE_ID_LOOSE_MOTION = "loose_motion001";
    public static final String DISEASE_ID_WOMEN = "women001";

    public static final String[] IMAGES = new String[]{
            // Heavy images
            "https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg",
            "https://lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s1024/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg",
            "https://lh5.googleusercontent.com/-7qZeDtRKFKc/URquWZT1gOI/AAAAAAAAAbs/hqWgteyNXsg/s1024/Another%252520Rockaway%252520Sunset.jpg",
            "https://lh3.googleusercontent.com/--L0Km39l5J8/URquXHGcdNI/AAAAAAAAAbs/3ZrSJNrSomQ/s1024/Antelope%252520Butte.jpg",
            "https://lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s1024/Antelope%252520Hallway.jpg",
            "https://lh4.googleusercontent.com/-WIuWgVcU3Qw/URqubRVcj4I/AAAAAAAAAbs/YvbwgGjwdIQ/s1024/Antelope%252520Walls.jpg",
            "https://lh6.googleusercontent.com/-UBmLbPELvoQ/URqucCdv0kI/AAAAAAAAAbs/IdNhr2VQoQs/s1024/Apre%2525CC%252580s%252520la%252520Pluie.jpg",
            "https://lh3.googleusercontent.com/-s-AFpvgSeew/URquc6dF-JI/AAAAAAAAAbs/Mt3xNGRUd68/s1024/Backlit%252520Cloud.jpg",
            "https://lh5.googleusercontent.com/-bvmif9a9YOQ/URquea3heHI/AAAAAAAAAbs/rcr6wyeQtAo/s1024/Bee%252520and%252520Flower.jpg",
            "https://lh5.googleusercontent.com/-n7mdm7I7FGs/URqueT_BT-I/AAAAAAAAAbs/9MYmXlmpSAo/s1024/Bonzai%252520Rock%252520Sunset.jpg",
            "https://lh6.googleusercontent.com/-4CN4X4t0M1k/URqufPozWzI/AAAAAAAAAbs/8wK41lg1KPs/s1024/Caterpillar.jpg",
            "https://lh3.googleusercontent.com/-rrFnVC8xQEg/URqufdrLBaI/AAAAAAAAAbs/s69WYy_fl1E/s1024/Chess.jpg",
            "https://lh5.googleusercontent.com/-WVpRptWH8Yw/URqugh-QmDI/AAAAAAAAAbs/E-MgBgtlUWU/s1024/Chihuly.jpg",
    };


    public static final String[] IMAGES_COUGH_COLD = new String[]{
            "drawable://" + R.drawable.cough,
            "drawable://" + R.drawable.cough1,
            "drawable://" + R.drawable.cough2,
            "drawable://" + R.drawable.cough3,
    };


    public static final String[] IMAGES_LOOSE_MOTION = new String[]{
            "drawable://" + R.drawable.stomach1,
            "drawable://" + R.drawable.stomach2,
            "drawable://" + R.drawable.stomach3,
            "drawable://" + R.drawable.lose_motion,
    };

    public static final String[] IMAGES_KNEE_ACHE = new String[]{
            "drawable://" + R.drawable.knee,
            "drawable://" + R.drawable.knee1,
            "drawable://" + R.drawable.knee2,
            "drawable://" + R.drawable.knee3,
    };

    public static final String[] IMAGES_BACK_ACHE = new String[]{
            "drawable://" + R.drawable.backpainpok,
            "drawable://" + R.drawable.backpainimg1,
            "drawable://" + R.drawable.backpainimg2,
            "drawable://" + R.drawable.backpainimg3,
            "drawable://" + R.drawable.backpainimg4,
            "drawable://" + R.drawable.backpainimg5,
            "drawable://" + R.drawable.backpainimg6,
            "drawable://" + R.drawable.backpainimg7,
            "drawable://" + R.drawable.backpainyoga,
    };
    public static final String[] IMAGES_FEVER = new String[]{
            "drawable://" + R.drawable.fever,
            "drawable://" + R.drawable.fever1,
            "drawable://" + R.drawable.fever2,
            "drawable://" + R.drawable.fever3,
    };
    public static final String[] IMAGES_DENTIST = new String[]{
            "drawable://" + R.drawable.teeth1,
            "drawable://" + R.drawable.teeth2,
            "drawable://" + R.drawable.teeth3,
            "drawable://" + R.drawable.teeth4,
    };
    public static final String[] IMAGES_PREGNANCY = new String[]{
            "drawable://" + R.drawable.pregnancy1,
            "drawable://" + R.drawable.pregnancy2,
            "drawable://" + R.drawable.pregnancy3,
            "drawable://" + R.drawable.pregnancy4,
    };
    public static final String[] IMAGES_EMERGENCY_CHOKING = new String[]{
            "drawable://" + R.drawable.emergency_chok_one,
            "drawable://" + R.drawable.emergency_chok_two,
            "drawable://" + R.drawable.emergency_chok_three,
            "drawable://" + R.drawable.ic_child_heimlichmanueuver,
            "drawable://" + R.drawable.ic_child_heimlicmanueuver04,
            "drawable://" + R.drawable.ic_adult_heimlichmanueuver02,
            "drawable://" + R.drawable.ic_adult_heimlichmanueuver03,
            "drawable://" + R.drawable.ic_infant_heimlichmanueuver,
            "drawable://" + R.drawable.ic_infant_heimlichmanueuver01,
    };
    public static final String FRAGMENT_SCREEN = "com.nostra13.example.universalimageloader.FRAGMENT_SCREEN";
    public static final String ARGUMENT_IMAGE_LIST = "image_list";
    public static final String ARGUMENT_VIDEO_LIST = "video_list";

    public static final String FRAGMENT_SCREEN_COUGH_COLD = "Cough and cold";
    public static final String FRAGMENT_SCREEN_LOOSE_MOTION = "Loose Motion";
    public static final String FRAGMENT_SCREEN_FEVER = "Fever";
    public static final String FRAGMENT_SCREEN_BACK_ACHE = "Back Ache";
    public static final String FRAGMENT_SCREEN_KNEE_ACHE = "Knee Ache";
    public static final String FRAGMENT_SCREEN_PREGNANCY = "Pregnancy";
    public static final String FRAGMENT_SCREEN_GYNAECOLOGY = "Gynaecology";
    public static final String FRAGMENT_SCREEN_BREAST = "Breast";
    public static final String FRAGMENT_SCREEN_TROUBLESOME_TEST = "Troublesome Test";
    public static final String FRAGMENT_SCREEN_TROUBLESOME_DRUGS = "Troublesome Drugs";

    public static final String FRAGMENT_SCREEN_DENTIEST = "Dentist";
    public static final String FRAGMENT_SCREEN_CHOCKING = "Emergency-Choking";

    public static List<String> ARRAY_LIST_IMAGES = new ArrayList<>();

    private Constants() {
    }

    public static String[] getScreenName(String screenName) {
        if (screenName.equals(Constants.FRAGMENT_SCREEN_COUGH_COLD)) {
            return Constants.IMAGES_COUGH_COLD;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_LOOSE_MOTION)) {
            return Constants.IMAGES_LOOSE_MOTION;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_KNEE_ACHE)) {
            return Constants.IMAGES_KNEE_ACHE;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_BACK_ACHE)) {
            return Constants.IMAGES_BACK_ACHE;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_FEVER)) {
            return Constants.IMAGES_FEVER;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_DENTIEST)) {
            return Constants.IMAGES_DENTIST;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_PREGNANCY)) {
            return Constants.IMAGES_PREGNANCY;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_CHOCKING)) {
            return Constants.IMAGES_EMERGENCY_CHOKING;
        }
        return null;
    }

    public static String getDiseasesId(String screenName) {
        if (screenName.equals(Constants.FRAGMENT_SCREEN_COUGH_COLD)) {
            return Constants.DISEASE_ID_COUGH;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_LOOSE_MOTION)) {
            return Constants.DISEASE_ID_LOOSE_MOTION;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_KNEE_ACHE)) {
            return Constants.DISEASE_ID_KNEE;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_BACK_ACHE)) {
            return Constants.DISEASE_ID_BACK;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_FEVER)) {
            return Constants.DISEASE_ID_FEVER;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_DENTIEST)) {
            return Constants.DISEASE_ID_DENTAL;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_PREGNANCY)) {
            return Constants.DISEASE_ID_WOMEN;
        } else if (screenName.equals(Constants.FRAGMENT_SCREEN_BREAST)) {
            return Constants.DISEASE_ID_CHEST;
        }
        return DISEASE_ID_BACK;
    }

    public static class Config {
        public static final boolean DEVELOPER_MODE = false;
    }

    public static class Extra {
        public static final String FRAGMENT_INDEX = "com.nostra13.example.universalimageloader.FRAGMENT_INDEX";
        public static final String IMAGE_POSITION = "com.nostra13.example.universalimageloader.IMAGE_POSITION";
    }

}
