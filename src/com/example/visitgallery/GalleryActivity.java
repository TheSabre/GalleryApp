package com.example.visitgallery;

import java.io.ByteArrayInputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryActivity extends Activity {
	
	Database db;
	byte[] photo;
	ImageView iv;
	TextView tv;
	String mum,ny,tok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		db = new Database(this);
		iv = (ImageView)findViewById(R.id.imageView);
		mum = getString(R.string.mum);
		ny = getString(R.string.ny);
		tok = getString(R.string.tok);
		tv = (TextView)findViewById(R.id.title);
	}
	public void getMumbai(View v){
		Log.e("mumbai","Getting mumbai");
		photo = db.getImage("Mumbai");
		tv.setText(mum);
		ByteArrayInputStream byteIS = new ByteArrayInputStream(photo);
		Bitmap bit_image = BitmapFactory.decodeStream(byteIS);
		BitmapDrawable bd = new BitmapDrawable(getResources(), bit_image);
		iv.setBackgroundDrawable(bd);
		
	}
	public void getNew_York(View v){
		Log.e("NewYork","Getting New York");
		photo = db.getImage("New York");
		tv.setText(ny);
		ByteArrayInputStream byteIS = new ByteArrayInputStream(photo);
		Bitmap bit_image = BitmapFactory.decodeStream(byteIS);
		BitmapDrawable bd = new BitmapDrawable(getResources(), bit_image);
		iv.setBackgroundDrawable(bd);
	}
	public void getTokyo(View v){
		photo = db.getImage("Tokyo");
		tv.setText(tok);
		ByteArrayInputStream byteIS = new ByteArrayInputStream(photo);
		Bitmap bit_image = BitmapFactory.decodeStream(byteIS);
		BitmapDrawable bd = new BitmapDrawable(getResources(), bit_image);
		iv.setBackgroundDrawable(bd);
	}
}
