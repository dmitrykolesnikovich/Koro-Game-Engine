package indi.koro.koroGameEngine.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import indi.koro.koroGameEngine.component.Print;
import indi.koro.koroGameEngine.data.Data;

public class MainGraphics {
    private BufferedImage bufImg;
    private BufferedImage nowImage;
    Graphics2D g;
    Graphics2D nowImageGraphics2d;
    Font font;
    ArrayList<Print> prints = new ArrayList<>();
    private MainFrame frame=null;

    public MainGraphics(MainFrame frame) {
	// TODO 自动生成的构造函数存根
	this.frame=frame;
	bufImg = createCompatibleImage(1920, 1080, Transparency.OPAQUE);
	nowImage=createCompatibleImage(1920, 1080, Transparency.OPAQUE);
	font = new Font("宋体", 0, 50);
	//g = bufImg.createGraphics();
	nowImageGraphics2d=nowImage.createGraphics();
    }

    public void render(Graphics2D g) {
	paint(g);
    }
    

    public void paint(Graphics2D g) {
	// 创建硬件加速图像
            // 绘制方法
    		g.setFont(font);
    		g.setColor(Color.BLACK);
    		 // 任意的渲染逻辑
    		if (Data.gameshow) {
    		    Data.nowGame.render(g);
    		}
    		for (Print print : prints) {
    		    print.printthis(g);
    		}
	
    		for (indi.koro.koroGameEngine.component.Component component : frame.getKoroComponents()) {
    		    component.print(g);
    		}


	//nowImageGraphics2d.drawImage(bufImg, 0, 0, 1920, 1080, null);
    }

    public void remove(Print print) {
	prints.remove(print);
    }

    public Graphics2D backGraphics() {
	return g;
    }

    public Image backImage() {
	return bufImg;
    }
    

    public static BufferedImage createCompatibleImage(int w, int h, int type) { // 创建image实例
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice device = env.getDefaultScreenDevice();
	GraphicsConfiguration gc = device.getDefaultConfiguration();
	return gc.createCompatibleImage(w, h, type);
    }

    public void add(Print print) {// 添加print类
	prints.add(print);
    }

    /**
     * @return bufImg
     */
    public BufferedImage getBufImg() {
       // return bufImg;
        return nowImage;
    }

    /**
     * @return g
     */
    public Graphics2D getG() {
        return g;
    }

    /**
     * @return font
     */
    public Font getFont() {
        return font;
    }

    /**
     * @return prints
     */
    public ArrayList<Print> getPrints() {
        return prints;
    }


    /**
     * @param font 要设置的 font
     */
    public void setFont(Font font) {
        this.font = font;
    }
}
