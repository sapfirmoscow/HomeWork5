package ru.sberbank.homework5.fragments;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ru.sberbank.homework5.MyBroadcastReceiver;
import ru.sberbank.homework5.MyIntentService;
import ru.sberbank.homework5.R;

public class FirstFragment extends Fragment {

    private EditText editText;
    private MyBroadcastReceiver myBroadcastReceiver;
    private IntentFilter intentFilter;


    public static FirstFragment newInstance() {
        return new FirstFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getActivity().registerReceiver(myBroadcastReceiver, intentFilter);
    }

    private void init() {
        myBroadcastReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter("ru.sberbank.SEND_MESSAGES_FILTER");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myBroadcastReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().startService(MyIntentService.newInteent(getActivity()));
    }
}
