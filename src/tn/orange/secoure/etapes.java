package tn.orange.secoure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.TabPageIndicator;

public class etapes extends BaseSampleActivity {
    private static final String[] CONTENT = new String[] { "�tape 1", "�tape 2", "�tape 3", "�tape 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_tabs);

        mAdapter = new GoogleMusicAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TabPageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }

    class GoogleMusicAdapter extends TestFragmentAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TestFragment.newInstance(etapes.CONTENT[position % etapes.CONTENT.length]);
        }

        @Override
        public int getCount() {
            return etapes.CONTENT.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return etapes.CONTENT[position % etapes.CONTENT.length].toUpperCase();
        }
    }
}
