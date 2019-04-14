
package indi.koro.koroGameEngine.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;

import indi.koro.koroGameEngine.listener.MouseListener;

/**
 *<p>项目名称：KoroGameEngine</p>
 *<p>类名称:Component</p>
 *创建时间：2019年4月5日上午1:41:35
 *类描述:KoroGameEngine基础组件。
 * @author  16415
 * @version 1.0
 * @since
 */
public class Component implements MouseListener, MouseWheelListener, KeyListener, ComponentListener,indi.koro.koroGameEngine.listener.ComponentListener {
    
    protected ArrayList<MouseListener> mouseListeners = new ArrayList<>();
    protected ArrayList<KeyListener> keyListeners = new ArrayList<>();
    protected ArrayList<ComponentListener> componentListeners = new ArrayList<>();
    protected ArrayList<Component> components = new ArrayList<>();
    protected ArrayList<MouseWheelListener> mouseWheelListeners = new ArrayList<>();
    protected ArrayList<ComponentAdapter>componentAdapters=new ArrayList<>();
    protected ArrayList< indi.koro.koroGameEngine.listener.ComponentListener> koroComponentListeners=new ArrayList<>();
    protected int x = 0;
    protected int y = 0;
    protected int width = 10;
    protected int height = 10;
    protected int absX = 0;
    protected int absY = 0;
    protected float alpha=1f;
    protected ArrayList<Print> prints=new ArrayList<>();
    protected boolean visible=true;
    protected Color backgroundColor=Color.white;
    protected boolean opaque=true;
    protected float rotate=0;
    protected boolean mouseIn=false;
    
    /**
     * @return visible
     */
    public boolean isVisible() {
        return visible;
    }
    public void setRotate(int rotate) {
	//this.rotate=rotate;
	this.rotate= (float) (Math.PI * rotate / 180);
    }

    /**
     * @param visible 要设置的 visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
        visible(new indi.koro.koroGameEngine.listener.ComponentEvent(x, y, absX, absY, width, height, this));
	for(indi.koro.koroGameEngine.listener.ComponentListener listener: koroComponentListeners) {
	    listener.visible(new indi.koro.koroGameEngine.listener.ComponentEvent(x, y, absX, absY, width, height, this));
	}
    }
    
    public void add(Print...prints) {
	for (Print print : prints) {
	    this.prints.add(print);
	}
    }
    public void remove(Print...prints) {
	for (Print print : prints) {
	    this.prints.remove(print);
	}
    }
    public void removeAllPrint() {
	prints.removeAll(prints);
    }
    
    public indi.koro.koroGameEngine.listener.ComponentEvent getComponentEvent(){
	return new indi.koro.koroGameEngine.listener.ComponentEvent(x, y, absX, absY, width, height, this);
    }
    
    public void addComponentListener(indi.koro.koroGameEngine.listener.ComponentListener componentListener) {
	koroComponentListeners.add( componentListener);
    }
    
    public void removeComponentListener(indi.koro.koroGameEngine.listener.ComponentListener componentListener) {
	koroComponentListeners.remove(componentListener);
    }
    
    public void removeAllComponentListener() {
	koroComponentListeners.removeAll(koroComponentListeners);
    }
    
    /**
     * @return mouseListeners
     */
    public ArrayList<MouseListener> getMouseListeners() {
        return mouseListeners;
    }

    /**
     * @return keyListeners
     */
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }

    /**
     * @return componentListeners
     */
    public ArrayList<ComponentListener> getComponentListeners() {
        return componentListeners;
    }

    /**
     * @return components
     */
    public ArrayList<Component> getComponents() {
        return components;
    }

    /**
     * @return mouseWheelListeners
     */
    public ArrayList<MouseWheelListener> getMouseWheelListeners() {
        return mouseWheelListeners;
    }

    /**
     * @return componentAdapters
     */
    public ArrayList<ComponentAdapter> getComponentAdapters() {
        return componentAdapters;
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
     * @return absx
     */
    public int getAbsX() {
        return absX;
    }

    /**
     * @return absy
     */
    public int getAbsY() {
        return absY;
    }
    
    public void setsize(int w,int h) {
	this.width=w;
	this.height=h;
	for(indi.koro.koroGameEngine.listener.ComponentListener listener: koroComponentListeners) {
	    listener.reSize(new indi.koro.koroGameEngine.listener.ComponentEvent(x, y, absX, absY, w, h, this));
	}
    }
    
    public void setLocation(int x,int y) {
	absX=absX+(x-this.x);
	this.x=x;
	absY=absY+(y-this.y);
	this.y=y;
	for (Component component : components) {
	    component.setabsLocation(absX, absY);
	}
	move(new indi.koro.koroGameEngine.listener.ComponentEvent(x, y, absX, absY, width, height, this));
	for(indi.koro.koroGameEngine.listener.ComponentListener listener: koroComponentListeners) {
	    listener.move(new indi.koro.koroGameEngine.listener.ComponentEvent(x, y, absX, absY, width, height, this));
	}
    }
    
    protected void setabsLocation(int absx,int absy) {
	this.absX=x+absx;
	this.absY=y+absy;
	for (Component component : components) {
	    component.setabsLocation(this.absX, this.absY);
	}
	move(new indi.koro.koroGameEngine.listener.ComponentEvent(x, y, absX, absY, width, height, this));
	for(indi.koro.koroGameEngine.listener.ComponentListener listener: koroComponentListeners) {
	    listener.move(new indi.koro.koroGameEngine.listener.ComponentEvent(x, y, absX, absY, width, height, this));
	}
    }
    
    public void setBounds(int x, int y, int width,int height) {
	this.width=width;
	this.height=height;
	absX=absX+(x-this.x);
	this.x=x;
	absY=absY+(y-this.y);
	this.y=y;
	for (Component component : components) {
	    component.setabsLocation(absX, absY);
	}
	indi.koro.koroGameEngine.listener.ComponentEvent event=new indi.koro.koroGameEngine.listener.ComponentEvent(x, y, absX, absY, width, height, this);
	move(event);
	reSize(event);
	for(indi.koro.koroGameEngine.listener.ComponentListener listener: koroComponentListeners) {
	    listener.move(event);
	    listener.reSize(event);
	}
    }
    
    /**
     * 
     * <p>方法名：printChildren</p>
     *创建时间：2019年4月5日 上午1:46:59
     *方法描述：绘制子类，覆盖可以控制子类的绘制（不建议）。
     *@author 16415
     *@return void
     *@param g
     */
    protected void printChildren(Graphics2D g) {
	for (Component component : components) {
	    component.print(g);
	}
    }

    /**
     * 
     * <p>方法名：printComponent</p>
     *创建时间：2019年4月5日 上午1:46:50
     *方法描述：绘制组件本身，覆盖可以自定义组件绘制。
     *@author 16415
     *@return void
     *@param g
     */
    protected void printComponent(Graphics2D g) {
	for (Print print : prints) {
	    print.print(g);
	}
    }

    /**
     * 
     * <p>方法名：print</p>
     *创建时间：2019年4月5日 上午1:46:33
     *方法描述：主绘制函数,覆盖可以自定义绘制。
     *@author 16415
     *@return void
     *@param g
     */
    public void print(Graphics2D g) {
	
	if(visible) {
	int x=absX+width/2,y=absY+height/2;
	float rotate=(float)this.rotate;
	g.rotate(rotate, x, y);
	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
	printComponent(g);
	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1f));
	printChildren(g);
	g.rotate(-rotate, x, y);
	}
	
    }
    
    /**
     *方法名：addComponentAdapter
     *创建时间：2019年4月7日 上午11:19:50
     *<p>方法描述：添加ComponentAdapter监听器。 </p>
     *@author 16415
     *@return void
     *@param listeners
     */
    public void addComponentAdapter(ComponentAdapter listeners) {
	componentAdapters.add(listeners);
    }

    /**
     * 
     * <p>方法名：addMouseWhellListeners</p>
     *创建时间：2019年4月5日 上午1:45:21
     *方法描述：添加所有MouseWhellListener监听器（AWT）。
     *@author 16415
     *@return void
     *@param listeners
     */
    public void addMouseWhellListeners(MouseWheelListener... listeners) {
	for (MouseWheelListener mouseWheelListener : listeners) {
	    mouseWheelListeners.add(mouseWheelListener);
	}
    }
    /**
     * 
     * <p>方法名：addMouseWhellListener</p>
     *创建时间：2019年4月5日 上午1:48:18
     *方法描述：添加MouseWhellListener监听器（AWT）。
     *@author 16415
     *@return void
     *@param listener
     */
    public void addMouseWhellListener(MouseWheelListener listener) {
	mouseWheelListeners.add(listener);
    }
    /**
     * 
     * <p>方法名：addComponentListener</p>
     *创建时间：2019年4月5日 上午1:48:32
     *方法描述：添加ComponentListener监听器（AWT）。
     *@author 16415
     *@return void
     *@param listener
     */
    public void addComponentListener(ComponentListener listener) {
	componentListeners.add(listener);
    }
    /**
     * 
     * <p>方法名：add</p>
     *创建时间：2019年4月5日 上午1:48:59
     *方法描述：添加KoroGameEngine组件。
     *@author 16415
     *@return void
     *@param comp
     */
    public void add(Component comp) {
	components.add(comp);
	comp.setabsLocation(absX, absY);
    }
    
    public void add(Component...components) {
	for (Component component : components) {
	    this.components.add(component);
	    component.setabsLocation(absX, absY);
	}
    }
    
    /**
     *方法名：addComponentAdapters
     *创建时间：2019年4月7日 上午11:21:10
     *<p>方法描述：添加所有ComponentAdapter监听器。</p>
     *@author 16415
     *@return void
     *@param listeners
     */
    public void addComponentAdapters(ComponentAdapter...listeners) {
	for (ComponentAdapter componentAdapter : listeners) {
	    componentAdapters.add(componentAdapter);
	}
    }
    
    /**
     * 
     * <p>方法名：addComponentListeners</p>
     *创建时间：2019年4月5日 上午1:50:18
     *方法描述：添加ComponentListeners监听器（AWT）。
     *@author 16415
     *@return void
     *@param listeners
     */
    public void addComponentListeners(ComponentListener... listeners) {
	for (ComponentListener listener : listeners) {
	    componentListeners.add(listener);
	}
    }
    /**
     * 
     * <p>方法名：addMouseListeners</p>
     *创建时间：2019年4月5日 上午1:50:51
     *方法描述：添加所有ComponentListeners监听器（AWT）。
     *@author 16415
     *@return void
     *@param listeners
     */
    public void addMouseListeners(MouseListener... listeners) {
	for (MouseListener mouseListener : listeners) {
	    mouseListeners.add(mouseListener);
	}
    }
    /**
     * 
     * <p>方法名：addKeyListeners</p>
     *创建时间：2019年4月5日 上午1:52:13
     *方法描述：添加所有KeyListenersListeners监听器（AWT）。
     *@author 16415
     *@return void
     *@param keyListeners
     */
    public void addKeyListeners(KeyListener... keyListeners) {
	for (KeyListener keyListener : keyListeners) {
	    this.keyListeners.add(keyListener);
	}
    }
    /**
     * 
     * <p>方法名：addMouseListener</p>
     *创建时间：2019年4月5日 上午1:53:05
     *方法描述：添加MouseListeners监听器。
     *@author 16415
     *@return void
     *@param listener
     */
    public void addMouseListener(MouseListener listener) {
	mouseListeners.add(listener);
    }
    /**
     * 
     * <p>方法名：addKeyListener</p>
     *创建时间：2019年4月5日 上午1:53:29
     *方法描述：添加KeyListeners监听器（AWT）。
     *@author 16415
     *@return void
     *@param listener
     */
    public void addKeyListener(KeyListener listener) {
	keyListeners.add(listener);
    }
    
    /**
     *方法名：removeComponentAdapter
     *创建时间：2019年4月7日 上午11:29:39
     *<p>方法描述：移除指定ComponentAdapter监听器。</p>
     *@author 16415
     *@return void
     *@param listener
     */
    public void removeComponentAdapter(ComponentAdapter listener) {
	componentAdapters.remove(listener);
    }
    
    
    
    /**
     * 
     * <p>方法名：remove</p>
     *创建时间：2019年4月5日 上午1:57:05
     *方法描述：移除指定KoroGameEngine组件。
     *@author 16415
     *@return void
     *@param comp
     */
    public void remove(Component comp) {
        components.remove(comp);
    }

    /**
     * <p>方法名：removeMouseWhellListener</p>
     *创建时间：2019年4月5日 上午1:43:43
     *方法描述：移除指定MouseWhellListener监听器（AWT）。
     *@author 16415
     *@return void
     *@param listeners
     */
    public void removeMouseWhellListener(MouseWheelListener listener) {
        mouseWheelListeners.remove(listener);
    }
    /**
     * 
     * <p>方法名：removeComponentListener</p>
     *创建时间：2019年4月5日 上午1:54:38
     *方法描述：移除指定ComponentListener监听器（AWT）。
     *@author 16415
     *@return void
     *@param listener
     */
    public void removeComponentListener(ComponentListener listener) {
	componentListeners.remove(listener);
    }
    /**
     * 
     * <p>方法名：removeMouseListener</p>
     *创建时间：2019年4月5日 上午1:55:04
     *方法描述：移除指定MouseListener监听器。
     *@author 16415
     *@return void
     *@param listener
     */
    public void removeMouseListener(MouseListener listener) {
	mouseListeners.remove(listener);
    }
    /**
     * 
     * <p>方法名：removeKeyListener</p>
     *创建时间：2019年4月5日 上午1:55:53
     *方法描述：移除指定KeyListener监听器（AWT）。
     *@author 16415
     *@return void
     *@param listener
     */
    public void removeKeyListener(KeyListener listener) {
	keyListeners.remove(listener);
    }
    /**
     * 
     * <p>方法名：removeAllMouseWhellListener</p>
     *创建时间：2019年4月5日 上午1:46:10
     *方法描述：移除所有MouseWhellListener监听器（AWT）。
     *@author 16415
     *@return void
     */
    public void removeAllMouseWhellListener() {
        mouseWheelListeners.removeAll(mouseWheelListeners);
    }

    /**
     * 
     * <p>方法名：removeAllMouseListener</p>
     *创建时间：2019年4月5日 上午1:56:32
     *方法描述：移除所有MouseListener监听器。
     *@author 16415
     *@return void
     */
    public void removeAllMouseListener() {
	mouseListeners.removeAll(mouseListeners);
    }
    /**
     * 
     * <p>方法名：removeAllComponentListeners</p>
     *创建时间：2019年4月5日 上午1:57:47
     *方法描述：移除所有ComponentListener监听器（AWT）。
     *@author 16415
     *@return void
     */
    public void removeAllComponentListeners() {
	componentListeners.removeAll(componentListeners);
    }
    /**
     * 
     * <p>方法名：removeAllKeyListener</p>
     *创建时间：2019年4月5日 上午1:57:58
     *方法描述：移除所有KeyListener监听器（AWT）。
     *@author 16415
     *@return void
     */
    public void removeAllKeyListener() {
	keyListeners.removeAll(keyListeners);
    }
    
    /**
     * 
     * <p>方法名：removeAll</p>
     *创建时间：2019年4月5日 上午2:00:01
     *方法描述：移除所有KoroGameEngine组件。
     *@author 16415
     *@return void
     */
    public void removeAll() {
	components.removeAll(components);
    }
    
    
    /**
     *方法名：removeAllComponentAdapters
     *创建时间：2019年4月7日 上午11:32:11
     *<p>方法描述：移除所有ComponentAdapter监听器。</p>
     *@author 16415
     *@return void
     */
    public void removeAllComponentAdapters() {
	componentAdapters.removeAll(componentAdapters);
    }

    /*
     * （非 Javadoc）
     * 
     * @see indi.koro.koroGameEngine.listener.MouseListener#mouseReleased(int, int,
     * java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(int x, int y, MouseEvent e) {
	// TODO 自动生成的方法存根
	for (MouseListener listener : mouseListeners) {
	    listener.mouseReleased(x, y, e);
	}
	for (int i=components.size();i>0;i--) {
	    Component component=components.get(i-1);
		if(component.absX>=x|component.absY>=y|component.width+component.absX<=x|component.height+component.absY<=y) {
		    component.mouseReleased(x-component.x, y-component.y, e);
		    return;
		}
	    }
    }

    /*
     * （非 Javadoc）
     * 
     * @see indi.koro.koroGameEngine.listener.MouseListener#mousePressed(int, int,
     * java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(int x, int y, MouseEvent e) {
	// TODO 自动生成的方法存根
	for (MouseListener listener : mouseListeners) {
	    listener.mousePressed(x, y, e);
	}
	for (int i=components.size();i>0;i--) {
	    Component component=components.get(i-1);
		if(component.absX>=x|component.absY>=y|component.width+component.absX<=x|component.height+component.absY<=y) {
		    component.mousePressed(x-component.x, y-component.y, e);
		    return ;
		}
	    }

    }

    /*
     * （非 Javadoc）
     * 
     * @see indi.koro.koroGameEngine.listener.MouseListener#mouseExited(int, int,
     * java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(int x, int y, MouseEvent e) {
	mouseIn=false;
	// TODO 自动生成的方法存根
	for (MouseListener listener : mouseListeners) {
	    listener.mouseExited(x, y, e);
	}
	for (int i=components.size();i>0;i--) {
	    Component component=components.get(i-1);
	    if(component.mouseIn) {
		if(component.absX>=x|component.absY>=y|component.width+component.absX<=x|component.height+component.absY<=y) {
		    component.mouseExited(x-component.x, y-component.y, e);
		    return;
		}
	    }
	}

    }

    /*
     * （非 Javadoc）
     * 
     * @see indi.koro.koroGameEngine.listener.MouseListener#mouseEntered(int, int,
     * java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(int x, int y, MouseEvent e) {
	mouseIn=true;
	// TODO 自动生成的方法存根
	for (MouseListener listener : mouseListeners) {
	    listener.mouseEntered(x, y, e);
	}
	for (int i=components.size();i>0;i--) {
	    Component component=components.get(i-1);
	    	if(!component.mouseIn) {
		if(component.absX>=x|component.absY>=y|component.width+component.absX<=x|component.height+component.absY<=y) {
		    component.mouseEntered(x-component.x, y-component.y, e);
		    return;
		}
	    	}
	    }

    }

    /*
     * （非 Javadoc）
     * 
     * @see indi.koro.koroGameEngine.listener.MouseListener#mouseClicked(int, int,
     * java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(int x, int y, MouseEvent e) {
	// TODO 自动生成的方法存根
	for (MouseListener listener : mouseListeners) {
	    listener.mouseClicked(x, y, e);
	}
	for (int i=components.size();i>0;i--) {
	    Component component=components.get(i-1);
		if(component.absX>=x|component.absY>=y|component.width+component.absX<=x|component.height+component.absY<=y) {
		    component.mouseClicked(x-component.x, y-component.y, e);
		    return;
		}
	    }

    }

    /*
     * （非 Javadoc）
     * 
     * @see indi.koro.koroGameEngine.listener.MouseListener#mouseMoved(int, int,
     * java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(int x, int y, MouseEvent e) {
	// TODO 自动生成的方法存根
	for (MouseListener listener : mouseListeners) {
	    listener.mouseMoved(x, y, e);
	}
	for (int i=components.size();i>0;i--) {
	    Component component=components.get(i-1);
		if(component.absX>=x|component.absY>=y|component.width+component.absX<=x|component.height+component.absY<=y) {
		    component.mouseMoved(x-component.x, y-component.y, e);
		    return;
		}
	    }

    }

    /*
     * （非 Javadoc）
     * 
     * @see indi.koro.koroGameEngine.listener.MouseListener#mouseDragged(int, int,
     * java.awt.event.MouseEvent)
     */
    @Override
    public void mouseDragged(int x, int y, MouseEvent e) {
	// TODO 自动生成的方法存根
	for (MouseListener listener : mouseListeners) {
	    listener.mouseDragged(x, y, e);
	}
	for (int i=components.size();i>0;i--) {
	    Component component=components.get(i-1);
		if(component.absX>=x|component.absY>=y|component.width+component.absX<=x|component.height+component.absY<=y) {
		    component.mouseDragged(x-component.x, y-component.y, e);
		    return;
		}
	    }

    }

    /*
     * （非 Javadoc）
     * 
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentHidden(ComponentEvent e) {
	// TODO 自动生成的方法存根
	for (ComponentListener componentListener : componentListeners) {
	    componentListener.componentHidden(e);
	}
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentMoved(ComponentEvent e) {
	// TODO 自动生成的方法存根
	for (ComponentListener componentListener : componentListeners) {
	    componentListener.componentMoved(e);
	}
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.awt.event.ComponentListener#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentResized(ComponentEvent e) {
	// TODO 自动生成的方法存根
	for (ComponentListener componentListener : componentListeners) {
	    componentListener.componentResized(e);
	}
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentShown(ComponentEvent e) {
	// TODO 自动生成的方法存根
	for (ComponentListener componentListener : componentListeners) {
	    componentListener.componentShown(e);
	}
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent e) {
	// TODO 自动生成的方法存根
	for (KeyListener keyListener : keyListeners) {
	    keyListener.keyTyped(e);
	}
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
	// TODO 自动生成的方法存根
	for (KeyListener keyListener : keyListeners) {
	    keyListener.keyPressed(e);
	}
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
	// TODO 自动生成的方法存根
	for (KeyListener keyListener : keyListeners) {
	    keyListener.keyReleased(e);
	}
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.
     * MouseWheelEvent)
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
	// TODO 自动生成的方法存根
	for (MouseWheelListener mouseWheelListener : mouseWheelListeners) {
	    mouseWheelListener.mouseWheelMoved(e);
	}
    }

    /**
     * @return koroComponentListeners
     */
    public ArrayList<indi.koro.koroGameEngine.listener.ComponentListener> getKoroComponentListeners() {
        return koroComponentListeners;
    }

    /**
     * @return alpha
     */
    public float getAlpha() {
        return alpha;
    }

    /**
     * @param alpha 要设置的 alpha
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    /**
     * @return backgroundColor
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @param backgroundColor 要设置的 backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @return opaque
     */
    public boolean isOpaque() {
        return opaque;
    }

    /**
     * @param opaque 要设置的 opaque
     */
    public void setOpaque(boolean opaque) {
        this.opaque = opaque;
    }
    /**
     * @return mouseIn
     */
    public boolean isMouseIn() {
        return mouseIn;
    }
    /**
     * @param mouseIn 要设置的 mouseIn
     */
    public void setMouseIn(boolean mouseIn) {
        this.mouseIn = mouseIn;
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.listener.ComponentListener#reSize(indi.koro.koroGameEngine.listener.ComponentEvent)
     */
    @Override
    public void reSize(indi.koro.koroGameEngine.listener.ComponentEvent componentEvent) {
	// TODO 自动生成的方法存根
	
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.listener.ComponentListener#move(indi.koro.koroGameEngine.listener.ComponentEvent)
     */
    @Override
    public void move(indi.koro.koroGameEngine.listener.ComponentEvent componentEvent) {
	// TODO 自动生成的方法存根
	
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.listener.ComponentListener#visible(indi.koro.koroGameEngine.listener.ComponentEvent)
     */
    @Override
    public void visible(indi.koro.koroGameEngine.listener.ComponentEvent componentEvent) {
	// TODO 自动生成的方法存根
	
    }
}
