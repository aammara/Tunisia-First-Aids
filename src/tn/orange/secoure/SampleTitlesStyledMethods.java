package tn.orange.secoure;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

public class SampleTitlesStyledMethods extends BaseSampleActivity {
	ImageView about;
	ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);
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

        mAdapter = new TestTitleFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        mIndicator = indicator;
        mIndicator.setViewPager(mPager);
        Bundle b = getIntent().getExtras();
        int value = b.getInt("key");
        mIndicator.setCurrentItem(value-1);

        final float density = getResources().getDisplayMetrics().density;
        indicator.setBackgroundColor(0x18FF0000);
        indicator.setFooterColor(0xFFAA2222);
        indicator.setFooterLineHeight(1 * density); //1dp
        indicator.setFooterIndicatorHeight(3 * density); //3dp
        indicator.setFooterIndicatorStyle(IndicatorStyle.Underline);
        indicator.setTextColor(0xAA000000);
        indicator.setSelectedColor(0xFF000000);
        indicator.setSelectedBold(true);
    }
}