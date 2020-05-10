package pt.ipg.application.testingcovid_19;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;
    Fragment fragment ;
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragment = new SignupActivity();
            case 1:
                return fragment = new SigninActivity();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SignUp";
            case 1:
                return "SignIn";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return  NUM_ITEMS;
    }
}
