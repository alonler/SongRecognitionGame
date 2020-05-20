import java.io.*;
import java.util.*;
import java.nio.*;

public class Game
{
   public static boolean isFinished(int[] a)
   {
      for(int i=0;i<a.length;i++)
         if(a[i]==0)
            return false;
      return true;
   }
   
   
   public static void main(String[] args)
   {
      List<String> tracklist = new ArrayList<String> ();
      String line = null;
      try
      {
         BufferedReader reader = new BufferedReader(new FileReader("tracklist.txt"));
         while((line = reader.readLine()) != null)
         {
            tracklist.add(line);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      Scanner s=new Scanner(System.in);
      
      Leaderboard lb=new Leaderboard(5);
      Score currentScore;
      String name;
      String yn="yes";
      while(yn.toLowerCase().equals("yes"))
      {
         System.out.println("ENTER YOUR NAME");
         name=s.nextLine();
         int[] a=new int[tracklist.size()];
         boolean error=false;
         int sum=0,lives=3,questionNumber=1;
         while(lives>0 && !isFinished(a))
         {
            if(!error)
            {
               System.out.print("SCORE: "+sum+"\tQUESTION "+questionNumber+"\t");
               for(int i=lives;i>0;i--){System.out.print("*");}
               System.out.println();
               System.out.println("ENTER THE NAME OF THE SONG");
            }
            int r=(int)(Math.random()*tracklist.size());
            while(a[r]!=0)
            {
               r=(int)(Math.random()*tracklist.size());
            }
            String location=tracklist.get(r)+".wav";
            String filePath=location;
            Music m=new Music();
            int roundScore=m.askQuestion(filePath);
            if(roundScore==0 || roundScore==-2)
               lives-=1;
            if(roundScore!=-1)
            {
               
               if(roundScore==0)
               {
                  System.out.println("YOU ARE WRONG :( THE SONG'S NAME IS \""+tracklist.get(r).toUpperCase()+"\". PRESS ENTER TO CONTINUE");
                  s.nextLine();
               }
               else if(roundScore>0)
               {
                  System.out.println("YOU ARE RIGHT :) YOU GAINED "+roundScore+" POINTS! PRESS ENTER TO CONTINUE");
                  sum+=roundScore;
                  s.nextLine();
               }
               error=false;
               questionNumber++;
            }
            else
               error=true;
            a[r]++;
            
            
         }
         if(isFinished(a))
            System.out.println("YOU WON THE GAME!!!");
         System.out.println("FINAL SCORE: "+sum+"\nCORRECT ANSWERS: "+(questionNumber-1-(3-lives)));
         currentScore=new Score(name.toUpperCase(),sum);
         lb.add(currentScore);
         lb.showLeaderboard();
         System.out.println("DO YOU WANT TO PLAY AGAIN? (YES/NO)");
         yn=s.nextLine();
      }
      System.exit(0);
      
   }
}
