/**
 *<p>文件名:Circuit.java</p>
 * @author 16415
 *创建时间：2019年4月11日 下午2:03:23
 */
package indi.koro.koroGameEngine.animation;

import java.util.ArrayList;

import indi.koro.koroGameEngine.component.Component;
import indi.koro.koroGameEngine.listener.AnimationListener;

/**
 *项目名称：KoroGameEngine
 *类名称:Circuit
 *创建时间：2019年4月11日下午2:03:23
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class Circuit extends Animation {
    protected boolean isloop=true;
    protected int loop=0;
    protected ArrayList<Animation> animations=new ArrayList<>();
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#run()
     */
    @Override
    protected void run() {
        // TODO 自动生成的方法存根
	if(isloop) {
	    while(true) {
		for (Animation animation : animations) {
		    animation.run();
		    animation.frame=0;
		}
	    }
	}else {
	    for (int i=0;i<loop;i++) {
		for (Animation animation : animations) {
		    animation.run();
		}
	    }
	}
	for (AnimationListener animationListener : animationListeners) {
	    animationListener.finish();
	}
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#stop()
     */
    public void loop(boolean loop) {
	isloop=loop;
    }
    public void loop(int loop) {
	isloop=false;
	this.loop=loop;
    }
    @Override
    public void stop() {
        // TODO 自动生成的方法存根
	for (Animation animation: animations) {
            animation.stop();
        }
        super.stop();
        
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
    public void remove(Animation...animations ) {
        for (Animation animation : animations) {
            this.animations.remove(animation);
        }
    }
    public void removeAll() {
        animations.removeAll(animations);
    }
}
