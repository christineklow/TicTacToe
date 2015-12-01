package com.awesomeness.christine.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;


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

    int turns = 0; // keeps track on the amount of turns played

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_v_computer);
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

        turns++;
    }

    int[][] win_combos = {
            {0,2,3},
            {0,3,6},
            {0,4,8},
            {1,4,7},
            {2,5,8},
            {3,4,5},
            {6,7,8},
            {2,4,6}
    };

    public boolean checkwin(){
        // check if there was a win
        // 0 1 2
        // 3 4 5
        // 6 7 8
        int[] checkarray = {0}; // adds up different winning combinations
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
                return true;
            }
            else if( checkarray[i] == 30 ) { // there is 3 in a row for the computer if it adds up to 30
                // computer won;
                return true;
            }
        }
        return false;
    }

    public boolean checktie(){
        // check if there was a tie
        return false;
    }

    public void computerturn(){
        // computer makes a move
        int choice = 0;
        int x,y,z,sum;
        int[] choice_array = {0};
        int weight = 0;     // priority of move
        for( int i = 0; i < 8; i++){
            x = win_combos[i][0];
            y = win_combos[i][1];
            z = win_combos[i][2];
            sum = x + y + z;
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
                // if it add to 2, that means player has a chance to win so block it
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

        // check which is the empty space and set choice as that
        for( int i = 0; i < 3; i++){
            if(choice_array[i] == 0) choice = choice_array[i];
        }

        // disable the button of the choice and set it as x
        switch(choice){
            case 0: {
                // set text
                grid0.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 1: {
                // set text
                grid1.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 2: {
                // set text
                grid2.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 3: {
                // set text
                grid3.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 4: {
                // set text
                grid4.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 5: {
                // set text
                grid5.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 6: {
                // set text
                grid6.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 7: {
                // set text
                grid7.setEnabled(false);    // set the button to be no longer clickable
                break;
            }
            case 8: {
                // set text
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
                // set text
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
                // set text
                grid1.setEnabled(false);    // set to be unclickable
                gameboard[1]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn();
            }
        });
    }

    public void configurebutton2(){
        grid2 = (Button) findViewById(R.id.grid2);
        grid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set text
                grid2.setEnabled(false);    // set to be unclickable
                gameboard[2]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn();
            }
        });
    }

    public void configurebutton3(){
        grid3 = (Button) findViewById(R.id.grid3);
        grid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set text
                grid3.setEnabled(false);    // set to be unclickable
                gameboard[3]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn();
            }
        });
    }

    public void configurebutton4(){
        grid4 = (Button) findViewById(R.id.grid4);
        grid4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set text
                grid4.setEnabled(false);    // set to be unclickable
                gameboard[4]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn();
            }
        });
    }

    public void configurebutton5(){
        grid5 = (Button) findViewById(R.id.grid5);
        grid5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set text
                grid5.setEnabled(false);    // set to be unclickable
                gameboard[5]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn();
            }
        });
    }

    public void configurebutton6(){
        grid6 = (Button) findViewById(R.id.grid6);
        grid6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set text
                grid6.setEnabled(false);    // set to be unclickable
                gameboard[6]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn();
            }
        });
    }

    public void configurebutton7(){
        grid7 = (Button) findViewById(R.id.grid7);
        grid7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set text
                grid7.setEnabled(false);    // set to be unclickable
                gameboard[7]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn();
            }
        });
    }

    public void configurebutton8(){
        grid8 = (Button) findViewById(R.id.grid8);
        grid8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set text
                grid8.setEnabled(false);    // set to be unclickable
                gameboard[8]=1;             // marks 1 for player pressing button
                if (!checkwin() && !checktie()) computerturn();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_v_computer, menu);
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
