import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;


public class RacingCar extends Application {
    @Override
    public void start (Stage primaryStage) {
        // Create a pane
        BorderPane pane = new BorderPane();
        pane.setBackground(Background.EMPTY);
        
        
        //Create car parts and add them to the pane
        Pane carPane = new Pane();
        Circle rearTire = new Circle(15, 150-5, 6, Color.BLACK);
        Circle frontTire = new Circle(35, 150-5, 6, Color.BLACK);
        Rectangle carRec = new Rectangle(0, 150-20, 50, 10);
        carRec.setFill(Color.LIMEGREEN);
        Polygon carTop = new Polygon(10, 150-20, 40, 150-20, 30, 150-30, 20, 150-30);
        carTop.setFill(Color.RED);
        carPane.getChildren().addAll(rearTire, frontTire, carRec, carTop);
        pane.setBottom(carPane);
        
        // Create pause/resume button
        HBox paneForButtons = new HBox(20);
        Button button = new Button("Pause/Resume");
        button.setStyle("-fx-background-color: slateblue; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Georgia' ");
        paneForButtons.getChildren().addAll(button);
        paneForButtons.setAlignment(Pos.TOP_LEFT);
        pane.setTop(paneForButtons);
        
        // Create a path transition
        PathTransition pt = new PathTransition(Duration.seconds(15), new Line(300, 25, 915, 25));
        pt.setNode(carPane);
        pt.setCycleCount(PathTransition.INDEFINITE);
        pt.play();
        
      // Button
        button.setOnMousePressed(e -> pt.pause());
        button.setOnMouseReleased(e -> pt.play());
        
        // Up arrow makes car go faster, down arrow makes car go slower
        pane.setOnKeyPressed(e -> {
            pt.stop();
            switch  (e.getCode()) {
                case UP: pt.setDuration(pt.getDuration().add(Duration.seconds(1))); 
                case DOWN: pt.setDuration(pt.getDuration().subtract(Duration.seconds(1))); 
            }
            
            pt.play();
        });
        
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 650, 150);
        String image = RacingCar.class.getResource("Race.jpg").toExternalForm();
pane.setStyle("-fx-background-image: url('" + image + "'); " +
           "-fx-background-position: none; " +
           "-fx-background-repeat: stretch;");
        primaryStage.setTitle("Racing Car");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}