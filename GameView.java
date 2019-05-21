package sample;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Random;

public class GameView extends Group {
    private Game game;
    private Random random = new Random();

    private Point2D delta;
    private boolean isRotate;

    private GridPane mainLayout;
    private Font font;

    private Label leftTopLabel;
    private Label rightTopLabel;
    private Label leftTurnLabel;
    private Label rightTurnLabel;
    private Label stonesLabel;

    private HBox lowerLayout;

    public GameView(Game game){
        this.game = game;
        mainLayout = new GridPane();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setHgap(0);
        mainLayout.setVgap(0);
        mainLayout.setGridLinesVisible(true);
        /*mainLayout.setBackground(new Background(new BackgroundImage(new Image("sample/images/main_menu_background2.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));*/

        font = Font.font("Tahoma", FontWeight.BOLD, 20);

        createLabels();
        createGameField();
        this.getChildren().addAll(mainLayout);
    }

    private void createLabels(){
        HBox topLayout = new HBox(0);
        Separator sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        Separator sep1 = new Separator();
        sep1.setOrientation(Orientation.VERTICAL);
        Separator sep2 = new Separator();
        sep2.setOrientation(Orientation.VERTICAL);
        Separator sep3 = new Separator();
        sep3.setOrientation(Orientation.VERTICAL);

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
        Image image = new Image("sample/images/fon.jpg");
        leftTopLabel.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        leftTopLabel.setPrefSize(175, 50);
        rightTopLabel.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
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
        if(game.getTurn() == COMPHUMAN.HUMAN) {
            leftTurnLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            rightTurnLabel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else {
            leftTurnLabel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
            rightTurnLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }

        stonesLabel = new Label();
        stonesLabel.setText(Integer.toString(game.getStonesNumber()));
        stonesLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 35));
        stonesLabel.setPrefSize(50, 50);
        stonesLabel.setStyle("-fx-text-fill: gold");
        stonesLabel.setAlignment(Pos.CENTER);
        stonesLabel.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));

        topLayout.getChildren().addAll(leftTopLabel, leftTurnLabel, stonesLabel, rightTurnLabel, rightTopLabel);

        mainLayout.add(topLayout, 0, 0);
        //mainLayout.set
    }

    private Circle createCircle(){
        Circle circle = new Circle(20);
        circle.setFill(Color.GRAY);
        circle.setLayoutY(random.nextDouble() * 200);
        circle.setLayoutX(random.nextDouble() * 200);
        circle.setRotate(random.nextDouble() * 180);
        circle.setOnMousePressed((MouseEvent mouseEvent) -> {
            delta = new Point2D((mouseEvent.getSceneX() - circle.getLayoutX()),
                    (mouseEvent.getSceneY() - circle.getLayoutY()));
                });
        circle.setOnMouseDragged((MouseEvent e)->{
            if(e.getSceneX() - delta.getX() < 470 && e.getSceneX() - delta.getX() > 0) {
                circle.setLayoutX(e.getSceneX() - delta.getX());
                System.err.println(circle.getLayoutX());
            }
            //circle.setLayoutX(e.getSceneX() - delta.getX());
            if(e.getSceneY() - delta.getY() < 370 && e.getSceneY() - delta.getY() > 0) {
                circle.setLayoutY(e.getSceneY() - delta.getY());
                //System.err.println(circle.getLayoutY());
            }
        });

        //addTranslateListener(circle);
        return circle;
    }

    private void addTranslateListener(final Node node) {
        node.setOnMousePressed((MouseEvent mouseEvent) -> {
            delta = new Point2D((mouseEvent.getSceneX() - node.getLayoutX()),
                    (mouseEvent.getSceneY() - node.getLayoutY()));
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                isRotate = true;
            }
        });

        node.setOnMouseReleased((MouseEvent mouseEvent) -> {
            isRotate = false;
        });

        node.setOnMouseDragged((MouseEvent mouseEvent) -> {
            /*if (isRotate) {
                // подсчет нового угла основывается на подсчете угла между двумя векторами

                double dx1 = mouseEvent.getSceneX() - node.getLayoutX();
                double dy1 = mouseEvent.getSceneY() - node.getLayoutY();
                double l = Math.sqrt(dx1 * dx1 + dy1 * dy1);
                dx1 /= l; // нормализация
                dy1 /= l; // вектора

                double angle = Math.PI / 2; //  второй вектор
                double dx2 = Math.sin(angle);      // единичный вектор,
                double dy2 = Math.cos(angle);      // который повернутый на 90 градусов

                double cosA = dx1 * dx2 + dy1 * dy2;
                angle = Math.acos(cosA);

                if (dy1 < 0) {
                    angle = Math.PI - angle;
                }
                node.setRotate(angle / Math.PI * 180); // из радиан в градусы
                */
            //} else {

            //if(mouseEvent.getSceneX() - delta.getX() < 400 && mouseEvent.getSceneX() - delta.getX() > 0 &&
            //        mouseEvent.getSceneY() - delta.getY() < 400 && mouseEvent.getSceneY() - delta.getY() > 0) {
                node.setLayoutX(mouseEvent.getSceneX() - delta.getX());
                node.setLayoutY(mouseEvent.getSceneY() - delta.getY());
            //}

            //if()


            System.err.println("X:" + (mouseEvent.getSceneX() - delta.getX()) +
                    " Y: " + (mouseEvent.getSceneY() - delta.getY()));
            //}
        });
    }

    private void createGameField(){
        Group root = new Group();
        for(int i = 0; i < game.getStonesNumber(); i++){
            root.getChildren().add(createCircle());
        }
        //mainLayout.getChildren().add(root);
        mainLayout.add(root, 0, 1);
    }



    public void dataChanged(){
        if(game.getTurn() == COMPHUMAN.HUMAN) {
            leftTurnLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            rightTurnLabel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else {
            leftTurnLabel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
            rightTurnLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }
    }
}
