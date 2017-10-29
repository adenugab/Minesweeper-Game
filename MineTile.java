/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 * 
 * @author 150074
 * @version 21/03/2017
 */

public class MineTile 
{
    private boolean marked;
    private int minedNeighbours;
    private boolean mined;
    private boolean revealed;

    /**
     * Constrctor of a 2-dimensional array of the MineTile class. Initialises 
     * all boolean variables to false and the minedNeighbours to 0.
     */
    public MineTile()
    {
        mined = false;
        minedNeighbours = 0;
        marked = false;
        revealed = false;
    }

    /**
     * @return returns if the tile is revealed
     */
    public boolean isRevealed() 
    {
        return revealed;
    }
    
    /**
     * changes the tile's mined variable to true.
     */
    public void becomeMined()
    {
        mined = true;
    }
   
    /**
     * Switches the tile's revealed status.
     */
    public void switchRevealed() 
    {
        this.revealed = !this.revealed;
    }

    /**
     * 
     * @return returns is the tile is marked or not.
     */
    public boolean isMarked() 
    {
        return marked;
    }

    /**
     * Switches the tile's marked status.
     */
    public void switchMarked() 
    {
        this.marked = !this.marked;
    }

    /**
     * 
     * @return returns the amount of mined neighbours in integer
     */
    public int numOfNeighbours() 
    {
        return minedNeighbours;
    }

    /**
     * Increments the amount of mined neighbours
     */
    public void incremNeighbours() 
    {
        this.minedNeighbours++;
    }

    /**
     * 
     * @return returns if the tile is mined or not.
     */
    public boolean isMined() 
    {
        return mined;
    }

    /**
     * Switches the tile's mined status.
     */
    public void switchMined() 
    {
        this.mined = !this.mined;       
    }

    /**
     * Prints the toString method in the command window.
     */
    public void print() 
    {
        System.out.println(toString());
    }
        
    /**
     * Changes the tile's revealed status to become true.
     */
    public void becomeRevealed() 
    {
        revealed = true;
    }   

    public String toString() 
    {
        if(revealed) 
        {
            if(mined) 
            {
                return "*";
            } 
            else 
            {
                return "" + minedNeighbours;
            }
        } 
        else 
        {
            if(marked) 
            {
                return "f";
            } 
            else 
            {
                return "";
            }
        }
    }
}