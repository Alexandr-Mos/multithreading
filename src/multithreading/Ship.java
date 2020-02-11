package multithreading;

import java.util.ArrayList;

public class Ship extends Loadable {
	private static int shipCount = 0;
	private final int id = ++shipCount;

	public Ship(int capacity) {
		super(capacity);
	}



	public int getId() {
		return id;
	}



	@Override
	public String toString() {
		return "Ship [id=" + id + ", capacity=" + getCapacity() + ", containers=" + getContainersList().size() + "]";
	}

}
