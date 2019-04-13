/**
 * 
 */
package indi.koro.koroGameEngine.system;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import indi.koro.koroGameEngine.data.Data;
import indi.koro.koroGameEngine.game.Game;

/**
 * @author 16415
 *
 */
public class MainSystem {
    public void thread() {
	Thread thread = new Thread(new thread());
	thread.start();
    }

    public void star() {
	try {
	    gameload();
	} catch (Exception e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	}
	thread();
    }

    private void gameload() throws Exception {// 加载游戏
	File mod = new File("game");
	File[] modfile = mod.listFiles();
	for (File file : modfile) {
	    if (file.isFile()) {
		URL[] urls = new URL[] {};
		KoroClassLoader classLoader = new KoroClassLoader(urls, ClassLoader.getSystemClassLoader());
		classLoader.addJar(file.toURI().toURL());
		Class<?> clazz = classLoader.loadClass("main.Main");
		Object ojb = clazz.newInstance();
		Game game = (Game) ojb;
		System.out.println("已加载：" + game.backname());
		Data.game.put(game.backname(), game);
		classLoader.close();

	    }
	}
    }

    public class thread implements Runnable {
	public void run() {
	    Data.nowGame = Data.game.get(Data.gamename);
	    Data.gameshow = true;
	    Data.title=Data.nowGame.backname();
	    Data.mainPanel.setIcon(Data.nowGame.backIcon());
	    Data.nowGame.run(Data.mainGraphics, Data.mainPanel);
	}
    }

    static class KoroClassLoader extends URLClassLoader {

	public KoroClassLoader(URL[] urls) {
	    super(urls);
	}

	public KoroClassLoader(URL[] urls, ClassLoader parent) {
	    super(urls, parent);
	}

	public void addJar(URL url) {
	    this.addURL(url);
	}

    }
}
