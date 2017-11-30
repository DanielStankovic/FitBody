package link.fitbody;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class CalculatorActivity extends MotherActivity implements onFragmentSendMessageListener {
    CalculatorPagerAdapter adapter;
    ViewPager vPager;
    TabLayout tabLayout;

    public static class CalculatorPagerAdapter extends FragmentPagerAdapter{
        static final int NUM_ITEMS = 3;
        static final String TAB_TITLES[]= new String[]{"Ideal Weight", "Calorie Calculator", "Body Type"};

        public CalculatorPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new IdealWeightFragment();
                case 1:
                    return new CalorieCalculatorFragment();
                case 2:
                    return new BodyShapeFragment();
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_TITLES[position];
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        vPager = (ViewPager)findViewById(R.id.vpPager) ;
        adapter = new CalculatorPagerAdapter(getSupportFragmentManager());
        vPager.setAdapter(adapter);

        vPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               /* CharSequence title = adapter.getPageTitle(position);
                changeActionBarText(title.toString());*/

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        int calculator_num = this.getIntent().getIntExtra("CALCULATOR_NUM", 0);

        /*if(vPager.getCurrentItem() == calculator_num){
            changeActionBarText(adapter.getPageTitle(calculator_num).toString());
        }*/

        vPager.setCurrentItem(calculator_num);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(vPager);
        getSupportActionBar().setElevation(0);


    }

    @Override
    public void onFragmentSendMessage(CharSequence message) {
        showCalculatorToast(message);
    }

    @Override
    public void onFragmentSendResult(CharSequence title, CharSequence result) {
        showResultDialog(title, result);
    }
}
