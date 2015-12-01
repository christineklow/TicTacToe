package com.awesomeness.christine.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class home extends Activity {

    // initialize buttons
    Button pvp;
    Button pvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        configureoneplayer();
        configuretwoplayer();
    }

    private void P_v_P() {
        startActivity(new Intent("player_v_player"));
    }

    // 1 player button
    public void configureoneplayer(){
        pvc = (Button) findViewById(R.id.pvc);
        pvc.setEnabled(true);
        pvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("player_v_computer"));
            }
        });
    }

    // 2 player button
    public void configuretwoplayer(){
        pvp = (Button) findViewById(R.id.pvp);
        pvp.setEnabled(true);
        pvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("player_v_player"));
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
