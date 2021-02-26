package _03_generics_store._03_non_food;

import java.util.Random;

import javax.swing.JLabel;

public class Clothing extends NonFood{
	public Clothing() {
		if(new Random().nextBoolean()) {
			this.item = "images/clothing1.jpeg";
		}
		else {
			this.item = "images/clothing2.jpeg";
		}
	}

	@Override
	public JLabel getNonFood() {
		// TODO Auto-generated method stub
		return new JLabel(loadImage(this.item));
	}
}
