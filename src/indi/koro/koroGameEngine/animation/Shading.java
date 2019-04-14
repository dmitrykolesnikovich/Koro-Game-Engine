/**
 *<p>文件名:Shading.java</p>
 * @author 16415
 *创建时间：2019年4月11日 下午1:25:13
 */
package indi.koro.koroGameEngine.animation;

import indi.koro.koroGameEngine.component.Component;

/**
 *项目名称：KoroGameEngine
 *类名称:Shading
 *创建时间：2019年4月11日下午1:25:13
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class Shading extends Animation {
    protected boolean repeat=false;
    protected boolean fadein=true;
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#render()
     */
    @Override
    protected void render() {
        // TODO 自动生成的方法存根
	for (Component component : components) {
	    if (fadein) {
		component.setAlpha((float)frame/(float)allFrame);
	    }else {
		component.setAlpha(1f-(float)frame/(float)allFrame);
	    }
	}
        super.render();
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.animation.Animation#run()
     */
    @Override
    protected void run() {
        // TODO 自动生成的方法存根
        super.run();
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
    	    if (fadein) {
    		component.setAlpha(0);
    	    }else {
    		component.setAlpha(1f);
    	    }
    	}
	}else {
	    for (Component component : components) {
		    if (fadein) {
			component.setAlpha(1f);
		    }else {
			component.setAlpha(0);
		    }
		}
	}
    }
    /**
     * @return fadein
     */
    public boolean isFadein() {
        return fadein;
    }
    /**
     * @param fadein 要设置的 fadein
     */
    public void setFadein(boolean fadein) {
        this.fadein = fadein;
    }
}
