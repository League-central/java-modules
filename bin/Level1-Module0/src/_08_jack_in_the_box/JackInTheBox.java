package _08_jack_in_the_box;

import java.applet.AudioClip;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

public class JackInTheBox {
	public JFrame frame = new JFrame();
	static JackInTheBox instance = new JackInTheBox();
	public int buttonTimes = 0;

	public static void main(String[] args) {
		
		instance.frame.setVisible(true);
		instance.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		instance.frame.add(panel);
		
		JButton button = new JButton("Surprise");
		panel.add(button);
		instance.frame.pack();
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance.buttonTimes++;
				if (instance.buttonTimes >= 5) {
					instance.showPicture("jackInTheBox.png");
					instance.playSound("homer-woohoo.wav");
				}
			}
		});
	}
	
	 private void showPicture(String fileName) {
	     try {
	          JLabel imageLabel = createLabelImage(fileName);
	          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	          frame.add(imageLabel);
	          frame.setVisible(true);
	          frame.pack();
	     } catch (Exception e) {
	          e.printStackTrace();
	     }
	}

	private JLabel createLabelImage(String fileName) {
	     try {
	          URL imageURL = getClass().getResource(fileName);
	          if (imageURL == null) {
	               System.err.println("Could not find image " + fileName);
	               return new JLabel();
	          } else {
	               Icon icon = new ImageIcon(imageURL);
	               JLabel imageLabel = new JLabel(icon);
	               return imageLabel;
	          }
	     } catch (Exception e) {
	          System.err.println("Could not find image " + fileName);
	          return new JLabel();
	     }
	}
	
	 private void playSound(String soundFile) {
	     try {
	          AudioClip sound = JApplet.newAudioClip(getClass().getResource(soundFile));
	          sound.play();
	     } catch (Exception e) {
	          e.printStackTrace();
	     }
	}


}