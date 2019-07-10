package _10_cuteness_tv;

import java.awt.event.*;

import java.net.URI;

import javax.swing.*;

public class CutenessTV {

	public static void main(String[] args) {
		CutenessTV instance = new CutenessTV();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		JButton b1 = new JButton("Duck");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance.showDucks();
			}
		});
		
		JButton b2 = new JButton("Frog");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance.showFrog();
			}
		});
		
		JButton b3 = new JButton("Unicorn");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance.showFluffyUnicorns();
			}
		});
		
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		frame.pack();
		
	}
	
	void showDucks() {
	     playVideo("https://www.youtube.com/watch?v=MtN1YnoL46Q");
	}

	void showFrog() {
	     playVideo("https://www.youtube.com/watch?v=cBkWhkAZ9ds");
	}

	void showFluffyUnicorns() {
	     playVideo("https://www.youtube.com/watch?v=a-xWhG4UU_Y");
	}

	void playVideo(String videoID) {
	     try {
	          URI uri = new URI(videoID);
	          java.awt.Desktop.getDesktop().browse(uri);
	     } catch (Exception e) {
	          e.printStackTrace();
	     }
	}

}