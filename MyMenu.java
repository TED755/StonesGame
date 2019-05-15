package sample;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MyMenu extends MenuBar{


    public MyMenu(){
        this.getMenus().addAll(createFileMenu(), createSettingsMenu());
    }

    private Menu createFileMenu(){
        Menu menuFile = new Menu("Файл");
        return menuFile;
    }

    private Menu createSettingsMenu(){
        Menu menuSettings = new Menu("Настройки");
        return menuSettings;
    }
}
