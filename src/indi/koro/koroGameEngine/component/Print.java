package indi.koro.koroGameEngine.component;

import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Print {
    ArrayList<Print> prints = new ArrayList<>();
    boolean visible = false;

    abstract public void print(Graphics2D g);// 变态自绘制

    public void setVisible(boolean Visible) {
	visible = Visible;
    }

    public void printthis(Graphics2D g) {// 主绘制
	if (visible) {
	    print(g);
	    for (Print print : prints) {
		print.printthis(g);
	    }
	}
    }

    public void add(Print print) {// 添加print类
	prints.add(print);
    }
}
