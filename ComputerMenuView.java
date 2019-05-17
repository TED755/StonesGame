package sample;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gekat
 */
public class ComputerMenuView extends Group{
    
    private GridPane grid;
    private Text playerName;
    private TextField playerNameField;
    private Font font;
    //private VBox chooseFirstTurn;
    private VBox verticalLayout;
    private COMPHUMAN ch;
    
    
    public ComputerMenuView(){
        verticalLayout = new VBox(20);
        verticalLayout.setAlignment(Pos.CENTER);
        createPane();
        createTextFields();
        verticalLayout.getChildren().add(grid);
        createRadioButtons();

        //verticalLayout.getChildren().add(chooseFirstTurn);
        this.getChildren().add(verticalLayout);
    }
    
    public void createTextFields(){
        playerName = new Text("Игрок:");
        playerName.setFont(font);
        grid.add(playerName, 0, 0);
        playerNameField = new TextField("Игрок");
        grid.add(playerNameField, 1, 0);
    }
    public void createPane(){
        grid = new GridPane();  
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);
    }

    public void createRadioButtons(){
        Text text = new Text("Первым ходит");
        text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

        HBox rb = new HBox(20);
        rb.setAlignment(Pos.CENTER);

        ToggleGroup radButtonGroup = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Человек");
        rb1.setToggleGroup(radButtonGroup);
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton("Компьютер");
        rb2.setToggleGroup(radButtonGroup);
        rb.getChildren().add(rb1);
        rb.getChildren().add(rb2);

        verticalLayout.getChildren().add(text);
        verticalLayout.getChildren().add(rb);

        rb1.setOnAction(event -> {
            ch  = COMPHUMAN.HUMAN;
            System.out.println("set human");
        });

        rb2.setOnAction(event -> {
            ch  = COMPHUMAN.COMPUTER;
            System.out.println("set comp");
        });
    }

    public COMPHUMAN getCOMPHUMAN(){
        return ch;
    }
}
