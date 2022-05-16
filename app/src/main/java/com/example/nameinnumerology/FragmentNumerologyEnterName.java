package com.example.nameinnumerology;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentNumerologyEnterName extends Fragment {
    //This fragment's View
    private View view;

    //Widgets and Buttons
    private EditText mEnteredName;
    private TextView mErrMsg;
    private Button mButtonNext;

    //Member Variables
    private String enteredName;
    private String nameConvertedToNum;
    private String personalityNumber;

    //Keys for passing data
    private static final String KEY_NAME_CONVERTED = "nameToNum";
    private static final String KEY_PERSONALITY_NUMBER = "personalityNum";
    private static final String KEY_USER_INPUT_NAME = "userInputName";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Store the layout for this fragment to view
        view = inflater.inflate(R.layout.fragment_numerology_enter_name, container, false);

        //Link Widgets
        mEnteredName = view.findViewById(R.id.fragEnterNameEnteredName);
        mErrMsg = view.findViewById(R.id.fragEnterNameErrMsg);

        //Clickable Buttons
        mButtonNext = view.findViewById(R.id.fragEnterNameButtonNext);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mEnteredName.getText().toString().isEmpty()){
                    //Get the personality number (Single digit or Master Number)
                    convertLettersToNum();
                    computePersonalityNumber();

                    //Bundle the personality number and send it to the results fragment
                    Fragment fragmentViewConvertedName = new FragmentViewConvertedName();
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_PERSONALITY_NUMBER, personalityNumber);
                    bundle.putString(KEY_NAME_CONVERTED, nameConvertedToNum);
                    bundle.putString(KEY_USER_INPUT_NAME, enteredName);
                    fragmentViewConvertedName.setArguments(bundle);

                    //Go to the viewConvertedName fragment to display user letters converted to numbers
                    ActivityHome.replaceFragment(R.id.actHomeMainFragHolder, fragmentViewConvertedName);

                    //Clear text
                    mEnteredName.setText("");
                }else{
                    //Determine the error to show corresponding error message
                    if(mEnteredName.getText().toString().isEmpty()){
                        mErrMsg.setText(getString(R.string.emptyNameField));
                    }else{
                        Log.d("errorHomeAct", "unknown error in button click");
                    }
                }
            }
        });
        //Inflate layout for this fragment
        return view;
    }

    //Gets the corresponding charValue of each letter of the user-input String
    //See WikiHow source (Name in Numerology) to see the value of each respective letter
    private void convertLettersToNum(){
        //Gets the name input by the user and converts it to all uppercase
        enteredName = mEnteredName.getText().toString().toUpperCase();

        //Prepares the charValue of each letter input by the user
        Integer charValue=0;
        nameConvertedToNum="";

        /*Prepares the alphabetArray of char arranged alphabetically.
          This will be used to compare each letter of the user-input name to get its charValue according to its value in numerology*/
        char [] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                'W', 'X', 'Y', 'Z'};

        //Gets the charValue of each letter of the user-inputted name String
        //First loop: loops through each letter from RTL (Enters the 2nd loop before proceeding to next letter of user-input name)
        for(int nameCharIndex=0; nameCharIndex<enteredName.length(); nameCharIndex++){
            charValue=0;
            //Second loop: loops through the array to compare the letter obtained from the first loop to get charValue
            for(int alphabetIndex=0; alphabetIndex<alphabet.length; alphabetIndex++){
                //Increase char value each loop if the character is a letter and not a space
                if(enteredName.charAt(nameCharIndex)!=' '){
                    //Char value resets to 1 after reaching 9. Sets possible maximum value to 9.
                    if(charValue==9){
                        charValue=0;
                    }
                    ++charValue;
                }

                if(enteredName.charAt(nameCharIndex) == alphabet[alphabetIndex]){
                    //Converts the letters to their corresponding charValue and stores the data in a String. Ready for further processing
                    nameConvertedToNum=nameConvertedToNum.concat(charValue.toString());
                    //Include spaces in the String to help distinguish if the user has entered a name that includes spaces
                }else if (enteredName.charAt(nameCharIndex) == ' '){
                    nameConvertedToNum=nameConvertedToNum.concat(" ");
                    break;
                }
            }
        }
    }

    //Run the converted name value String through a series of filters. Determines the final personality number
    private void computePersonalityNumber(){
        //Contains the same value. The sum of each digits. Only difference is the dataType.
        Integer integerTotalSum=0;  //Responsible for handling the computations
        String strTotalSum;         //Responsible for handling the loops
        final String MASTER_NUMBERS = "11 22 33";

        //Get the first total sum of each digit of the convertedNameToNum String
        for(int i=0; i<nameConvertedToNum.length(); i++){
            //Disregard spaces from the String in the equation
            if(Character.isDigit(nameConvertedToNum.charAt(i))){
                int currentNumber = Integer.parseInt(String.valueOf(nameConvertedToNum.charAt(i)));
                integerTotalSum += currentNumber;
            }
        }
        //Store it in the temporary String.
        strTotalSum = integerTotalSum.toString();

        //FILTERS
        //If it has 3 digits, add each digit until it only has 2
        if(strTotalSum.length()==3){
            integerTotalSum=0;
            //Adding each digits
            for (int i=0; i<strTotalSum.length(); i++){
                int currentNumber = Integer.parseInt(String.valueOf(strTotalSum.charAt(i)));
                integerTotalSum += currentNumber;
            }
            strTotalSum = integerTotalSum.toString();
        }
        //The value should reach here with only 2 digits

        /*If the number is not a master number, run it through this filter.
          If it's a master number, pass through all remaining filters. It will be the final number.*/
        if (!MASTER_NUMBERS.contains(strTotalSum)) {
            //This filter adds both digits until it's a single number.
            //Keep getting the sum of each digit until the sum is finally a single digit
            while(strTotalSum.length()==2){
                integerTotalSum=0;
                //Adding each digit
                for (int i=0; i<strTotalSum.length(); i++){
                    int currentNumber = Integer.parseInt(String.valueOf(strTotalSum.charAt(i)));
                    integerTotalSum += currentNumber;
                }
                strTotalSum = integerTotalSum.toString();
            }
                strTotalSum = integerTotalSum.toString();
        }

       //Set personality number to the filtered value. (Either a masterNum or a single-digit value)
        personalityNumber = strTotalSum;
    }
}