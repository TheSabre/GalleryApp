package com.example.visitgallery;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {

	EditText pass, f_name, l_name, id ;
	Button reg_button;
	String str="\\d";
	Database db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		db = new Database(this);
		id = (EditText)findViewById(R.id.id);
		pass = (EditText)findViewById(R.id.pass);
		f_name = (EditText)findViewById(R.id.first);
		l_name = (EditText)findViewById(R.id.last);
		pass.setTypeface(Typeface.DEFAULT);
		pass.setTransformationMethod(new PasswordTransformationMethod());
	}
	
	
	public void register(View v){
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
			boolean added = db.addData(f_name.getText().toString(), l_name.getText().toString(), id.getText().toString(), pass.getText().toString());
			
			if(added){
				Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_LONG).show();
				startActivity(new Intent(this,EntryActivity.class));
			}
			else{
				Toast.makeText(SignUp.this, "Registration unsuccessful", Toast.LENGTH_LONG).show();
			}
		}
		}
		
}
