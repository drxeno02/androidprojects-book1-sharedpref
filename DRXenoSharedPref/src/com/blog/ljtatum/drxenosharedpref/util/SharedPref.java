/** Project and code provided by Leonard Tatum
 * For any questions or comments regarding the use of this code
 * or issues please contact LJTATUM@HOTMAIL.COM
 * ONLINE MOBILE TUTORIALS: ljtatum.blog.com/
 * GITHUB: https://github.com/drxeno02/androidprojects-book1-sharedpref */

package com.blog.ljtatum.drxenosharedpref.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
	
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor prefsEditor;	
	
	/**
	 * 
	 * @param context
	 * @param prefName  : Name of Preference
	 */
	public SharedPref(Context context, String prefName) {
		this.sharedPreferences = context.getSharedPreferences(prefName, Activity.MODE_PRIVATE);
		this.prefsEditor = sharedPreferences.edit();
	}
	
	/**
	 *  Method for clearing all data of preference
	 */
	public void clearAllPreferences(){
		 prefsEditor.clear();
		 prefsEditor.commit();
	}
	
	/**
	 *  Method for remove data of preference
	 */
	public void removePreference(String key){
		 prefsEditor.remove(key);
		 prefsEditor.commit();
	}
	
	/**
	 * 
	 * @param key
	 * @param value  : String Value
	 */
	public void setPref(String key, String value) {
		 prefsEditor.putString(key,value);
		 prefsEditor.commit();
	}

	/**
	 * 
	 * @param key
	 * @param defValue 
	 * @return String Type
	 */
	public String getStringPref(String key, String defValue){
		return sharedPreferences.getString(key, defValue);
	}
	
}
