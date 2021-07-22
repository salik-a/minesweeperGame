import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row, Row;
    int column, Column;
    int array[][] ;
    boolean game= true, end =false;

    //Constructor method
    MineSweeper(int row, int column){
        this.row = row;
        this.column = column;
        this.array = new int [row][column];
    }
    // create method design minesweeper
    void create(){
        // I assign zero value to each element of the array.
        for (int i = 0;i< this.row; i++){
            for (int j = 0; j< this.column; j++){
                array[i][j]= 0;
            }
        }
        // I create random values for mines indexes
        int min = 0;
        int max = this.row;
        Random random = new Random();
        int randRow, randColumn, count=0;
        //I generate random mines quarter of the size of the array and assign it into the array.
        while(count != (this.row*this.column/4)){
            randRow = random.nextInt(max + min) + min;
            randColumn = random.nextInt(max + min) + min;
            if (array[randRow][randColumn] != -1){
                array[randRow][randColumn]= -1;
                count++;
            }
        }
        // I show the all minesweeper map
        for (int i = 0;i< this.row; i++){
            for (int j = 0; j< this.column; j++){
                if (array[i][j]==0)
                    System.out.print("0  ");
                else
                    System.out.print("*  ");
            }
            System.out.println();
        }
    }

    // check method looks around of choosed array element and if there is mines around of this element it counts and write it to the choosed element.
    // While doing this, I check the border elements separately to avoid getting an "indexoutofbound exception" error.
    void check(int r, int c){
        if (array[r][c]==0){
            if ((c<column-1) && (array[r][c+1] == -1)){
                array[r][c]++;
            }
            if ((r<row-1) && (array[r+1][c] == -1)){
                array[r][c]++;
            }
            if ((r>0) && (array[r-1][c] == -1)){
                array[r][c]++;
            }
            if ((c>0) && (array[r][c-1] == -1)){
                array[r][c]++;
            }

            if ((r<row-1) &&(c<column-1) && (array[r+1][c+1] == -1)){
                array[r][c]++;
            }
            if ((c>0) && (r<row-1) && (array[r+1][c-1] == -1)){
                array[r][c]++;
            }
            if ((c>0) && (r>0) && (array[r-1][c-1] == -1)){
                array[r][c]++;
            }
            if ((r>0) && (c<column-1) && (array[r-1][c+1] == -1)){
                array[r][c]++;
            }
            if (array[r][c]==0)
                array[r][c]=-2;
        }
    }
    void run(){
        create();
        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.println("Welcome to the MineSweeper Game");
        System.out.println("-1 means there is a mine, -2 means there are no mines around");


        while(game){
            // I get row and column value from users and I'm checking if the given value is in the required range.
            boolean a = true;
            while(a){
                // I get row and column value from users
                System.out.println();
                System.out.println("===========================");
                System.out.print("Enter Row: ");
                Row= scan.nextInt();
                System.out.print("Enter Column: ");
                Column= scan.nextInt();

                //I'm checking if the given value is in the required range.
                if(((0<Row) && (Row<=row)) && ((0<Column) && (Column<=column))){
                    a=false;
                }
                else{
                    a=true;
                    System.out.println("Wrong value, enter again");
                }
                // I fix the values because of array index started from 0
                Row=Row-1;
                Column=Column-1;
            }

            // I am looking is users opened element with mine or without mine
            if (array[Row][Column] != -1){
                check(Row,Column);
                // I show the minesweeper map with the opened elements
                for (int i = 0;i< this.row; i++){
                    for (int j = 0; j< this.column; j++){
                        if (array[i][j]==0 || array[i][j]==-1 )
                            System.out.print("0  ");
                        else
                            System.out.print(array[i][j]+"  ");
                    }
                    System.out.println();
                }
            }
            // I am making game value as false to finish game if users open mines
            else{
                game=false;
                System.out.println();
                System.out.println("===========================");
                System.out.println("GAME OVER");
                System.out.println("===========================");
                System.out.println();
            }

            // I am looking if users finished game. To do that firstly I make end value as a true and then I am looking every element of array and if any element value is 0 I make end value is false.
            // This means the game is not finished.
            end = true;
            for (int i = 0;i< this.row; i++){
                for (int j = 0; j< this.column; j++){
                    if ((array[i][j]==0))
                        end = false;
                }
            }
            // If end value is equals true the game is finished and say you won.
            if (end){
                System.out.println();
                System.out.println("===========================");
                System.out.println("YOU WON");
                System.out.println("===========================");
                System.out.println();
                break;
            }
        }
    }
}
