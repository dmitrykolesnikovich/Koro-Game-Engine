/**
 *<p>文件名:Move.java</p>
 * @author 16415
 *创建时间：2019年4月10日 下午8:59:15
 */
package indi.koro.koroGameEngine.animation;
import java.util.ArrayList;

import indi.koro.koroGameEngine.component.*;
import indi.koro.koroGameEngine.listener.ComponentEvent;

/**
 *项目名称：KoroGameEngine
 *类名称:Move
 *创建时间：2019年4月10日下午8:59:15
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class Move extends Animation {
    protected int endX=0;
    protected int endY=0;
    public void setMove(int x,int y) {
	endX=x;
	endY=y;
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#render()
     */
    @Override
    protected void render() {
	// TODO 自动生成的方法存根
	go();
	super.render();
    }
    protected void go() {
	for (Component component : components) {
	    component.setBounds((int)(((float)endX-(float)componentEvents.get(components.indexOf(component)).getX())*easeBoth((float)frame/(float)allFrame)+(float)componentEvents.get(components.indexOf(component)).getAbsX()), (int)(((float)endY-(float)componentEvents.get(components.indexOf(component)).getY())*easeBoth((float)frame/(float)allFrame)+(float)componentEvents.get(components.indexOf(component)).getAbsY()),componentEvents.get(components.indexOf(component)).getWidth(),componentEvents.get(components.indexOf(component)).getHeight());}
    } 
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#start()
     */
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#add(indi.koro.koroGameEngine.component.Component[])
     */
    @Override
    public void add(Component... components) {
        // TODO 自动生成的方法存根
	for (Component component : components) {
	    this.components.add(component);
	}
    }
    @Override
    public void start() {
        // TODO 自动生成的方法存根
	componentEvents=new ArrayList<>();
	for (Component component : components) {
	    componentEvents.add(component.getComponentEvent());
	}
        super.start();
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#stop()
     */
    @Override
    public void stop() {
        // TODO 自动生成的方法存根
        super.stop();
        if (repeat) {
            for (Component component : components) {
        	ComponentEvent componentEvent=componentEvents.get(components.indexOf(component));
    	    	component.setLocation(componentEvent.getX(),componentEvent.getY());
    	}
	}
        
        
    }
}
