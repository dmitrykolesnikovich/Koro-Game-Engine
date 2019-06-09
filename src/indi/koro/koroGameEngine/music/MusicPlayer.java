package indi.koro.koroGameEngine.music;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
    private String musicPath;
    private volatile boolean run = true;
    private Thread mainThread;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceDataLine;
    private int thread=0;
    private boolean stop=false;
    public MusicPlayer() {
    }

    public MusicPlayer(String file) {
	this.musicPath = file;
	prefetch();
    }

    public void play(String file, boolean loop) {
	this.musicPath = file;
	prefetch();
	if (musicPath == null) {// 测试文件名
	    try {
		throw new Exception("请勿提供空的文件名");
	    } catch (Exception e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	    }
	}
	mainThread = new Thread(new Runnable() {
	    public void run() {
		try {
		    playMusic(loop);
		} catch (InterruptedException e) {
		    System.err.println(e);
		}
	    }
	});
	mainThread.start();
    }

    public void setfile(String file) {
	this.musicPath = file;
	prefetch();
    }

    public void start(boolean loop)  {
	mainThread = new Thread(new Runnable() {
	    public void run() {
		try {
		    if (thread<=2) {
			thread++;
		    
		    playMusic(loop);
		    thread--;
		    }
		} catch (InterruptedException e) {
		    System.err.println(e);
		}
	    }
	});
	mainThread.start();
    }
    
    public void close() {
	new Thread(new Runnable() {
	    public void run() {
		try {
		stopMusic();
		sourceDataLine.drain();
		sourceDataLine.close();
		audioStream.close();
		} catch (IOException e) {
		    // TODO 自动生成的 catch 块
		    e.printStackTrace();
		}

	    }
	}).start();
	
    }

//停止
    public void stop() {
	new Thread(new Runnable() {
	    public void run() {
		stopMusic();
		thread=0;
	    }
	}).start();
    }

    protected void finalize() throws Throwable {
	super.finalize();
	sourceDataLine.drain();
	sourceDataLine.close();
	audioStream.close();
    }

    private void prefetch() {
	try {
	    audioStream = AudioSystem.getAudioInputStream(new File(musicPath));
	    audioFormat = audioStream.getFormat();
	    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat,
		    AudioSystem.NOT_SPECIFIED);
	    sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
	    sourceDataLine.open(audioFormat);
	    sourceDataLine.start();
	} catch (UnsupportedAudioFileException ex) {
	    System.err.println(ex);
	} catch (LineUnavailableException ex) {
	    System.err.println(ex);
	} catch (IOException ex) {
	    System.err.println(ex);
	}

    }

    private void playMusic(boolean loop) throws InterruptedException {
	    if (loop) {
		while (!stop) {
		    playMusic();
		}
		stop=false;
	    } else {
		playMusic();
	    }


    }

    private void playMusic() {
	    
	try {
	    synchronized (this) {
		run = true;
	    
	    audioStream = AudioSystem.getAudioInputStream(new File(musicPath));
	    int count;
	    byte tempBuff[] = new byte[10240];

	    while ((count = audioStream.read(tempBuff, 0, tempBuff.length)) != -1) {
		synchronized (this) {
		    while (!run)
			wait();
		}
		sourceDataLine.write(tempBuff, 0, count);
		
	    }
	   
	    }
	} catch (UnsupportedAudioFileException ex) {
	    System.err.println(ex);
	} catch (IOException ex) {
	    System.err.println(ex);
	} catch (InterruptedException ex) {
	    System.err.println(ex);
	}
	
    }

    private void stopMusic() {
	synchronized (this) {
	    run = false;
	    stop=true;
	    notifyAll();
	}
    }

    private void continueMusic() {
	synchronized (this) {
	    run = true;
	    notifyAll();
	}
    }

    public void continues() {
	new Thread(new Runnable() {
	    public void run() {
		continueMusic();
	    }
	}).start();
    }

}
