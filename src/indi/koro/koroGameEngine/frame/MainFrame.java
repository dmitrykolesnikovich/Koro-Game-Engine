package indi.koro.koroGameEngine.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import indi.koro.koroGameEngine.component.Print;
import indi.koro.koroGameEngine.data.Data;

public class MainFrame extends JPanel {

    protected int fps = 0;
    private int jfps = 0;
    private int truefps=60;
    JFrame frame;
    float zoom = 1f;
    float gamex = 0;
    float gamey = 0;
    float zoomw = 1;
    float zoomh = 1;
    int w = 1920;
    int h = 1080;
    boolean showfps = false;
    boolean highdraw=false;
    Font font = new Font("宋体", Font.BOLD, 40);
    Thread mainrender = null;
    boolean gamestar = false;
    JPanel mainJpanel = this;
    String name = new String("");
    MainGraphics mainGraphics = null;
    private ArrayList<indi.koro.koroGameEngine.listener.MouseListener> mouselisteners = new ArrayList<indi.koro.koroGameEngine.listener.MouseListener>();
    private static Timer timer = new Timer(true);
    private ArrayList<Print> prints=new ArrayList<>();
    long fpsTime=0;
    
    /* （非 Javadoc）
     * @see javax.swing.JComponent#update(java.awt.Graphics)
     */
    @Override
    public void update(Graphics g) {
        // TODO 自动生成的方法存根
       // super.update(g);
	paint(g);
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
    /**
     *变量类型：ArrayList<indi.koro.koroGameEngine.component.Component>
      *创建时间：2019年4月5日 下午7:21:27
     *<p>变量描述：KoroGameEngine组件储存数组。</p>
     *@author 16415
     */
    protected ArrayList<indi.koro.koroGameEngine.component.Component> components=new ArrayList<>();
    
    
    /**
     * @return truefps
     */
    public int getTruefps() {
        return truefps;
    }

    /**
     * @return frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @return gamex
     */
    public float getGamex() {
        return gamex;
    }

    /**
     * @return gamey
     */
    public float getGamey() {
        return gamey;
    }

    /**
     * @return w
     */
    public int getW() {
        return w;
    }

    /**
     * @return h
     */
    public int getH() {
        return h;
    }

    /**
     * @return showfps
     */
    public boolean isShowfps() {
        return showfps;
    }

    /**
     * @return highdraw
     */
    public boolean isHighdraw() {
        return highdraw;
    }

    /**
     * @return gamestar
     */
    public boolean isGamestar() {
        return gamestar;
    }

    /**
     * @return mainJpanel
     */
    public JPanel getMainJpanel() {
        return mainJpanel;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return mainGraphics
     */
    public MainGraphics getMainGraphics() {
        return mainGraphics;
    }

    /**
     * @return mouselisteners
     */
    public ArrayList<indi.koro.koroGameEngine.listener.MouseListener> getMouselisteners() {
        return mouselisteners;
    }

    /**
     * @return components
     */
    public ArrayList<indi.koro.koroGameEngine.component.Component> getKoroComponents() {
        return components;
    }

    /**
     * @param highdraw 要设置的 highdraw
     */
    public void setHighdraw(boolean highdraw) {
        this.highdraw = highdraw;
    }
    
    
    /* （非 Javadoc）
     * @see java.awt.Container#add(java.awt.Component)
     */
    @Override
    public Component add(Component comp) {
        // TODO 自生成的方法存根
        return comp;
    }

    /**
     *方法名：add
     *创建时间：2019年4月5日 下午7:21:14
     *<p>方法描述：添加KoroGameEngine组件。</p>
     *@author 16415
     *@return void
     *@param components
     */
    public void add(indi.koro.koroGameEngine.component.Component... components) {
	for (indi.koro.koroGameEngine.component.Component component : components) {
	    this.components.add(component);
	}
    }
    
    /**
     *方法名：remove
     *创建时间：2019年4月5日 下午7:24:22
     *<p>方法描述：移除指定KoroGameEngine组件。</p>
     *@author 16415
     *@return void
     *@param components
     */
    public void remove(indi.koro.koroGameEngine.component.Component... components) {
	for (indi.koro.koroGameEngine.component.Component component : components) {
	    this.components.remove(component);
	}
    }
    
    /**
     *方法名：removeAll
     *创建时间：2019年4月5日 下午7:24:22
     *<p>方法描述：移除所有KoroGameEngine组件。</p>
     *@author 16415
     *@return void
     *@param void
     */
    @Override
    public void removeAll() {
	this.components.removeAll(components);
    }
    
    /**
     *方法名：removeAllswing
     *创建时间：2019年4月5日 下午7:28:10
     *<p>方法描述：移除所有javaswing组件。</p>
     *@author 16415
     *@return void
     */
    public void removeAllswing() {
	super.removeAll();
    }
    
    /** （非 Javadoc）
     * @see java.awt.Component#addMouseListener(java.awt.event.MouseListener)
     */
    public void addMouseListener(indi.koro.koroGameEngine.listener.MouseListener mouseListener) {
	this.mouselisteners.add(mouseListener);
    }

    /**
     *方法名：setfps
     *创建时间：2019年4月6日 上午8:11:12
     *<p>方法描述：设置fps（可动态设置）。</p>
     *@author 16415
     *@return void
     *@param fps
     */
    public  void setfps(int fps) {
	Data.fps = fps;
    }

    /**
     *方法名：backFrame
     *创建时间：2019年4月6日 上午8:11:51
     *<p>方法描述：返回JFrame窗口。</p>
     *@author 16415
     *@return JFrame
     *@return
     */
    public JFrame backFrame() {
	return frame;
    }
    
    public void setIcon(BufferedImage icon) {
	frame.setIconImage(icon);
    }

    private void startrender() {
	mainrender = new Thread(new Runnable() {

	    @Override
	    public void run() {
		// TODO 自动生成的方法存根
		fpsTime = (long) ((Double.valueOf(1000) / Double.valueOf(truefps)) * 1000000);
		// 绘制图像前的时间戳
		long now = 0;
		// 每次绘制图像耗时（毫秒）
		long total = 0;
		while (true) {
		    now = System.nanoTime();
		    // 绘制图像
		    truefps=Data.fps;
		    mainJpanel.repaint();
		    try {
			// 除去绘制之后还需要休眠的时间
			total = System.nanoTime() - now;
			if (total > fpsTime) {
			    // 如果本次绘制时间超过每帧需要绘制的时间，则直接继续绘制
			    continue;
			}
			Thread.sleep((fpsTime - (System.nanoTime() - now)) / 1000000);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		    while ((System.nanoTime() - now) < fpsTime) {
			// 使用循环，精确控制每帧绘制时长
			System.nanoTime();
		    }

		
		
		
		}
	    }
	});
	mainrender.start();
	TimerTask task = new TimerTask() {
	    @Override
	    public void run() {// TODO 1S后执行，没间隔1S 重复做的事情
		fps = jfps;
		jfps = 0;
		fpsTime = (long) ((Double.valueOf(1000) / Double.valueOf(truefps)) * 1000000);
		if (!frame.getTitle().equals(Data.title))
		    frame.setTitle(Data.title);
		if (Thread.activeCount() <= 1) {
		    System.exit(0);
		}
	    }
	};
	timer.schedule(task, 1000, 1000);
    }

    public void setname(String name) {
	this.name = name;
	Data.gamename = name;
    }

    public void show() {
	Data.show = true;
	gamestar = true;
	startrender();
	frame.setVisible(true);// 开启窗口
    }

    public void stopshow() {
	frame.setVisible(false);// 关闭窗口
	Data.show = false;
	gamestar = false;
    }

    @Override
    public void paint(Graphics g) {// 重写print
	//g.setColor(Color.white);
	//g.fillRect(0, 0, 100, 100);
	// TODO 自动生成的方法存根
	Graphics2D g2d = (Graphics2D) g;
	g2d.scale(Data.zoom,Data.zoom);
        g2d.setColor(Color.white);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	mainGraphics.render((Graphics2D)g);
	//g2d.drawImage(mainGraphics.backImage(), -1, -1, w+1, h+1, null);
	g2d.setColor(Color.black);
	g2d.setFont(font);
	g2d.drawString("内部版本", 0, 40);
	if (showfps) {
	    g2d.drawString("FPS:" + fps, 0, 80);
	}
	jfps++;
	

    }

    public void setitle(String title) {
	frame.setTitle(title);
    }

    public void showfps(boolean show) {
	this.showfps = show;
    }

    public MainFrame(int fw, int fh) {// 设置窗口
	w = fw;
	h = fh;
	frame = new JFrame();
	frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	frame.getContentPane().setBackground(Color.BLACK);
	frame.setLayout(null);
	frame.setBounds(0, 0, w, h);
	frame.setTitle(Data.title);
	this.setLayout(null);
	this.setBounds(0, 0, w, h);
	this.setBackground(Color.black);
	this.setVisible(true);
	frame.add(this);
	JPanel mainpanel = this;
	mainGraphics = new MainGraphics( this);
	Data.mainGraphics = mainGraphics;
	Data.mainPanel = this;
	Data.mainFrame = frame;
	frame.addComponentListener(new ComponentAdapter() {// 自动缩放
	    @Override
	    public void componentResized(ComponentEvent e) {
		// TODO 自动生成的方法存根
		super.componentResized(e);
		Container contentPane = frame.getContentPane();
		Dimension size = contentPane.getSize();
		zoomw = (float) size.width / (float) 1920;
		zoomh = (float) size.height / (float) 1080;
		if (zoomw >= zoomh) {
		    System.out.println("zoomw>=zoomh");
		    zoom = zoomh;
		    gamex = (int) ((((float) size.width) - (((float) 1920) * zoomh)) / 2);
		    gamey = 0;
		    System.out.println("gamex" + gamex);
		} else if (zoomw < zoomh) {
		    System.out.println("zoomw<zoomh");
		    zoom = zoomw;
		    gamex = 0;
		    gamey = (int) ((((float) size.height) - (((float) 1080) * zoomw)) / 2);
		    System.out.println("gamey" + gamey);
		}
		Data.zoom = zoom;
		System.out.println("zoom=" + zoom);
		System.out.println("ComponentResized: w=" + size.width + " h=" + size.height);
		System.out.println(
			"Bottom resized:w=" + ((int) ((float) 1920 * zoom)) + " h=" + ((int) ((float) 1080 * zoom)));
		w = ((int) ((float) fw * zoom));
		h = ((int) ((float) fh * zoom));
		Data.scenew = w;
		Data.sceneh = h;
		mainpanel.setBounds((int) gamex, (int) gamey, (int) ((float) 1920 * zoom), (int) ((float) 1080 * zoom));
		//frame.repaint();
		
	    }
	});
	for (indi.koro.koroGameEngine.component.Component component : components) {
	    this.addKeyListener(component);
	    this.addComponentListener(component);
	    this.addMouseWheelListener(component);
	}
	// ------------------------------------------------------------------------------------------------------
	this.addMouseListener(new MouseListener() {// 自动缩放鼠标事件

	    @Override
	    public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		int x = arg0.getX(), y = arg0.getY();
		x = (int) (x * Data.zoom);
		y = (int) (y * Data.zoom);
		for (indi.koro.koroGameEngine.listener.MouseListener mouseListener : mouselisteners)
		    mouseListener.mouseReleased(x, y, arg0);
		for (int i=components.size();i>0;i--) {
		    indi.koro.koroGameEngine.component.Component component=components.get(i-1);
			if(component.getAbsX()>=x|component.getAbsY()>=y|component.getWidth()+component.getAbsX()<=x|component.getHeight()+component.getAbsY()<=y) {
			    component.mouseReleased(x-component.getX(), y-component.getY(), arg0);
			    return;
			}
		    }
	    }

	    @Override
	    public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		int x = arg0.getX(), y = arg0.getY();
		x = (int) (x * Data.zoom);
		y = (int) (y * Data.zoom);
		for (indi.koro.koroGameEngine.listener.MouseListener mouseListener : mouselisteners)
		    mouseListener.mousePressed(x, y, arg0);
		for (int i=components.size();i>0;i--) {
		    indi.koro.koroGameEngine.component.Component component=components.get(i-1);
			if(component.getAbsX()>=x|component.getAbsY()>=y|component.getWidth()+component.getAbsX()<=x|component.getHeight()+component.getAbsY()<=y) {
			    component.mousePressed(x-component.getX(), y-component.getY(), arg0);
			    return;
			}
		    }
	    }

	    @Override
	    public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		int x = arg0.getX(), y = arg0.getY();
		x = (int) (x * Data.zoom);
		y = (int) (y * Data.zoom);
		for (indi.koro.koroGameEngine.listener.MouseListener mouseListener : mouselisteners)
		    mouseListener.mouseReleased(x, y, arg0);
		for (int i=components.size();i>0;i--) {
		    indi.koro.koroGameEngine.component.Component component=components.get(i-1);
		    if(component.isMouseIn()) {
			if(component.getAbsX()>=x|component.getAbsY()>=y|component.getWidth()+component.getAbsX()<=x|component.getHeight()+component.getAbsY()<=y) {
			    component.mouseExited(x-component.getX(), y-component.getY(), arg0);
			    return;
			}
		    }
		}
	    }

	    @Override
	    public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		int x = arg0.getX(), y = arg0.getY();
		x = (int) (x * Data.zoom);
		y = (int) (y * Data.zoom);
		for (indi.koro.koroGameEngine.listener.MouseListener mouseListener : mouselisteners)
		    mouseListener.mouseEntered(x, y, arg0);
		for (int i=components.size();i>0;i--) {
		    indi.koro.koroGameEngine.component.Component component=components.get(i-1);
		    if(!component.isMouseIn()) {
			if(component.getAbsX()>=x|component.getAbsY()>=y|component.getWidth()+component.getAbsX()<=x|component.getHeight()+component.getAbsY()<=y) {
			    component.mouseEntered(x-component.getX(), y-component.getY(), arg0);
			    return;
			}
		    }
		}
	    }

	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		int x = arg0.getX(), y = arg0.getY();
		x = (int) (x * Data.zoom);
		y = (int) (y * Data.zoom);
		for (indi.koro.koroGameEngine.listener.MouseListener mouseListener : mouselisteners)
		    mouseListener.mouseClicked(x, y, arg0);
		for (int i=components.size();i>0;i--) {
		    indi.koro.koroGameEngine.component.Component component=components.get(i-1);
			if(component.getAbsX()>=x|component.getAbsY()>=y|component.getWidth()+component.getAbsX()<=x|component.getHeight()+component.getAbsY()<=y) {
			    component.mouseClicked(x-component.getX(), y-component.getY(), arg0);
			    return;
			}
		    }
	    }
	});
	this.addMouseMotionListener(new MouseMotionListener() {

	    @Override
	    public void mouseMoved(MouseEvent e) {
		// TODO 自动生成的方法存根
		int x = e.getX(), y = e.getY();
		x = (int) (x * Data.zoom);
		y = (int) (y * Data.zoom);
		for (indi.koro.koroGameEngine.listener.MouseListener mouseListener : mouselisteners)
		    mouseListener.mouseMoved(x, y, e);
		for (int i=components.size();i>0;i--) {
		    indi.koro.koroGameEngine.component.Component component=components.get(i-1);
			if(component.getAbsX()>=x|component.getAbsY()>=y|component.getWidth()+component.getAbsX()<=x|component.getHeight()+component.getAbsY()<=y) {
			    component.mouseMoved(x-component.getX(), y-component.getY(), e);
			    return;
			}
		    }
	    }

	    @Override
	    public void mouseDragged(MouseEvent e) {
		// TODO 自动生成的方法存根
		int x = e.getX(), y = e.getY();
		x = (int) (x * Data.zoom);
		y = (int) (y * Data.zoom);
		for (indi.koro.koroGameEngine.listener.MouseListener mouseListener : mouselisteners)
		    mouseListener.mouseDragged(x, y, e);
		for (int i=components.size();i>0;i--) {
		    indi.koro.koroGameEngine.component.Component component=components.get(i-1);
			if(component.getAbsX()>=x|component.getAbsY()>=y|component.getWidth()+component.getAbsX()<=x|component.getHeight()+component.getAbsY()<=y) {
			    component.mouseDragged(x-component.getX(), y-component.getY(), e);
			    return;
			}
		    }
	    }
	});

    }


}
