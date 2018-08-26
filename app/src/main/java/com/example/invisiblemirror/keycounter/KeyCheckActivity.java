package com.example.invisiblemirror.keycounter;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.invisblemirror.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;

public class KeyCheckActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView textView;
    private String content;
    private int PointCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_check);

        button=(Button) findViewById(R.id.button);
        textView=(TextView) findViewById(R.id.textView);

        button.setOnClickListener(this);
        PointCount=0;

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.button)
        {
            try {
                String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
                String FILENAME = "kingking.txt";
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
                br.close();
                BufferedReader br1 = new BufferedReader(new FileReader(SDCARD + File.separator + "input.txt"));
                while(true) {
                    String line = br1.readLine();
                  //  line =encodingStr(line);
                    Log.v("Gwang",line);
                    if(content.contains(line))
                        PointCount++;
                    if (line==null) break;
                }
                br1.close();
                /***정환이 봐라: PointCount가 점수*/

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
