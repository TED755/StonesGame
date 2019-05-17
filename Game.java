package sample;

enum COMPHUMAN {HUMAN, COMPUTER}
//enum FIRSTSECOND {FIRST, SECOND}
enum WINOPTION {TAKE, LEAVE}

public class Game {
    private COMPHUMAN game_mode;
    private COMPHUMAN turn;
    private WINOPTION win_option;

    private String player1_name;
    private String player2_name;

    private int stones_number;
    private int removed_stones;

    public Game(){}

    public Game(COMPHUMAN _game_mode, WINOPTION _win_option, COMPHUMAN _first_turn, String names, int _stones_number){
        this.game_mode = _game_mode;
        this.win_option = _win_option;
        this.turn = _first_turn;

        if(turn == COMPHUMAN.COMPUTER){
            player1_name = "Компьютер";
            player2_name = names;
        }
        else {
            player2_name = "Компьютер";
            player1_name = names;
        }

        stones_number = _stones_number;
    }
    public Game(COMPHUMAN _game_mode, WINOPTION _win_option, String names, int _stones_number){
        this.game_mode = _game_mode;
        this.win_option = _win_option;

        String str[] = names.split(" ");
        player1_name = str[0];
        player2_name = str[1];

        stones_number = _stones_number;
    }

    public void recountStones(int value){
        if(value <= 0)
            throw new NumberFormatException();
        stones_number -= value;
    }

    public void changeTurn(){
        turn = turn == COMPHUMAN.COMPUTER ? COMPHUMAN.HUMAN : COMPHUMAN.COMPUTER;
    }

    public boolean isVictory(){
        if(win_option == WINOPTION.TAKE){
            if(stones_number == 0) {
                return true;
            }
        }
        if(win_option == WINOPTION.LEAVE){
            if(stones_number == 1){
                return true;
            }
        }
        return false;

    }

    public void setRemovedStones(int value){
        if(value <= 0)
            throw new NumberFormatException();
        removed_stones = value;
    }
    public void takeLastGame(){
        /*Computer comp_strategy = new Computer();
        while(stones_number > 1){
            if(turn == COMPHUMAN.COMPUTER)
                comp_strategy.takeLastStoneWinStrategy(stones_number);
            else

        }*/
    }

}
