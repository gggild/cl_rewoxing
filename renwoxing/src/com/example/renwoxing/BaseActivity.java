package com.example.renwoxing;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;
/**
 * 
 * @author Donghui
 *	���ܣ�����Ļ������ʾһ��Toast
 */
public class BaseActivity extends Activity {

	public void showToast(CharSequence text){
		Toast toast= Toast.makeText(this, text, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER,0,0);
		toast.show();
	}

}
