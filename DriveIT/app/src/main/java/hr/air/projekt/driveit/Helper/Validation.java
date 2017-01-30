package hr.air.projekt.driveit.Helper;

import android.widget.EditText;

import hr.air.projekt.driveit.R;

/**
 * Created by mico on 30.1.2017..
 */

public class Validation {
    public static boolean hasText(EditText editText){

        String text = editText.getText().toString().trim();
        editText.setError(null);

        if(text.length()==0){
            editText.setError(CurrentActivity.getActivity().getString(R.string.required));
            return false;
        }
        return true;
    }
}
