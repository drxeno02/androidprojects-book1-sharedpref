/** Project and code provided by Leonard Tatum, A.K.A DRXeno
 * For any questions or comments regarding the use of this code
 * or issues please contact LJTATUM@HOTMAIL.COM
 * ONLINE MOBILE TUTORIALS: ljtatum.blog.com/
 * GITHUB: https://github.com/drxeno02/androidprojects-book1-sharedpref */

package com.blog.ljtatum.drxenosharedpref.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.blog.ljtatum.drxenosharedpref.R;
import com.blog.ljtatum.drxenosharedpref.helper.SimpleTextWatcher;

public class ValidationUtils extends SimpleTextWatcher {
	
	private static Context context;
	private static EditText[] fieldsArray;
	private static String[] fieldsErrorArray;
	
	public ValidationUtils(Context context, EditText[] fieldsArray,
			String[] fieldsErrorArray) {
		ValidationUtils.context = context;
		ValidationUtils.fieldsArray = fieldsArray;
		ValidationUtils.fieldsErrorArray = fieldsErrorArray;
		initListeners();
	}

	private void initListeners() {
		for (int i = 0; i < fieldsArray.length; i++) {
			fieldsArray[i].addTextChangedListener(this);
		}
	}
	
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		super.onTextChanged(s, start, before, count);
		setError(null);
	}
	
	public static void setError(String error) {
		for (int i = 0; i < fieldsArray.length; i++) {
			fieldsArray[i].setError(error);
		}
	}

	public void setError(int index, String error) {
		fieldsArray[index].setError(error);
	}
	
	private static void setErrors(boolean[] isFieldsEnteredArray) {
		for (int i = 0; i < fieldsArray.length; i++) {
			
			try {
				fieldsArray[i].setError(isFieldsEnteredArray[i] ? null
						: fieldsErrorArray[i]);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}
	
	public boolean isValidData(String msg) {
		boolean isMsgValid = !TextUtils.isEmpty(msg);

		if (isMsgValid) {
			return true;
		} else if (!isMsgValid) {
			setError(context.getString(R.string.txt_error));
		} else {
			setErrors(new boolean[] { isMsgValid });
		}

		return false;
	}
	
}
