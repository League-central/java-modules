package _03_generics_store._03_non_food;

import java.util.Random;

import javax.swing.JLabel;

public class Toy extends NonFood {
	public Toy() {
		if(new Random().nextBoolean()) {
			this.item = "images/toy1.jpeg";
		}
		else {
			this.item = "images/toy2.jpeg";
		}
	}

	@Override
	public JLabel getNonFood() {
		// TODO Auto-generated method stub
		return new JLabel(loadImage(this.item));
	}
}
