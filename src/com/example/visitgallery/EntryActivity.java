package com.example.visitgallery;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class EntryActivity extends Activity {
	
	Database db;
	String a,b,c;
	String first,last,id,pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry);
		db = new Database(this);
		enterRecords();
		convertImages();
	}
	public void convertImages(){
		// Mumbai image
		ByteArrayOutputStream byteOS1 = new ByteArrayOutputStream();
		Bitmap bitmap_mumbai = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.mumbai)).getBitmap();
		bitmap_mumbai.compress(Bitmap.CompressFormat.PNG, 100, byteOS1);
		byte[] mumbai_photo = byteOS1.toByteArray();
		db.addImage("Mumbai".toString(), mumbai_photo); 
		//new_york image
		ByteArrayOutputStream byteOS2 = new ByteArrayOutputStream();
		Bitmap bitmap_new_york = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.new_york)).getBitmap();
		bitmap_new_york.compress(Bitmap.CompressFormat.PNG, 100, byteOS2);
		byte[] new_york_photo = byteOS2.toByteArray();
		db.addImage("New York".toString(), new_york_photo);
		//Tokyo image
		ByteArrayOutputStream byteOS3 = new ByteArrayOutputStream();
		Bitmap bitmap_tokyo = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.tokyo)).getBitmap();
		bitmap_tokyo.compress(Bitmap.CompressFormat.PNG, 100, byteOS3);
		byte[] tokyo_photo = byteOS3.toByteArray();
		db.addImage("Tokyo".toString(), tokyo_photo);
	}
	
	public void enterRecords(){
		first = "Vishnu"; last = "Pisharody"; id="300143609"; pass="Vish12";
		
		a = db.validateInfo(first.toString(), last.toString(), pass.toString(), id.toString());
		
		if(a!=pass){
			Log.e("a",a);
			db.addData(first.toString(), last.toString(), id.toString(), pass.toString());
		}
		first = "Sam"; last = "Fisher"; id="300143610"; pass="Sam123";
		b = db.validateInfo(first.toString(), last.toString(), pass.toString(), id.toString());
		
		if(b!=pass){
			Log.e("b",b);
			db.addData(first.toString(), last.toString(), id.toString(), pass.toString());
		}
		first="Bruce"; last="Wayne";id="300143611";pass="Bru123";
		c = db.validateInfo(first.toString(),last.toString(),pass.toString(),id.toString());
		if(c!=pass){
			Log.e("c",c);
			db.addData(first.toString(),last.toString(),id.toString(),pass.toString());
		}
	}
	
	public void goLogin(View v){
		startActivity(new Intent(this, LoginActivity.class));
	}
	
	public void goSignUp(View v){
		startActivity(new Intent(this, SignUp.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entry, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
