package main;

import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class Sound implements Runnable {
	String url;
	public void run() {
		try{
			URL place = new URL("file:///"  +url);//only handles midi so far, maybe some other formats
			AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(place);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start( );
		} /*catch(UnsupportedAudioFileException e) {
			JOptionPane.showMessageDialog(MainWindow.window.frame,
				    "Cannot open files of that type (sorry)",
				    "File error",
				    JOptionPane.ERROR_MESSAGE);
		}*/
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	static void playSound(String file) {
		Sound sound=new Sound();
		sound.url=file;
		sound.run();
	}
}
