package program;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCount {
	// Define a ThreadLocal variable to store the count of steps for each thread
	public static ThreadLocal<Integer> steps = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0; // Initialize the count to 0 for each thread
		}
	};

	public static void main(String[] args) {
		// Create a fixed thread pool with 3 threads
		ExecutorService executor = Executors.newFixedThreadPool(3);

		// Submit tasks to the thread pool
		for (int i = 1; i <= 5; i++) {
			final int taskId = i;
			executor.submit(() -> {
				// Simulate processing of tasks
				for (int j = 1; j <= 4; j++) {
					System.out.println("Thread " + Thread.currentThread().getName() +
							" is processing task " + taskId + ", step " + j);
					// Increment the count of steps for the current thread
					steps.set(steps.get() + 1);
				}
				// Display the total steps completed by the current thread
				System.out.println("Thread " + Thread.currentThread().getName() +
						" completed " + steps.get() + " steps.");
			});
		}

		// Shutdown the executor
		executor.shutdown();
	}
}
