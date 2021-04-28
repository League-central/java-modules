package _04_Thread_Pool.tests;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

class DistanceFromCenterTest {

	public static double distance(double x1, double y1, double x2, double y2) {
		double dx = x2 - x1;
		double dy = y2 - y1;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public static byte calculateDistanceFromCenter(int x, int y, int width, int height) {
		double hwidth = (double) width * 0.5;
		double hheight = (double) height * 0.5;
		double d = distance(x, y, hwidth, hheight);
		double md = distance(0, 0, hwidth, hheight);
		return (byte) ((d / md) * 255);
	}
	
	@Test
	void checkSpeedTest() {
		System.out.println("Distance From Center Test");
		
		int width = 8192;
		int height = 8192;
		int size = width * height * 3;
		byte[] pix1 = new byte[size];
		byte[] pix2 = new byte[size];
		long startTime, singleEndTime, multiEndTime;
		double elapsedTimeSingle;
		double elapsedTimeMulti;
		
		System.out.println("Starting single threaded block");
		startTime = System.nanoTime();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int index = y * width * 3 + x * 3;
				pix1[index + 0] = calculateDistanceFromCenter(x, y, width, height);
				pix1[index + 1] = calculateDistanceFromCenter(x, y, width, height);
				pix1[index + 2] = calculateDistanceFromCenter(x, y, width, height);
			}
		}
		singleEndTime = System.nanoTime();

		elapsedTimeSingle = (double) (singleEndTime - startTime) / 1_000_000_000.0;
		System.out.println("single thread time: " + elapsedTimeSingle + " seconds");

		System.out.println("Starting multi-threaded block");
		startTime = System.nanoTime();
		
		WorkQueue wq = new WorkQueue();
		int ct = wq.getThreadCount() + 1;
		int dv = height / ct;
		for (int i = 0; i < ct; i++) {
			int ys = dv * i;
			int ye = ys + dv;
			wq.addJob(() -> {
				for (int y = ys; y < ye; y++) {
					for (int x = 0; x < width; x++) {
						int xx = x;
						int yy = y;
						int index = y * width * 3 + x * 3;
						pix2[index + 0] = calculateDistanceFromCenter(xx, yy, width, height);
						pix2[index + 1] = calculateDistanceFromCenter(xx, yy, width, height);
						pix2[index + 2] = calculateDistanceFromCenter(xx, yy, width, height);
					}
				}

			});
		}
		wq.shutdown();
		
		multiEndTime = System.nanoTime();
		elapsedTimeMulti = (double) (multiEndTime - startTime) / 1_000_000_000.0;
		System.out.println("milt-thread time: " + elapsedTimeMulti + " seconds");
		assertArrayEquals(pix1, pix2);
		System.out.println("Arrays are a match");
		assertTrue(singleEndTime < multiEndTime);
		System.out.println("The single-threaded block was " + (elapsedTimeSingle - elapsedTimeMulti) + " seconds slower.");
	}

}
