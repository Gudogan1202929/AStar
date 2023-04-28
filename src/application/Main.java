package application;

import java.io.FileNotFoundException;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	public Problem p = new Problem();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws FileNotFoundException {
		scene1(stage);
	}

	// the main interface
	public void scene1(Stage stage) {

		BorderPane bp = new BorderPane();

		VBox vb = new VBox(20);

		Label lCoinExp = new Label();
		Label l2 = new Label();
		Label lDpTable = new Label();
		Label l4 = new Label();
		Label LabExpeacted = new Label();

		Button Enter = new Button("Enter coins");
		Enter.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		Enter.setMinHeight(40);
		Enter.setMinWidth(160);

		Button pEXP = new Button("The expected result");
		pEXP.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		pEXP.setMinHeight(40);
		pEXP.setMinWidth(200);

		Button pDpTable = new Button("The DP table");
		pDpTable.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		pDpTable.setMinHeight(40);
		pDpTable.setMinWidth(240);

		Button pCoinExp = new Button("The coins that give the expected result");
		pCoinExp.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		pCoinExp.setMinHeight(40);
		pCoinExp.setMinWidth(280);

		vb.getChildren().addAll(l4, Enter, l2, pEXP, LabExpeacted, pDpTable, lDpTable, pCoinExp, lCoinExp);

		bp.setCenter(vb);
		vb.setAlignment(Pos.CENTER);

		bp.setBackground(new Background(new BackgroundImage(new Image("download.jpg"), null, null, null, null)));

		Scene scene1 = new Scene(bp, 850, 420);
		stage.setScene(scene1);
		stage.setTitle("Optimal Strategy for a Game using Dynamic Programming");
		stage.show();

		Enter.setOnAction(e -> {
			// to enter number of coin and the value
			cene2(stage);
		});
		pEXP.setOnAction(e -> {
			// to show expected value
			cene3(stage);
		});

		pDpTable.setOnAction(e -> {
			// to show the dinamyc table
			cene4(stage);
		});
		pCoinExp.setOnAction(e -> {
			// to show coins me and coins user
			cene5(stage);
		});
	}

	// to enter number of coin and the value
	public void cene2(Stage stage) {

		BorderPane bp6 = new BorderPane();

		GridPane gp6 = new GridPane();
		gp6.setVgap(20);
		gp6.setHgap(20);

		Label l6 = new Label("COIN REQUIRMINT");
		l6.setStyle("-fx-font: 24 arial;");

		bp6.setTop(l6);
		BorderPane.setAlignment(l6, Pos.TOP_CENTER);

		Label l0 = new Label("");
		l0.setStyle("-fx-font: 14 arial;");

		Label l1 = new Label("NUMBER OF COIN : MOST BE EVEN :");
		l1.setStyle("-fx-font: 14 arial;");

		Label l2 = new Label("ENTER ALL NUMBER :");
		l2.setStyle("-fx-font: 14 arial;");

		gp6.add(l1, 0, 0);
		gp6.add(l2, 0, 1);

		TextField tf0 = new TextField("");
		TextField tf1 = new TextField("");
		Label la = new Label("");
		la.setStyle("-fx-font: 24 arial;");

		gp6.add(tf0, 1, 0);
		gp6.add(tf1, 1, 1);
		gp6.add(la, 1, 2);

		bp6.setCenter(gp6);
		BorderPane.setAlignment(gp6, Pos.BOTTOM_CENTER);

		HBox hb6 = new HBox(20);
		Button b61 = new Button("done");
		b61.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		b61.setMinHeight(40);
		b61.setMinWidth(80);

		Button b62 = new Button("back");
		b62.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		b62.setMinHeight(40);
		b62.setMinWidth(80);

		hb6.getChildren().addAll(b61, b62);
		bp6.setBottom(hb6);
		BorderPane.setAlignment(hb6, Pos.TOP_CENTER);

		bp6.setBackground(new Background(new BackgroundImage(new Image("prog.jpg"), null, null, null, null)));

		Scene scene6 = new Scene(bp6, 508, 250);
		stage.setScene(scene6);
		stage.setTitle("Optimal Strategy for a Game using Dynamic Programming");
		stage.show();

		b62.setOnAction(e -> {
			scene1(stage);
		});

		b61.setOnAction(e -> {

			// to trim string and make sure input is not null
			if (tf0.getText().trim() == null || tf1.getText().trim() == null) {
				return;
			}

			// number of coin
			int n;
			try {
				// to not make an error if input not integer
				n = Integer.parseInt(tf0.getText().trim());
			} catch (Exception e2) {
				n = 0;
			}

			// make sure n even rule of game
			if (Problem.isEven(n) == true) {

				// arr to split string of space into and trim it
				String[] arr = tf1.getText().trim().split(" ");
				// to make sure we enter all coins value

				if (arr.length != n) {
					la.setText("enter number as you wrote");
					return;
				}
				// array to covert array of strings to integer
				int[] arrIntger = new int[arr.length];

				for (int i = 0; i < arr.length; i++) {
					arrIntger[i] = Integer.parseInt(arr[i].trim());
				}

				p.setArr(arrIntger);

			} else {
				la.setText("enter even number");
			}
		});

	}

	// to show expected value
	public void cene3(Stage stage) {

		BorderPane bp7 = new BorderPane();

		Label l71 = new Label("The expected result");
		l71.setStyle("-fx-font: 24 arial;");

		bp7.setTop(l71);
		BorderPane.setAlignment(l71, Pos.TOP_CENTER);

		HBox hb7 = new HBox(20);
		Button b61 = new Button("done");
		b61.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		b61.setMinHeight(40);
		b61.setMinWidth(80);

		Button b62 = new Button("back");
		b62.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		b62.setMinHeight(40);
		b62.setMinWidth(80);

		hb7.getChildren().addAll(b61, b62);
		bp7.setBottom(hb7);
		BorderPane.setAlignment(hb7, Pos.CENTER);

		Label l72 = new Label("  ");
		l72.setStyle("-fx-font: 24 arial;");

		bp7.setCenter(l72);
		BorderPane.setAlignment(l72, Pos.TOP_CENTER);

		bp7.setBackground(new Background(new BackgroundImage(new Image("prog.jpg"), null, null, null, null)));

		Scene scene7 = new Scene(bp7, 500, 200);
		stage.setScene(scene7);
		stage.setTitle("The expected result");
		stage.show();

		b61.setOnAction(e -> {
			// expected value we will get from a game
			l72.setText(Problem.expected + "");
		});

		b62.setOnAction(e -> {
			scene1(stage);
		});

	}

	// to show the dinamyc table
	public void cene4(Stage stage) {
		BorderPane bp7 = new BorderPane();

		Label l71 = new Label("The DP table.");
		l71.setStyle("-fx-font: 20 arial;");
		bp7.setTop(l71);
		BorderPane.setAlignment(l71, Pos.TOP_CENTER);

		HBox hb7 = new HBox(20);
		Button b61 = new Button("done");
		b61.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		b61.setMinHeight(40);
		b61.setMinWidth(80);

		Button b62 = new Button("back");
		b62.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		b62.setMinHeight(40);
		b62.setMinWidth(80);

		hb7.getChildren().addAll(b61, b62);
		bp7.setBottom(hb7);
		BorderPane.setAlignment(hb7, Pos.CENTER);

		Label l72 = new Label("  ");
		l72.setStyle("-fx-font: 20 arial;");
		bp7.setCenter(l72);
		BorderPane.setAlignment(l72, Pos.TOP_CENTER);

		bp7.setBackground(new Background(new BackgroundImage(new Image("sky.png"), null, null, null, null)));

		Scene scene7 = new Scene(bp7, 1200, 500);
		stage.setScene(scene7);
		stage.setTitle("The DP table.");
		stage.show();

		b61.setOnAction(e -> {
			// array to insert on table

			try {
				int[][] arr2 = new int[Problem.arr.length][Problem.arr.length];
				l72.setText(p.solution(arr2));
			} catch (Exception e2) {
				l72.setText("no data");
			}

		});

		b62.setOnAction(e -> {
			scene1(stage);
		});
	}

	// to show coins me and coins user
	public void cene5(Stage stage) {
		BorderPane bp7 = new BorderPane();

		Label l71 = new Label("The coins that give the expected result.");
		l71.setStyle("-fx-font: 30 arial;");

		bp7.setTop(l71);
		BorderPane.setAlignment(l71, Pos.TOP_CENTER);

		Label l90 = new Label("\t \t Up for me :) ");
		l90.setStyle("-fx-font: 22 arial;");

		Label l20 = new Label("\t \t Down for the user :(");
		l20.setStyle("-fx-font: 22 arial;");

		HBox hb7 = new HBox(20);
		Button b61 = new Button("done");
		b61.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		b61.setMinHeight(40);
		b61.setMinWidth(80);

		Button b62 = new Button("back");
		b62.setStyle("-fx-background-color: rgb(211, 211, 211);-fx-background-radius: 20;-fx-text-fill: red");
		b62.setMinHeight(40);
		b62.setMinWidth(80);

		hb7.getChildren().addAll(b61, b62, l90, l20);
		bp7.setBottom(hb7);
		BorderPane.setAlignment(hb7, Pos.BOTTOM_CENTER);

		VBox hb = new VBox(15);

		Label l72 = new Label("  ");
		Label l73 = new Label("  ");
		Label l74 = new Label("  ");

		Label l75 = new Label("  ");
		Label l76 = new Label("  ");
		Label l77 = new Label("  ");
		Label l78 = new Label("  ");

		hb.getChildren().addAll(l78, l75, l76, l77, l72, l73, l74);

		bp7.setCenter(hb);
		BorderPane.setAlignment(l72, Pos.CENTER);

		// the background for scene
		bp7.setBackground(new Background(new BackgroundImage(new Image("sky.png"), null, null, null, null)));

		Scene scene7 = new Scene(bp7, 1200, 500);
		stage.setScene(scene7);
		stage.setTitle("The coins that give the expected result.");
		stage.show();

		b61.setOnAction(e -> {

			String k = "\t \t \t \t";
			// the origin array

			try {
				for (int i = 0; i < Problem.arr.length; i++) {
					try {
						k += Problem.arr[i] + "\t\t";
					} catch (Exception e2) {
						l72.setText("there is error");
						return;
					}
				}
			} catch (Exception e2) {
				l72.setText("there is error");
				l72.setStyle("-fx-font: 50 arial;");

			}

			l73.setText(k);
			l73.setStyle("-fx-font: 30 arial;");

			String[] res = new String[2];

			// array to set on it coin me and user
			try {
				res = p.whatCoins();
			} catch (Exception e2) {
				l72.setText("there is error");
				return;
			}

			for (int i = 0; i < res.length; i++) {
				System.out.println(res[i]);
			}

			// to remove , to not make an error
			if (res[0].charAt(res[0].length() - 1) == ',') {
				res[0] = res[0].substring(0, res[0].length() - 1);
			} else if (res[1].charAt(res[1].length() - 1) == ',') {
				res[1] = res[1].substring(0, res[0].length() - 1);
			}

			String[] myCoin = res[0].split(",");
			String[] userCoin = res[1].split(",");

			int[] myCoinINt = new int[myCoin.length];
			int[] userCoinINt = new int[userCoin.length];

			// to change it for integer to search
			for (int i = 0; i < userCoin.length; i++) {
				userCoinINt[i] = Integer.parseInt(userCoin[i].trim());
			}

			// to change it for integer to search
			for (int i = 0; i < myCoin.length; i++) {
				myCoinINt[i] = Integer.parseInt(myCoin[i].trim());
			}

			String mine = "\t\t";
			String user = "\t\t";

			// this to know location for coin on origin array for animation
			int n = 0;

			for (int i = 0; i < Problem.arr.length; i++) {

				mine += " \t \t";
				user += " \t \t";

				n = 0;

				for (int j = 0; j < myCoinINt.length; j++) {
					if (Problem.arr[i] == myCoinINt[j]) {

						mine += myCoinINt[j];
						myCoinINt[j] = -1;
						n = 1;
						break;
					}
				}
				if (n == 0) {
					for (int j1 = 0; j1 < userCoinINt.length; j1++) {
						if (Problem.arr[i] == userCoinINt[j1]) {
							user += userCoinINt[j1];
							userCoinINt[j1] = -1;
							break;
						}

					}
				}

			}

			// to set text of mine on label
			l72.setText(mine + "\n");
			l72.setStyle("-fx-font: 30 arial;");

			// to set text of user on label
			l74.setText(user + "\n");
			l74.setStyle("-fx-font: 30 arial;");

			// for animation
			TranslateTransition translate = new TranslateTransition(Duration.seconds(2));
			translate.setNode(l72);
			translate.setCycleCount(TranslateTransition.INDEFINITE);
			translate.setByY(-250);
			translate.setAutoReverse(true);
			translate.setInterpolator(Interpolator.EASE_IN);
			translate.play();

			// for animation
			TranslateTransition translate2 = new TranslateTransition(Duration.seconds(2));
			translate2.setNode(l74);
			translate2.setCycleCount(TranslateTransition.INDEFINITE);
			translate2.setByY(250);
			translate2.setAutoReverse(true);
			translate.setInterpolator(Interpolator.EASE_IN);
			translate2.play();
		});

		b62.setOnAction(e -> {
			scene1(stage);
		});
	}

}
