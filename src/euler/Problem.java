package euler;

public class Problem implements Runnable {
	
	private int id;
	private Runnable solver;
	

	public Problem(int id, Runnable solver) {
		this.id = id;
		this.solver = solver;
	}

	@Override
	public void run() {
		System.out.println("Problem " + id);
		System.out.println("--------------------------------");
		
		solver.run();
		
		System.out.println();
	}
	
}
