package package_9;

public class GameArrayMain
   {

    public static void main(String[] args)
       {
          
        int rowCap = 15, colCap = 10;
        GameArrayClass gac = new GameArrayClass( rowCap, colCap );
        boolean gameOver = false;
        
        gac.resetGame(rowCap, colCap);
        
        gac.cheat();
        
        System.out.println( "Game configured with " + rowCap 
                              + " rows, and " + colCap + " columns. Enjoy!\n" );
        
        do
           {
            gameOver = gac.tryElement();
           }
        while( !gameOver );
        
       }

   }