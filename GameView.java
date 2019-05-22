package sample;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameView extends Group {
    private Game game;
    private Random random = new Random();
    private ArrayList<Circle> deleteList;
    private ArrayList<Circle> circles;
    private Point2D delta;

    private Group position;

    private GridPane mainLayout;
    //private VBox mainLayout;
    //private BorderPane mainLayout;

    private Font font;

    private Label leftTopLabel;
    private Label rightTopLabel;
    private Label leftTurnLabel;
    private Label rightTurnLabel;
    private Label stonesLabel;

    private Button move;

    private HBox lowerLayout;

    public GameView(Game game){
        this.game = game;
        mainLayout = new GridPane();
        //position = new Group;
        //mainLayout.setVgap(20);
        deleteList = new ArrayList<>();
        circles = new ArrayList<>();
        //mainLayout = new BorderPane();
        //mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);

        //mainLayout.setHgap(0);
        //mainLayout.setVgap(0);
        //mainLayout.setGridLinesVisible(true);
        /*mainLayout.setBackground(new Background(new BackgroundImage(new Image("sample/images/main_menu_background2.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));*/

        font = Font.font("Tahoma", FontWeight.BOLD, 20);

        move = new Button("Сходить");
        createLabels();
        createGameField();
        mainLayout.add(move, 0, 2);
        //mainLayout.setBottom(move);
        //mainLayout.getChildren().add(move);
        if(deleteList.isEmpty())
            move.setDisable(true);
        /*move.setOnAction((ActionEvent e) ->{
            movePushed();
        });*/



        this.getChildren().addAll(mainLayout);
    }

    public Button getMoveButton(){
        return move;
    }

    private void createLabels(){
        HBox topLayout = new HBox(0);
        Image image = new Image("sample/images/fon.jpg");
        topLayout.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        /*Separator sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        Separator sep1 = new Separator();
        sep1.setOrientation(Orientation.VERTICAL);
        Separator sep2 = new Separator();
        sep2.setOrientation(Orientation.VERTICAL);
        Separator sep3 = new Separator();
        sep3.setOrientation(Orientation.VERTICAL);*/

        leftTopLabel = new Label(game.getPlayer1().getName());
        rightTopLabel = new Label();
        if(game.getGame_mode() == COMPHUMAN.HUMAN)
            rightTopLabel.setText(game.getPlayer2().getName());
        else {
            rightTopLabel.setText("Компьютер");
        }

        leftTopLabel.setFont(font);
        rightTopLabel.setFont(font);
        leftTopLabel.setStyle("-fx-text-fill: gold");
        leftTopLabel.setAlignment(Pos.CENTER);
        rightTopLabel.setStyle("-fx-text-fill: gold");
        rightTopLabel.setAlignment(Pos.CENTER);

        /*leftTopLabel.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));*/
        leftTopLabel.setPrefSize(175, 50);
        /*rightTopLabel.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));*/
        rightTopLabel.setPrefSize(175, 50);

        leftTurnLabel = new Label();
        leftTurnLabel.setPrefSize(50, 50);
        /*leftTurnLabel.setBackground(new Background(new BackgroundImage(new Image("sample/images/stone.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));*/

        rightTurnLabel = new Label();
        rightTurnLabel.setPrefSize(50, 50);
        rightTurnLabel.setBackground(new Background(new BackgroundImage(new Image("sample/images/2.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        leftTurnLabel.setBackground(new Background(new BackgroundImage(new Image("sample/images/3.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        if(game.getTurn() == COMPHUMAN.HUMAN) {
            leftTurnLabel.setVisible(true);
            rightTurnLabel.setVisible(false);
        }
        else {
            leftTurnLabel.setVisible(false);
            rightTurnLabel.setVisible(true);
        }

        stonesLabel = new Label();
        stonesLabel.setText(Integer.toString(game.getStonesNumber()));
        stonesLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 35));
        stonesLabel.setPrefSize(50, 50);
        stonesLabel.setStyle("-fx-text-fill: gold");
        stonesLabel.setAlignment(Pos.CENTER);
        /*stonesLabel.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));*/

        topLayout.getChildren().addAll(leftTopLabel, leftTurnLabel, stonesLabel, rightTurnLabel, rightTopLabel);

        //topLayout.setLayoutY(0);

        //mainLayout.add(topLayout, 0, 0);
        //mainLayout.setTop(topLayout);
        mainLayout.getChildren().add(topLayout);
    }

    private Circle createCircle(){
        Image image = new Image("sample/images/1.png");
        Circle circle = new Circle(20);
        circle.setLayoutY(random.nextDouble() * 200);
        circle.setLayoutX(random.nextDouble() * 200);
        circle.setRotate(random.nextDouble() * 180);
        circle.setFill(new ImagePattern(image));
        circle.setOnMousePressed((MouseEvent mouseEvent) -> {
            delta = new Point2D((mouseEvent.getSceneX() - circle.getLayoutX()),
                    (mouseEvent.getSceneY() - circle.getLayoutY()));

                if (mouseEvent.getClickCount() == 2) {
                    if(!deleteList.contains(circle)) {
                        circle.setScaleX(0.8);
                        circle.setScaleY(0.8);
                        deleteList.add(circle);
                        dataChanged();
                    }
                    //game.setRemovedStones(game.getRemoved_stones() + 1);
                }
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    if(deleteList.contains(circle)) {
                        circle.setScaleX(1);
                        circle.setScaleY(1);
                        deleteList.remove(circle);
                        dataChanged();
                    }


                    //try {
                    // game.setRemovedStones(game.getRemoved_stones() - 1);
                    //}
                    //catch (NumberFormatException e){
                    //e.getMessage();
                    //}
                }

        });

        circle.setOnMouseDragged((MouseEvent e)->{
            if(e.getSceneX() - delta.getX() < 470 && e.getSceneX() - delta.getX() > 0) {
                circle.setLayoutX(e.getSceneX() - delta.getX());
                System.err.println(circle.getLayoutX());
            }
            //circle.setLayoutX(e.getSceneX() - delta.getX());
            if(e.getSceneY() - delta.getY() < 305 && e.getSceneY() - delta.getY() > 0) {
                circle.setLayoutY(e.getSceneY() - delta.getY());
                //System.err.println(circle.getLayoutY());
            }
        });

        //circle.setOnMousePressed();

        //addTranslateListener(circle);
        return circle;
    }

    //private void movePushed(){
        //game.setRemovedStones(deleteList.size());
    //}

    private void createGameField(){
        Group root = new Group();

        //root.prefHeight(450);
        for(int i = 0; i < game.getStonesNumber(); i++){
            Circle tmp = createCircle();
            circles.add(tmp);
            root.getChildren().add(tmp);
        }
        //mainLayout.getChildren().add(root);
        mainLayout.add(root, 0, 1);
    }

    public void computerDeleteFromField() {

        for(int i = 0; i < game.getRemoved_stones(); i++) {
            Random rnd = new Random(System.currentTimeMillis());
            //int number = ;
            //number = rnd.nextInt(game.getRemoved_stones() - i);
            deleteList.add(circles.get(0));
            circles.remove(0);
        }
        for(int i = 0; i < deleteList.size(); i++){
            deleteList.get(i).setVisible(false);
        }
        deleteList = new ArrayList<>();
    }

    public void deleteStonesFromField(){
        for(int i = 0; i < deleteList.size(); i++){
            deleteList.get(i).setVisible(false);
            circles.remove(deleteList.get(i));
        }
        deleteList = new ArrayList<>();
    }

    public void dataChanged(){
        if(deleteList.isEmpty() || deleteList.size() > 4)
            move.setDisable(true);
        else move.setDisable(false);
        if(game.getTurn() == COMPHUMAN.HUMAN) {
            leftTurnLabel.setVisible(true);
            rightTurnLabel.setVisible(false);
        }
        else {
            leftTurnLabel.setVisible(false);
            rightTurnLabel.setVisible(true);
        }
        stonesLabel.setText(Integer.toString(game.getStonesNumber()));
        leftTopLabel.setText(game.getPlayer1().getName());
        if(game.getGame_mode() == COMPHUMAN.HUMAN)
            rightTopLabel.setText(game.getPlayer2().getName());
        /*if(game.getTurn() == COMPHUMAN.HUMAN) {
            leftTurnLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            rightTurnLabel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else {
            leftTurnLabel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
            rightTurnLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }*/
    }

    public int getDeleteListSize(){
        return deleteList.size();
    }


}
