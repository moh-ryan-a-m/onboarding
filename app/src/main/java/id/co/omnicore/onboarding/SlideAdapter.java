package id.co.omnicore.onboarding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int[] mSlideImages;
    private String[] mSlideHeadings;
    private String[] mSlideDescriptions;

    public SlideAdapter(Context context) {
        this.mContext = context;
        this.mSlideImages = new int[] {R.drawable.eat_icon, R.drawable.sleep_icon, R.drawable.code_icon};
        this.mSlideHeadings = new String[]{"EAT", "SLEEP", "CODE"};
        this.mSlideDescriptions = new String[]{
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        };

    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return this.mSlideHeadings.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        this.mLayoutInflater = LayoutInflater.from(this.mContext);
        View view = this.mLayoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.img_slide_image);
        TextView slideHeading = view.findViewById(R.id.tv_slide_heading);
        TextView slideDescription = view.findViewById(R.id.tv_description);

        slideImageView.setImageResource(this.mSlideImages[position]);
        slideHeading.setText(this.mSlideHeadings[position]);
        slideDescription.setText(this.mSlideDescriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
