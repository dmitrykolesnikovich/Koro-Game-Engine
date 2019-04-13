/**
 *<p>文件名:CustomSceneMode.java</p>
 * @author 16415
 *创建时间：2019年4月9日 下午1:49:17
 */
package indi.koro.koroGameEngine.component.scene;

import java.awt.Graphics2D;

/**
 *项目名称：KoroGameEngine
 *类名称:CustomSceneMode
 *创建时间：2019年4月9日下午1:49:17
 *<p>类描述:自定义场景绘图。</p>
 * @author  16415
 * @version 1.0
 */
public interface CustomSceneMode {
    public void customDraw(Graphics2D g,int time);
}
