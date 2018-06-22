package com.example.tuanspk.baitaptuan09gamedapchuot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tuanspk.baitaptuan09gamedapchuot.DeviceHelper.AndroidDeviceHelper;

public class MainActivity extends AppCompatActivity {

    public static int iWidth;
    public static int iHeight;

    public static String sPlayerName;

    Button buttonPlay;

    EditText editTextPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iWidth = AndroidDeviceHelper.getWithScreen(this);
        iHeight = AndroidDeviceHelper.getHeightSreen(this);

        editTextPlayerName = (EditText) findViewById(R.id.edittext_player_name);
        editTextPlayerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() != 0)
                    buttonPlay.setEnabled(true);
                else buttonPlay.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        PlayGame view = new PlayGame(this);
//        view.setBackgroundColor(Color.WHITE);
//        setContentView(view);

        buttonPlay = (Button) findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPlayerName = editTextPlayerName.getText().toString().trim();

                Intent intent = new Intent(MainActivity.this, PlayGameActivity.class);
                startActivity(intent);
            }
        });
    }
}
