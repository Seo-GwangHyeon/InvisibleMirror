package com.example.invisblemirror;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText IdEdit,PwEdit;
    Button JoinButton,SigninButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        IdEdit = (EditText) findViewById(R.id.id_edittext);
        PwEdit = (EditText) findViewById(R.id.pw_edittext);

        JoinButton= (Button) findViewById(R.id.join_button);
        SigninButton = (Button) findViewById(R.id.signin_button);

        JoinButton.setOnClickListener(this);
        SigninButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.join_button:
            {
                Toast.makeText(this, "Join Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.signin_button:
            {
                Toast.makeText(this, "Sign in Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }
}