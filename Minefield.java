/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author 150074
 * @version 16/02/2017
 */

public class Minefield 
{
    private MineTile[][] minetiles;

    private int rows, columns, numMines;
    /**
     * Creates the Minefield arrays and defines the rows and columns of the field. 
     * @param rows  the number of rows. 
     * @param columns   the number of columns.
     * @param numMines  maximum number of mines.
     */
    public Minefield(int rows, int columns, int numMines) 
    {
        this.rows = rows;
        this.columns = columns;
        this.numMines = numMines;
        minetiles = new MineTile[rows][columns];

        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < columns; j++) 
            {
                minetiles[i][j] = new MineTile();
            }
        }

        if (rows <= 1) {
            System.out.println("The number of rows must be more than 0!");
        }

        if (columns <= 1) {
            System.out.println("The number of columns must be more than 0!");
        }

        if (numMines <= 1) {
            System.out.println("The number of mines must be higher than 0!");
        }
    }

    /**
     * The mineTile sets the tiles to be mined and increments the minedNeighbours.  
     * @param row represents the rows in integers. 
     * @param column represents the columns in integers
     * @return  true if square is mined, and false if maxMines is exceeded, or square has already been mined.
     */
    public boolean mineTile(int row, int column) 
    {
        if (minetiles[row][column].isMined()) 
        {
            return false;
        } 
        else 
        {
            minetiles[row][column].becomeMined();
        }

        // There are a number of ways of doing this.  I've chosen to precalculate
        // the start and end points of the for loops
        // However, these checks could be internal to the for loops
        // or the arrays could have unused boundary row and columns

        int startRow, endRow, startColumn, endColumn;
        if (row == 0) {
            startRow = 0;
        } else {
            startRow = row - 1;
        }
        if (column == 0) {
            startColumn = 0;
        } else {
            startColumn = column - 1;
        }
        if (row == this.rows - 1) {
            endRow = row;
        } else {
            endRow = row + 1;
        }
        if (column == this.columns - 1) {
            endColumn = column;
        } else {
            endColumn = column + 1;
        }

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startColumn; j <= endColumn; j++) {

                minetiles[i][j].incremNeighbours();

            }
        }
        return true;
    }

    /**
     * This method returns the number of rows. 
     */
    public int getRows() 
    {
        return rows;
    }

    /**
     * This method returns the number of columns. 
     */
    public int getColumns() 
    {
        return columns;
    }

    /**
     * The toString prints out a string representing the minefield.   
     * @return s Stars for mines, ints for adjacent
     */ 
    public String toString() 
    {
        String s = "";
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < columns; j++) 
            {
                if (minetiles[i][j].isMined()) 
                {
                    s += "*";
                } 
                else 
                {
                    s += minetiles[i][j].numOfNeighbours();
                }
            }
            s += "\n";
        }
        return s;
    }

    /**
     * This print method prints out the toString method in the command window.
     */
    public void printOne() 
    {
        System.out.println(toString()); 
        //this method is easier to see the Minefield printed instead of the toString which just returns a sting 
    }

    /**
     * This print method prints out the both MineTile class with methods called in this class.
     */
    public void printTwo() 
    {
        for (int hori = 0; hori < rows; hori++) 
        {
            for (int vert = 0; vert < columns; vert++) 
            {
                System.out.print(minetiles[hori][vert]);
            }
            System.out.println();
        }
    } 

    /**
     * A simple loop creating random coordinates,
     * populates the minefield with the required number of mines.
     */
    public void populate() 
    {
        int created = 0;
        while (created < this.numMines) {
            int row = (int) (Math.random() * this.rows);
            int col = (int) (Math.random() * this.columns);
            if (!minetiles[row][col].isMined() && !(row == 0 && col == 0)) {
                mineTile(row, col);
                created++;
            }

        }
    }

    /**
     * This method flags tiles at particular coordinates.
     * @param row   represents the number of rows.
     * @param column    represents the number of columns.
     */
    public void mark(int row, int column) throws ArrayIndexOutOfBoundsException
    {
        if (row>rows)
        {
            throw new ArrayIndexOutOfBoundsException("The number of rows must be less than the number of rows!");
        }
        if (column>columns)
        {
            throw new ArrayIndexOutOfBoundsException("The number of columns must be less than the number of columns!");
        }
        this.minetiles[row][column].switchMarked(); 
        System.out.println("This tile has now been flagged!");
        //calls the external method switchMarked which toggles the marked state 

    }

    /**
     * This method steps on tiles at coordinates. It retuns false if a tile being stepped on has a mine present. 
     * @param row   represents the number of rows.
     * @param column    represents the number of columns.
     * @return nomines  represents if there are any mines in the paramerised tile
     */
    public boolean step(int row, int column) throws ArrayIndexOutOfBoundsException
    {       
        boolean nomines = true;
        if (row>rows)
        {
            throw new ArrayIndexOutOfBoundsException("The number of rows must be less than the number of rows!");
        }
        else if (column>columns)
        {
            throw new ArrayIndexOutOfBoundsException("The number of columns must be less than the number of columns!");
        }
        
        else 
        {
            if (this.minetiles[row][column].isMined()) 
            {
                nomines = false; //returns false if the tile isMined()
            } 
            else 
            {
                this.revealer(row, column); //if the tile is !isMined()
            }
        }
        return nomines;
    }   

    /**
     * This is a supporting method of the step method it is executed in the case where the tile !isMarked(). 
     * @param row   represents the number of rows.
     * @param column    represents the number of columns.
     */
    public void revealer (int row, int column)
    {
        for (int hori = row - 1; hori <= row + 1; hori++) 
        {
            for (int vert = column - 1; vert <= column + 1; vert++) 
            {
                if (hori >= 0 && vert >= 0 && hori < minetiles.length && vert < minetiles[hori].length) 
                {
                    //The loop must begin after 0 
                    if (!this.minetiles[row][column].isRevealed()) 
                    {
                        this.minetiles[row][column].becomeRevealed(); 
                        //if the tile is not revealed, you call the method to becomeRevealed() 
                        if (this.minetiles[row][column].numOfNeighbours() == 0)
                        {
                            revealer(hori, vert); // calls the revealer method if the tile has 0 neighbours. 
                        }
                    }
                }
            }
        }
    }

    /**
     * This method returns true if all mined tiles are correctly flagged. It check if mines are revealed.
     * @return allRevelead  true if mines are flagged, false otherwise. 
     */

    public boolean areAllMinesRevealed()
    {
        boolean allRevealed = false; 
        for (int hori = 0; hori < rows; hori++) 
        {
            for (int vert = 0; vert < columns; vert++) 
            {
                if ((!minetiles[hori][vert].isMined() && minetiles[hori][vert].isMarked())) 
                //in the case where tile is not mined and not marked or a case where the tile is mined and is marked e  
                {
                    allRevealed = true;
                }
            }
        }
        return allRevealed;
    }
}