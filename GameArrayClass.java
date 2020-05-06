package package_9; //Use whatever package your project is under

import javax.swing.JOptionPane;

public class GameArrayClass
   {
      
      private final int EMPTY_ELEMENT = 0;
      private final int USED_ELEMENT = -1;
      private final int WIN_ELEMENT = 1;
      public static final int NO_ELEMENT_FOUND = -999;
      
      private int[][] gameArray;
      
      private int rowCapacity;
      private int colCapacity;
      private int gameScore;
      
      public GameArrayClass( int rowCap, int colCap )
      {
         
         rowCapacity = rowCap;
         colCapacity = colCap;
         
         gameArray = new int[ rowCapacity ][ colCapacity ];
         
      }
      
      public void cheat()
      {
         System.out.println("Cheat screen:");
         
         int row, col;
         
         for( row = 0; row < rowCapacity; row++ )
            {
               
               for( col = 0; col < colCapacity; col++ )
                  {
    
                     System.out.printf( "%4d", gameArray[ row ][ col ] );
                     
                  }
               
               System.out.println();
              
            }
      }
      
      private void clearGameBoard()
      {
         int row, col;
         
         for( row = 0; row < rowCapacity; row++ )
            {
               for( col = 0; col < colCapacity; col++ )
                  {
                     gameArray[ row ][ col ] = EMPTY_ELEMENT;
                  }
            }
      }
      
      public void decrementGameScore()
      {
         gameScore --;
      }
      
      private int getRandBetween( int lowLimit, int highLimit )
      {
         int range = highLimit - lowLimit + 1;
         
         return (int)( Math.random() * range );
      }
      
      private boolean elementExists( int rowIndex, int colIndex )
      {
         
         if( rowIndex < rowCapacity && rowIndex >= 0 && 
               colIndex < colCapacity && colIndex >= 0 )
            {
               return true;
            }
         
         return false;
         
      }
      
      private boolean elementHasBeenUsed( int rowIndex, int colIndex )
      {
         
         if( elementExists( rowIndex, colIndex ) )
            {
               if( gameArray[ rowIndex ][ colIndex ] == USED_ELEMENT )
                  {
                     return true;
                  }
            }
            
            return false;
         
      }
      
      private boolean elementIsClear( int rowIndex, int colIndex )
      {
    
         if( elementExists( rowIndex, colIndex ) )
         {
            if( gameArray[ rowIndex ][ colIndex ] == EMPTY_ELEMENT )
               {
                  return true;
               }
         }
         
         return false;
         
      }
      
      public int getColumnCapacity()
      {
         return colCapacity;
      }
         
      public int getGameScore()
      {
         return gameScore;
      }
         
      public int getRowCapacity()
      {
         return rowCapacity;
      }
         
      public int getValueAt( int rowIndex, int colIndex )
      {
         
         if( elementExists( rowIndex, colIndex ) )
            {
               
               return gameArray[ rowIndex ][ colIndex ];
               
            }
            
         return NO_ELEMENT_FOUND;
            
      }
      
      public void resetGame( int rowCap, int colCap )
      {
         
         gameScore = rowCap * colCap;
         
         boolean flag = true;
         
         clearGameBoard();
         setWinSpot();
         
         int row, col;
         
         while( flag == true )
            {
               
               flag = false;
               
               for( row = 0; row < rowCap; row++ )
                  {
                           
                    for( col = 0; col < colCap; col++ )
                       {
                          
                          if( setNearNeighbors( row, col ) == true )
                             {
                                flag = true;
                             }
                         
                       }
                   }
            }
         
      }
      
      public void setElementUsed( int rowIndex, int colIndex )
      {
         
         if( elementExists( rowIndex, colIndex ) )
            {
               
               gameArray[ rowIndex ][ colIndex ] = USED_ELEMENT;
               
            }
         
      }
      
      private boolean setNearNeighbors( int rowIndex, int colIndex )
      {
         
         boolean flag = false;
         
         if( !elementIsClear( rowIndex, colIndex ) )
            {
               
               if( elementExists( rowIndex + 1, colIndex ) )
                 {
                    
                    if( elementIsClear( rowIndex + 1, colIndex ) )
                       {
                          gameArray[ rowIndex + 1 ][ colIndex ] = 
                                gameArray[ rowIndex ][ colIndex ] + 1;
                          flag = true;
                          
                       }
                    
                 }
               
               if( elementExists( rowIndex, colIndex + 1 ) )
                  {
                     
                     if( elementIsClear( rowIndex, colIndex + 1 ) )
                        {
                           gameArray[ rowIndex ][ colIndex + 1 ] = 
                                 gameArray[ rowIndex ][ colIndex ] + 1;
                           flag = true;
                           
                        }
                     
                  }
               
               if( elementExists( rowIndex - 1, colIndex ) )
                  {
                     
                     if( elementIsClear( rowIndex - 1, colIndex ) )
                        {
                           gameArray[ rowIndex - 1 ][ colIndex ] = 
                                 gameArray[ rowIndex ][ colIndex ] + 1;
                           flag = true;
                           
                        }
                     
                  }
               
               if( elementExists( rowIndex, colIndex - 1 ) )
                  {
                     
                     if( elementIsClear( rowIndex, colIndex - 1 ) )
                        {
                           gameArray[ rowIndex ][ colIndex - 1 ] = 
                                 gameArray[ rowIndex ][ colIndex ] + 1;
                           flag = true;
                           
                        }
                     
                  }
               
               
            }
                 
         return flag;
         
      }
      
      private void setWinSpot()
      {
         
         int winSpotRow, winSpotCol;
         
         winSpotRow = getRandBetween( colCapacity, rowCapacity );
         
         winSpotCol = getRandBetween( colCapacity, rowCapacity );
         
         gameArray[ winSpotRow ][ winSpotCol ] = WIN_ELEMENT;
         
      }
      
      public boolean tryElement()
      {
         
         int row, col;
         String rowStr, colStr;
         boolean win = false;
      
         do
            {
               
               rowStr = JOptionPane.showInputDialog("Enter row index "
                     + "between 0 and 14");
               row = Integer.parseInt(rowStr);
               
               colStr = JOptionPane.showInputDialog("Enter col index "
                     + "between 0 and 14");
               col = Integer.parseInt(colStr);
               
               if( elementExists( row, col ) )
                  {
                     
                     if( getValueAt( row, col ) == WIN_ELEMENT )
                        {
                           System.out.println("YOU FOUND IT at (" + 
                                 row + "," + col + ")! Your score is " + 
                                 getGameScore() );
                           win = true;
                           
                        }
                     else
                        {
                           
                           if( elementHasBeenUsed( row, col ) )
                              {
                                 decrementGameScore();
                                 System.out.println("You have already"
                                       + " tried the "
                                       + "element at (" + 
                                       row + "," + col + "); your score"
                                             + " is now "
                                       + getGameScore() );
                              }
                           else
                              {
                                 decrementGameScore();
                                 System.out.println("Value " + 
                                       getValueAt( row, col ) + " "
                                             + "found"
                                       + " at (" + 
                                       row + "," + col + "); your score"
                                             + " is now "
                                 + getGameScore() );
                                 
                                 setElementUsed( row, col );
                              }
                           
                        }
                     
                  }
               else
                  {
                     decrementGameScore();
                     System.out.println("The element at (" + row + "," +
                     col + 
                           ") is outside the boundaries of your game; "
                           + "your score is "
                           + "now " + getGameScore() );
                  }
               
            }
         while( !win );
         
         return win;
         
      }
      
   }
