package com.likairabbit.tool;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class MyToast {


	public static void makeTextShort(Context context, CharSequence text){
		Toast makeText = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		makeText.setGravity(Gravity.TOP, 0, 200);
		makeText.show();
	}
	public static void makeTextShort(Context context, int resId){
		Toast makeText = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
		makeText.setGravity(Gravity.TOP, 0, 200);
		makeText.show();
	}
	public static void makeTextLong(Context context, CharSequence text){
		Toast makeText = Toast.makeText(context, text, Toast.LENGTH_LONG);
		makeText.setGravity(Gravity.TOP, 0, 200);
		makeText.show();
	}
	public static void makeTextLong(Context context, int resId){
		Toast makeText = Toast.makeText(context, resId, Toast.LENGTH_LONG);
		makeText.setGravity(Gravity.TOP, 0, 200);
		makeText.show();
	}
}
