// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();
  private int port = 8000;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    // Create socket and wait for client
    new Thread(() -> {
    	try {
    		ta.setText(ta.getText() + "Waiting for connection...\r\n");
        	ServerSocket serverSocket = new ServerSocket(port);
        	java.net.Socket socket = serverSocket.accept();
        	ta.setText(ta.getText() + "Connected to client on port " + port + ".\r\n");
        	DataInputStream in = new DataInputStream(socket.getInputStream());
        	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        	while (true) {
        		Loan loan = new Loan(in.readDouble(), (int) in.readDouble(), in.readDouble());
        		ta.appendText("Recieving data\r\n");
        		
        		out.writeDouble(loan.getMonthlyPayment());
        		ta.appendText("Sending " + loan.getMonthlyPayment() + "...\r\n");
        		out.writeDouble(loan.getTotalPayment());
        		ta.appendText("Sending " + loan.getTotalPayment() + "...\r\n");
        		out.flush();
        	}
        	
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
