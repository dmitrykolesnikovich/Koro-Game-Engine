package indi.koro.koroGameEngine.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import indi.koro.koroGameEngine.component.Print;
import indi.koro.koroGameEngine.data.Data;

public class MainGraphics {
    private BufferedImage bufImg;
    Graphics2D g;
    Font font;
    ArrayList<Print> prints = new ArrayList<>();
    boolean isfullGPU=false;
    private MainFrame frame=null;

    public MainGraphics(MainFrame frame) {
	// TODO 自动生成的构造函数存根
	this.frame=frame;
	bufImg = createCompatibleImage(1920, 1080, Transparency.OPAQUE);
	font = new Font("宋体", 0, 50);
	g = bufImg.createGraphics();
    }

    public void render() {
	paint();
    }

    public void paint() {// 绘制方法
	g.setColor(Color.white);
	g.fillRect(0, 0, 1920, 1080);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	g.setFont(font);
	g.setColor(Color.BLACK);

	for (Print print : prints) {
	    print.printthis(g);
	}

	if (Data.gameshow) {
	    Data.nowGame.render(g);
	}
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
    
    public void fullGPUuse(boolean fullGPU) {
	isfullGPU=fullGPU;
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
        return bufImg;
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
     * @return isfullGPU
     */
    public boolean isIsfullGPU() {
        return isfullGPU;
    }

    /**
     * @param font 要设置的 font
     */
    public void setFont(Font font) {
        this.font = font;
    }
}