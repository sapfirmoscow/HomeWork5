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
            initTransactions();
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
        if (mFirstCreate) {
            firstFragment = FirstFragment.newInstance();
            secondFragment = SecondFragment.newInstance();
            secondFragment.setOnButtonPressed(new OnButtonPressedImpl());
        }
        if (firstFragment == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            firstFragment = (FirstFragment) fragmentManager.findFragmentByTag("FIRST");
        }

        if (secondFragment == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            secondFragment = (SecondFragment) fragmentManager.findFragmentByTag("SECOND");
            secondFragment.setOnButtonPressed(new OnButtonPressedImpl());
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startSerice();
    }

    private void initTransactions() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.first_layout, firstFragment, "FIRST");
        fragmentTransaction.add(R.id.second_layout, secondFragment, "SECOND");
        fragmentTransaction.commit();

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

            fragmentTransaction.replace(R.id.second_layout, thirdFragment, "THIRDTAG");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
