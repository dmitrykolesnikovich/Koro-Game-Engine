/**
 *<p>文件名:Waiting.java</p>
 * @author 16415
 *创建时间：2019年4月14日 下午12:07:05
 */
package indi.koro.koroGameEngine.animation;

import java.util.ArrayList;

import indi.koro.koroGameEngine.component.Component;
import indi.koro.koroGameEngine.listener.AnimationListener;

/**
 *项目名称：KoroGameEngine
 *类名称:Waiting
 *创建时间：2019年4月14日下午12:07:05
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class Waiting extends Animation {
    protected int waittime;
    protected ArrayList<Animation> animations=new ArrayList<>();
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#run()
     */
    @Override
    protected void run() {
        // TODO 自动生成的方法存根
        try {
	    Thread.sleep(waittime);
	} catch (InterruptedException e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	}
        for (Animation animation : animations) {
	    animation.start();
	}
        for (AnimationListener animationListener : animationListeners) {
	    animationListener.finish();
	}
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
}
