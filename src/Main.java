import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main extends Application {
    ArrayList<Line> lines = new ArrayList<>();
    HashMap<String, Circle> circles = new HashMap<String, Circle>();

    String currentStart = "";
    String currentEnd = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("<<RAMALLAH MAP>>");
        AnchorPane mainPage = new AnchorPane();
        AnchorPane Cont = new AnchorPane();
        ScrollPane scroll = new ScrollPane();
        Cont.setPrefWidth(382);
        Cont.setPrefHeight(613);
        Cont.setLayoutX(471);
        Cont.setLayoutY(0);
        scroll.setPrefWidth(471);
        scroll.setPrefHeight(620);
        scroll.setLayoutY(0);
        scroll.setLayoutX(0);
        Cont.setStyle("-fx-background-color:  DarkSlateGray;");
        scroll.setStyle("-fx-background-color: Blue;");
        mainPage.getStylesheets().add("Style.css");
        ComboBox<String> Start = new ComboBox<String>();
        ComboBox<String> End = new ComboBox<String>();

        Start.setPromptText("Start Point");
        End.setPromptText("End Point");

        Start.getStyleClass().add("combo-box");
        End.getStyleClass().add("combo-box");

        Start.setPrefWidth(323);
        Start.setPrefHeight(26);
        Start.setLayoutX(29);
        Start.setLayoutY(190);

        End.setPrefWidth(323);
        End.setPrefHeight(26);
        End.setLayoutX(29);
        End.setLayoutY(240);


        Button Calculate = new Button("Show Path");
        Calculate.setFont(Font.font("Dubai", 20));
        Calculate.getStyleClass().add("callButton");
        Calculate.setTextFill(Color.WHITE);
        Calculate.setPrefWidth(118);
        Calculate.setPrefHeight(43);

        Button reset = new Button("Reset");
        reset.setFont(Font.font("Dubai", 20));
        reset.getStyleClass().add("callButton");
        reset.setTextFill(Color.WHITE);
        reset.setPrefWidth(118);
        reset.setPrefHeight(43);

        HBox hBox = new HBox(Calculate, reset);
        hBox.setSpacing(20);
        hBox.setLayoutX(70);
        hBox.setLayoutY(290);

        TextField Disatnce = new TextField("Distance: ");
        TextArea Path = new TextArea("Path: ");
        Disatnce.setPrefWidth(358);
        Disatnce.setPrefHeight(50);
        Disatnce.setLayoutX(40);
        Disatnce.setLayoutY(344);
        Disatnce.getStyleClass().add("txtF");
        Path.getStyleClass().add("txtF");
        Path.setPrefWidth(358);
        Path.setPrefHeight(200);
        Path.setLayoutX(11);
        Path.setLayoutY(400);
        Disatnce.setEditable(false);
        Path.setEditable(false);
        ImageView logo = new ImageView(new Image(new FileInputStream("logo.jpg")));
        logo.setFitWidth(132);
        logo.setFitHeight(112);
        logo.setLayoutX(124);
        logo.setLayoutY(26);

        Label label = new Label("RAM");
        label.setTextFill(Color.BLACK);
        label.setFont(new Font("serif", 40));
        label.setLayoutX(50);
        label.setLayoutY(135);

        Label label1 = new Label("ALLAH");
        label1.setTextFill(Color.RED);
        label1.setFont(new Font("serif", 40));
        label1.setLayoutX(135);
        label1.setLayoutY(135);

        Label label2 = new Label("MAP");
        label2.setTextFill(Color.WHITE);
        label2.setFont(new Font("serif", 40));
        label2.setLayoutX(260);
        label2.setLayoutY(135);

        Cont.getChildren().addAll(Start, End, hBox, Disatnce, Path, logo, label, label1, label2);

        ImageView imageView = new ImageView(new Image(new FileInputStream("Ramallah.jpg")));

        Group group = new Group(imageView);
        StackPane Map = new StackPane(new Group(group));
        Map.setOnMouseClicked(e ->
        {
            System.out.print("," + e.getX() + ",");
            System.out.print(e.getY());
        });
        Map.setOnScroll(
                e -> {
                    if (e.isShortcutDown() && e.getDeltaY() != 0) {
                        if (e.getDeltaY() < 0) {
                            group.setScaleX(Math.max(group.getScaleX() - 0.1, 0.5));
                        } else {
                            group.setScaleX(Math.min(group.getScaleX() + 0.1, 5.0));
                        }
                        group.setScaleY(group.getScaleX());
                        e.consume(); // prevents ScrollEvent from reaching ScrollPane
                    }
                });


        scroll = new ScrollPane(Map);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll.setPannable(true);
        scroll.setMaxWidth(471);
        scroll.setMaxHeight(620);
        scroll.setMinWidth(471);
        scroll.setMinHeight(620);

        mainPage.getChildren().addAll(scroll, Cont);


        Graph graph = new Graph();
        ReadGraph(graph);
        for (String i : graph.Vertices.keySet()) {
            Start.getItems().add(i);
            End.getItems().add(i);
            Circle circle = new Circle(6);

            circle.setStrokeWidth(0);
            circle.setFill(Color.BLACK);
            circle.setCenterX(graph.Vertices.get(i).getCity().getX());
            circle.setCenterY(graph.Vertices.get(i).getCity().getY());

            circle.setOnMouseClicked(event -> {
                if (Start.getValue() == null || Start.getValue().isEmpty() || Start.getValue() == "") {
                    Start.setValue(i);
                    circle.setFill(Color.RED);
                    currentStart = i;
                } else if (End.getValue() == null || End.getValue().isEmpty()) {
                    End.setValue(i);
                    circle.setFill(Color.RED);
                    currentEnd = i;
                } else {
                    circles.get(Start.getValue()).setFill(Color.BLACK);
                    circles.get(i).setFill(Color.RED);
                    String temp = End.getValue();
                    End.setValue(i);
                    Start.setValue(temp);
                    currentStart = temp;
                    currentEnd = i;
                }

            });

            group.getChildren().add(circle);
            circles.put(i, circle);
        }

        Start.setOnAction(event -> {
            if (Start.getValue() != null) {
                if (!currentStart.equals(""))
                    circles.get(currentStart).setFill(Color.BLACK);
                circles.get(Start.getValue()).setFill(Color.RED);
                currentStart = Start.getValue();

            }
        });
        End.setOnAction(event -> {
            if (End.getValue() != null) {
                if (!End.equals(""))
                    circles.get(End.getValue()).setFill(Color.BLACK);
                circles.get(End.getValue()).setFill(Color.RED);
                currentEnd = End.getValue();
            }
        });

        /////
        Scene ASTAR = new Scene(mainPage, 847, 613);
        primaryStage.setScene(ASTAR);
        primaryStage.setResizable(false);
        Calculate.setOnAction(event -> {
            if (Start.getValue() == null || End.getValue() == null || Start.getValue().isEmpty() || End.getValue().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ALERT!");
                alert.setContentText("fill the combo boxes!");
                alert.showAndWait();
                alert.setTitle("");
            } else if (Start.getValue() == End.getValue()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ALERT!");
                alert.setContentText(" Are you kidding its the Same region -_- ");
                alert.showAndWait();
                alert.setTitle("");
            } else {
                Complexity com = new Complexity();
                AStar.A_Star(graph, Start.getValue(), End.getValue(), com);

                graph.fill(Disatnce, Path, End.getValue());
                AddLines(group, graph, End.getValue());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("*Complexity*");
                alert.setContentText("Time (Loob executed): " + com.time + "\n" + "Space (list size): " + com.space);
                alert.showAndWait();
                alert.setTitle("");
            }

        });

        //restart*_*
        reset.setOnAction(e -> {
            primaryStage.close();
            Platform.runLater(() -> {
                try {
                    new Main().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void AddLines(Group group, Graph graph, String Distance) {

        for (int i = 0; i < lines.size(); i++) {
            group.getChildren().remove(lines.get(i));
        }
        addLines(group, graph.Vertices.get(Distance));

    }

    private void addLines(Group group, Vertex vertex) {
        if (vertex.getPrev() == null)
            return;
        addLines(group, vertex.getPrev());
        Line line = new Line();
        line.setStrokeWidth(4);
        line.setStroke(Color.BLACK);
        line.setStartX(vertex.getCity().getX());
        line.setEndX(vertex.getPrev().getCity().getX());
        while (vertex.getPrev() != null) {
            Line l = new Line();
            l.setStrokeWidth(3);
            l.setStroke(Color.BLACK);
            l.setStartX(vertex.getCity().getX());
            l.setEndX(vertex.getPrev().getCity().getX());
            l.setStartY(vertex.getCity().getY());
            l.setEndY(vertex.getPrev().getCity().getY());
            vertex = vertex.getPrev();
            group.getChildren().add(l);

        }


        line.setStyle("-fx-stroke-dash-array: 4;");
        lines.add(line);
        group.getChildren().add(line);

    }

    ////read
    public static void ReadGraph(Graph graph) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("City.csv"));
//        scanner.useDelimiter(",");
        String tokens[];
        while (scanner.hasNextLine()) {
            tokens = scanner.nextLine().split(",");
//            System.out.println(tokens[0]);
            graph.insertVertex(new Vertex(new City(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]))));
        }
        Scanner scanner1 = new Scanner(new File("Roads.csv"));
//        scanner.useDelimiter(",");
        while (scanner1.hasNextLine()) {

            tokens = scanner1.nextLine().split(",");
//            System.out.println(tokens[1]);
            graph.addAdjacent(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
            graph.addAdjacent(tokens[1], tokens[0], Double.parseDouble(tokens[2]));
        }
    }

}