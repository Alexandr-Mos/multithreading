package multithreading;

import java.util.ArrayList;

public class Loadable {
	private volatile int capacity;
	private volatile ArrayList<Container> containersList;

	public Loadable(int capacity) {
		if (capacity < 0) {
			this.capacity = 0;
		} else {
			this.capacity = capacity;
		}
		containersList = new ArrayList<Container>();
	}

	public synchronized Container getContainer() {
		return containersList.get(0);
	}

	public synchronized void removeContainer() {
		if (!containersList.isEmpty()) {
			containersList.remove(0);
		}
	}

	public synchronized void addContainer(Container container) throws InterruptedException {
		while (isFull()) {
			System.out.println("ПОЛНЫЙ ПОРТ КОРАБЛЬ ОЖИДАЕТ ");

			wait();
		}
		containersList.add(container);
		notify();
	}

	public synchronized Container unloadContainer() throws InterruptedException {
		while (isEmpty()) {
			System.out.println("ПУСТОЙ ПОРТ КОРАБЛЬ ОЖИДАЕТ");
			wait();
		}
		Container con = getContainer();
		removeContainer();
		notify();
		return con;
	}

	public synchronized boolean isEmpty() {
		if (containersList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized boolean isFull() {
		if (containersList.size() >= capacity) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized int getCapacity() {
		return capacity;
	}

	public synchronized void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public synchronized ArrayList<Container> getContainersList() {
		return containersList;
	}

	public synchronized void setContainersList(ArrayList<Container> containersList) {
		this.containersList = containersList;
	}
}
