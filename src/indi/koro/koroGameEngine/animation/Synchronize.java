/**
 *<p>文件名:Synchronize.java</p>
 * @author 16415
 *创建时间：2019年4月11日 下午1:49:14
 */
package indi.koro.koroGameEngine.animation;

import java.util.ArrayList;

import indi.koro.koroGameEngine.component.Component;

/**
 *项目名称：KoroGameEngine
 *类名称:Synchronize
 *创建时间：2019年4月11日下午1:49:14
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class Synchronize extends Animation{
    protected ArrayList<Animation> animations=new ArrayList<>();
    /**
     * 
     */
    public Synchronize() {
	// TODO 自动生成的构造函数存根
	thread=null;
    }
    public void add(Animation...animations) {
	for (Animation animation : animations) {
	    this.animations.add(animation);
	}
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#remove(indi.koro.koroGameEngine.component.Component[])
     */
    @Override
    public void remove(Component... components) {
        // TODO 自动生成的方法存根
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#add(indi.koro.koroGameEngine.component.Component[])
     */
    @Override
    public void add(Component... components) {
        // TODO 自动生成的方法存根
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#start()
     */
    @Override
    public void start() {
        // TODO 自动生成的方法存根
	for (Animation animation : animations) {
	    animation.start();
	}
    }
    public void remove(Animation...animations ) {
	for (Animation animation : animations) {
	    this.animations.remove(animation);
	}
    }
    public void removeAll() {
	animations.removeAll(animations);
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#stop()
     */
    @Override
    public void stop() {
        // TODO 自动生成的方法存根
        for (Animation animation: animations) {
	    animation.stop();
	}
    }
}
