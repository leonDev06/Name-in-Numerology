package com.example.nameinnumerology;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;

public class FragmentViewConvertedName extends Fragment {
    //This fragment's view
    private View view;

    //Widgets
    private TextView mName;
    private TextView mNameToNum;
    private TextView mPersonalityNumber;
    private TextView mButtonNext;

    //Member Variables
    private String name;
    private String nameToNum;
    private String personalityNumber;

    //Keys for passing data
    private static final String KEY_NAME_CONVERTED = "nameToNum";
    private static final String KEY_USER_INPUT_NAME = "userInputName";
    private static final String KEY_PERSONALITY_NUMBER = "personalityNum";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_converted_name, container, false);

        //Link widgets
        mName = view.findViewById(R.id.fragViewConvNameName);
        mNameToNum = view.findViewById(R.id.fragViewConvNameNum);
        mPersonalityNumber = view.findViewById(R.id.fragViewConvNamePersoNum);
        mButtonNext = view.findViewById(R.id.fragViewConvNameButton);

        //Retrieve data from bundle
        name = getArguments().getString(KEY_USER_INPUT_NAME);
        nameToNum = getArguments().getString(KEY_NAME_CONVERTED);
        personalityNumber = getArguments().getString(KEY_PERSONALITY_NUMBER);

        //formats display and displays it to UI
        formatStringsForDisplay();
        displayToUser();

        //Clickable Buttons
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(KEY_PERSONALITY_NUMBER, personalityNumber);
                Fragment fragmentResult = new FragmentNumerologyResult();
                fragmentResult.setArguments(bundle);
                ActivityHome.replaceFragment(R.id.actHomeMainFragHolder, fragmentResult);
            }
        });
        return view;
    }

    //Format the Display
    //Adds spaces and '+' signs between each character of both original name String and the number value String;
    private void formatStringsForDisplay(){
        String tempName="";             //Temporary String holder for the formatted name
        String tempConvertedName="";    //Temporary String holder for the formatted number value String

        //Loop through each letter of the name String
        //Should have the same length as the number String
        for (int i=0; i<name.length(); i++){
            try {
                //If current char and the next char is not a space
                //This is needed so that the String will not become something like (1 + 2 + 3 + +   4 + 5 + 6)
                if(name.charAt(i+1) != ' ' && name.charAt(i)!=' '){
                    //(Letters String) Copy the current character and add 4 spaces after it
                    for (String s : Arrays.asList(String.valueOf(name.charAt(i)), " ", " ", " ", " ")){
                        tempName = tempName.concat(s);
                    }
                    //(Numbers String) Copy the current character and add a space, +, then space again after it
                    for (String s : Arrays.asList(String.valueOf(nameToNum.charAt(i)), " ", "+", " ")) {
                        tempConvertedName = tempConvertedName.concat(s);
                    }
                    //If the current, or the next, character is a space
                }else{
                    //(Letters String) Copy the current character and add as space
                    for (String s : Arrays.asList(String.valueOf(name.charAt(i)), " ")) {
                        tempName = tempName.concat(s);
                    }
                    //(Numbers String) Copy the current character and add as space
                    for (String s : Arrays.asList(String.valueOf(nameToNum.charAt(i)), " ")) {
                        tempConvertedName= tempConvertedName.concat(s);
                    }

                    //Add two more spaces to both Strings
                    for (int j = 0; j < 2; j++) {
                        tempConvertedName= tempConvertedName.concat(" ");
                        tempName = tempName.concat(" ");
                    }
                }
                //Caused by i+1. This is the last character in both Strings. Simply copy each characters to their respective Strings
            }catch (StringIndexOutOfBoundsException e){
                tempName = tempName.concat(String.valueOf(name.charAt(i)));
                tempConvertedName = tempConvertedName.concat(String.valueOf(nameToNum.charAt(i)));
            }
        }
        name = tempName;
        nameToNum = tempConvertedName;
    }
    //Print Data on the User Interface
    private void displayToUser(){
        mName.setText(name);
        mNameToNum.setText(nameToNum);
        mPersonalityNumber.setText(personalityNumber);
    }
}