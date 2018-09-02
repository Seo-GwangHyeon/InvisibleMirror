package com.example.invisiblemirror.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.invisblemirror.R;
import com.example.invisiblemirror.MainActivity;

import static com.example.invisiblemirror.keyboard.inputmethod.SoftKeyboard.appData;
import static com.example.invisiblemirror.keyboard.inputmethod.SoftKeyboard.editor;

public class DBHelper extends SQLiteOpenHelper {


    private Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    /**
     * Database가 존재하지 않을 때, 딱 한번 실행된다. * DB를 만드는 역할을 한다. * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // String 보다 StringBuffer가 Query 만들기 편하다.
         StringBuffer sb = new StringBuffer();

         sb.append(" create table if not exists user_word ( ");
         sb.append(" _id integer primary key autoincrement, ");
         sb.append(" title text,");
         sb.append(" content text)");
        db.execSQL(sb.toString());

        if(appData.getBoolean("initDatabase",true)) {
            editor.putBoolean("initDatabase",false);
            editor.apply();
            ContentValues values = new ContentValues();
            values.put("title", "사용한단어");
            values.put("content", " ");
            db.insert("user_word", null, values);

            String str = context.getResources().getString(R.string.absolute);
            String[] array1 = str.split(",");

            int length = array1.length;
            for (int i = 0; i < length; i++) {
                values.put("title", "absolute");
                values.put("content", array1[i]);
                db.insert("user_word", null, values);
            }

            str = "";
            str = context.getResources().getString(R.string.about_me);
            array1 = str.split(",");
            length = array1.length;
            for (int i = 0; i < length; i++) {
                values.put("title", "about_me");
                values.put("content", array1[i]);
                db.insert("user_word", null, values);
            }

            str = "";
            str = context.getResources().getString(R.string.negative);
            array1 = str.split(",");
            length = array1.length;
            for (int i = 0; i < length; i++) {
                values.put("title", "about_me");
                values.put("content", array1[i]);
                db.insert("user_word", null, values);
            }
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }
}

