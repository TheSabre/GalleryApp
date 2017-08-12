package com.example.visitgallery;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText pass;
	EditText f_name;
	EditText l_name;
	EditText id;
	String f,l,p,i;
	Database db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		db = new Database(this);
		pass = (EditText)findViewById(R.id.pass);
		f_name = (EditText)findViewById(R.id.first);
		l_name = (EditText)findViewById(R.id.last);
		id = (EditText)findViewById(R.id.id);
		pass.setTypeface(Typeface.DEFAULT);
		pass.setTransformationMethod(new PasswordTransformationMethod());
	}
	public void login(View v){
		if(f_name.getText().toString().matches(".*\\d.*") | f_name.getText().toString().contains(" ")
				| f_name.getText().toString().contains("!") | f_name.getText().toString().contains("_")){
			f_name.setError("Name should not contain numbers, spaces "+ "_ or"+" !");
		}
		else if(l_name.getText().toString().matches(".*\\d.*") | l_name.getText().toString().contains(" ")
				| l_name.getText().toString().contains("!") | l_name.getText().toString().contains("_")){
			l_name.setError("Name should not contain numbers, spaces "+ "_ or"+" !");
		}
		else if(!(pass.getText().toString().matches(".*[A-Z].*")) | !(pass.getText().toString().matches(".*\\d.*"))){
			pass.setError("Passwords should contain atleast one number and one upper case letter.");
		}
		else if(id.length()!=9)
		{
			id.setError("ID should be 9 digit long!");
		}
		else{
			f = f_name.getText().toString();
			l = l_name.getText().toString();
			p = pass.getText().toString();
			i = id.getText().toString();
			
			String password = db.validateInfo(f,l,p,i);
			
			if(p.equals(password)){
				startActivity(new Intent(LoginActivity.this, GalleryActivity.class));
			}
			else{
				Toast.makeText(LoginActivity.this,"Login details do not exist", Toast.LENGTH_LONG).show();
			}
		}
		}
	
}
