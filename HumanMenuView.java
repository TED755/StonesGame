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

    private Font font;

    public HumanMenuView(Game _game){
        game = _game;
        createPane();
        createTextFields();
        this.getChildren().add(grid);
    }

    private void createTextFields(){
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
        //namePlayer1.setText(game.getPlayer1_name());
        //namePlayer2.setText(game.getPlayer2_name());
    }

    //public String getTextFieldPlayer2Value(){
        //return namePlayer2.getText();
    //}
}
