public class Score
{
   private String name;
   private int points;
   public Score(String n,int p)
   {
      name=n;
      points=p;
   }
   public String getName() {return name;}
   public int getPoints() {return points;}
   public void setName(String n){name=n;}
   public void setPoints(int p){points=p;}
}