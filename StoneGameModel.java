/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;
/**
 *
 * @author gekat
 */

enum Mode {COMPUTER, HUMAN}
enum Start {COMPUTER, HUMAN}

public class StoneGameModel {
    public String player1;
    public String player2;
    public int stonesNumber;
    public Mode mode;
    public Start start;
    
    
    public StoneGameModel(){
        player1 = "player 1";
        player2 = "Computer";
        stonesNumber = 21;
        mode = Mode.COMPUTER;
        start = Start.COMPUTER;
    }
    
    public StoneGameModel(String firstPlayerName, String secondPlayerName, int numberOfStones, Mode mode, Start starts){
        player1 = firstPlayerName;
        player2 = secondPlayerName;
        stonesNumber = numberOfStones;
        this.mode = mode;
        this.start = starts;
    }
    
    public void game(){
    }
    
    public String getFirstPlayerName(){
        return player1;
    }
    
    public String getSecondPlayerName(){
        return player2;
    }
    
    public int getNumberOfStones(){
        return stonesNumber;
    }
    
    public void setFirstPlayerName(String newName){
        player1 = newName;
    }
    
    public void setSecondPlayerName(String newName){
        player2 = newName;
    }
    
    public void setNumberOfStones(int newValue){
        stonesNumber = newValue;
    }
    
    public void setMode(Mode newMode){
        mode = newMode;
    }
    
    public Mode getMode(){
        return mode;
    }
}


