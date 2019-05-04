/**
 *<p>文件名:Button.java</p>
 * @author 16415
 *创建时间：2019年4月17日 下午1:21:07
 */
package indi.koro.koroGameEngine.component;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *项目名称：KoroGameEngine
 *类名称:Button
 *创建时间：2019年4月17日下午1:21:07
 *<p>类描述:TODO</p>
 * @author  16415
 * @version 1.0
 */
public class Button extends Component {
    protected int length=0;
    protected boolean noneImage=true;
    protected String textString=new String();
    protected int mode=0;
    protected boolean mouseEntered=false;
    protected BufferedImage buttonImage=null;
    protected BufferedImage clickImage=null;
    protected BufferedImage enteredImage=null;
    final static public int MOUSEENTERED=1;
    final static public int MOUSECLICKED=2;
    final static public int MOUSENONE=0;
    /**
     * 
     */
    public Button(BufferedImage image,BufferedImage clickImage,BufferedImage enteredImage) {
	// TODO 自动生成的构造函数存根
	noneImage=false;
	this.buttonImage=image;
	this.clickImage=clickImage;
	this.enteredImage=enteredImage;
    } 
    /**
     * 
     */
    public Button(BufferedImage image,BufferedImage clickImage) {
	// TODO 自动生成的构造函数存根
	noneImage=false;
	this.buttonImage=image;
	this.clickImage=clickImage;
	this.enteredImage=image;
    }
    /**
     * 
     */
    public Button(BufferedImage image) {
	// TODO 自动生成的构造函数存根
	noneImage=false;
	this.buttonImage=image;
	this.clickImage=image;
	this.enteredImage=image;
    }
    public void setImage(BufferedImage image,BufferedImage clickImage,BufferedImage enteredImage) {
	noneImage=false;
	this.buttonImage=image;
	this.clickImage=clickImage;
	this.enteredImage=enteredImage;
    }
    public void setImage(BufferedImage image,BufferedImage clickImage) {
	noneImage=false;
	this.buttonImage=image;
	this.clickImage=clickImage;
	this.enteredImage=image;
    }
    public void setImage(BufferedImage image) {
	noneImage=false;
	this.buttonImage=image;
	this.clickImage=image;
	this.enteredImage=image;
    }
    public static int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }
    /**
     * 
     */
    public Button() {
	// TODO 自动生成的构造函数存根
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.component.Component#printComponent(java.awt.Graphics2D)
     */
    @Override
    protected void printComponent(Graphics2D g) {
        // TODO 自动生成的方法存根
        super.printComponent(g);
        if (!noneImage) {
        switch (mode) {
	case MOUSENONE:
	    printNone(g);
	    break;
	case MOUSEENTERED:
	    printEntered(g);
	    break;
	case MOUSECLICKED:
	    printClick(g);
	    break;
	default:
	    printNone(g);
	    break;
	}
        }else {
            g.fillRect(absX, absY, width, height);  
	}
        g.setFont(font);
        g.drawString(textString, absX+(width-font.getSize()*length/2)/2, absY+(height-font.getSize())/2);

    }
    protected void printClick(Graphics2D g) {
	g.drawImage(clickImage, null, absX, absY);
    }
    protected void printNone(Graphics2D g) {
	g.drawImage(buttonImage, null, absX, absY);
    }
    protected void printEntered(Graphics2D g) {
	g.drawImage(enteredImage, null, absX, absY);
    }
    /**
     * @return mode
     */
    public int getMode() {
        return mode;
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.component.Component#mouseClicked(int, int, java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(int x, int y, MouseEvent e) {
        // TODO 自动生成的方法存根
        super.mouseClicked(x, y, e);
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.component.Component#mousePressed(int, int, java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(int x, int y, MouseEvent e) {
        // TODO 自动生成的方法存根
        super.mousePressed(x, y, e);
        mode=MOUSECLICKED;
        System.out.println("mouceclick");
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.component.Component#mouseEntered(int, int, java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(int x, int y, MouseEvent e) {
        // TODO 自动生成的方法存根
        super.mouseEntered(x, y, e);
        mode=MOUSEENTERED;
        mouseEntered=true;
        System.out.println("mouceen");
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.component.Component#mouseExited(int, int, java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(int x, int y, MouseEvent e) {
        // TODO 自动生成的方法存根
        super.mouseExited(x, y, e);
        mode=MOUSENONE;
        mouseEntered=false;
        System.out.println("mouceex");
    }
    /* （非 Javadoc）
     * @see indi.koro.koroGameEngine.component.Component#mouseReleased(int, int, java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(int x, int y, MouseEvent e) {
        // TODO 自动生成的方法存根
        super.mouseReleased(x, y, e);
        if(mouseEntered) {
            mode=MOUSEENTERED;
            System.out.println("moucer");
        }else {
            mode=MOUSENONE;
            System.out.println("moucer2");
        }
        
    }
    /**
     * @return noneImage
     */
    public boolean isNoneImage() {
        return noneImage;
    }
    /**
     * @return textString
     */
    public String getTextString() {
        return textString;
    }
    /**
     * @param textString 要设置的 textString
     */
    public void setTextString(String textString) {
        this.textString = textString;
        length=length(textString);
    }
}
