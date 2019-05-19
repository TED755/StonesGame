package sample;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.input.MouseEvent;

public class MainMenuView extends Group {
    private Game game;
    private VBox mainLayout;
    private Label player1;
    private Label player2;
    private Label info;
    private GridPane pane;
    private Font font;

    public MainMenuView(Game _game){
        this.game = _game;
        mainLayout = new VBox(20);
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);
        mainLayout.setAlignment(Pos.BOTTOM_LEFT);
        pane = new GridPane();

        createLabels();
        createActions();
        this.getChildren().addAll(mainLayout);
    }

    public void createLabels(){
        Text player1Text = new Text("Первый игрок ");
        player1Text.setFont(font);
        player1 = new Label(game.getPlayer1_name());
        player1.setFont(font);
        pane.add(player1Text, 0, 0);
        pane.add(player1, 1, 0);

        Text player2Text = new Text("Второй игрок");
        player2Text.setFont(font);
        player2 = new Label(game.getPlayer2_name());
        player2.setFont(font);
        pane.add(player2Text, 0, 1);
        pane.add(player2, 1, 1);

        info = new Label(game.toString());
        info.setFont(font);

        mainLayout.getChildren().addAll(pane, info);
    }

    public void createActions(){
        info.setCursor(Cursor.HAND);
        //info.setTooltip(Tooltip.);
        info.setOnMousePressed((MouseEvent e) ->{
            if(!(e.getButton() == MouseButton.SECONDARY)){
                GameEditDialog gameEditDialog = new GameEditDialog(game, "Настройка");
                if (gameEditDialog.getDialog().showAndWait().isPresent()) {
                    dataChanged();
                    System.out.println("Work");
                }
            }
        });
        info.setOnMouseEntered((MouseEvent e) ->{
            info.setScaleX(1.05);
            info.setScaleY(1.05);
        });
        info.setOnMouseExited((MouseEvent e) ->{
            info.setScaleX(1);
            info.setScaleY(1);
        });
        actionsChanged();
        /*addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e)->{
            if(!(e.getButton() == MouseButton.SECONDARY)){
                GameEditDialog gameEditDialog = new GameEditDialog(game, "Настройка");
                if (gameEditDialog.getDialog().showAndWait().isPresent())
                    System.out.println("Work");
            }
        });*/


    }
    public void dataChanged(){
        player1.setText(game.getPlayer1_name());
        if(game.getGame_mode() == COMPHUMAN.HUMAN)
            player2.setText(game.getPlayer2_name());
        info.setText(game.toString());
    }

    public void actionsChanged(){
        if(game.getGame_mode() == COMPHUMAN.COMPUTER){
            if(game.getTurn() == COMPHUMAN.COMPUTER){
                player2.setCursor(Cursor.HAND);
                player2.setOnMouseEntered((MouseEvent e) ->{
                    player2.setScaleX(1.15);
                    player2.setScaleY(1.15);

                });
                player2.setOnMouseExited((MouseEvent e) ->{
                    player2.setScaleX(1);
                    player2.setScaleY(1);
                });
                player2.setOnMousePressed((MouseEvent e) ->{
                    if(!(e.getButton() == MouseButton.SECONDARY)){
                        NameEditDialog nameEditDialog = new NameEditDialog(game, "Сменить ник");
                        if (nameEditDialog.getDialog().showAndWait().isPresent()) {
                            dataChanged();
                            System.out.println("Work");
                        }
                    }
                });
            }
            else {
                player1.setCursor(Cursor.HAND);
                player1.setOnMouseEntered((MouseEvent e) ->{
                    player1.setScaleX(1.15);
                    player1.setScaleY(1.15);
                });
                player1.setOnMouseExited((MouseEvent e) ->{
                    player1.setScaleX(1);
                    player1.setScaleY(1);
                });
                player1.setOnMousePressed((MouseEvent e) ->{
                    NameEditDialog nameEditDialog = new NameEditDialog(game, "Сменить ник");
                    if(!(e.getButton() == MouseButton.SECONDARY)){
                        if (nameEditDialog.getDialog().showAndWait().isPresent()){
                            dataChanged();
                            System.out.println("Work");}
                    }
                });
            }
        }
        else {
            player1.setCursor(Cursor.HAND);
            player1.setOnMouseEntered((MouseEvent e) ->{
                player1.setScaleX(1.15);
                player1.setScaleY(1.15);
            });
            player1.setOnMouseExited((MouseEvent e) ->{
                player1.setScaleX(1);
                player1.setScaleY(1);
            });
            player1.setOnMousePressed((MouseEvent e) ->{
                NameEditDialog nameEditDialog = new NameEditDialog(game, "Сменить ник");
                if(!(e.getButton() == MouseButton.SECONDARY)){
                    if (nameEditDialog.getDialog().showAndWait().isPresent()) {
                        dataChanged();
                        System.out.println("Work");
                    }
                }
            });
            player2.setCursor(Cursor.HAND);
            player2.setOnMouseEntered((MouseEvent e) ->{
                player2.setScaleX(1.15);
                player2.setScaleY(1.15);
            });
            player2.setOnMouseExited((MouseEvent e) ->{
                player2.setScaleX(1);
                player2.setScaleY(1);
            });
            player2.setOnMousePressed((MouseEvent e) ->{
                NameEditDialog nameEditDialog = new NameEditDialog(game, "Сменить ник");
                if(!(e.getButton() == MouseButton.SECONDARY)){
                    if (nameEditDialog.getDialog().showAndWait().isPresent()) {
                        dataChanged();
                        System.out.println("Work");
                    }
                }
            });
        }
    }
}
