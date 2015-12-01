package com.awesomeness.christine.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class player_v_player extends Activity {

    // initialize tic tac toe buttons
    Button grid0;
    Button grid1;
    Button grid2;
    Button grid3;
    Button grid4;
    Button grid5;
    Button grid6;
    Button grid7;
    Button grid8;
    Button replaybutton;
    TextView status;

    /* tic tac toe grid spots and the corresponding IDs
        0 1 2
        3 4 5
        6 7 8
     */

    /* initialize gameboard array
     * 0  means the grid spot is still available
     * 1 means the spot is taken by the player
     * 10 means the spot is taken by the computer
     */
    int[] gameboard = {0,0,0,0,0,0,0,0,0};
    int player = 1;

    // possible winning combinations (3 in a row)
    int[][] win_combos = {
            {0,1,2},
            {0,3,6},
            {0,4,8},
            {1,4,7},
            {2,5,8},
            {3,4,5},
            {6,7,8},
            {2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_v_player);

        replaybutton = (Button) findViewById(R.id.replaybutton);
        status = (TextView) findViewById(R.id.status);

        playerturn();
    }

    public void playerturn(){
        // configure buttons n grid so play can choose one
        for( int i = 0; i < 9; i++)
        {
            if( gameboard[i] == 0 ){    // if the spot in the gameboard is 0, that means it is still available to be played
                switch(i){              // make the button clickable if it is still available
                    case 0: configurebutton0(); break;
                    case 1: configurebutton1(); break;
                    case 2: configurebutton2(); break;
                    case 3: configurebutton3(); break;
                    case 4: configurebutton4(); break;
                    case 5: configurebutton5(); break;
                    case 6: configurebutton6(); break;
                    case 7: configurebutton7(); break;
                    case 8: configurebutton8(); break;
                }
            }
        }
    }

    public boolean checkwin(){
        // check if there was a win
        // 0 1 2
        // 3 4 5
        // 6 7 8

        int[] checkarray = {0,0,0,0,0,0,0,0}; // adds up different winning combinations

        checkarray[0] = gameboard[0] + gameboard[1] + gameboard[2];    // 0 1 2 in a row
        checkarray[1] = gameboard[0] + gameboard[3] + gameboard[6];    // 0 3 6 in a row
        checkarray[2] = gameboard[0] + gameboard[4] + gameboard[8];    // 0 4 8 in a row
        checkarray[3] = gameboard[1] + gameboard[4] + gameboard[7];    // 1 4 7 in a row
        checkarray[4] = gameboard[2] + gameboard[5] + gameboard[8];    // 2 3 8 in a row
        checkarray[5] = gameboard[3] + gameboard[4] + gameboard[5];    // 3 4 5 in a row
        checkarray[6] = gameboard[6] + gameboard[7] + gameboard[8];    // 0 1 2 in a row
        checkarray[7] = gameboard[2] + gameboard[4] + gameboard[6];    // 2 4 6 in a row

        for( int i = 0; i < 8; i++)
        {
            if( checkarray[i] == 3) { // there is 3 in a row for the player if it adds up to 3
                //player won;
                gameWon(i);
                return true;
            }
            else if( checkarray[i] == 30 ) { // there is 3 in a row for the computer if it adds up to 30
                // computer won;
                gameWon(i);
                return true;
            }
        }
        return false;
    }

    public void gameWon(int combo){
        //change colors of 3 in a row
        for( int i = 0; i < 3; i++){
            switch(win_combos[combo][i]){
                case 0: grid0.setTextColor(0xFFFF615D); break;
                case 1: grid1.setTextColor(0xFFFF615D); break;
                case 2: grid2.setTextColor(0xFFFF615D); break;
                case 3: grid3.setTextColor(0xFFFF615D); break;
                case 4: grid4.setTextColor(0xFFFF615D); break;
                case 5: grid5.setTextColor(0xFFFF615D); break;
                case 6: grid6.setTextColor(0xFFFF615D); break;
                case 7: grid7.setTextColor(0xFFFF615D); break;
                case 8: grid8.setTextColor(0xFFFF615D); break;
            }
        }

        // disable all grid buttons
        grid0.setEnabled(false);
        grid1.setEnabled(false);
        grid2.setEnabled(false);
        grid3.setEnabled(false);
        grid4.setEnabled(false);
        grid5.setEnabled(false);
        grid6.setEnabled(false);
        grid7.setEnabled(false);
        grid8.setEnabled(false);

        replaybutton.setVisibility(View.VISIBLE);
        status.setVisibility(View.VISIBLE);
        if(gameboard[win_combos[combo][0]] == 1) status.setText("Player 1 Won!");
        else status.setText("Player 2 Won!");

        replaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartgame();
            }
        });
    }

    public boolean checktie(){
        // check if there was a tie
        for( int i = 0; i < 9; i++){   // if a spot was not taken yet there is no tie
            if( gameboard[i] != 1 && gameboard[i] != 10) return false;
        }

        // disable all grid buttons
        grid0.setEnabled(false);
        grid1.setEnabled(false);
        grid2.setEnabled(false);
        grid3.setEnabled(false);
        grid4.setEnabled(false);
        grid5.setEnabled(false);
        grid6.setEnabled(false);
        grid7.setEnabled(false);
        grid8.setEnabled(false);

        replaybutton.setVisibility(View.VISIBLE);   // show replay button
        status.setVisibility(View.VISIBLE);         // show status of who won
        status.setText("You Tied!");

        replaybutton.setOnClickListener(new View.OnClickListener() {    // set replay button
            @Override
            public void onClick(View v) {
                restartgame();
            }
        });

        return true;
    }

    public void restartgame(){
        // enable all grid buttons
        grid0.setEnabled(true);
        grid1.setEnabled(true);
        grid2.setEnabled(true);
        grid3.setEnabled(true);
        grid4.setEnabled(true);
        grid5.setEnabled(true);
        grid6.setEnabled(true);
        grid7.setEnabled(true);
        grid8.setEnabled(true);

        // clear X's and O's
        grid0.setText("");
        grid1.setText("");
        grid2.setText("");
        grid3.setText("");
        grid4.setText("");
        grid5.setText("");
        grid6.setText("");
        grid7.setText("");
        grid8.setText("");

        // set colors back to white
        grid0.setTextColor(0xFFFFFFFF);
        grid1.setTextColor(0xFFFFFFFF);
        grid2.setTextColor(0xFFFFFFFF);
        grid3.setTextColor(0xFFFFFFFF);
        grid4.setTextColor(0xFFFFFFFF);
        grid5.setTextColor(0xFFFFFFFF);
        grid6.setTextColor(0xFFFFFFFF);
        grid7.setTextColor(0xFFFFFFFF);
        grid8.setTextColor(0xFFFFFFFF);

        // hide replay button and status
        replaybutton.setVisibility(View.INVISIBLE);
        status.setVisibility(View.INVISIBLE);

        player = 1;     // reset back to player 1

        // reset gameboard
        for(int i = 0 ; i < 9 ; i++) gameboard[i]=0;
    }

    public void setGrid(int choice)
    {
        String xo;
        gameboard[choice] = player; // marks 1 for player 1, marks 10 for player 2

        // player 1 is O
        // player 2 is X
        if(player == 1)  {
            xo = "O";
            player = 10; // toggle player
        }
        else {
            xo = "X";
            player = 1; // toggle player
        }

        // set chosen grid to either x or o
        switch(choice){
            case 0: grid0.setText(xo);break;
            case 1: grid1.setText(xo);break;
            case 2: grid2.setText(xo);break;
            case 3: grid3.setText(xo);break;
            case 4: grid4.setText(xo);break;
            case 5: grid5.setText(xo);break;
            case 6: grid6.setText(xo);break;
            case 7: grid7.setText(xo);break;
            case 8: grid8.setText(xo);break;
        }

        checkwin(); // check if anybody won
        checktie(); // check if anybody tied
    }

    /***********************************************************/

    // Configure buttons on tic tac toe grid to be clickable

    public void configurebutton0(){
        grid0 = (Button) findViewById(R.id.grid0);
        grid0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid0.setEnabled(false);    // set to be unclickable
                setGrid(0);                 // set grid choice to x or o
            }
        });
    }

    public void configurebutton1(){
        grid1 = (Button) findViewById(R.id.grid1);
        grid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid1.setEnabled(false);    // set to be unclickable
                setGrid(1);                 // set grid choice to x or o
            }
        });
    }

    public void configurebutton2(){
        grid2 = (Button) findViewById(R.id.grid2);
        grid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid2.setEnabled(false);    // set to be unclickable
                setGrid(2);                 // set grid choice to x or o
            }
        });
    }

    public void configurebutton3(){
        grid3 = (Button) findViewById(R.id.grid3);
        grid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid3.setEnabled(false);    // set to be unclickable
                setGrid(3);                 // set grid choice to x or o
            }
        });
    }

    public void configurebutton4(){
        grid4 = (Button) findViewById(R.id.grid4);
        grid4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid4.setEnabled(false);    // set to be unclickable
                setGrid(4);                 // set grid choice to x or o
            }
        });
    }

    public void configurebutton5(){
        grid5 = (Button) findViewById(R.id.grid5);
        grid5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid5.setEnabled(false);    // set to be unclickable
                setGrid(5);                 // set grid choice to x or o
            }
        });
    }

    public void configurebutton6(){
        grid6 = (Button) findViewById(R.id.grid6);
        grid6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid6.setEnabled(false);    // set to be unclickable
                setGrid(6);                 // set grid choice to x or o
            }
        });
    }

    public void configurebutton7(){
        grid7 = (Button) findViewById(R.id.grid7);
        grid7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid7.setEnabled(false);    // set to be unclickable
                setGrid(7);                 // set grid choice to x or o
            }
        });
    }

    public void configurebutton8(){
        grid8 = (Button) findViewById(R.id.grid8);
        grid8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid8.setEnabled(false);    // set to be unclickable
                setGrid(8);                 // set grid choice to x or o
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_v_player, menu);
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
