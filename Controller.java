/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 *
 * @author gekat
 */
public class Controller {
    private Scene mainScene;
    private Scene gameScene;
    private Game game;
    private MyMenu myMenu;
    private Stage primaryStage;

    private BorderPane bp1;
    private BorderPane bp2;

    public void start(Stage stage){
        primaryStage = stage;
        primaryStage.setTitle("Камешки");
        game = new Game();
        bp1 = new BorderPane();
        Image image = new Image("sample/images/main_menu_background1.jpg");
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
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        primaryStage.setResizable(false);
        primaryStage.show();
        createGameScene();
    }

    private void startButtonPushed(){
        game();
        //Game game = new Game(gm, );

        System.out.println("start pushed");
    }

    private void game(){

        primaryStage.setScene(gameScene);
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
        bp2 = new BorderPane();
        bp2.setTop(myMenu1);

        gameScene = new Scene(bp2, 500, 500);
    }
}
