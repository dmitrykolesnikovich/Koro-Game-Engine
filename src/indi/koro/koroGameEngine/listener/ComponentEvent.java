/**
 *<p>文件名:ComponentEvent.java</p>
 * @author 16415
 *创建时间：2019年4月10日 上午10:26:01
 */
package indi.koro.koroGameEngine.listener;

import indi.koro.koroGameEngine.component.Component;

/**
 *项目名称：KoroGameEngine
 *类名称:ComponentEvent
 *创建时间：2019年4月10日上午10:26:01
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class ComponentEvent {
    private int x;
    private int y;
    private int absX;
    private int absY;
    private int width;
    private int height;
    private Component component;
    /**
     * 
     */
    public ComponentEvent(int x,int y,int absX,int absY,int width,int height,Component component) {
	// TODO 自动生成的构造函数存根
	this.absX=absX;
	this.absY=absY;
	this.x=x;
	this.y=y;
	this.width=width;
	this.height=height;
	this.component=component;
    }
    /**
     * @return x
     */
    public int getX() {
        return x;
    }
    /**
     * @return y
     */
    public int getY() {
        return y;
    }
    /**
     * @return absX
     */
    public int getAbsX() {
        return absX;
    }
    /**
     * @return absY
     */
    public int getAbsY() {
        return absY;
    }
    /**
     * @return width
     */
    public int getWidth() {
        return width;
    }
    /**
     * @return height
     */
    public int getHeight() {
        return height;
    }
    /**
     * @return component
     */
    public Component getComponent() {
        return component;
    }
}
