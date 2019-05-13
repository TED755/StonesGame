package sample;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    
    
    public ComputerMenuView(){
        createPane();
        createTextFields();
        this.getChildren().add(grid);
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
}