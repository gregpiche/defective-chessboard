import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent> {

    public static int count = 2;
    public int[][] chessboard;
    public VBox layout = new VBox(10);
    public GridPane root = new GridPane();
    public TextField nInput = new TextField();
    public Scene scene = new Scene(layout, 360, 425);

    // GUI component initialization
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Defective Chessboard");

        // GUI elements
        button = new Button("Solve");
        button.setOnAction(this);

        Label fieldDescriptor = new Label("Enter value for n:");
        //TextField nInput = new TextField();

        // Layout
        HBox init = new HBox(10);
        init.getChildren().addAll(fieldDescriptor, nInput);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(init, button);
        //nInput.setMaxWidth(scene.getWidth() -  fieldDescriptor.getWidth());
        button.setMaxWidth(scene.getWidth());
        init.setAlignment(Pos.CENTER);

        root.setAlignment(Pos.CENTER);

        stage.setScene(scene); // align chessboard
        stage.show();

    }

    @Override
    public void handle(ActionEvent event) {

        int n = Integer.parseInt(nInput.getText());
        int size = (int) Math.pow(2, n);

        chessboard = new int[size][size];

        chessboard[0][size - 1] = 1;

        fill(chessboard, 0, 0, size, 0, size, size);

        /* Uncomment to print to terminal
        for (int[] row : chessboard){
            System.out.println(Arrays.toString(row));
        }*/

        // Table GUI

        layout.getChildren().remove(root);

        // Set color to chess board triminoes
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col ++) {
                StackPane square = new StackPane();
                String color = "";
                switch (chessboard[row][col]){
                    case 0:
                        color = "CYAN";
                        break;
                    case 1:
                        color = "black";
                        break;
                    case 2:
                        color = "blue";
                        break;
                    case 3:
                        color = "red";
                        break;
                    case 4:
                        color = "orange";
                        break;
                    case 5:
                        color = "green";
                        break;
                    case 6:
                        color = "white";
                        break;
                    case 7:
                        color = "INDIGO";
                        break;
                    case 8:
                        color = "YELLOW";
                        break;
                    case 9:
                        color = "SIENNA";
                        break;
                }
                square.setStyle("-fx-background-color: "+color+";");
                root.add(square, col, row);
            }
        }

        // Set size to each column and row in chessboard
        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(20));
            root.getRowConstraints().add(new RowConstraints(20));
        }

        layout.getChildren().addAll(root);
    }

    public static void fill(int a[][], int rot, int x1, int x2, int y1, int y2, int size){
        int sizex = x2 -1;
        int sizey = y2 - 1;
        int startx = x1;
        int starty = y1;

        if(sizex-x1 == 1){
            a[starty][startx] = count;
            a[sizey][startx] = count;
            a[sizey][sizex] = count;
            if(rot != 0){
                rotate(a, rot, x1, x2, y1, y2, size);
            }
            count++;
            if(count == 10){
                count = 2;
            }
        }
        else{
            fill(a, 90, x1, ((x2-x1)/2)+x1, y1, ((y2-y1)/2)+y1, size); //top left
            fill(a, 0, x1, ((x2-x1)/2)+x1, y2-((y2-y1)/2), y2, size); //bottom left
            fill(a, 270, x2-((x2-x1)/2), x2, y2-((y2-y1)/2), y2, size); //bottom right
            fill(a, 0, x2-((x2-x1)/2), x2, y1, ((y2-y1)/2)+y1, size); //top right
            if(rot != 0){
                rotate(a, rot, x1, x2, y1, y2, size);
            }
        }
    }

    public static void rotate(int a[][], int rot, int x1, int x2, int y1, int y2, int size){
        x2 = x2 - 1;
        y2 = y2 - 1;
        boolean didRotation = false;

        int[][] newa = new int[size][size];
        if (rot != 0) {
            didRotation = true;
            newa = dorotation(a, rot, x1, x2, y1, y2, size);
        }

        if (didRotation) {
            for (int i = y1; i <= y2; i++) {
                for (int j = x1; j <= x2; j++) {
                    a[i][j] = newa[i][j];
                }
            }
        }
    }

    public static int[][] dorotation(int a[][], int rot, int x1, int x2, int y1, int y2, int size){
        int[][] rota = new int[size][size];

        int n = x2;
        for (int r = y1; r <= y2; r++) {
            int m = y1;
            for (int c = x1; c <= x2; c++) {
                rota[m][n] = a[r][c];
                m++;
            }
            n--;
        }
        rot = rot - 90;
        if (rot != 0){
            return dorotation(rota, rot, x1, x2, y1, y2, size);
        }
        return rota;

    }
}