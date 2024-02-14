import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise33_09Server extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  
  DataInputStream in;
  DataOutputStream out;
  
  private int port = 8000;
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    taServer.setDisable(true);
    taServer.setStyle("-fx-text-fill: black;");

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);
    
    taClient.setOnKeyPressed(e -> {
    	try {
    		if (e.getCode() == KeyCode.ENTER) {
        		out.writeUTF(taClient.getText());
        		taClient.clear();
        	}
    	}
    	catch (java.io.IOException ex) {
    		taServer.appendText(ex.toString());
    	}
    	
    });

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // Thread to connect and wait for data
    new Thread(() -> {
    	try {
    		taServer.appendText("Connecting...\r\n");
    		ServerSocket serverSocket = new ServerSocket(port);
    		Socket socket = serverSocket.accept();
    		taServer.appendText("Connected.\r\n");
    		in = new DataInputStream(socket.getInputStream());
    		out = new DataOutputStream(socket.getOutputStream());
    		
    		while (true) {
    			taServer.appendText(in.readUTF());
    		}
    		
    	}
    	catch (java.io.IOException e) {
    		taServer.appendText(e.toString());
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
