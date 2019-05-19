package sample;

enum COMPHUMAN {HUMAN, COMPUTER}
enum WINOPTION {TAKE, LEAVE}

public class Game {
    private COMPHUMAN game_mode;
    private COMPHUMAN turn;
    private WINOPTION win_option;

    private Player player1;
    private Player player2;
    private Computer computer;

    private String player1_name;
    private String player2_name;

    private int stones_number;
    private int removed_stones;

    public Game(){
        this.game_mode = COMPHUMAN.COMPUTER;
        this.turn = COMPHUMAN.HUMAN;
        this.win_option = WINOPTION.TAKE;

        if(game_mode == COMPHUMAN.COMPUTER){
            if(turn == COMPHUMAN.COMPUTER){
                player1_name = "Компьютер";
                player2_name = "Игрок";
            }
            else {
                player2_name = "Компьютер";
                player1_name = "Игрок";
            }
        }
        else {
            player1_name = "Первый игрок";
            player2_name = "Второй игрок";
        }

        stones_number = 20;
    }

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

    public int getStonesNumber(){
        return stones_number;
    }

    public COMPHUMAN getGame_mode(){
        return game_mode;
    }

    public COMPHUMAN getTurn(){
        return turn;
    }

    public WINOPTION getWin_option(){
        return win_option;
    }

    public String getPlayer1_name(){
        return  player1_name;
    }

    public String getPlayer2_name(){
        return player2_name;
    }

    public int getRemoved_stones(){
        return removed_stones;
    }

    public void setTurn(COMPHUMAN value){
        turn = value;
    }

    public void setGame_mode(COMPHUMAN value){
        game_mode = value;
    }

    public void setPlayer1_name(String newName){
        player1_name = newName;
    }

    public void setPlayer2_name(String newName){
        player2_name = newName;
    }

    public void setWin_option(WINOPTION win_option){ this.win_option = win_option; }

    public void setStones_number(int stones_number){ this.stones_number = stones_number; }

    public void setNicks(String first, String second){
        player1_name = first;
        player2_name = second;
        /*if(turn == COMPHUMAN.COMPUTER){
            player1_name = f;
            player2_name = names;
        }
        else {
            player2_name = "Компьютер";
            player1_name = names;
        }*/
    }

    @Override
    public String toString(){
        String str = "Игра\n";

        str += "• " + (game_mode == COMPHUMAN.HUMAN ? "С человеком" : "С компьютером") + "\n";
        if(game_mode == COMPHUMAN.COMPUTER)
            str += "• Первый ход делает " + (turn == COMPHUMAN.HUMAN ? "человек" : "компьютер") + "\n";
        str += "• Побеждает игрок " + (win_option == WINOPTION.TAKE ? "забравший последний камень" :
                "оставивший последний камень") + "\n";
        str += "• Количество камней в куче " + stones_number + "\n";
        return str;
    }
}
