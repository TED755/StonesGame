package sample;

import javafx.scene.control.*;

public class WinWindow {
    private Label winMessage;
    private Game game;
    private Dialog<Game> dialog;

    public WinWindow(Game game){
        this.game = game;
        dialog = new Dialog<>();
        createButtons();
        winMessage = new Label(game.whoWin());
        dialog.getDialogPane().setContent(winMessage);
    }
    private void createButtons() {
        ButtonType buttonTypeOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        handleOk();

    }

    private void handleOk() {

    }

    public Dialog<Game> getDialog(){
        return dialog;
    }
}
