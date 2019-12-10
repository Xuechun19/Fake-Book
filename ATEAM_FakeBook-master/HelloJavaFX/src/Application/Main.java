package Application;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import javax.security.auth.callback.ConfirmationCallback;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  private List<String> args;

  private static final String APP_TITLE = "FakeBook";

  @Override
  public void start(Stage primaryStage) throws Exception {

    BorderPane root = new BorderPane();

    // Five buttons
    Button addButton = new Button("Add");
    addButton.setPrefWidth(120);
    addButton.setTranslateY(-10);
    addButton.setOnAction(EventHandler -> addWindow());

    Button loadButton = new Button("Loadfile");
    loadButton.setPrefWidth(120);
    loadButton.setOnAction(EventHandler -> upload());

    Button removeButton = new Button("Remove");
    removeButton.setPrefWidth(120);
    removeButton.setTranslateY(10);
    removeButton.setOnAction(EventHandler -> removeWindow());

    Button clearButton = new Button("Clear");
    clearButton.setPrefWidth(120);
    clearButton.setTranslateY(20);


    Button showAllNetWorkButton = new Button("ShowAllNetwork");
    showAllNetWorkButton.setPrefWidth(120);
    showAllNetWorkButton.setTranslateY(30);
    
    Button centeruserButton = new Button("Set CenterUser");
    centeruserButton.setPrefWidth(120);
    centeruserButton.setTranslateY(40);
    centeruserButton.setOnAction(EventHandler -> centerUserWindow());

    VBox rightVBox = new VBox();

    rightVBox.setPadding(new Insets(50, 20, 20, 20));
    rightVBox.getChildren().addAll(addButton, loadButton, removeButton, clearButton,
        showAllNetWorkButton, centeruserButton);

    root.setRight(rightVBox);

    // // Canvas
    // Canvas canvas = new Canvas(200, 200);
    // GraphicsContext gc = canvas.getGraphicsContext2D();
    // // Write some text
    // // Text is filled with the fill color
    // gc.setFill(Color.GREEN);
    // gc.setFont(new Font(30));
    //
    // // Draw a line
    // // Lines use the stroke color
    // gc.setStroke(Color.BLUE);
    // gc.setLineWidth(2);
    // gc.strokeLine(40, 100, 250, 50);
    // // Draw a few circles
    // gc.setFill(Color.BLACK);
    // // The circles draw from the top left, so to center them, subtract the radius from each
    // // coordinate
    // gc.fillOval(40 - 15, 100 - 15, 30, 30);
    // gc.setFill(Color.RED);
    // gc.fillOval(250 - 15, 50 - 15, 30, 30);

    FileInputStream imageFile = new FileInputStream("./Network.jpg");
    Image image = new Image(imageFile);
    ImageView view = new ImageView(image);
    view.setFitWidth(450);
    view.setFitHeight(400);
    view.setPreserveRatio(true);
    view.setTranslateY(30);


    VBox centerVBox = new VBox();

    Label l1 = new Label("Your Social Network");
    l1.setFont(new Font("MV Boli", 100));
    l1.setTranslateX(40);
    l1.setTranslateY(20);
    
    centerVBox.getChildren().add(view);
    
    root.setTop(l1);
    root.setCenter(centerVBox);

    Scene mainScene = new Scene(root, 600, 400);

    clearButton.setOnAction(EventHandler -> {
      try {
      centerVBox.getChildren().removeAll(centerVBox.getChildren().remove(centerVBox.getChildren().size()-1));
      root.setCenter(centerVBox);
      } catch (Exception e) {}
    });
 
    showAllNetWorkButton.setOnAction(e -> {
      try {
      centerVBox.getChildren().removeAll(view);
      if(centerVBox.getChildren().size() > 0) centerVBox.getChildren().removeAll(centerVBox.getChildren().remove(centerVBox.getChildren().size()-1));
      showfile1(centerVBox);
      root.setCenter(centerVBox);
      } catch (Exception m) {}
    });
    

    mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();

  }


  // Start a new window after clicking the add button
  public void addWindow() {

    Stage currStage = new Stage();

    // GridPane
    BorderPane pane = new BorderPane();

    // HBox row1
    HBox hbox1 = new HBox();
    TextField userField = new TextField();
    userField.setTranslateX(37);
    Button confirmbutton1 = new Button("Confirm");
    hbox1.getChildren().addAll(new Label("User: "), userField, confirmbutton1);
    confirmbutton1.setOnAction(EventHandler -> {
      try {
        if (userField.getText().isEmpty())
          addFailure();
        else
          addSuccess();
      } catch (Exception e) {
        addFailure();
      }
    });


    // HBox row2
    HBox hbox2 = new HBox();
    TextField relation1 = new TextField();
    TextField relation2 = new TextField();
    Button confirmbutton2 = new Button("Confirm");
    confirmbutton1.setTranslateX(37);
    confirmbutton2.setOnAction(EventHandler -> {
      try {
        if (relation1.getText().isEmpty() || relation2.getText().isEmpty())
          addFailure();
        else
          addSuccess();
      } catch (Exception e) {
        addFailure();
      }
    });
    hbox2.getChildren().addAll(new Label("Friendship: "), relation1, relation2, confirmbutton2);

    Button button2 = new Button("CLOSE");
    button2.setTranslateX(520);
    button2.setTranslateY(-10);
    button2.setPrefWidth(70);
    button2.setPrefHeight(40);
    button2.setOnAction(EventHandler -> currStage.close());

    VBox centerBox = new VBox();
    centerBox.getChildren().addAll(hbox1, hbox2);
    centerBox.setPadding(new Insets(50, 50, 50, 50));

    pane.setCenter(centerBox);
    pane.setBottom(button2);

    Scene newScene = new Scene(pane, 600, 200);
    newScene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());

    currStage.setTitle("Adding");
    currStage.setScene(newScene);
    currStage.showAndWait();

  }

  public void upload() {

    Stage stage = new Stage();
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // creating a horizontal box
    VBox VBox = new VBox();

    Label l1 = new Label("Load File");
    l1.setFont(new Font("MV Boli", 40));
    l1.setTranslateX(30);
    VBox.getChildren().add(l1);

    Label l2 = new Label("File: ");
    l2.setFont(new Font("Ariel", 20));
    l2.setTranslateY(20);
    l2.setTranslateX(20);
    VBox.getChildren().add(l2);


    HBox hbox = new HBox();
    // add the following elements into the right panel
    TextField t1 = new TextField("");
    t1.setTranslateX(-150);
    t1.setTranslateY(-5);
    hbox.getChildren().add(t1);
    

    // create upload button and put it in the same hbox with textField
    Button button = new Button("UPLOAD");
    button.setTranslateX(-150);
    button.setTranslateY(-5);
    
    hbox.getChildren().add(button);
    
    button.setOnAction(EventHandler -> {
      try {
        File file = new File(t1.getText());
        if (!file.exists())
          loadFailure();
        else
          loadSuccess();
      } catch (Exception e) {
        loadFailure();
      }
    });

    Button button2 = new Button("CLOSE");
    button2.setTranslateX(420);
    button2.setTranslateY(-10);
    button2.setPrefWidth(70);
    button2.setPrefHeight(40);
    button2.setOnAction(EventHandler -> stage.close());


    root.setTop(VBox);
    root.setRight(hbox);
    root.setBottom(button2);

    // Create a vertical box with Hello labels for each args
    VBox vbox = new VBox();

    // Add the vertical box to the center of the root pane
    root.setCenter(vbox);
    Scene mainScene = new Scene(root, 500, 300, Color.PINK); // color not showing
    mainScene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());

    // Add the stuff and set the primary stage
    stage.setTitle("Load File");
    stage.setScene(mainScene);
    stage.showAndWait();

  }

  public void removeWindow() {

    Stage currStage = new Stage();

    // GridPane
    BorderPane pane = new BorderPane();

    // HBox row1
    HBox hbox1 = new HBox();
    TextField userField = new TextField();
    userField.setTranslateX(37);

    Button confirmbutton1 = new Button("Confirm");
    confirmbutton1.setTranslateX(37);
    hbox1.getChildren().addAll(new Label("User: "), userField, confirmbutton1);
    confirmbutton1.setOnAction(EventHandler -> {
      try {
        if (userField.getText().isEmpty()) removeFailure();
        else removeSuccess();
      } catch (Exception e) {
        removeFailure();
      }
    });


    // HBox row2
    HBox hbox2 = new HBox();
    TextField relation1 = new TextField();
    TextField relation2 = new TextField();
    Button confirmbutton2 = new Button("Confirm");
    hbox2.getChildren().addAll(new Label("Friendship: "), relation1, relation2, confirmbutton2);
    confirmbutton2.setOnAction(EventHandler -> {
      try {
        if(relation1.getText().isEmpty() || relation2.getText().isEmpty()) removeFailure();
        else removeSuccess();
      } catch (Exception e) {
        removeFailure();
      }
    });

    // Close Button
    Button button2 = new Button("CLOSE");
    button2.setTranslateX(520);
    button2.setTranslateY(-10);
    button2.setPrefWidth(70);
    button2.setPrefHeight(40);
    button2.setOnAction(EventHandler -> currStage.close());

    VBox centerBox = new VBox();
    centerBox.getChildren().addAll(hbox1, hbox2);
    centerBox.setPadding(new Insets(50, 50, 50, 50));

    pane.setCenter(centerBox);
    pane.setBottom(button2);

    Scene newScene = new Scene(pane, 600, 200);
    newScene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());

    currStage.setTitle("Removing");
    currStage.setScene(newScene);
    currStage.showAndWait();

  }

  public void addSuccess() throws Exception {

    Stage stage = new Stage();

    Label l3 = new Label("ADD Success!");
    l3.setFont(new Font("Ariel", 20));
    l3.setTranslateX(66);
    l3.setTranslateY(65);

    Button b3 = new Button("GO BACK");
    b3.setOnAction(EventHandler -> stage.close());
    b3.setMaxSize(150, 200);
    b3.setTranslateX(66);
    b3.setTranslateY(65);

    VBox vbox3 = new VBox();

    vbox3.getChildren().addAll(l3, b3);


    BorderPane loadFile3 = new BorderPane();
    loadFile3.setCenter(vbox3);

    Scene loadScene = new Scene(loadFile3, 300, 200);
    loadScene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());

    stage.setTitle("Add Success");
    stage.setScene(loadScene);
    stage.show();

  }

  public void loadSuccess() throws Exception {

    Stage stage = new Stage();
    // save args example
    args = this.getParameters().getRaw();

    Label l1 = new Label("LOAD success!");
    // l1.Font = new Font(l1.Font.Name, 12, FontStyle.Bold | FontStyle.Underline);
    l1.setFont(new Font("Ariel", 20));
    l1.setTranslateX(66);
    l1.setTranslateY(65);

    Button b1 = new Button("GO BACK");
    b1.setMaxSize(150, 200);
    b1.setTranslateX(66);
    b1.setTranslateY(65);
    b1.setOnAction(EventHandler -> stage.close());


    VBox vbox1 = new VBox();

    vbox1.getChildren().addAll(l1, b1);

    BorderPane loadFile = new BorderPane();
    loadFile.setCenter(vbox1);

    Scene loadScene = new Scene(loadFile, 300, 200);
    loadScene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());

    stage.setTitle("Load Success");
    stage.setScene(loadScene);
    stage.showAndWait();


  }

  public void removeSuccess() throws Exception {

    Stage stage = new Stage();
    // save args example
    args = this.getParameters().getRaw();

    Label l1 = new Label("Remove success!");

    // l1.Font = new Font(l1.Font.Name, 12, FontStyle.Bold | FontStyle.Underline);
    l1.setFont(new Font("Ariel", 20));
    l1.setTranslateX(66);
    l1.setTranslateY(65);

    Button b1 = new Button("GO BACK");
    b1.setMaxSize(150, 200);
    b1.setTranslateX(66);
    b1.setTranslateY(65);
    b1.setOnAction(EventHandler -> stage.close());


    VBox vbox1 = new VBox();

    vbox1.getChildren().addAll(l1, b1);

    BorderPane loadFile = new BorderPane();
    loadFile.setCenter(vbox1);

    Scene loadScene = new Scene(loadFile, 300, 200);
    loadScene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());

    stage.setScene(loadScene);
    stage.showAndWait();

  }

  public void addFailure() {

    Stage stage = new Stage();
    // save args example
    args = this.getParameters().getRaw();

    Label l1 = new Label("Add Fails!");

    // l1.Font = new Font(l1.Font.Name, 12, FontStyle.Bold | FontStyle.Underline);
    l1.setFont(new Font("Ariel", 20));
    l1.setTranslateX(66);
    l1.setTranslateY(65);

    Button b1 = new Button("GO BACK");
    b1.setMaxSize(150, 200);
    b1.setTranslateX(66);
    b1.setTranslateY(65);
    b1.setOnAction(EventHandler -> stage.close());


    VBox vbox1 = new VBox();

    vbox1.getChildren().addAll(l1, b1);

    BorderPane loadFile = new BorderPane();
    loadFile.setCenter(vbox1);

    Scene loadScene = new Scene(loadFile, 300, 200);
    loadScene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());

    stage.setScene(loadScene);
    stage.showAndWait();

  }

  public void loadFailure() {

    Stage stage = new Stage();
    // save args example
    args = this.getParameters().getRaw();

    Label l1 = new Label("Load Fails!");

    // l1.Font = new Font(l1.Font.Name, 12, FontStyle.Bold | FontStyle.Underline);
    l1.setFont(new Font("Ariel", 20));
    l1.setTranslateX(66);
    l1.setTranslateY(65);

    Button b1 = new Button("GO BACK");
    b1.setMaxSize(150, 200);
    b1.setTranslateX(66);
    b1.setTranslateY(65);
    b1.setOnAction(EventHandler -> stage.close());


    VBox vbox1 = new VBox();

    vbox1.getChildren().addAll(l1, b1);

    BorderPane loadFile = new BorderPane();
    loadFile.setCenter(vbox1);

    Scene loadScene = new Scene(loadFile, 300, 200);
    loadScene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());

    stage.setScene(loadScene);
    stage.showAndWait();


  }

  public void removeFailure() {

    Stage stage = new Stage();
    // save args example
    args = this.getParameters().getRaw();

    Label l1 = new Label("Remove Fails!");

    // l1.Font = new Font(l1.Font.Name, 12, FontStyle.Bold | FontStyle.Underline);
    l1.setFont(new Font("Ariel", 20));
    l1.setTranslateX(66);
    l1.setTranslateY(65);

    Button b1 = new Button("GO BACK");
    b1.setMaxSize(150, 200);
    b1.setTranslateX(66);
    b1.setTranslateY(65);
    b1.setOnAction(EventHandler -> stage.close());


    VBox vbox1 = new VBox();

    vbox1.getChildren().addAll(l1, b1);

    BorderPane loadFile = new BorderPane();
    loadFile.setCenter(vbox1);

    Scene loadScene = new Scene(loadFile, 300, 200);
    loadScene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());

    stage.setScene(loadScene);
    stage.showAndWait();

  }
  
  public void showfile1(VBox center) {
    
    int WINDOW_WIDTH = 300;
    int WINDOW_HEIGHT = 200;

    Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
   
    gc.setStroke(Color.BLUE);
    gc.setLineWidth(1);
    gc.strokeLine(48,68,138,68);
    gc.strokeLine(48,68,88,123);
    gc.strokeLine(88,123,138,68);
    
    gc.drawImage(createCircledNumber("mark"),30,50);
   
    gc.drawImage(createCircledNumber("Sid"), 120, 50);
          
    gc.drawImage(createCircledNumber("Sri"), 70, 105);
    
    center.getChildren().addAll(canvas);
    
   
  }
  
  private WritableImage createCircledNumber(String name) {
    StackPane sPane = new StackPane();
    sPane.setPrefSize(36,36);        

    Circle c = new Circle(36/2.0);
    c.setStroke(Color.BLACK);
    c.setFill(Color.WHITE);
    c.setStrokeWidth(1);
    sPane.getChildren().add(c);

    Text txtNum = new Text(name + "");
    sPane.getChildren().add(txtNum);
    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setFill(Color.TRANSPARENT);
    return sPane.snapshot(parameters, null);
}
  
  // New window for setting center user
  public void centerUserWindow() {
    
    Stage stage = new Stage();
    
    int WINDOW_WIDTH = 300;
    int WINDOW_HEIGHT = 200;

    Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
   
    gc.setStroke(Color.BLUE);
    gc.setLineWidth(1);
    gc.strokeLine(48,68,138,68);
    gc.strokeLine(48,68,88,123);
    
    gc.drawImage(createCircledNumber("mark"),30,50);
    gc.drawImage(createCircledNumber("Sid"), 120, 50);
    gc.drawImage(createCircledNumber("Sri"), 70, 105);
    
    VBox vBox = new VBox();
    vBox.getChildren().addAll(canvas);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(canvas);
    
    Scene newScene = new Scene(pane, 400, 400);
    newScene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());
    
    stage.setScene(newScene);
    stage.show();
    
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
