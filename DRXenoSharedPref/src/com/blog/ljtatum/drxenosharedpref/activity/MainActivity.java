/** Project and code provided by Leonard Tatum, A.K.A DRXeno
 * For any questions or comments regarding the use of this code
 * or issues please contact LJTATUM@HOTMAIL.COM
 * ONLINE MOBILE TUTORIALS: ljtatum.blog.com/
 * GITHUB: https://github.com/drxeno02/androidprojects-book1-sharedpref */

package com.blog.ljtatum.drxenosharedpref.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blog.ljtatum.drxenosharedpref.R;
import com.blog.ljtatum.drxenosharedpref.helper.Constants;
import com.blog.ljtatum.drxenosharedpref.util.SharedPref;
import com.blog.ljtatum.drxenosharedpref.util.ValidationUtils;

public class MainActivity extends Activity implements OnClickListener {
	
	private Context mContext;
	private SharedPref sharedPref;
	private ValidationUtils validationUtils;
	private TextView tv_meta_msg1, tv_meta_msg2;
	private EditText edt_msg1, edt_msg2;
	private Button btn_submit1, btn_submit2;
	private Animation animation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getIds();

	}

	// instantiate views
	private void getIds() {
		// Animation snippet
        animation = new AlphaAnimation(1.0F, 0.6F);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        // Repeat animation infinitely
        animation.setRepeatCount(Animation.INFINITE);
        // Reverse animation at the end so the button will fade back in
        animation.setRepeatMode(Animation.REVERSE);
		
        mContext = MainActivity.this;
		sharedPref = new SharedPref(MainActivity.this, Constants.PRE_FILE);
		tv_meta_msg1 = (TextView) findViewById(R.id.tv_meta_msg1);
		tv_meta_msg2 = (TextView) findViewById(R.id.tv_meta_msg2);
		edt_msg1 = (EditText) findViewById(R.id.edt_msg1);
		edt_msg2 = (EditText) findViewById(R.id.edt_msg2);
		btn_submit1 = (Button) findViewById(R.id.btn_submit1);
		btn_submit2 = (Button) findViewById(R.id.btn_submit2);
		
		// set click listeners
		btn_submit1.setOnClickListener(this);
		btn_submit2.setOnClickListener(this);
		
		validationUtils = new ValidationUtils(mContext,
				new EditText[]{edt_msg1, edt_msg2},
				new String[]{getResources().getString(R.string.txt_error)});
		
		// retrieve messages stored in shared prefs
		String storedMsg1 = sharedPref.getStringPref(Constants.PREF_MSG_ONE, "");
		String storedMsg2 = sharedPref.getStringPref(Constants.PREF_MSG_TWO, "");
		
		if (!storedMsg1.equalsIgnoreCase("") && storedMsg1 != null) {
			tv_meta_msg1.setText(storedMsg1);
		}
		
		if (!storedMsg2.equalsIgnoreCase("") && storedMsg2 != null) {
			tv_meta_msg2.setText(storedMsg2);
		}
		
		edt_msg1.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!edt_msg1.getText().toString().isEmpty()) {
					btn_submit1.setAnimation(animation);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (edt_msg1.getText().toString().isEmpty() || 
					edt_msg1.length() == 0 || edt_msg1.equals("") || 
					edt_msg1 == null) {
					btn_submit1.clearAnimation();
				}
			}
			
		});
		
		edt_msg2.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!edt_msg2.getText().toString().isEmpty()) {
					btn_submit2.setAnimation(animation);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (edt_msg2.getText().toString().isEmpty() || 
					edt_msg2.length() == 0 || edt_msg2.equals("") || 
					edt_msg2 == null) {
					btn_submit2.clearAnimation();
				}
			}
			
		});
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_submit1:

			if (!edt_msg1.getText().toString().isEmpty() &&
					validationUtils.isValidData(edt_msg1.getText().toString())) {
				String msg = edt_msg1.getText().toString();
				sharedPref.setPref(Constants.PREF_MSG_ONE, msg);
				tv_meta_msg1.setText(msg);
				edt_msg1.setText("");
				closeKeyboard();
			} else {
				validationUtils.setError(0, getResources().getString(R.string.txt_error));
			}
			
			break;
		case R.id.btn_submit2:

			if (!edt_msg2.getText().toString().isEmpty() &&
					validationUtils.isValidData(edt_msg2.getText().toString())) {
				String msg = edt_msg2.getText().toString();
				sharedPref.setPref(Constants.PREF_MSG_TWO, msg);
				tv_meta_msg2.setText(msg);
				edt_msg2.setText("");
				closeKeyboard();
			} else {
				validationUtils.setError(1, getResources().getString(R.string.txt_error));
			}
			
			break;
		default:
			break;
		}
		
	}
	
	private void closeKeyboard() {
		// hide virtual keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
