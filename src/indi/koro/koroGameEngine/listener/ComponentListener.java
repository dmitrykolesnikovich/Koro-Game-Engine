/**
 *<p>文件名:ComponentListener.java</p>
 * @author 16415
 *创建时间：2019年4月10日 上午10:25:28
 */
package indi.koro.koroGameEngine.listener;

/**
 *项目名称：KoroGameEngine
 *类名称:ComponentListener
 *创建时间：2019年4月10日上午10:25:28
 *<p>类描述:KoroComponent控件变化监听器</p>
 * @author  16415
 * @version 1.0
 */
public interface ComponentListener {
    public void reSize(ComponentEvent componentEvent);
    public void move(ComponentEvent componentEvent);
    public void visible(ComponentEvent componentEvent);
    }
