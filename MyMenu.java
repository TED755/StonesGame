package sample;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MyMenu extends MenuBar{


    public MyMenu(){
        this.getMenus().addAll(createGameMenu(), createFileMenu(), createSettingsMenu());
    }

    private Menu createFileMenu(){
        Menu menuFile = new Menu("Файл");
        MenuItem loadGame = new MenuItem("Загрузаить игру");
        MenuItem saveGame =  new MenuItem("Сохранить игру");
        menuFile.getItems().addAll(loadGame, saveGame);
        return menuFile;
    }

    private Menu createSettingsMenu(){
        Menu menuSettings = new Menu("Настройки");
        return menuSettings;
    }

    private Menu createGameMenu(){
        Menu menuGame = new Menu("Игра");
        MenuItem newGame = new MenuItem("Новая игра");
        MenuItem exitMenu = new MenuItem("Выйти в меню");
        MenuItem exit = new MenuItem("Выйти из игры");
        //MenuItem loadGame = new MenuItem("Загрузаить игру");
        //MenuItem saveGame =  new MenuItem("Сохранить игру");
        menuGame.getItems().addAll(newGame, exitMenu, exit);
        return menuGame;
    }
}
