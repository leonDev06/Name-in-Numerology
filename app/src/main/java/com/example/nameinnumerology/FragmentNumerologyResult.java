package com.example.nameinnumerology;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class FragmentNumerologyResult extends Fragment {
    //This fragment's view
    private View view;

    //Widgets
    private TextView mPersonalityNumber;
    private TextView mPersonalityType;
    private Button mButtonConfirm;
    //Test

    //Keys for passing data
    private final String KEY_PERSONALITY_NUMBER = "personalityNum";

    private String personalityNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_numerology_result, container, false);

        //Link widgets
        mPersonalityNumber = view.findViewById(R.id.fragNumResultNumber);
        mPersonalityType = view.findViewById(R.id.fragNumResultResult);
        mButtonConfirm = view.findViewById(R.id.fragNumResultButton);

        //Retrieve the personality number processed from previous fragment
        personalityNumber = getArguments().getString(KEY_PERSONALITY_NUMBER);

        //Display user's numerology result
        displayResult();

        //Clickable Buttons
        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
        return view;
    }

    //Display text based on computed user's personality number
    private void displayResult(){
        mPersonalityNumber.setText(personalityNumber);
        switch(personalityNumber){
            case "1":
                mPersonalityType.setText(getString(R.string.result1));
                break;
            case "2":
                mPersonalityType.setText(getString(R.string.result2));
                break;
            case "3":
                mPersonalityType.setText(getString(R.string.result3));
                break;
            case "4":
                mPersonalityType.setText(getString(R.string.result4));
                break;
            case "5":
                mPersonalityType.setText(getString(R.string.result5));
                break;
            case "6":
                mPersonalityType.setText(getString(R.string.result6));
                break;
            case "7":
                mPersonalityType.setText(getString(R.string.result7));
                break;
            case "8":
                mPersonalityType.setText(getString(R.string.result8));
                break;
            case "9":
                mPersonalityType.setText(getString(R.string.result9));
                break;
            case "11":
                mPersonalityType.setText(getString(R.string.result11));
                break;
            case "22":
                mPersonalityType.setText(getString(R.string.result22));
                break;
            case "33":
                mPersonalityType.setText(getString(R.string.result33));
                break;
            default:
                Log.d("error_FragmentResult", "Invalid case");
                break;
        }
    }
}