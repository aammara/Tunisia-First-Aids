package tn.orange.secoure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.viewpagerindicator.TabPageIndicator;

public class SampleTabsStyled extends BaseSampleActivity {
    private static final String[] CONTENT = new String[] { "Žtape 1", "Žtape 2", "Žtape 3", "Žtape 4" };
    ImageView about;
	ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_tabs);
      //******** HIDE ABOUT IMAGE START ********//
        about = (ImageView) findViewById(R.id.about);
        about.setImageDrawable(null);
        //******** HIDE ABOUT IMAGE END ********//
        
      //******** HOME IMAGE START ********//
        home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			        finish();
				}
     });
        //******** HOME IMAGE END ********//
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
            return TestFragment.newInstance(SampleTabsStyled.CONTENT[position % SampleTabsStyled.CONTENT.length]);
        }

        @Override
        public int getCount() {
            return SampleTabsStyled.CONTENT.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return SampleTabsStyled.CONTENT[position % SampleTabsStyled.CONTENT.length].toUpperCase();
        }
    }
}
