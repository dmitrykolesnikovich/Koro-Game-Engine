/**
 *<p>文件名:Rotation.java</p>
 * @author 16415
 *创建时间：2019年4月18日 下午12:48:18
 */
package indi.koro.koroGameEngine.animation;

import indi.koro.koroGameEngine.component.Component;

/**
 *项目名称：KoroGameEngine
 *类名称:Rotation
 *创建时间：2019年4月18日下午12:48:18
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class Rotation extends Animation {
protected int oldRotation=0;
protected int rotate=0;
protected int nowRotate=0;
/* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#render()
     */
    @Override
    protected void render() {
	// TODO 自动生成的方法存根
	nowRotate=(int)(((float)frame/(float)allFrame)*rotate);
	for (Component component : components) {
	component.setRotate(rotate);
	}
	super.render();
    }
    /**
     * @return rotation
     */
    public int getRotate() {
        return rotate;
    }
    /* （非 Javadoc）
         * @see indi.koro.koroGameEngine.animation.Animation#stop()
         */
        @Override
        public void stop() {
    	// TODO 自动生成的方法存根
    	super.stop();
    	for (Component component : components) {
	    component.setRotate(componentEvents.get(components.indexOf(component)).getRotate());
	}
        }
    /**
     * @param rotation 要设置的 rotation
     */
    public void setRotate(int rotate) {
        this.rotate = rotate;
    }
}
