package com.likairabbit.tool;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class LoadingDialog extends AlertDialog{
	private String message = null;
	  private TextView tips_loading_msg;

	  public LoadingDialog(Context paramContext)
	  {
	    super(paramContext);
	    this.message = "";
	  }

	  public LoadingDialog(Context paramContext, int paramInt, String paramString)
	  {
	    super(paramContext, paramInt);
	    this.message = paramString;
	    setCancelable(false);
	  }

	  public LoadingDialog(Context paramContext, String paramString)
	  {
	    super(paramContext);
	    this.message = paramString;
	    setCancelable(false);
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    setContentView(R.layout.loading_dialog);
	    this.tips_loading_msg = ((TextView)findViewById(R.id.tips_loading_msg));
	    this.tips_loading_msg.setText(this.message);
	  }

	  public void setText(int paramInt)
	  {
	    setText(getContext().getResources().getString(paramInt));
	  }

	  public void setText(String paramString)
	  {
	    this.message = paramString;
	    this.tips_loading_msg.setText(this.message);
	  }
}
