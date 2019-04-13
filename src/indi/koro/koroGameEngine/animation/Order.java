/**
 *<p>文件名:Order.java</p>
 * @author 16415
 *创建时间：2019年4月11日 下午1:58:29
 */
package indi.koro.koroGameEngine.animation;

import java.util.ArrayList;

import indi.koro.koroGameEngine.component.Component;

/**
 *项目名称：KoroGameEngine
 *类名称:Order
 *创建时间：2019年4月11日下午1:58:29
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class Order extends Animation {
    protected ArrayList<Animation> animations=new ArrayList<>();

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
     * @see indi.koro.koroGameEngine.animation.Animation#run()
     */
    @Override
    protected void run() {
        // TODO 自动生成的方法存根
        for (Animation animation : animations) {
	    animation.run();
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
	super.stop();
        for (Animation animation: animations) {
            animation.stop();
        }
    }
}
