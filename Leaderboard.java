public class Leaderboard
{
   private Score[] lb;
   public Leaderboard(int size)
   {
      lb=new Score[size];
   }
   public void showLeaderboard()
   {
      System.out.println("********************\n TOP "+lb.length+" LEADERBOARD");
      System.out.printf("%-3s %-10s %-10s\n","#","NAME","SCORE");
      for(int i=0;i<lb.length;i++)
      {
         if(lb[i]!=null)
            System.out.printf("%-3s %-10s %-10s\n",(i+1),lb[i].getName(),lb[i].getPoints());
      }
      System.out.println("********************");
   }
   public void add(Score s)
   {
      if(doesExist(s))
         return;
      for(int i=0;i<lb.length;i++)
      {
         if(lb[i]==null)
         {
            lb[i]=s;
            return;
         }
         else
         {
            if(lb[i].getPoints()>s.getPoints())
            {
               continue;
            }
            else
            {
               for(int j=lb.length-1;j>i;j--)
               {
                  lb[j]=lb[j-1];
               }
               lb[i]=s;
               return;
            }
         }
      }
   }
   private boolean doesExist(Score s)
   {
      for(int i=0;i<lb.length;i++)
      {
         if(lb[i]==null)
            return false;
         else
         {
            if(lb[i].getName().equals(s.getName()))
            {
               if(s.getPoints()>lb[i].getPoints())
               {
                  lb[i]=s;
                  for(int j=i-1;j>=0;j--)
                  {
                     if(lb[j].getPoints()>lb[i].getPoints())
                     {
                        for(int k=i;k>j+1;k--)
                        {
                           Score temp=lb[k];
                           lb[k]=lb[k-1];
                           lb[k-1]=temp;
                        }
                        return true;
                     }
                     else if(j==0)
                     {
                        for(int k=i;k>0;k--)
                        {
                           Score temp=lb[k];
                           lb[k]=lb[k-1];
                           lb[k-1]=temp;
                        }
                        return true;
                     }
                  }
               }
               return true;
            }
         }
      }
      return false;
   }
}