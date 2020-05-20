import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
public class Music 
{
   private String guess="";
   boolean timesUp=false;
   private TimerTask task=new TimerTask()    
   { 
      long starttime=System.currentTimeMillis();

      public void run()
      {
         if(guess.equals(""))
         {
            System.out.println("TIMES UP! PRESS ENTER TO CONTINUE");
            task.cancel();
            timesUp=true;
         }
      }
   };
      
   public int askQuestion2(String location) throws Exception
   {
         Scanner s=new Scanner(System.in);
         File musicPath=new File(location);
         if(musicPath.exists())
         {
            AudioInputStream audioInput=AudioSystem.getAudioInputStream(musicPath);
            Clip clip=AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            Timer timer=new Timer();
            timer.schedule(task,50*1000);          
            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
            guess=in.readLine();
            timer.cancel();
            int clipTimePosition=((int)clip.getMicrosecondPosition())/1000;
            if(guess.toLowerCase().equals(location.substring(0,location.indexOf(".wav")).toLowerCase()))
               if(100-clipTimePosition/500>0)
               {
                  clip.stop();
                  return 100-clipTimePosition/500;
               }
            clip.stop();
            if(timesUp)
               return -2;
            return 0;
         }
         else
         {
            return -1;
         }
   }
   
   public int askQuestion(String location)
   {
      try
      {
         return (new Music()).askQuestion2(location);
      }
      catch(Exception e)
      {
         return -1;
      }
   }
   
}
