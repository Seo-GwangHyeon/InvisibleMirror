package com.example.invisiblemirror.keycounter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.invisblemirror.R;
import com.example.invisiblemirror.database.DBHelper;

import java.io.UnsupportedEncodingException;

public class KeyCheckActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView textView;
    private String content;
    private int PointCount;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_check);

        button=(Button) findViewById(R.id.button);
        textView=(TextView) findViewById(R.id.textView);

        button.setOnClickListener(this);
        PointCount=0;

        dbHelper =new DBHelper(this,"user_word.db",null,1);

        db = dbHelper.getReadableDatabase();

        dbHelper.onCreate(db);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.button)
        {
            try {

                String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
                String FILENAME = "kingking.txt";/*
                content="";
                //File infile = new File(SDCARD + File.separator + FILENAME);
                BufferedReader br = new BufferedReader(new FileReader(SDCARD + File.separator + FILENAME));
                while(true) {
                    String line = br.readLine();
                    content+=line;
                    //content+=encodingStr(line);
                    Log.v("Gwang","wow: "+content);

                    if (line==null) break;
                }
                br.close();*/
               String SQL="select content from user_word where title=?";
                String[] args = new String[] {"사용한단어"};
                Cursor c = db.rawQuery(SQL,args);
                c.moveToNext();
                String content =c.getString(0);

                //여기까지가 사용한 단어 읽기

             /* BufferedReader br1 = new BufferedReader(new FileReader(SDCARD + File.separator + "input.txt"));
                PointCount=0;
                while(true) {
                    String line = br1.readLine();
                  //  line =encodingStr(line);
                    Log.v("Gwang",line);
                    if(content.contains(line))
                        PointCount++;
                    if (line==null) break;
                }
                br1.close();
                */

                PointCount=0;
                SQL="select content FROM user_word where title=?";
                args = new String[] {"about_me"};
                Cursor cursor = db.rawQuery(SQL, args);
                while(cursor.moveToNext() ) {
                    String temp=cursor.getString(0);
                    //Log.v("Gwang","aboutme"+temp);
                    if(content.contains(temp))
                        PointCount++;
                }

                SQL="select content FROM user_word where title=?";
                args = new String[] {"absolute"};
                 cursor = db.rawQuery(SQL, args);
                while( cursor.moveToNext() ) {
                    String temp=cursor.getString(0);
                    //Log.v("Gwang","absolute"+temp);
                    if(content.contains(temp))
                        PointCount++;
                }
                SQL="select content FROM user_word where title=?";
                args = new String[] {"negative"};
                cursor = db.rawQuery(SQL, args);
                while( cursor.moveToNext() ) {
                    String temp=cursor.getString(0);
                    //Log.v("Gwang","negative"+temp);
                    if(content.contains(temp))
                        PointCount++;
                }
                /** PointCount가 점수*/

            } catch (Exception e) {
                Log.v("Gwang", e.getMessage());
            }
            textView.setText(String.valueOf(PointCount)+"번");


        }
    }

    public String encodingStr(String str)
    {
          final String DEF_CHARSET = "UTF-8";
        String ret = null;
        try
        {
            ret = new String(str.getBytes(), DEF_CHARSET);
        }
        catch(UnsupportedEncodingException e)
        {
            Log.v("Gwang", "UnsupportedEncodingException");
        }

        return ret;
    }
}
