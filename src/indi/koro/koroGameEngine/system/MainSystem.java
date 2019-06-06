/**
 * 
 */
package indi.koro.koroGameEngine.system;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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

    public void star(String filename,String mainurl) {
	try {
	    gameload(filename,mainurl);
	} catch (Exception e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	}
	thread();
    }

    private void gameload(String filename,String mainurl) throws Exception {// 加载游戏
	
	
	 Set<Class<?>> classes = new LinkedHashSet<Class<?>>();//所有的Class对象  
	 Map<Class<?>, Annotation[]> classAnnotationMap = new HashMap<Class<?>, Annotation[]>();//每个Class对象上的注释对象  
	 Map<Class<?>, Map<Method, Annotation[]>> classMethodAnnoMap = new HashMap<Class<?>, Map<Method,Annotation[]>>();//每个Class对象中每个方法上的注释对象  
	 try { 
	 JarFile jarFile = new JarFile(new File("game/"+filename));  
	  URL url = new URL("file:" +"game/"+ filename);  
	ClassLoader loader = new URLClassLoader(new URL[]{url});//自己定义的classLoader类，把外部路径也加到load路径里，使系统去该路经load对象   
	  Enumeration<JarEntry> es = jarFile.entries();  
	  while (es.hasMoreElements()) {  
	   JarEntry jarEntry = (JarEntry) es.nextElement();  
	   String name = jarEntry.getName();  
	   if(name != null && name.endsWith(".class")){//只解析了.class文件，没有解析里面的jar包  
	    //默认去系统已经定义的路径查找对象，针对外部jar包不能用  
	    //Class<?> c = Thread.currentThread().getContextClassLoader().loadClass(name.replace("/", ".").substring(0,name.length() - 6));  
	    Class<?> c = loader.loadClass(name.replace("/", ".").substring(0,name.length() - 6));//自己定义的loader路径可以找到  
	    System.out.println("加载文件："+c);  
	    classes.add(c);  
	    Annotation[] classAnnos = c.getDeclaredAnnotations();  
	    classAnnotationMap.put(c, classAnnos);  
	    Method[] classMethods = c.getDeclaredMethods();  
	    Map<Method, Annotation[]> methodAnnoMap = new HashMap<Method, Annotation[]>();  
	    for(int i = 0;i<classMethods.length;i++){  
	     Annotation[] a = classMethods[i].getDeclaredAnnotations();  
	     methodAnnoMap.put(classMethods[i], a);  
	    }  
	    classMethodAnnoMap.put(c, methodAnnoMap);  
	   }  
	  }  
	  System.out.println("加载类总数："+classes.size());  
	  
	  
	  Class<?> clazz = Class.forName(mainurl,true,loader);
		Object ojb = clazz.newInstance();
		Game game = (Game) ojb;
		System.out.println("已加载游戏：" + game.backname());
		Data.game.put(game.backname(), game);
	 } catch (IOException e) {  
	  e.printStackTrace();  
	 } catch (ClassNotFoundException e) {  
	  e.printStackTrace();  
	 }
	 
	
	
	
	/*File mod = new File("game");
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

	    }*/
	
	
	/* Method method = null;
	    // 获取方法的访问权限以便写回
	    boolean accessible =false; 
	    try {
	        method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
	        accessible=method.isAccessible();
	        method.setAccessible(true);
	        // 获取系统类加载器
	        URL[] urls = new URL[] {};
		KoroClassLoader classLoader = new KoroClassLoader(urls, ClassLoader.getSystemClassLoader());
	        URL url = file.toURI().toURL();
	        method.invoke(classLoader, url);
	        System.out.println("加载："+file.getName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        method.setAccessible(accessible);
	    }
	    Class<?> clazz = Class.forName("main.Main");
		Object ojb = clazz.newInstance();
		Game game = (Game) ojb;
		System.out.println("已加载：" + game.backname());
		Data.game.put(game.backname(), game);
	}*/
    }

    public class thread implements Runnable {
	public void run() {
	    Data.nowGame = Data.game.get(Data.gamename);
	    Data.gameshow = true;
	    Data.title=Data.nowGame.backname();
	    Data.mainPanel.setIcon(Data.nowGame.backIcon());
	    Data.nowGame.load();
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
