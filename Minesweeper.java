/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author Ade
 */
public class Minesweeper {
    Parser parser = new Parser();
    Minefield md;
    public Minesweeper() 
    {
        md = new Minefield(10,10,10);
    }

    private void execute(Command c) {

        switch(c.getCommand()) {
            case STEP: case MARK: 
            System.out.println(c);
            break;
            case QUIT: System.out.println(c.getMsg());
            break;
            default:
            System.out.println(c);
        }
        printPrompt(c.getMsg());
    }

    private void commandLine() {
        printPrompt("New Game");
        Command c = parser.getCommand();
        
        while(c.getCommand() != CommandWord.QUIT) 
        {
            execute(c);
            c = parser.getCommand();
        }
        
    }

    private void commandLineTwo() 
    {
        printPrompt("Play");
        Command c1 = parser.getCommand();
        while (c1.getCommand() != CommandWord.STEP)
        {
            md.populate();
            md.step(6,6);  
            md.printOne();
            c1 = parser.getCommand();     
        }
    }
    
    private void printPrompt(String msg) 
    {
        System.out.println(msg);
        System.out.print(">");
    }

    public static void main(String args[]) 
    {
        Minesweeper ms = new Minesweeper();
        Minefield mf = new Minefield(10,10,10);
        MineTile mt = new MineTile();
        ms.commandLine();
        ms.commandLineTwo();
    }
    
}
