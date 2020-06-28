package pt.ipg.application.testingcovid_19.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class DoctorAuthPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    Fragment fragment = null;
    public DoctorAuthPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragment = new DoctorLoginActivity();
                return fragment;
            case 1:
                fragment = new DoctorSignUpActivity();
                return fragment;
            default:
                return fragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SignIn";
            case 1:
                return "SignUp";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return  NUM_ITEMS;
    }
}
