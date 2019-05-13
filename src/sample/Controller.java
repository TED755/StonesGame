/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    HBox radioButtons;

    Group mainView;
    ComputerMenuGameView cmgv;

    Button startButton;
    Spinner<Integer> stonesNumber;

    public void start(Stage primaryStage){
        primaryStage.setTitle("Камешки");

        mainView = new Group();

        /*create vertical layout*/
        verticalLayout = new VBox();
        verticalLayout.setAlignment(Pos.CENTER);
        verticalLayout.setSpacing(30);

        /*create horizontal layout for radio buttons*/
        radioButtons = new HBox();
        radioButtons.setAlignment(Pos.CENTER);
        radioButtons.setSpacing(20);

        /*create scene's elements*/
        startButton = new Button("Начать игру");
        cmgv = new ComputerMenuGameView();
        stonesNumber = new Spinner<>(10, 100, 20);
        stonesNumber.setEditable(true);
        Text gameName = new Text("Stones Game");
        gameName.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 30));


        /*create radio buttons*/
        ToggleGroup modeRadioButtonGroup = new ToggleGroup();
        RadioButton withComputerRadioButton = new RadioButton("Играть с компьютером");/*with computer*/
        withComputerRadioButton.setToggleGroup(modeRadioButtonGroup);
        radioButtons.getChildren().add(withComputerRadioButton);
        RadioButton withHumanRadioButton = new RadioButton("Играть с человеком");/*with human*/
        withHumanRadioButton.setToggleGroup(modeRadioButtonGroup);
        radioButtons.getChildren().add(withHumanRadioButton);

        verticalLayout.getChildren().add(gameName);
        verticalLayout.getChildren().add(radioButtons);
        verticalLayout.getChildren().add(mainView);
        verticalLayout.getChildren().add(stonesNumber);
        verticalLayout.getChildren().add(startButton);

        //cmgv.setVisible(false);
        withComputerRadioButton.setOnAction((event) -> {
            computerRadioButtonSelect();
        });
        

        withHumanRadioButton.setOnAction((event) -> {
            //mainScene.setRoot(radioButtons);
            humanRadioButtonSelect();

        });

        TODO: /*добавить событие для проверки значения в spinner*/

        mainScene = new Scene(verticalLayout);
        

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void humanRadioButtonSelect(){
        //cmgv.setVisible(false);
        System.out.println("Human");
    }

    private void computerRadioButtonSelect(){
        //verticalLayout.getChildren().remove(startButton);
        //cmgv.setVisible(true);
        mainView.getChildren().setAll(cmgv);
        //verticalLayout.getChildren().add(cmgv);
        //verticalLayout.getChildren().add(startButton);
        //mainScene.setRoot(verticalLayout);
        System.out.println("Comp");
    }
}
