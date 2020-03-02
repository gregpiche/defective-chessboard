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

    // GUI component initialization
    public static int count = 2;
    public int[][] chessboard;
    public VBox layout = new VBox(10);
    public GridPane root = new GridPane(); // Where chessboard will be printed
    public TextField nInput = new TextField(); // Takes user input
    public Scene scene = new Scene(layout, 360, 425);
    public Button button;

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

        // First line layout
        HBox init = new HBox(10);
        init.getChildren().addAll(fieldDescriptor, nInput);
        init.setAlignment(Pos.CENTER);

        // VBox layout
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(init, button, root);
        layout.setAlignment(Pos.TOP_CENTER);

        // Button layout
        button.setPrefWidth(300);

        // chessboard alignment
        root.setAlignment(Pos.TOP_CENTER);
        stage.setScene(scene);
        stage.show();
    }

    // handle setups and calls the algorithm
    @Override
    public void handle(ActionEvent event) {

        int n = Integer.parseInt(nInput.getText());
        int size = (int) Math.pow(2, n);

        chessboard = new int[size][size];

        chessboard[0][size - 1] = 1;

        fill(chessboard, 0, 0, size, 0, size, size);


        /* Uncomment to print 2D array to terminal
        for (int[] row : chessboard){
            System.out.println(Arrays.toString(row));
        }*/

        // Reset constraints on GridPane (root)
        root.getChildren().clear();
        root.getColumnConstraints().clear();
        root.getRowConstraints().clear();

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
    }

    // Executes the algo
    public static void fill(int a[][], int rot, int x1, int x2, int y1, int y2, int size){
        int sizex = x2 -1;
        int sizey = y2 - 1;
        int startx = x1;
        int starty = y1;

        if(sizex-x1 == 1){ // base case of 2x2
            a[starty][startx] = count;
            a[sizey][startx] = count;
            a[sizey][sizex] = count;
            if(rot != 0){
                rotate(a, rot, x1, x2, y1, y2, size);
            }
            // The count is used to identify Triminoes in the array/chessboard
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
                rotate(a, rot, x1, x2, y1, y2, size); // does the rotation of the recursive calls
            }
        }
    }

    // Rotates the triminoes and the recursive calls
    public static void rotate(int a[][], int rot, int x1, int x2, int y1, int y2, int size){
        x2 = x2 - 1;
        y2 = y2 - 1;

        int[][] newa;

        if (rot != 0) { // Will call dorotation if the angle is not 0
            newa = dorotation(a, rot, x1, x2, y1, y2, size); // assign rotated array
            for (int i = y1; i <= y2; i++) { // Transfers the the rotated array to the chessboard array (a)
                for (int j = x1; j <= x2; j++) {
                    a[i][j] = newa[i][j];
                }
            }
        }
    }

    // this function will rotate the array by desired
    public static int[][] dorotation(int a[][], int rot, int x1, int x2, int y1, int y2, int size){
        int[][] rota = new int[size][size];

        int n = x2;

        // Will copy each row of array to right most column of same size array
        for (int r = y1; r <= y2; r++) {
            int m = y1;
            for (int c = x1; c <= x2; c++) {
                rota[m][n] = a[r][c];
                m++;
            }
            n--;
        }

        rot = rot - 90; // decrement by 90 until rotated desired angle

        if (rot != 0){ // returns rotated array if rotation is complete
            return dorotation(rota, rot, x1, x2, y1, y2, size);
        }

        return rota; // return rotated array
    }
}