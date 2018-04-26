package id.co.omnicore.onboarding;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private SlideAdapter mSlideAdapter;
    private Button mPrevButton;
    private Button mNextButton;
    private int mCurrentPagePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.vp_main);
        mDotLayout = findViewById(R.id.ly_indicator);

        mSlideAdapter = new SlideAdapter(this);

        mViewPager.setAdapter(mSlideAdapter);
        mViewPager.addOnPageChangeListener(viewPagerListener);

        mPrevButton = findViewById(R.id.btn_previous);
        mNextButton = findViewById(R.id.btn_next);

        addDotsIndicator(0);
        mViewPager.addOnPageChangeListener(viewPagerListener);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCurrentPagePosition + 1);
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCurrentPagePosition - 1);
            }
        });
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i=0;i<3;i++){
            mDots[i]= new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mCurrentPagePosition = position;
            if(mCurrentPagePosition == 0){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(false);
                mPrevButton.setVisibility(View.INVISIBLE);

                mNextButton.setText("Next");
                mPrevButton.setText("");
            }
            else if (mCurrentPagePosition == 1){
                mNextButton.setEnabled(true);
                mNextButton.setVisibility(View.VISIBLE);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Next");
                mPrevButton.setText("Previous");
            }else{
                mNextButton.setEnabled(true);
                mNextButton.setVisibility(View.VISIBLE);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Finish");
                mPrevButton.setText("Previous");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
