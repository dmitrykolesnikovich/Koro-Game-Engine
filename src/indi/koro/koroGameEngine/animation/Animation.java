/**
 *<p>文件名:Animation.java</p>
 * @author 16415
 *创建时间：2019年4月10日 下午12:55:53
 */
package indi.koro.koroGameEngine.animation;

import java.util.ArrayList;


import indi.koro.koroGameEngine.component.Component;
import indi.koro.koroGameEngine.listener.ComponentEvent;

/**
 *项目名称：KoroGameEngine
 *类名称:Animation
 *创建时间：2019年4月10日下午12:55:53
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class Animation {
    protected Animation animation=this;
    protected Thread thread=null;
    protected int time=1000;
    protected int allFrame=60;
    protected int frame=0;
    protected boolean repeat=false;
    ArrayList<Component>components=new ArrayList<>();
    ArrayList<ComponentEvent> componentEvents=new ArrayList<>();
    public Animation() {
	// TODO 自动生成的构造函数存根
	thread=new Thread(new ARunnable());
    }
    protected void run() {
	while (frame<allFrame) {
	    render();
	    frame++;
	}
        if(repeat) {
        while (frame>0) {
	    render();
	    frame--;
	}}
    }
    /**
     * @return repeat
     */
    public boolean isRepeat() {
        return repeat;
    }
    /**
     * @param repeat 要设置的 repeat
     */
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
    public void stop() {
	thread.stop();
    }
    public static float easeOut(float x) {
	return 1-(1-x)*(1-x);
    }
    public static float easeIn(float x) {
	return x*x;
    }
    public static float easeBoth(float x) {
	return x>0.5? 1-2*(1-x)*(1-x) :2*x*x ;
    }
    protected void render() {
	try {
	    Thread.sleep(16);
	} catch (InterruptedException e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	}
    }
    public void start() {
	allFrame=(int)((float)(time)/16.666666667f);
	thread.start();
    }
    protected class ARunnable implements Runnable{

	/* （非 Javadoc）
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	    // TODO 自动生成的方法存根
	    animation.run();
	}
	
    }
    
    public void add(Component... components) {
	for (Component component : components) {
	    componentEvents.add(component.getComponentEvent());
	    this.components.add(component);
	}
    }
    
    public void remove(Component...components) {
	for (Component component : components) {
	    componentEvents.remove(this.components.indexOf(component));
	    this.components.remove(component);
	}
    }
    public void removeAll() {
	components.removeAll(components);
	componentEvents.removeAll(componentEvents);
    }
    
    /**
     * @return thread
     */
    public Thread getThread() {
        return thread;
    }
    /**
     * @return time
     */
    public int getTime() {
        return time;
    }
    /**
     * @param time 要设置的 time
     */
    public void setTime(int time) {
        this.time = time;
    }
    /**
     * @return allFrame
     */
    public int getAllFrame() {
        return allFrame;
    }
    /**
     * @return frame
     */
    public int getFrame() {
        return frame;
    }
    /**
     * @return components
     */
    public ArrayList<Component> getComponents() {
        return components;
    }
}
