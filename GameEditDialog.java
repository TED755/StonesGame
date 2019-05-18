package sample;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameEditDialog {
    private Game game;
    private VBox verticalLayout;
    private HBox chooseGameMode;
    private HBox chooseVictoryOption;

    private Group mainView;
    private ComputerMenuView cmgv;
    private HumanMenuView hmv;

    private Spinner<Integer> stonesNumber;

    private Dialog<Game> dialog;

    public GameEditDialog(Game game, String title){
        this.game = game;
        dialog = new Dialog<>();
        dialog.setTitle(title);
        //dialog.setHeaderText("Настройка игры");

        createLayouts();
        createViews();
        createRadioButtons();
        createButtons();
        createSpinner();
        dialog.getDialogPane().setContent(verticalLayout);

        dialog.setResizable(true);
    }

    private void createLayouts(){
        verticalLayout = new VBox();
        verticalLayout.setAlignment(Pos.CENTER);
        verticalLayout.setSpacing(20);

        chooseGameMode = new HBox();
        chooseGameMode.setAlignment(Pos.CENTER);
        chooseGameMode.setSpacing(20);

        chooseVictoryOption = new HBox();
        chooseVictoryOption.setAlignment(Pos.CENTER);
        chooseVictoryOption.setSpacing(20);
    }

    private void createViews(){
        mainView = new Group();
        cmgv = new ComputerMenuView(game);
        hmv = new HumanMenuView(game);
        mainView.getChildren().setAll(cmgv);
        verticalLayout.getChildren().add(mainView);
    }
    private void createButtons() {
        ButtonType buttonTypeOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);
        dialog.setResultConverter((ButtonType b) -> {
            if (b == buttonTypeOk) {
                if (isInputValid() == true) {
                    handleOk();
                    return game;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Data entry error");
                    alert.setHeaderText("Name entry error");
                    alert.setContentText("\"The name of organization consists of letters, numbers, spaces, +, -!!!\\n \"");
                    alert.showAndWait();
                }
            }
            return null;
        });
    }

    private void createRadioButtons(){
        /*create mode radio buttons*/
        ToggleGroup modeRadioButtonGroup = new ToggleGroup();
        RadioButton withComputerRadioButton = new RadioButton("Играть с компьютером");/*with computer*/
        withComputerRadioButton.setToggleGroup(modeRadioButtonGroup);

        chooseGameMode.getChildren().add(withComputerRadioButton);
        RadioButton withHumanRadioButton = new RadioButton("Играть с человеком");/*with human*/
        withHumanRadioButton.setToggleGroup(modeRadioButtonGroup);
        chooseGameMode.getChildren().add(withHumanRadioButton);
        if(game.getGame_mode() == COMPHUMAN.COMPUTER){
            computerRadioButtonSelect();
            withComputerRadioButton.setSelected(true);
        }
        else {
            humanRadioButtonSelect();
            withHumanRadioButton.setSelected(true);
        }

        /*create victory option radio buttons*/
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        Text text = new Text("Выигрывает тот, кто");
        text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        ToggleGroup victoryOptionRadioButtonGroup = new ToggleGroup();
        RadioButton takeLastRadioButton = new RadioButton("Забирает последний камень");
        takeLastRadioButton.setToggleGroup(victoryOptionRadioButtonGroup);
        //takeLastRadioButton.setSelected(true);
        chooseVictoryOption.getChildren().add(takeLastRadioButton);
        RadioButton leaveLastRadioButton = new RadioButton("Оставляет последний камень");
        leaveLastRadioButton.setToggleGroup(victoryOptionRadioButtonGroup);
        chooseVictoryOption.getChildren().add(leaveLastRadioButton);
        vBox.getChildren().addAll(text, chooseVictoryOption);
        if(game.getWin_option() == WINOPTION.TAKE){
            takeLastRadioButton.setSelected(true);
        }
        else leaveLastRadioButton.setSelected(true);

        /*create actions*/
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

        verticalLayout.getChildren().addAll(chooseGameMode, vBox);
    }

    private void createSpinner(){
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        Text text = new Text("Количество камней");
        text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        stonesNumber = new Spinner<>(10, 100, 20);
        stonesNumber.setEditable(true);
        vBox.getChildren().addAll(text, stonesNumber);
        verticalLayout.getChildren().add(vBox);
    }

    private boolean isInputValid(){
        return true;
    }
    private void handleOk(){

    }

    private void humanRadioButtonSelect(){
        mainView.getChildren().setAll(hmv);
        game.setGame_mode(COMPHUMAN.HUMAN);
        game.setPlayer2_name(hmv.getTextFieldPlayer2Value());
        //hmv.dataChanged();
        //gm = COMPHUMAN.HUMAN;
        System.out.println("Human");
    }

    private void computerRadioButtonSelect(){
        mainView.getChildren().setAll(cmgv);
        game.setGame_mode(COMPHUMAN.COMPUTER);
        System.out.println("Comp");
    }

    private void takeLastRadioButtonSelect(){

    }

    private void leaveLastRadioButtonSelect(){

    }

    public Dialog<Game> getDialog(){
        return dialog;
    }
}
