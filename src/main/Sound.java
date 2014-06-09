package main;

import java.net.URL;

import javax.sound.sampled.*;

public class Sound implements Runnable {
	static int row;
	static int col;
	public void run() {
		try{
			URL place = new URL("file:///"  +MainWindow.sound[row][col]);//only handles midi so far, maybe some other formats
			AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(place);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start( );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
