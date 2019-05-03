package indi.koro.koroGameEngine.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import indi.koro.koroGameEngine.frame.MainFrame;
import indi.koro.koroGameEngine.frame.MainGraphics;

public abstract class Game {
    public abstract void run(MainGraphics mainGraphics, MainFrame mainFrame);


    public abstract String backname();

    public abstract void render(Graphics2D g);
    
    public abstract BufferedImage backIcon() ;
    
}
