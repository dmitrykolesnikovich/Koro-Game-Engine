package indi.koro.koroGameEngine.main;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.UIManager;

import indi.koro.koroGameEngine.component.Print;
import indi.koro.koroGameEngine.data.Data;
import indi.koro.koroGameEngine.frame.MainFrame;
import indi.koro.koroGameEngine.game.Game;
import indi.koro.koroGameEngine.system.MainSystem;

public class Appentry {

    public void star(String name,String filename,String mainurl, int mode) {
	// TODO 自动生成的方法存根
	if (mode == 1) {
		Thread thread=new Thread(new Runnable() {
		    
		    @Override
		    public void run() {
			// TODO 自动生成的方法存根
			try {
			    ConsoleToTextArea consoleToTextArea = new ConsoleToTextArea();
			} catch (IOException e) {
			    // TODO 自动生成的 catch 块
			    e.printStackTrace();
			}
		    }
		});
		thread.start();
	}
	MainFrame mainFrame = new MainFrame(1920, 1080);
	MainSystem mainSystem = new MainSystem();
	mainFrame.setname(name);
	mainFrame.showfps(true);
	mainSystem.star(filename,mainurl);
	mainFrame.show();
    }

    public static void main(String arg0[]) {
	Appentry appentry = new Appentry();
	appentry.star("Spice And Wolf-The Journey With Horo","SPICE AND WOLF-The journey with Horo.jar","main.Main", 0);
    }

}
