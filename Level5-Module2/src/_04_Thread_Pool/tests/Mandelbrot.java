package _04_Thread_Pool.tests;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Mandelbrot implements KeyListener {
	public static final int WIDTH = 700;
	public static final int HEIGHT = 500;

	private JFrame window;
	private JLabel image;
	private JLabel textBox;
	private BufferedImage buffer;
	private byte[] pixels;

	private int maxIterations = 64;
	private double hmin = -1.5;
	private double hmax = 1.5;
	private double vmin = -1.5;
	private double vmax = 1.5;

	private double latestElapsedTime;
	private long startTime;
	private long endTime;
	
	private boolean singleThreaded = true;

	private WorkQueue workQueue;

	public Mandelbrot() {
		window = new JFrame();
		image = new JLabel();
		textBox = new JLabel("Press C for Controls");
		buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		pixels = new byte[WIDTH * HEIGHT * 3];
		
		textBox.setBounds(0, 0, 200, 100);
		textBox.setOpaque(true);
		textBox.setBackground(Color.WHITE);
		image.add(textBox);
		
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				calculateMandelbrotPixelColor(pixels, x, y);
			}
		}
		updateWindowImage();
		image.setIcon(new ImageIcon(buffer));
		window.add(image);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(this);
		window.setVisible(true);

		if (!singleThreaded) {
			workQueue = new WorkQueue();
		}
	}

	public double map(double val, double min1, double max1, double min2, double max2) {
		double pctg = (val - min1) / (max1 - min1);
		return (pctg * (max2 - min2)) + min2;
	}

	private void calculateMandelbrotPixelColor(byte[] pix, int x, int y) {
		double a = map(x, 0, WIDTH, hmin, hmax);
		double b = map(y, 0, HEIGHT, vmin, vmax);

		double ca = a;
		double cb = b;

		int n = 0;

		while (n < maxIterations) {
			double aa = a * a - b * b;
			double bb = 2 * a * b;
			a = aa + ca;
			b = bb + cb;

			if (a + b > 16) {
				break;
			}

			n++;
		}

		double rd = 0.5f * Math.sin(0.1 * n + 0) + 0.5f;
		double gr = 0.5f * Math.sin(0.1 * n + 2) + 0.5f;
		double bl = 0.5f * Math.sin(0.1 * n + 4) + 0.5f;

		int index = y * WIDTH * 3 + x * 3;
		pix[index + 0] = (byte) (rd * 255);
		pix[index + 1] = (byte) (gr * 255);
		pix[index + 2] = (byte) (bl * 255);
	}
	
	public void updateWindowImage() {
		byte[] imgData = ((DataBufferByte) buffer.getRaster().getDataBuffer()).getData();
		System.arraycopy(pixels, 0, imgData, 0, pixels.length);
		image.setIcon(new ImageIcon(buffer));
		window.pack();
	}

	public void updateWindowImageSingle() {
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				calculateMandelbrotPixelColor(pixels, x, y);
			}
		}
	}

	public void updateWindowImageMulti() {
		int t = workQueue.getThreadCount() + 1;
		int div = HEIGHT / t;

		for (int i = 0; i < t; i++) {
			int ys = i * div;
			int ye = ys + div;
			workQueue.addJob(() -> {
				for (int y = ys; y < ye; y++) {
					for (int x = 0; x < WIDTH; x++) {
						calculateMandelbrotPixelColor(pixels, x, y);
					}
				}
			});
		}
		workQueue.completeAllJobs();
	}

	public void keyPressed(KeyEvent e) {
		boolean update = false;
		if (e.getKeyCode() == KeyEvent.VK_W) {
			double pct = (vmax - vmin) * 0.1;
			vmin -= pct;
			vmax -= pct;
			update = true;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			double pct = (vmax - vmin) * 0.1;
			vmin += pct;
			vmax += pct;
			update = true;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			double pct = (vmax - vmin) * 0.1;
			hmin -= pct;
			hmax -= pct;
			update = true;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			double pct = (vmax - vmin) * 0.1;
			hmin += pct;
			hmax += pct;
			update = true;
		} else if (e.getKeyCode() == KeyEvent.VK_Z) {
			double hpct = (hmax - hmin) * 0.1;
			double vpct = (vmax - vmin) * 0.1;
			hmin -= hpct;
			hmax += hpct;
			vmin -= vpct;
			vmax += vpct;
			update = true;
		} else if (e.getKeyCode() == KeyEvent.VK_X) {
			double hpct = (hmax - hmin) * 0.1;
			double vpct = (vmax - vmin) * 0.1;
			hmin += hpct;
			hmax -= hpct;
			vmin += vpct;
			vmax -= vpct;
			update = true;
		} else if (e.getKeyCode() == KeyEvent.VK_1) {
			maxIterations /= 2;
			update = true;
		} else if (e.getKeyCode() == KeyEvent.VK_2) {
			maxIterations *= 2;
			update = true;
		} else if (e.getKeyCode() == KeyEvent.VK_M) {
			singleThreaded = !singleThreaded;
			update = true;
			if (!singleThreaded) {
				workQueue = new WorkQueue();
			} else {
				if (workQueue != null) {
					workQueue.shutdown();
					workQueue = null;
				}
			}
		}else if (e.getKeyCode() == KeyEvent.VK_C) {
			String txt = "W: move up\n";
			txt += "S: move down\n";
			txt += "A: move left\n";
			txt += "D: move right\n";
			txt += "Z: zoom out\n";
			txt += "X: zoom in\n";
			txt += "1: decrease detail\n";
			txt += "2: increase detail\n";
			txt += "M: toggle single and multi thread mode\n";
			JOptionPane.showMessageDialog(null, txt);
		}
		
		if (update) {
			String txt = "<html>";
			if (singleThreaded) {
				startTime = System.nanoTime();
				updateWindowImageSingle();
				endTime = System.nanoTime();
			} else {
				startTime = System.nanoTime();
				updateWindowImageMulti();
				endTime = System.nanoTime();
			}
			latestElapsedTime = (double)(endTime - startTime) / 1_000_000_000.0;
			txt += "Press C for Controls<br>";
			txt += "Mode: " + (singleThreaded ? "single thread<br>" : "multi thread<br>");
			txt += "Detail Level: " + maxIterations + "<br>";
			txt += "Calculation Time: " + latestElapsedTime + "</html>";
			textBox.setText(txt);
			updateWindowImage();
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
	
	public static void main(String[] args) {
		new Mandelbrot();
	}
}
