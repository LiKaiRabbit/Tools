package com.likairabbit.tool.SecurityEdit;


import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.likairabbit.tool.R;


public class SecurityEdit extends LinearLayout{
	 public SecurityEdit(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = LayoutInflater.from(context);
        initWidget(inflater);
	}

	private EditText mEditText;
	    private TextView oneTextView;
	    private TextView twoTextView;
	    private TextView threeTextView;
	    private TextView fourTextView;
	    private TextView fiveTextView;
	    private TextView sixTextView;
	    private TextView[] mTextViews;

	    private StringBuilder builder = new StringBuilder();
	    private SecurityEditCompileListener mListener;

	

	    private void initWidget(LayoutInflater inflater) {
	        View contentView = inflater.inflate(R.layout.auth_code_edittext_widget, this,false);
	        mEditText = (EditText) contentView
	                .findViewById(R.id.sdk2_pwd_edit_simple);
	        oneTextView = (TextView) contentView
	                .findViewById(R.id.pwd_one_tv);
	        twoTextView = (TextView) contentView
	                .findViewById(R.id.pwd_two_tv);
	        threeTextView = (TextView) contentView
	                .findViewById(R.id.pwd_three_tv);
	        fourTextView = (TextView) contentView
	                .findViewById(R.id.pwd_four_tv);
	        fiveTextView = (TextView) contentView
	                .findViewById(R.id.pwd_five_tv);
	        sixTextView = (TextView) contentView
	                .findViewById(R.id.pwd_six_tv);


	        LayoutParams lParams = new LayoutParams(
	                LayoutParams.MATCH_PARENT,
	                LayoutParams.WRAP_CONTENT);
	        mEditText.addTextChangedListener(mTextWatcher);
	        mTextViews = new TextView[] { oneTextView, twoTextView, threeTextView,
	                fourTextView, fiveTextView, sixTextView };
	        this.addView(contentView, lParams);
	    }

	    TextWatcher mTextWatcher = new TextWatcher() {
	        @Override
	        public void onTextChanged(CharSequence s, int start, int before,
	                                  int count) {
	        }
	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count,
	                                      int after) {
	        }

	        @Override
	        public void afterTextChanged(Editable s) {
	            setBuilderValue(s.toString());
	        }
	    };

	    private void setBuilderValue(String str) {
	        int strLen = str.length();
	        int builderLen = builder.length();
	        if(strLen == builderLen){
	            return;
	        }
	        if(strLen < builderLen){
	            for (int i = strLen; i <= builderLen - 1; i++) {
	                mTextViews[i].setText("");
	            }
	            builder = new StringBuilder(str);
	        }else{
	            builder = new StringBuilder(str);
	            for (int i = builderLen; i <= strLen - 1; i++) {
	                if(true){
	                    mTextViews[i].setText("●");
	                }else {
	                    mTextViews[i].setText(builder.toString().substring(i, i + 1));
	                }
	            }
	        }
	        if (mTextViews.length == builder.length() && this.mListener != null) {
	        	
	        	mListener.onNumCompleted(str);
	        }
	    }
	    public void setSecurityEditCompileListener(
	            SecurityEditCompileListener mListener) {
	        this.mListener = mListener;
	       
	    }

	public static SecurityEdit ShowSecurityEdit(AlertDialog dialog){
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		Window window = dialog.getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		window.setContentView(R.layout.activity_input_password_dialog);
		SecurityEdit editSecurityCode = (SecurityEdit) window
				.findViewById(R.id.security_linear123);
		return editSecurityCode;
	}
}
