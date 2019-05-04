/**
 *<p>文件名:Panel.java</p>
 * @author 16415
 *创建时间：2019年4月10日 下午9:36:01
 */
package indi.koro.koroGameEngine.component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *项目名称：KoroGameEngine
 *类名称:Panel
 *创建时间：2019年4月10日下午9:36:01
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class Panel extends Component {
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.component.Component#printComponent(java.awt.Graphics2D)
     */
    BufferedImage background =null;
    
    @Override
    protected void printComponent(Graphics2D g) {
        // TODO 自动生成的方法存根
        //super.printComponent(g);
	g.setColor(getBackgroundColor());
	if (background!=null) {
	    g.drawImage(background, null, absX, absY);
	}else {
	    if(opaque) {
	        g.fillRect(absX, absY, width, height);
		}
	}
	
    }

    /**
     * @return background
     */
    public BufferedImage getBackground() {
        return background;
    }

    /**
     * @param background 要设置的 background
     */
    public void setBackground(BufferedImage background) {
        this.background = background;
    }
}
