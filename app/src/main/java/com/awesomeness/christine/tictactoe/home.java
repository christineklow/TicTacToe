package com.awesomeness.christine.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.MotionEvent;

public class home extends Activity {

    // initialize buttons
    Button pvp;
    Button pvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        configureoneplayer();   // player vs computer
        configuretwoplayer();   // player vs player
    }

    private void P_v_P() {
        startActivity(new Intent("player_v_player"));
    }

    // 1 player button leads to player vs computer game
    public void configureoneplayer(){
        pvc = (Button) findViewById(R.id.pvc);
        pvc.setEnabled(true);
        pvc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        pvc.setTextColor(0xFFC9FFBE);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        startActivity(new Intent("player_v_computer"));
                        pvc.setTextColor(0xFFFFFFFF);
                    }
                }
                return false;
            }
        });
    }

    // 2 player button leads to player vs player game
    public void configuretwoplayer(){
        pvp = (Button) findViewById(R.id.pvp);
        pvp.setEnabled(true);
        pvp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        pvp.setTextColor(0xFFC9FFBE);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        startActivity(new Intent("player_v_player"));
                        pvp.setTextColor(0xFFFFFFFF);
                    }
                }
                return false;
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
