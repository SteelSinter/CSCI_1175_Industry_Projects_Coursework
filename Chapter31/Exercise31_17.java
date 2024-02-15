import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Exercise31_17 extends Application {
	// Accessible nodes
	TextField tfAmount = new TextField();
	TextField tfYears = new TextField();
	TextField tfInterestRate = new TextField();
	TextField tfFuture = new TextField();
	
	@Override
	public void start(Stage mainStage) {
		// Panes
		Pane pane = new Pane();
		VBox vBox = new VBox();
		GridPane gridPane = new GridPane();
		
		// Menus
		MenuBar menuBar = new MenuBar();
		Menu menuOperation = new Menu("Operation");
		MenuItem menuCalc = new MenuItem("Calculate");
		MenuItem menuExit = new MenuItem("Exit");
		
		// Nodes
		Button btCalc = new Button("Calculate");
		
		// Scene
		Scene scene = new Scene(pane, 300, 200);
		
		// Build menus
		menuOperation.getItems().addAll(menuCalc, menuExit);
		menuBar.getMenus().add(menuOperation);
		menuBar.setMinWidth(pane.getWidth());
		
		// Build panes
		pane.getChildren().add(vBox);
		vBox.getChildren().addAll(menuBar, gridPane);
		gridPane.setHgap(5);
		gridPane.setPadding(new Insets(10));
		gridPane.add(new Label("Investment Amount:"), 0, 0);
		gridPane.add(tfAmount, 1, 0);
		gridPane.add(new Label("Number of Years:"), 0, 1);
		gridPane.add(tfYears, 1, 1);
		gridPane.add(new Label("Annual Interest Rate:"), 0, 2);
		gridPane.add(tfInterestRate, 1, 2);
		gridPane.add(new Label("Future Value:"), 0, 3);
		gridPane.add(tfFuture, 1, 3);
		gridPane.add(btCalc, 1, 4);
		tfFuture.setDisable(true);
		tfFuture.setStyle("-fx-opacity: 1");
		
		// Events
		menuCalc.setOnAction(e -> {
			calculate();
		});
		
		menuExit.setOnAction(e -> {
			System.exit(0);
		});
		
		btCalc.setOnAction(e -> {
			calculate();
		});
		
		mainStage.setTitle("Exercise31-17");
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	public void calculate() {
		try {
			double amount = Double.parseDouble(tfAmount.getText());
			double annualInterestRate = Double.parseDouble(tfInterestRate.getText());
			double years = Double.parseDouble(tfYears.getText());
			double result = 0;
			
			result = amount *= Math.pow(1 + ((annualInterestRate / 12) * .01), years * 12);
			
			tfFuture.setText(String.format("$%.2f", result));
		}
		catch (NumberFormatException e) {
			
		}
		
		
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
