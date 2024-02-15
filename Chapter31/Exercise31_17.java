import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Exercise31_17 extends Application {
	
	@Override
	public void start(Stage mainStage) {
		// Panes
		Pane pane = new Pane();
		Pane vBox = new VBox();
		Pane gridPane = new GridPane();
		
		// Menus
		MenuBar menuBar = new MenuBar();
		Menu menuOperation = new Menu("Operation");
		MenuItem menuCalc = new MenuItem("Calculate");
		MenuItem menuExit = new MenuItem("Exit");
		
		// Scene
		Scene scene = new Scene(pane, 200, 100);
		
		// Build menus
		menuOperation.getItems().addAll(menuCalc, menuExit);
		menuBar.getMenus().add(menuOperation);
		
		// Build panes
		pane.getChildren().add(vBox);
		vBox.getChildren().addAll(menuBar, gridPane);
		
		// Events
		menuCalc.setOnAction(e -> {
			
		});
		
		menuExit.setOnAction(e -> {
			System.exit(0);
		});
		
		mainStage.setTitle("Exercise31-17");
		mainStage.setScene(scene);
		mainStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
