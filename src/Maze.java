
import java.io.File;
import java.util.Scanner;

public class Maze {
    private Cell[][] board;
    private final int DELAY = 20;

    public Maze(int rows, int cols, int[][] map) {
        StdDraw.setXscale(0, cols);
        StdDraw.setYscale(0, rows);
        board = new Cell[rows][cols];
        //grab number of rows to invert grid system with StdDraw (lower-left, instead of top-left)
        int height = board.length - 1;
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                board[r][c] = map[r][c] == 1 ? new Cell(c, height - r, 0.5, false) : new Cell(c, height - r, 0.5, true);
            }
    }

    public void draw() {
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[r].length; c++) {
                Cell cell = board[r][c];
                StdDraw.setPenColor(cell.getColor());
                StdDraw.filledSquare(cell.getX(), cell.getY(), cell.getRadius());
            }
        StdDraw.show();
    }

    public boolean findPath(int row, int col) {
        boolean complete = false;
        if (this.isValid(row, col)) {
            board[row][col].visitCell();
            draw();
            StdDraw.pause(DELAY);
            if (isExit(row, col)) {
                board[row][col].becomePath();
                return true;
            } else {
                complete = findPath(row, col + 1);

            }
            if (!complete) {
                complete = findPath(row - 1, col);
            }
            if (!complete) {
                complete = findPath(row, col - 1);
            }

            if (!complete) {
                complete = findPath(row + 1, col);
            }

            if (complete) {
                board[row][col].becomePath();
                draw();
                StdDraw.pause(DELAY);

            }
        }
        return complete;
    }

    private boolean isValid(int row, int col) {
        if (row < board.length && col < board[0].length && row > -1 && col > -1)
            if (!board[row][col].isWall() && !board[row][col].isVisited())
                return true;
            else return false;
        else return false;
    }

    private boolean isExit(int row, int col) {
        if (row == board.length - 1 && col == board[0].length - 1)
            return true;
        else return false;
    }

    public static void main(String[] args) throws Exception {
        if(args[0].equals("")){
            StdDraw.enableDoubleBuffering();
            int[][] maze = {{1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 0, 1, 1, 1, 0},
                    {0, 1, 1, 1, 1, 0, 1, 1, 0, 0},
                    {0, 1, 0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 0, 0, 0, 0, 0, 1, 1, 0},
                    {0, 1, 1, 0, 1, 1, 1, 1, 1, 0},
                    {0, 0, 1, 0, 0, 1, 0, 1, 0, 0},
                    {0, 1, 1, 0, 1, 1, 0, 1, 1, 0},
                    {0, 1, 1, 0, 1, 1, 0, 1, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 1}};
            Maze geerid = new Maze(maze.length, maze[0].length, maze);
            geerid.draw();
            geerid.findPath(0, 0);
            geerid.draw();
        }
        else{
            File inputFile = new File(""+args[0]);
            Scanner inputObject = new Scanner(inputFile);
            int row = 0;
            while (inputObject.hasNextLine()){
                row++;
                inputObject.nextLine();
            }
            inputObject.close();
            inputObject = new Scanner(inputFile);
            String[][] path = new String[row][inputObject.nextLine().split(",").length];
            inputObject.close();
            inputObject = new Scanner(inputFile);
            row=0;
            while (inputObject.hasNextLine()){
                path[row]= inputObject.nextLine().split(",");
                row++;
            }
            inputObject.close();
            int[][] maze = new int[path.length][path[0].length];
            for (int r = 0; r < path.length; r++) {
                for (int c = 0; c < path.length; c++) {
                    maze[r][c] = Integer.parseInt(path[r][c]);

                }

            }
            StdDraw.enableDoubleBuffering();
            Maze geerid = new Maze(maze.length, maze[0].length, maze);
            geerid.draw();
            geerid.findPath(0, 0);
            geerid.draw();
        }
    }

    public static void file(String args) throws Exception {


        File inputFile = new File("" + args);
        Scanner inputObject = new Scanner(inputFile);
        int row = 0;
        while (inputObject.hasNextLine()) {
            row++;
            inputObject.nextLine();
        }
        inputObject.close();
        inputObject = new Scanner(inputFile);
        String[][] path = new String[row][inputObject.nextLine().split(",").length];
        inputObject.close();
        inputObject = new Scanner(inputFile);
        row = 0;
        while (inputObject.hasNextLine()) {
            path[row] = inputObject.nextLine().split(",");
            row++;
        }
        inputObject.close();
        int[][] maze = new int[path.length][path[0].length];
        for (int r = 0; r < path.length; r++) {
            for (int c = 0; c < path.length; c++) {
                maze[r][c] = Integer.parseInt(path[r][c]);

            }

        }
        StdDraw.enableDoubleBuffering();
        Maze geerid = new Maze(maze.length, maze[0].length, maze);
        geerid.draw();
        geerid.findPath(0, 0);
        geerid.draw();


    }

}