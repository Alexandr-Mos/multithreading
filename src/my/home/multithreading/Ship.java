package my.home.multithreading;

import java.util.ArrayList;

public class Ship extends Loadable {
	private static int shipCount = 0;
	private final int id = ++shipCount;
	private boolean isGoingToLoad;

	public Ship(int capacity, ArrayList<Container> containersList) {
		super(capacity, containersList);
		if (containersList.size() == 0) {
			isGoingToLoad = true;
		} else {
			isGoingToLoad = false;
		}
	}

	public int getId() {
		return id;
	}

	public boolean isGoingToLoad() {
		return isGoingToLoad;
	}

	public void setGoingToLoad(boolean isGoingToLoad) {
		this.isGoingToLoad = isGoingToLoad;
	}

	@Override
	public String toString() {
		return "Ship [id=" + id + ", capacity=" + getCapacity() + ", containers=" + getContainersList().size() + "]";
	}

}
