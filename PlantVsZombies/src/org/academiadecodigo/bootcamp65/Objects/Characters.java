package org.academiadecodigo.bootcamp65.Objects;


public interface Characters {

    public void createImage(String source);

    public int getHealth();

    public boolean isDead();

    public void setDead();

    public void setHealth(int health);

    public void setImage(String source);


    public int getCol();

    public int getRow();

}
