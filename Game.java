package sample;

enum COMPHUMAN {HUMAN, COMPUTER}

public class Game {
    private COMPHUMAN game_mode;
    private COMPHUMAN first_turn;

    private String player1_name;
    private String player2_name;

    private int stones_number;
    public Game(COMPHUMAN _game_mode, COMPHUMAN _first_turn, String names, int _stones_number){
        this.game_mode = _game_mode;
        this.first_turn = _first_turn;
        if(game_mode == COMPHUMAN.HUMAN){
            String str[] = names.split(" ");
            player1_name = str[0];
            player2_name = str[1];
        }
        else {
            if(first_turn == COMPHUMAN.COMPUTER){
                player1_name = "Компьютер";
                player2_name = names;
            }
            else {
                player2_name = "Компьютер";
                player1_name = names;
            }

        }
        stones_number = _stones_number;
    }
}
