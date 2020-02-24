import java.util.Arrays;

public class Main {

    public static int count = 2;

    public static void main(String[] args) {
        int n = 3;
        int size = (int)Math.pow(2,n);

        int[][] chessboard = new int[size][size];

        chessboard[0][size-1] = 1;

        fill(chessboard, 0, 0, size,0,size, size);

        for (int[] row : chessboard)
            System.out.println(Arrays.toString(row));
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
            rotate(a, rot, x1, x2, y1, y2, size);
            count++;
        }
        else{
            fill(a, 90, x1, x2/2, y1, y2/2, size); //top left
            fill(a, 0, x1, x2/2, y2/2, y2, size); //bottom left
            fill(a, 270, x2/2, x2, y2/2, y2, size); //bottom right
            fill(a, 0, x2/2, x2, y1, y2/2, size); //top right
        }
    }

    public static void rotate(int a[][], int rot, int x1, int x2, int y1, int y2, int size){
        x2 = x2 - 1;
        y2 = y2 - 1;
        boolean didRotation = false;

        int[][] newa = new int[size][size];
        while (rot != 0) {
            didRotation = true;
            newa = dorotation(a, rot, x1, x2, y1, y2, size);
            rot = 0;
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