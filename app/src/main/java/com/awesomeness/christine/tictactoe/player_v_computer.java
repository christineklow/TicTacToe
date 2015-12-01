package com.awesomeness.christine.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import java.util.Random;
import android.widget.TextView;



public class player_v_computer extends Activity {

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
        setContentView(R.layout.activity_player_v_computer);

        replaybutton = (Button) findViewById(R.id.replaybutton);
        status = (TextView) findViewById(R.id.status);

        playerturn();
    }

    public void playerturn(){
        // configure buttons n grid so play can choose one
        for( int i = 0; i < 9; i++)
        {
            if( gameboard[i] == 0 ){    // if the spot in the gameboard is 0, that means it is still available to be played
                switch(i){          // make the button clickable if it is still available
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
        if(gameboard[win_combos[combo][0]] == 1) status.setText("You Won!");
        else status.setText("Computer Won.");

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

        replaybutton.setVisibility(View.VISIBLE);       // show replay button
        status.setVisibility(View.VISIBLE);             // show status on who won
        status.setText("You Tied!");

        replaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartgame();
            }
        });     // set replay button

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

        // reset gameboard
        for(int i = 0 ; i < 9 ; i++) gameboard[i]=0;
    }

    public void computerturn(){
        // computer makes a move
        int choice = 0;
        int x,y,z,sum;
        int[] choice_array = {0,0,0};
        int weight = 0;     // priority of move
        for( int i = 0; i < 8; i++){
            x = win_combos[i][0];
            y = win_combos[i][1];
            z = win_combos[i][2];
            sum = gameboard[x] + gameboard[y] + gameboard[z];
            if( sum == 20 ) {
                // if it adds to 20, then it is a winning combo
                if(weight < 40) {
                    weight = 40;
                    choice_array[0] = x;
                    choice_array[1] = y;
                    choice_array[2] = z;
                }
                break;
            }
            else if( sum == 2){
                // if it adds to 2, that means player has a chance to win so block it
                if(weight < 30) {
                    weight = 30;
                    choice_array[0] = x;
                    choice_array[1] = y;
                    choice_array[2] = z;
                }
            }
            else if( sum == 10 ) {
                // if it adds to 10, that means there are 2 empty spaces in a row for a winning opportunity
                if(weight < 20)
                {
                    weight = 20;
                    choice_array[0] = x;
                    choice_array[1] = y;
                    choice_array[2] = z;
                }
            }
            else {
                // pick randomly
                if(weight < 10) weight = 10;
            }
        }

        if(weight != 10) {    // check which is the empty space and set choice as that
            for (int i = 0; i < 3; i++) {
                if (gameboard[choice_array[i]] == 0) choice = choice_array[i];
            }
        }
        else{
            do {
                Random random = new Random(System.currentTimeMillis()); // seed random time
                choice = random.nextInt(9);     // generate random spot if no preferred choice
            } while(gameboard[choice] != 0);    // make sure the spot is available
        }

        // disable the button of the choice and set it as x
        switch(choice){
            case 0: {
                grid0.setText("X");         // set text
                grid0.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 1: {
                grid1.setText("X");         // set text
                grid1.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 2: {
                grid2.setText("X");         // set text
                grid2.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 3: {
                grid3.setText("X");         // set text
                grid3.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 4: {
                grid4.setText("X");         // set text
                grid4.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 5: {
                grid5.setText("X");         // set text
                grid5.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 6: {
                grid6.setText("X");         // set text
                grid6.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 7: {
                grid7.setText("X");         // set text
                grid7.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 8: {
                grid8.setText("X");         // set text
                grid8.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
        }

        gameboard[choice] = 10;  // set spot on gameboard as taken by computer
        checkwin();            // check if computer won
    }

    /***********************************************************/

    // Configure buttons on tic tac toe grid to be clickable

    public void configurebutton0(){
        grid0 = (Button) findViewById(R.id.grid0);
        grid0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid0.setText("O");         // set text
                grid0.setEnabled(false);    // set to be unclickable
                gameboard[0]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn();    // computer's turn if there was no win or tie
            }
        });
    }

    public void configurebutton1(){
        grid1 = (Button) findViewById(R.id.grid1);
        grid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid1.setText("O");         // set text
                grid1.setEnabled(false);    // set to be unclickable
                gameboard[1]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn(); // computer's turn if there was no win or tie
            }
        });
    }

    public void configurebutton2(){
        grid2 = (Button) findViewById(R.id.grid2);
        grid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid2.setText("O");         // set text
                grid2.setEnabled(false);    // set to be unclickable
                gameboard[2]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn(); // computer's turn if there was no win or tie
            }
        });
    }

    public void configurebutton3(){
        grid3 = (Button) findViewById(R.id.grid3);
        grid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid3.setText("O");         // set text
                grid3.setEnabled(false);    // set to be unclickable
                gameboard[3]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn(); // computer's turn if there was no win or tie
            }
        });
    }

    public void configurebutton4(){
        grid4 = (Button) findViewById(R.id.grid4);
        grid4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid4.setText("O");         // set text
                grid4.setEnabled(false);    // set to be unclickable
                gameboard[4]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn(); // computer's turn if there was no win or tie
            }
        });
    }

    public void configurebutton5(){
        grid5 = (Button) findViewById(R.id.grid5);
        grid5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid5.setText("O");         // set text
                grid5.setEnabled(false);    // set to be unclickable
                gameboard[5]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn(); // computer's turn if there was no win or tie
            }
        });
    }

    public void configurebutton6(){
        grid6 = (Button) findViewById(R.id.grid6);
        grid6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid6.setText("O");         // set text
                grid6.setEnabled(false);    // set to be unclickable
                gameboard[6]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn(); // computer's turn if there was no win or tie
            }
        });
    }

    public void configurebutton7(){
        grid7 = (Button) findViewById(R.id.grid7);
        grid7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid7.setText("O");         // set text
                grid7.setEnabled(false);    // set to be unclickable
                gameboard[7]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn(); // computer's turn if there was no win or tie
            }
        });
    }

    public void configurebutton8(){
        grid8 = (Button) findViewById(R.id.grid8);
        grid8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid8.setText("O");         // set text
                grid8.setEnabled(false);    // set to be unclickable
                gameboard[8]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn(); // computer's turn if there was no win or tie
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_v_computer, menu); // computer's turn if there was no win or tie
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
