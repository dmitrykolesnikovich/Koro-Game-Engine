package indi.koro.koroGameEngine.data;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JFrame;

import indi.koro.koroGameEngine.frame.MainFrame;
import indi.koro.koroGameEngine.frame.MainGraphics;
import indi.koro.koroGameEngine.game.Game;
import indi.koro.koroGameEngine.music.MusicPlayer;

public class Data {
    public static int x;
    public static int y;
    public static boolean show = false;
    public static double zoom = 0;
    public static int scenew = 0;
    public static int sceneh = 0;
    static public boolean gameshow = false;
    static public HashMap<String, Game> game = new HashMap<String, Game>();
    static public String gamename = new String();
    static public String title = new String("Loading");
    static public MainGraphics mainGraphics;
    static public JFrame mainFrame;
    static public MainFrame mainPanel;
    static public MusicPlayer bgmPlayer = new MusicPlayer();
    static public Game nowGame;
    static public int fps = 60;
    static public BufferedImage icon=null;
    
}
