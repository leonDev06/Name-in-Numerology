package com.example.nameinnumerology;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentWelcomeThird extends Fragment {
    private View view;
    private Button buttonStart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_welcome_third, container, false);

        buttonStart = view.findViewById(R.id.frag3ButtonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ActivityHome.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });


        return view;
    }
}