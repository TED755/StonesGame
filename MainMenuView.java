package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainMenuView extends VBox {
    private Game game;
    private VBox mainLayout;
    private Label player1;
    private Label player2;
    private Label about;
    private GridPane pane;
    private Font font;

    public MainMenuView(Game _game){
        this.game = _game;
        mainLayout = new VBox(20);
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);

    }

    public void createLabels(){
        Text player1Text = new Text("Первый игрок");
        player1Text.setFont(font);
        player1 = new Label(game.getPlayer1_name());

        Text player2Text = new Text("Второй игрок");
        player2Text.setFont(font);
        player2 = new Label(game.getPlayer2_name());

        about = new Label();
    }
}
