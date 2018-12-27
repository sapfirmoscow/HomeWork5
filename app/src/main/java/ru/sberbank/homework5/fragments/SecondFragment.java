package ru.sberbank.homework5.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.sberbank.homework5.OnButtonPressed;
import ru.sberbank.homework5.R;

public class SecondFragment extends Fragment {

    private OnButtonPressed onButtonPressed;
    private Button button;

    public SecondFragment() {
    }

    public static SecondFragment newInstance() {
        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setRetainInstance(true);
    }

    public void setOnButtonPressed(OnButtonPressed onButtonPressed) {
        this.onButtonPressed = onButtonPressed;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.second_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initListeners();
    }

    private void initViews(View view) {
        button = view.findViewById(R.id.sendButton);
    }

    private void initListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed.onPress();
            }
        });
    }
}
