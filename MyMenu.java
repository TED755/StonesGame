package sample;


import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCombination;

import java.security.Key;

public class MyMenu extends MenuBar{

    private MenuItem settings;
    public MyMenu(){
        this.getMenus().addAll(createGameMenu(), createFileMenu());
    }

    private Menu createFileMenu(){
        Menu menuFile = new Menu("Файл");
        MenuItem loadGame = new MenuItem("Загрузаить игру");
        MenuItem saveGame =  new MenuItem("Сохранить игру");
        saveGame.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        menuFile.getItems().addAll(loadGame, saveGame);
        return menuFile;
    }

    private Menu createGameMenu(){
        Menu menuGame = new Menu("Игра");
        MenuItem newGame = new MenuItem("Новая игра");
        settings = new MenuItem("Настройки");
        MenuItem exitMenu = new MenuItem("Выйти в меню");
        MenuItem exit = new MenuItem("Выйти из игры");

        exit.setOnAction((ActionEvent t) -> {
            Platform.exit();
        });
        exit.setAccelerator(KeyCombination.keyCombination("Shift+Esc"));

        settings.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+S"));

        menuGame.getItems().addAll(newGame, exitMenu, new SeparatorMenuItem(), settings, new SeparatorMenuItem(), exit);
        return menuGame;
    }

    public MenuItem getSettingsItem(){
        return settings;
    }
}
