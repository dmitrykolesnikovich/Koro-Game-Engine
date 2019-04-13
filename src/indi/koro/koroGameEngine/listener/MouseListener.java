package indi.koro.koroGameEngine.listener;

import java.awt.event.MouseEvent;

public interface MouseListener {
    
    
    
    public void mouseReleased(int x, int y, MouseEvent e);

    public void mousePressed(int x, int y, MouseEvent e);

    public void mouseExited(int x, int y, MouseEvent e);

    public void mouseEntered(int x, int y, MouseEvent e);

    public void mouseClicked(int x, int y, MouseEvent e);

    public void mouseMoved(int x, int y, MouseEvent e);

    public void mouseDragged(int x, int y, MouseEvent e);
}
