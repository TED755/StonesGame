/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 *
 * @author gekat
 */
public class Controller {
    Scene mainScene;
    //BorderPane bp;
    Game game;
    Group mainView;

    Button startButton;

    public void start(Stage primaryStage){
        primaryStage.setTitle("Камешки");
        mainView = new Group();
        game = new Game();
        /*create menu*/
        MyMenu myMenu = new MyMenu();
        myMenu.getSettingsItem().setOnAction(event -> {
            GameEditDialog gameEditDialog = new GameEditDialog(game, "Настройка игры");
            if(gameEditDialog.getDialog().showAndWait().isPresent())
                System.out.println("Alles gut");
            //OrganizationEditDialog orgEditDialog = new OrganizationEditDialog(org, "Edit organization");
            //if(orgEditDialog.getDialog().showAndWait().isPresent())
            //viewOrg.dataChanged();
        });
        BorderPane bp = new BorderPane();

        //Image img = new Image("/main_menu_background.jpg");
        //ImageView mv = new ImageView(img);
        //bp.getChildren().addAll(mv);

//        bp.setBackground(new Background(new BackgroundImage(new Image("images/main_menu_background.jpg", 0, 400, true, true),
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                BackgroundSize.DEFAULT)));

        mainScene = new Scene(bp, 500, 500);

        TODO: /*добавить событие для проверки значения в spinner
                добавить в меню правили и о программе*/

        bp.setTop(myMenu);
//        bp.setCenter(verticalLayout);

        primaryStage.setScene(mainScene);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        primaryStage.show();
    }

    private void startButtonPushed(){
        //Game game = new Game(gm, );

        System.out.println("start pushed");
    }
}
