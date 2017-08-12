package com.example.visitgallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

	public static final String D_name="info.db";
	public static final String Table_name="info_table";
	public static final String Table_image="final_images";
	public final String column_1="f_name";
	public final String column_2="l_name";
	public final String column_3="id";
	public final String column_4="pass";
	public final String column_img="image";
	public final String column_name="img_name";
	SQLiteDatabase db;
	public static final int DATABASE_VERSION=6;

	public Database(Context context) {
		super(context, D_name, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		SQLiteDatabase db = this.getReadableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table " + Table_name + "(f_name TEXT, l_name TEXT, id INTEGER PRIMARY KEY, pass TEXT)");
		Log.v("Database","Created Database info");
		db.execSQL("create table " + Table_image + "(img_name TEXT, image BLOB)");
		Log.v("Database","Created table image");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + Table_name);
		db.execSQL("DROP TABLE IF EXISTS " + Table_image);
		Log.e("Drop", "Table Dropped");
		onCreate(db);
	}
	
	public void addImage(String name, byte[] photo){
		ContentValues contentValues = new ContentValues();
		contentValues.put(column_name, name);
		contentValues.put(column_img, photo);
		
		long result = db.insert(Table_image, null, contentValues);
		if(result==-1){
			Log.e("Adding Images","Failed");
		}
		else{
			Log.e("Adding Images", "Not failed");
			Log.e("Blob", ""+ photo);
		}
	}
	
	public byte[] getImage(String str){
		db = getReadableDatabase();
		byte[] img = null;
		String query = "select image from final_images where img_name = '"+str+"'";
		Cursor cur = db.rawQuery(query, null);
		while(cur.moveToNext()){
			 img = cur.getBlob(0);
		}
		return img;
	}
	
	public boolean addData (String first, String last, String identity, String pass){
		db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(column_1, first);
		contentValues.put(column_2, last);
		contentValues.put(column_3, identity);
		contentValues.put(column_4, pass);
		long result = db.insert(Table_name, null, contentValues);
		if(result==-1){
			Log.e("ID",identity);
			Log.e("First Name",first);
			Log.e("last name",last);
			Log.e("Password",pass);
			
			return false;
		}
		else{
			return true;
		}
		
	}
	
	
	
	public String validateInfo(String first, String last, String pass, String id){
		db = getReadableDatabase();
		String query = "select f_name, l_name, id, pass from info_table";
		String f,l,p,i;
		Cursor cursor = db.rawQuery(query, null);
		p = "Password doesn't exist.";
		if(cursor.moveToFirst()){
			do{
				f = cursor.getString(0);
				if(f.equals(first)){

					l = cursor.getString(1);
					if(l.equals(last)){
						i = cursor.getString(2);
						if(i.equals(id)){
							p = cursor.getString(3);
						}
					}
					break;
				}
			}while(cursor.moveToNext());
		}
		return p;
	}
	
}
