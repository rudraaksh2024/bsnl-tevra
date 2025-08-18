package bsnl.bsnl_teevra;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class SectionPageAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList();
    private final List<String> fragmentTitleList = new ArrayList();

    public int getItemPosition(Object obj) {
        return -2;
    }

    public void addFragments(Fragment fragment, String str) {
        this.fragmentList.add(fragment);
        this.fragmentTitleList.add(str);
    }

    public SectionPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public CharSequence getPageTitle(int i) {
        return this.fragmentTitleList.get(i);
    }

    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    public int getCount() {
        return this.fragmentList.size();
    }
}
