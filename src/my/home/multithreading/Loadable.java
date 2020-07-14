package my.home.multithreading;

import java.util.ArrayList;

public class Loadable {
	private volatile int capacity;
	private volatile ArrayList<Container> containersList;

	public Loadable(int capacity) { 
		if (capacity < 0) {        // проверка на корректность грузоподьемности
			this.capacity = 0;
		} else {
			this.capacity = capacity;
		}
		containersList = new ArrayList<Container>();
	}
	
	public Loadable(int capacity, ArrayList<Container> containersList) { 
		if (capacity < 0) {       
			this.capacity = 0;
		} else {
			this.capacity = capacity;
		}
		this.containersList = containersList;
	}
	

	//загрузка контейнера
	public synchronized void addContainer(Container container) throws InterruptedException {
		while (isFull()) { //если обьект загружен, то включается режим ожидания
			System.out.println("ПОЛНЫЙ ПОРТ КОРАБЛЬ ОЖИДАЕТ РАЗГРУЗКИ");
			wait();
		}
		containersList.add(container);
		notify();
	}

	//разгрузка контейнера
	public synchronized Container unloadContainer() throws InterruptedException {
		while (isEmpty()) { //если обьект пустой, то включается режим ожидания
			System.out.println("ПУСТОЙ ПОРТ КОРАБЛЬ ОЖИДАЕТ ЗАГРУЗКИ");
			wait();
		}
		Container con = getContainer();
		removeContainer();
		notify();
		return con;
	}
	
	public synchronized Container getContainer() {
		return containersList.get(0);
	}
	
	public synchronized void removeContainer() {
		if (!containersList.isEmpty()) {
			containersList.remove(0);
		}
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
