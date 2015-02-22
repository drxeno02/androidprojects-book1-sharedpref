/** Project and code provided by Leonard Tatum, A.K.A DRXeno
 * For any questions or comments regarding the use of this code
 * or issues please contact LJTATUM@HOTMAIL.COM
 * ONLINE MOBILE TUTORIALS: ljtatum.blog.com/
 * CODEBASE: http://ljtatum.blog.com/codebase_eight/ */

package com.blog.ljtatum.drxenosharedpref.activity;

import com.blog.ljtatum.drxenosharedpref.R;
import com.blog.ljtatum.drxenosharedpref.helper.Constants;
import com.blog.ljtatum.drxenosharedpref.util.SharedPref;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private SharedPref sharedPref;
	private TextView tv_meta_msg1, tv_meta_msg2;
	private EditText edt_msg1, edt_msg2;
	private Button btn_submit1, btn_submit2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getIds();

	}

	// instantiate views
	private void getIds() {
		sharedPref = new SharedPref(MainActivity.this, Constants.PRE_FILE);
		tv_meta_msg1 = (TextView) findViewById(R.id.tv_meta_msg1);
		tv_meta_msg2 = (TextView) findViewById(R.id.tv_meta_msg2);
		edt_msg1 = (EditText) findViewById(R.id.edt_msg1);
		edt_msg2 = (EditText) findViewById(R.id.edt_msg2);
		btn_submit1 = (Button) findViewById(R.id.btn_submit1);
		btn_submit2 = (Button) findViewById(R.id.btn_submit2);
		
		// retrieve messages stored in shared prefs
		String storedMsg1 = sharedPref.getStringPref(Constants.PREF_MSG_ONE, "");
		String storedMsg2 = sharedPref.getStringPref(Constants.PREF_MSG_TWO, "");
		
		if (!storedMsg1.equalsIgnoreCase("") && storedMsg1 != null) {
			tv_meta_msg1.setText(storedMsg1);
		}
		
		if (!storedMsg2.equalsIgnoreCase("") && storedMsg2 != null) {
			tv_meta_msg2.setText(storedMsg2);
		}
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_submit1:
			if (!edt_msg1.getText().toString().isEmpty()) {
				String msg = edt_msg1.getText().toString();
				sharedPref.setPref(Constants.PREF_MSG_ONE, msg);
			}
			
			break;
		case R.id.btn_submit2:
			if (!edt_msg2.getText().toString().isEmpty()) {
				String msg = edt_msg2.getText().toString();
				sharedPref.setPref(Constants.PREF_MSG_TWO, msg);
			}
			
			break;
		default:
			break;
		}
		
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
