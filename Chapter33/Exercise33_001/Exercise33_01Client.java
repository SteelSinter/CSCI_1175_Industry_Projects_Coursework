// Exercise31_01Client.java: The client sends the input to the server and receives
// result back from the server
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise33_01Client extends Application {
  // Text field for receiving radius
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfNumOfYears = new TextField();
  private TextField tfLoanAmount = new TextField();
  private Button btSubmit= new Button("Submit");
  private int port = 8000;
  private String serverName = "localhost";
  
  DataInputStream in;
  DataOutputStream out;

  // Text area to display contents
  private TextArea ta = new TextArea();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
	
    ta.setWrapText(true);
   
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Annual Interest Rate"), 0, 0);
    gridPane.add(new Label("Number Of Years"), 0, 1);
    gridPane.add(new Label("Loan Amount"), 0, 2);
    gridPane.add(tfAnnualInterestRate, 1, 0);
    gridPane.add(tfNumOfYears, 1, 1);
    gridPane.add(tfLoanAmount, 1, 2);
    gridPane.add(btSubmit, 2, 1);
    
    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
    tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);
    
    tfLoanAmount.setPrefColumnCount(5);
    tfNumOfYears.setPrefColumnCount(5);
    tfLoanAmount.setPrefColumnCount(5);
            
    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setTop(gridPane);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btSubmit.setOnAction(e -> {
		try {
			out.writeDouble(Double.parseDouble(tfAnnualInterestRate.getText()));
			ta.appendText("Sending " + tfAnnualInterestRate.getText() + "...\r\n");
			out.writeDouble(Double.parseDouble(tfNumOfYears.getText()));
			ta.appendText("Sending " + tfNumOfYears.getText() + "...\r\n");
			out.writeDouble(Double.parseDouble(tfLoanAmount.getText()));
			ta.appendText("Sending " + tfLoanAmount.getText() + "...\r\n");
			out.flush();
			
			ta.appendText(String.valueOf("Monthly payment: " + in.readDouble() + "\r\n"));
			ta.appendText(String.valueOf("Total patment: " + in.readDouble() + "\r\n"));
		}
    	catch (java.io.IOException ex) {
    		ex.printStackTrace();
    	}
    });
    
    // Connect to server
 	new Thread(() -> {
 		try {
 			ta.setText(ta.getText() + "Connecting to server " + serverName + "...\r\n");
 			Socket socket = new Socket(serverName, port);
 			ta.setText(ta.getText() + "Connected to " + serverName + " on port " + port + ".\r\n");
 			in = new DataInputStream(socket.getInputStream());
 			out = new DataOutputStream(socket.getOutputStream());
 		}
 		catch (java.io.IOException e) {
 			ta.appendText(e.toString() + "\r\n");
 		}
 		
 	}).start();
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
