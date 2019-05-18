package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HumanMenuView extends Group {
    private Game game;
    private GridPane grid;
    private TextField namePlayer1;
    private TextField namePlayer2;
    private Font font;

    public HumanMenuView(Game _game){
        game = _game;
        createPane();
        createTextFields();
        this.getChildren().add(grid);
    }

    private void createTextFields(){
        Text player1 = new Text("Первый игрок:");
        player1.setFont(font);
        grid.add(player1, 0, 0);
        namePlayer1 = new TextField(game.getPlayer1_name());
        grid.add(namePlayer1, 1, 0);

        Text player2 = new Text("Второй игрок:");
        player2.setFont(font);
        grid.add(player2, 0, 1);
        namePlayer2 = new TextField(game.getPlayer2_name().equals("Компьютер") ? "Второй игрок" : game.getPlayer2_name());
        grid.add(namePlayer2, 1, 1);
    }

    private void createPane(){
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        font = Font.font("Tahoma", FontWeight.NORMAL, 20);
    }
    public void dataChanged(){
        namePlayer1.setText(game.getPlayer1_name());
        namePlayer2.setText(game.getPlayer2_name());
    }

    public String getTextFieldPlayer2Value(){
        return namePlayer2.getText();
    }
}
