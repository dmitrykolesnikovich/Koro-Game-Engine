package indi.koro.koroGameEngine.script;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Script {
    ArrayList<String> scipt = new ArrayList<String>();
    int line = 0;
    String name = null;

    public Script(String file) {
	// TODO 自动生成的构造函数存根
	this.name = file;
	read();
    }

    public String line() {
	String string = new String(scipt.get(line));
	line++;
	return string;
    }

    public Script() {
	// TODO 自动生成的构造函数存根
    }

    public void setscript(String name) {
	this.name = name;
	read();
    }

    public void setline(int line) {
	this.line = line;
    }

    public void start(int line) {
	this.line = line;
    }

    public static String deletespace(String string) {
	String string2 = new String(string);
	return string2.replaceAll(" +", "");
    }

    // public
    private void read() {
	try (FileReader reader = new FileReader(name); BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
	) {
	    String line;
	    // 网友推荐更加简洁的写法
	    while ((line = br.readLine()) != null) {
		// 一次读入一行数据
		if (deletespace(line).equalsIgnoreCase("//off")) {
		    break;
		}
		if (line.substring(0, 2).equalsIgnoreCase("//")) {
		    scipt.add(line);
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
