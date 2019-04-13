/**
 * 
 */
package indi.koro.koroGameEngine.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import indi.koro.koroGameEngine.component.scene.CustomSceneMode;

/**
 * @author 16415
 *
 */


public class Scene extends Component{
    
    
    final static public int None=0;
    final static public int Fade_in=1;
    final static public int Fade_out=2;
    final static public int Cursor_left=3;
    final static public int Cursor_right=4;
    final static public int Cursor_up=5;
    final static public int Cursor_down=6;
    final static public int Custom=-1;
    private int intoMode=0;
    private int exitMode=0;
    private SThread thread =null;
    private int frame=0;
    private int allFrame=0;
    private int time=1000;
    private int nowMode=0;
    private CustomSceneMode customSceneMode=null;
    private boolean draw=false;
    private Color backgroundColor=Color.black;
    private boolean disVisible=false;
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
     * @return customSceneMode
     */
    public CustomSceneMode getCustomSceneMode() {
        return customSceneMode;
    }

    /**
     * @param customSceneMode 要设置的 customSceneMode
     */
    public void setCustomSceneMode(CustomSceneMode customSceneMode) {
        this.customSceneMode = customSceneMode;
    }
    
    /**
     * @return frame
     */
    public int getFrame() {
        return frame;
    }

    /**
     * @return time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param frame 要设置的 frame
     */
    public void setFrame(int frame) {
        this.frame = frame;
    }

    /**
     * @param allFrame 要设置的 allFrame
     */
    public void setTime(int time) {
        this.time = time;
    }
    
    /**
     * @return intoMode
     */
    public int getIntoMode() {
        return intoMode;
    }
    
    public boolean isExit() {
	return thread.isExit();
    }
    
    /**
     * @return exitMode
     */
    public int getExitMode() {
        return exitMode;
    }

    /**
     * @param intoMode 要设置的 intoMode
     */
    public void setIntoMode(int intoMode) {
        this.intoMode = intoMode;
    }

    /**
     * @param exitMode 要设置的 exitMode
     */
    public void setExitMode(int exitMode) {
        this.exitMode = exitMode;
    }
    
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.component.Component#print(java.awt.Graphics2D)
     */
    @Override
    public void print(Graphics2D g) {
        // TODO 自动生成的方法存根
        super.print(g);
        if (draw) {
	    printEffects(g);
	}
        
    }
    
    public void drawinto() {
	draw=false;
	drawEffects(intoMode);
    }
    public void drawexit() {
	draw=false;
	drawEffects(exitMode);
	disVisible=true;
    }
    
    private void drawEffects(int mode) {
	if(thread!=null) {
	    thread.exit();
	}
	thread=new SThread();
	nowMode=mode;
	thread.start();
	draw=true;
    }
    private void printEffects(Graphics2D g) {
	Color oldColor=g.getColor();
	g.setColor(backgroundColor);
	switch (nowMode) {
	case Fade_in:
	    drawFade_in(g);
	    break;
	case Fade_out:
	    drawFade_out(g);
	    break;
	case Cursor_left:
	    drawCursor_left(g);
	    break;
	case Cursor_right:
	    drawCursor_right(g);
	    break;
	case Cursor_up:
	    drawCursor_up(g);
	    break;
	case Cursor_down:
	    drawCursor_down(g);
	    break;
	default:
	    customSceneMode.customDraw(g, time);
	    break;
	}
	g.setColor(oldColor);
    }
    private void drawFade_in(Graphics2D g) {
	float alpha = 1f-(float)(frame)/(float)(allFrame);
	System.out.println(alpha);
	System.out.println(frame);
	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
	g.fillRect(absX, absY, width, height);
	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1));
    }
    private void drawFade_out(Graphics2D g) {
	float alpha = (float)(frame)/(float)(allFrame);
	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
	g.fillRect(absX, absY, width, height);
	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1));
    }
    private void drawCursor_left(Graphics2D g) {
	
    }
    private void drawCursor_right(Graphics2D g) {
	
    }
    private void drawCursor_up(Graphics2D g) {
	
    }
    private void drawCursor_down(Graphics2D g) {
	
    }
    private class SThread extends Thread {
	private boolean exit=false;
	public void exit(){
	    exit=true;
	}
	/**
	 * @return exit
	 */
	public boolean isExit() {
	    return exit;
	}
	
	/* （非 Javadoc）
	 * @see java.lang.Thread#run()
	 */ 
	@Override
	public void run() {
	    // TODO 自动生成的方法存根
	    super.run();
	    frame=0;
	    allFrame=(int)(time/16.6666666666667);
	    while ( frame < allFrame) {
		frame++;
		if (exit) {
			return ;
		    }
		try {
		    Thread.sleep(16);
		} catch (InterruptedException e) {
		    // TODO 自动生成的 catch 块
		    e.printStackTrace();
		}
	    }
	    draw=false;
	    if (disVisible) {
		setVisible(false);
	    }
	}
    }
}