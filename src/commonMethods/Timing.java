package commonMethods;

public class Timing {
	long start;
	long end;
	boolean running;
	boolean timed;
	
	public void start() {
		running = true;
		start = System.nanoTime();
	}
	
	public void end() {
		if (running) {
			end = System.nanoTime();
			running = false;
			timed = true;
		}
	}
	
	public void time() {
		long timens = end - start;
		if (timens < 1000) {
			System.out.println(timens + " nanoseconds");
			return;
		} 
		double timeus = timens/1000.0;
		if (timeus < 1000) {
			System.out.println(timeus + " microseconds");
			return;
		}
		timeus /= 1000;
		if (timeus < 1000) {
			System.out.println(timeus + " milliseconds");
			return;
		}
		timeus /= 1000;
		System.out.println(timeus + " seconds");
	}
}
