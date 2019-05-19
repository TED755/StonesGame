package sample;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class NameEditDialog {
    private Game game;
    private TextField namePlayer1;
    private TextField namePlayer2;
    private Font font;
    private GridPane pane;
    private Dialog<Game> dialog;

    public NameEditDialog(Game game, String title){
        this.game = game;
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);
        dialog = new Dialog<>();
        dialog.setTitle(title);

        //namePlayer1 = new
        if(game.getGame_mode() == COMPHUMAN.HUMAN) {
            namePlayer2 = new TextField(game.getPlayer2().getName());
        }
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        if(game.getGame_mode() == COMPHUMAN.COMPUTER){
            Text text = new Text("Новый ник:");
            text.setFont(font);
            namePlayer1 = new TextField(game.getPlayer1().getName());

            pane.add(text, 0, 0);
            pane.add(namePlayer1, 1, 0);
        }
        else{
            Text text1 = new Text("Новый ник первого игрока:");
            text1.setFont(font);
            namePlayer1 = new TextField(game.getPlayer1().getName());

            pane.add(text1, 0, 0);
            pane.add(namePlayer1, 1, 0);

            Text text2 = new Text("Новый ник второго игрока:");
            text2.setFont(font);
            namePlayer2 = new TextField(game.getPlayer2().getName());

            pane.add(text2, 0, 1);
            pane.add(namePlayer2, 1, 1);
        }

        dialog.getDialogPane().setContent(pane);
        dialog.setTitle(title);
        createButtons();
    }

    public Dialog<Game> getDialog(){
        return dialog;
    }

    private boolean isInputValid() {
        if(game.getGame_mode() == COMPHUMAN.COMPUTER)
            return namePlayer1.getText().matches("[а-яА-Яa-zA-Z0-9&\\-\\+ ]+");
        else {
            return namePlayer1.getText().matches("[а-яА-Яa-zA-Z0-9&\\-\\+ ]+")
                    && namePlayer2.getText().matches("[а-яА-Яa-zA-Z0-9&\\-\\+ ]+");
        }
    }

    private void handleOk() {
        game.getPlayer1().setName(namePlayer1.getText());
        if(game.getGame_mode() == COMPHUMAN.HUMAN)
            game.getPlayer2().setName(namePlayer2.getText());
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
//                        Organization newOrg = new Organization(nameEdit.getText(), personnelEdit.getValue(),
//                                holidayEdit.getValue().toString(), dateEdit.getValue(), drinkVolumeEdit.getValue());
//                        return newOrg;
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
}
