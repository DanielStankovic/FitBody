package link.fitbody;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends MotherActivity implements onFragmentSendMessageListener {
    View fragmentContainer;
    ListView drawerList;


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle( this, drawerLayout,R.string.drawer_open, R.string.drawer_close  );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle.syncState();

        drawerList = (ListView)findViewById(R.id.left_drawer);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
                switch (position){
                    case 0 :
                        intent.putExtra("CALCULATOR_NUM", 0);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("CALCULATOR_NUM", 1);
                        startActivity(intent);
                        break;
                    case 2 :
                        intent.putExtra("CALCULATOR_NUM", 2);
                        startActivity(intent);
                        break;
                    case 3:
                        Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(settingsIntent);
                }

            }
        });

        fragmentContainer = findViewById(R.id.fragment_container);

        if (fragmentContainer != null) {
            if (savedInstanceState == null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment idealWeightFragment = new IdealWeightFragment();
                ft.add(fragmentContainer.getId(), idealWeightFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
       switch (item.getItemId()) {
           case R.id.menuInfo:



               int spannableColor = ContextCompat.getColor(this, R.color.textMenuInfo);
               String infoText = "FIT BODY\nDaniel Stankovic\nBeograd 2017";
               Spannable spannable = new SpannableString(infoText);
               spannable.setSpan(new ForegroundColorSpan(spannableColor), 0, infoText.indexOf("\n"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
               spannable.setSpan(new RelativeSizeSpan(0.7f), infoText.indexOf("\n"), infoText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

               showInfoToast(spannable);
               return true;
           case R.id.settings_item:
               Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
               startActivity(settingsIntent);

           default:
               return super.onOptionsItemSelected(item);
       }


    }

    public void idealWeightOnClick(View view){
        if(fragmentContainer != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment idealWeightFragment = new IdealWeightFragment();
            ft.replace(fragmentContainer.getId(), idealWeightFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        else {
            Intent intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra("CALCULATOR_NUM", 0 );
            startActivity(intent);
        }

    }


    public void calorieOnClick(View view){
        if(fragmentContainer != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment calorieCalculatorFragment = new CalorieCalculatorFragment();
            ft.replace(fragmentContainer.getId(), calorieCalculatorFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        else {
            Intent intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra("CALCULATOR_NUM", 1 );
            startActivity(intent);
        }

    }

    public void bodyTypeOnClick(View view){
        if(fragmentContainer != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment bodyTypeFragment = new BodyShapeFragment();
            ft.replace(fragmentContainer.getId(), bodyTypeFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        else {
            Intent intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra("CALCULATOR_NUM", 2 );
            startActivity(intent);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
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
