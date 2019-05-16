/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author gekat
 */
public class Controller {
    Scene mainScene;
    VBox verticalLayout;
    HBox chooseGameMode;
    HBox chooseVictoryOption;
    //BorderPane bp;

    Group mainView;
    ComputerMenuView cmgv;
    HumanMenuView hmv;

    Button startButton;
    Spinner<Integer> stonesNumber;

    COMPHUMAN gm;

    public void start(Stage primaryStage){
        primaryStage.setTitle("Камешки");
        mainView = new Group();

        /*create menu*/
        MyMenu myMenu = new MyMenu();
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


        /*create vertical layout*/
        verticalLayout = new VBox();
        verticalLayout.setAlignment(Pos.CENTER);
        verticalLayout.setSpacing(30);
        //verticalLayout.getChildren().addAll(mv);

        /*create horizontal layout for radio buttons*/
        chooseGameMode = new HBox();
        chooseGameMode.setAlignment(Pos.CENTER);
        chooseGameMode.setSpacing(20);
        chooseVictoryOption = new HBox();
        chooseVictoryOption.setAlignment(Pos.CENTER);
        chooseVictoryOption.setSpacing(20);

        /*create scene's elements*/
        startButton = new Button("Начать игру");
        cmgv = new ComputerMenuView();
        hmv = new HumanMenuView();
        stonesNumber = new Spinner<>(10, 100, 20);
        stonesNumber.setEditable(true);
        Text gameName = new Text("Stones Game");
        gameName.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 30));

        /*create mode radio buttons*/
        ToggleGroup modeRadioButtonGroup = new ToggleGroup();
        RadioButton withComputerRadioButton = new RadioButton("Играть с компьютером");/*with computer*/
        withComputerRadioButton.setToggleGroup(modeRadioButtonGroup);
        withComputerRadioButton.setSelected(true);
        computerRadioButtonSelect();
        chooseGameMode.getChildren().add(withComputerRadioButton);
        RadioButton withHumanRadioButton = new RadioButton("Играть с человеком");/*with human*/
        withHumanRadioButton.setToggleGroup(modeRadioButtonGroup);
        chooseGameMode.getChildren().add(withHumanRadioButton);

        /*create victory option radio buttons*/
        ToggleGroup victoryOptionRadioButtonGroup = new ToggleGroup();
        RadioButton takeLastRadioButton = new RadioButton("Забирает последний камень");
        takeLastRadioButton.setToggleGroup(victoryOptionRadioButtonGroup);
        chooseVictoryOption.getChildren().add(takeLastRadioButton);
        RadioButton leaveLastRadioButton = new RadioButton("Оставляет последний камень");
        leaveLastRadioButton.setToggleGroup(victoryOptionRadioButtonGroup);
        chooseVictoryOption.getChildren().add(leaveLastRadioButton);

        /*add elements to main layout*/
        verticalLayout.getChildren().add(gameName);
        verticalLayout.getChildren().add(chooseGameMode);
        verticalLayout.getChildren().add(chooseVictoryOption);
        verticalLayout.getChildren().add(mainView);
        verticalLayout.getChildren().add(stonesNumber);
        verticalLayout.getChildren().add(startButton);

        withComputerRadioButton.setOnAction((event) -> {
            computerRadioButtonSelect();
        });


        withHumanRadioButton.setOnAction((event) -> {
            humanRadioButtonSelect();
        });

        takeLastRadioButton.setOnAction(event -> {

        });

        leaveLastRadioButton.setOnAction(event -> {

        });

        startButton.setOnAction(event -> {
            startButtonPushed();
        });
        TODO: /*добавить событие для проверки значения в spinner
                добавить диалоговое окно, вызываемое при нажатие настройки*/

        bp.setTop(myMenu);
        bp.setCenter(verticalLayout);

        primaryStage.setScene(mainScene);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        primaryStage.show();
    }

    private void humanRadioButtonSelect(){
        mainView.getChildren().setAll(hmv);
        gm = COMPHUMAN.HUMAN;
        System.out.println("Human");
    }

    private void computerRadioButtonSelect(){
        mainView.getChildren().setAll(cmgv);
        gm = COMPHUMAN.COMPUTER;
        System.out.println("Comp");
    }

    private void startButtonPushed(){
        //Game game = new Game(gm, );

        System.out.println("start pushed");
    }
}
