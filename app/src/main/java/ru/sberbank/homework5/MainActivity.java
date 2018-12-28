package ru.sberbank.homework5;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.sberbank.homework5.fragments.FirstFragment;
import ru.sberbank.homework5.fragments.SecondFragment;
import ru.sberbank.homework5.fragments.ThirdFragment;

public class MainActivity extends AppCompatActivity {

    private static final String FIRST_CREATE = "first";
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private Boolean mFirstCreate = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mFirstCreate = savedInstanceState.getBoolean(FIRST_CREATE, false);
        }

        initFragments();
        if (mFirstCreate) {
            startSerice();
            mFirstCreate = false;

        }
    }

    private void startSerice() {
        startService(MyIntentService.newInteent(MainActivity.this));
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean(FIRST_CREATE, mFirstCreate);

    }

    private void initFragments() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        firstFragment = (FirstFragment) fragmentManager.findFragmentById(R.id.first_fragment);
        secondFragment = (SecondFragment) fragmentManager.findFragmentById(R.id.second_fragment);
        secondFragment.setOnButtonPressed(new OnButtonPressedImpl());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startSerice();
    }



    private class OnButtonPressedImpl implements OnButtonPressed {

        @Override
        public void onPress() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            ThirdFragment thirdFragment = (ThirdFragment) fragmentManager.findFragmentByTag("THIRDTAG");
            if (thirdFragment == null)
                thirdFragment = ThirdFragment.newInstance();

            Bundle bundle = new Bundle();
            bundle.putString("Data", firstFragment.getEditText());
            thirdFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.second_fragment, thirdFragment, "THIRDTAG");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
