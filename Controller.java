/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author gekat
 */
public class Controller {
    private Scene mainScene;
    private Scene gameScene;
    private Game game;
    private Game saveGame;
    private MyMenu myMenu;
    private Stage primaryStage;
    private GameView gameView;

    private BorderPane bp1;
    private BorderPane bp2;

    public void start(Stage stage){
        primaryStage = stage;
        primaryStage.setTitle("Камешки");
        game = new Game();
        bp1 = new BorderPane();
        Image image = new Image("sample/images/main_menu_background2.jpg");
        bp1.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        bp1.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        VBox mainView = new VBox(10);
        mainView.setAlignment(Pos.CENTER);
        MainMenuView view = new MainMenuView(game);

        Button startButton = new Button("Начать игру");
        startButton.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        /*create menu*/
        myMenu = new MyMenu();
        myMenu.getSettingsItem().setOnAction(event -> {
            GameEditDialog gameEditDialog = new GameEditDialog(game, "Настройка игры");
            if(gameEditDialog.getDialog().showAndWait().isPresent()){
                view.dataChanged();
                System.out.println("Alles gut");
            }
        });

        myMenu.getExitMenuItem().setDisable(true);
        /*myMenu.getExitMenuItem().setOnAction(event -> {
            menu();
        });*/

        startButton.setOnAction(event -> {
            startButtonPushed();
        });

        mainView.getChildren().addAll(view, startButton);
        bp1.setTop(myMenu);
        bp1.setCenter(mainView);
        mainScene = new Scene(bp1, 500, 500);

        TODO: /*добавить событие для проверки значения в spinner
                добавить в меню правили и о программе
                add read from file*/

        primaryStage.setScene(mainScene);
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.setResizable(false);
        primaryStage.show();
        createGameScene();
    }

    private void startButtonPushed(){
        newGame();
        //gameView.dataChanged();
        //System.out.println("Game info: " + game);
        //game();
        System.out.println("start pushed");
    }

    private void game(){
        primaryStage.setScene(gameScene);
        if(game.getGame_mode() == COMPHUMAN.HUMAN)
            humanGame();
        else computerGame();
    }

    private void newGame(){
        saveGame = new Game(game);

        gameView = new GameView(game);
        bp2.setCenter(gameView);
        game();

        saveSettings();
    }

    private void humanGame(){
        if(game.isVictory()){
            System.out.println("Turn: " + game.getTurn());
            System.out.println("Stones: " + game.getStonesNumber());
            WinWindow w = new WinWindow(game);
            if(w.getDialog().showAndWait().isPresent()) {
                game = new Game(saveGame);
                menu();
            }
            return;
        }
        Button button = gameView.getMoveButton();
        button.setOnAction((ActionEvent e) -> {
            game.setRemovedStones(gameView.getDeleteListSize());
            gameView.deleteStonesFromField();
            game.recountStones();
            game.changeTurn();
            gameView.dataChanged();
            humanGame();
        });
    }

    private void computerGame(){
        if(game.isVictory()){
            System.out.println("Turn: " + game.getTurn());
            System.out.println("Stones: " + game.getStonesNumber());
            WinWindow w = new WinWindow(game);
            if(w.getDialog().showAndWait().isPresent()) {
                game = new Game(saveGame);
                menu();
            }
            return;
        }
        Button button = gameView.getMoveButton();
        if(game.getTurn() == COMPHUMAN.COMPUTER) {
            computerTurn();
            computerGame();
        }
        else {
            button.setOnAction((ActionEvent e) -> {
                game.setRemovedStones(gameView.getDeleteListSize());
                gameView.deleteStonesFromField();
                game.recountStones();
                game.changeTurn();
                gameView.dataChanged();
                computerGame();
            });
        }

        //while(!game.isVictory()){

                //gameView.getDeleteListSize();

        //}
    }

    private void computerTurn(){

        if(game.getTurn() == COMPHUMAN.COMPUTER)
            if(game.getWin_option() == WINOPTION.LEAVE)
                game.setRemovedStones(game.getComputer().leaveLastStoneWinStrategy(game.getStonesNumber()));
            else game.setRemovedStones(game.getComputer().takeLastStoneWinStrategy(game.getStonesNumber()));
        gameView.computerDeleteFromField();
        game.recountStones();
        game.changeTurn();
        gameView.dataChanged();
    }

    private void menu(){
        primaryStage.setScene(mainScene);

    }

    private void createGameScene(){
        MyMenu myMenu1 = new MyMenu();
        myMenu1.getSettingsItem().setDisable(true);
        /*myMenu.getSettingsItem().setOnAction(event -> {
            GameEditDialog gameEditDialog = new GameEditDialog(game, "Настройка игры");
            if(gameEditDialog.getDialog().showAndWait().isPresent()){
                view.dataChanged();
                System.out.println("Alles gut");
            }
        });*/

        myMenu1.getExitMenuItem().setOnAction(event -> {
            menu();
        });
        //gameView = new GameView(game);
        bp2 = new BorderPane();
        bp2.setTop(myMenu1);
        Image image = new Image("sample/images/main_menu_background2.jpg");
        bp2.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        bp2.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));

        gameScene = new Scene(bp2, 500, 500);
    }

    private void saveSettings(){
        try(FileWriter writer = new FileWriter("saves.txt", false))
        {
            String text = game.getPlayer1().getName() + " ";
            text += game.getPlayer2().getName() + " ";
            text += game.getGame_mode() + " ";
            text += game.getWin_option() + " ";
            text += game.getTurn() + " ";
            text += game.getStonesNumber();
            writer.write(text);

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    private Game loadSettings(){
        Game tmp = new Game();

        return tmp;
    }

    private void SaveGame(){
        try(FileWriter writer = new FileWriter("save_game.txt", false))
        {
            // запись всей строки
            String text = "Hello Gold!";
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.append('E');

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
