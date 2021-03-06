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

    private int stones_number;
    private int removed_stones;

    public Game(){
        this.game_mode = COMPHUMAN.COMPUTER;
        this.turn = COMPHUMAN.HUMAN;
        this.win_option = WINOPTION.TAKE;

        player1 = new Player("Первый игрок");
        player2 = new Player("Второй игрок");
        computer = new Computer("Компьютер");
        stones_number = 20;
    }

    public Game (Game game){
        this.game_mode = game.game_mode;
        this.turn = game.turn;
        this.player1 = game.player1;
        this.player2 = game.player2;
        this.computer = game.computer;
        this.stones_number = game.stones_number;
    }

    /*public Game(COMPHUMAN game_mode, WINOPTION win_option, COMPHUMAN first_turn, String names, int stones_number){
        this.game_mode = game_mode;
        this.win_option = win_option;
        this.turn = first_turn;
        player1 = new Player(names);
        computer = new Computer("Компьютер");
        this.stones_number = stones_number;
    }
    public Game(COMPHUMAN game_mode, WINOPTION win_option, String names, int stones_number){
        this.game_mode = game_mode;
        this.win_option = win_option;
        String str[] = names.split(" ");
        player1 = new Player(str[0]);
        player2 = new Player(str[1]);
        this.stones_number = stones_number;
    }*/

    public void recountStones(){
        stones_number -= removed_stones;
        removed_stones = 0;
    }

    public void changeTurn(){
        turn = turn == COMPHUMAN.COMPUTER ? COMPHUMAN.HUMAN : COMPHUMAN.COMPUTER;
    }

    public boolean isVictory(){
        //if(win_option == WINOPTION.TAKE){
            if(stones_number == 0) {
                return true;
            }
        //}
        //if(win_option == WINOPTION.LEAVE){
            //if(stones_number == 1){
                //return true;
            //}
        //}
        return false;
    }

    public void setRemovedStones(int value){
        if(value <= 0 || value > 4)
            throw new NumberFormatException("Выбрано некорректное количество камней");
        removed_stones = value;
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

    public Computer getComputer(){ return computer; }

    public Player getPlayer1(){ return player1; }

    public Player getPlayer2(){
        return player2;
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

    public void setWin_option(WINOPTION win_option){ this.win_option = win_option; }

    public void setStones_number(int stones_number){ this.stones_number = stones_number; }

    public String whoWin(){
        String str = "Победил ";
        if(game_mode == COMPHUMAN.COMPUTER) {
            if (win_option == WINOPTION.TAKE) {
                if(turn == COMPHUMAN.HUMAN){
                    str += "Компьютер";
                }
                else str += player1.getName();
            }
            else{
                if(turn == COMPHUMAN.HUMAN){
                    str += player1.getName();
                }
                else str += "Компьютер";
            }
        }
        else{
            if(turn == COMPHUMAN.HUMAN){
                str += player2.getName();
            }
            else str += player1.getName();
        }

        return str + "!";
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
