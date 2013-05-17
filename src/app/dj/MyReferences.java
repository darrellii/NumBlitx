package app.dj;

import android.app.Application;

public class MyReferences extends Application
{
  private int color;
  private int level = 1;
  private int numplayers;
  private Player[] playaz = new Player[4];

  public int getColor()
  {
    return this.color;
  }

  public int getLevel()
  {
    return this.level;
  }

  public int getNumplayers()
  {
    return this.numplayers;
  }

  public Player getPlaya(int paramInt)
  {
    return this.playaz[paramInt];
  }

  public Player[] getPlayaz()
  {
    return this.playaz;
  }

  public void setColor(int paramInt)
  {
    this.color = paramInt;
  }

  public void setLevel(int paramInt)
  {
    this.level = paramInt;
  }

  public void setNumplayers(int paramInt)
  {
    this.numplayers = paramInt;
  }

  public void setPlaya(int paramInt, Player paramPlayer)
  {
    this.playaz[paramInt] = paramPlayer;
  }

  public void setPlayaz(Player[] paramArrayOfPlayer)
  {
    this.playaz = paramArrayOfPlayer;
  }
}

/* Location:           C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     app.dj.MyReferences
 * JD-Core Version:    0.6.0
 */