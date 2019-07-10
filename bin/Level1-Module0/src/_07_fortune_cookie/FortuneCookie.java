package _07_fortune_cookie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class FortuneCookie implements ActionListener {

	public static void main(String[] args) {
		FortuneCookie test = new FortuneCookie();
		test.showButton();
	}

	public void showButton() {
		System.out.println("Button clicked");
		
		JFrame frame = new JFrame();
		frame.setVisible(true);

		JButton button = new JButton("press me");
		frame.add(button);
		frame.pack();
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] fortunes = {"Beware! At midnight tonight, a giant\n frog will approach you. Run away\n from it, or be kidnapped and\n taken to its lair!", "Good news! You will be eaten\n tomorrow. But you'll get a free\n Amazon $50 gift card from it.", "You will find a chest of\n cursed gold in the garage. Throw\n it into the sea. The fishies\n will eat it.", "You will find a can of old\n peaches in the closet. Eat them.\n They will make you as strong\n as an old frog.", "The house will collapse at noon.\n Swallow a raw salmon 5 minutes\n beforehand to survive the collapse."};
		int rand = new Random().nextInt(fortunes.length);
		for (int i = 0; i < fortunes.length; i++) {
			if (rand == i) JOptionPane.showMessageDialog(null, fortunes[i]);
		}
	}

}
