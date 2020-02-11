package multithreading;

public class Dock extends Thread {
	private volatile static Port port;
	private volatile boolean isOccupied;
	private volatile Ship ship;
	private static int dockCount = 0;
	private final int id = ++dockCount;

	public Dock(Port port) {
		Dock.port = port;
		isOccupied = false;
		ship = null;
	}

	@Override
	public void run() {
		if (ship == null) {
			return;
		}

		setOccupied(true);

		try {
			if (ship.isEmpty()) {
				load();
			} else {
				unload();
			}
		} catch (InterruptedException e) {
			System.out.println("Произошло ЧП в порту №" + id);
			e.printStackTrace();
		}

		setOccupied(false);
	}

	public synchronized void load() throws InterruptedException {
		while (!ship.isFull()) {
			while (port.isEmpty()) {
				wait();
			}
			Thread.sleep(3000);
			System.out.println("Загружен на корабль №" + ship.getId() + " " + ship.getContainersList().size() + " из " + ship.getCapacity());

			ship.addContainer(port.unloadContainer());
			notify();
			
		}

	}

	public synchronized void unload() throws InterruptedException {
		while (!ship.isEmpty()) {
			while (port.isFull()) {
				wait();
			}
			Thread.sleep(3000);
			System.out.println("Разгружен из корабля №" + ship.getId() + " " + ship.getContainersList().size() + " из " + ship.getCapacity());
			port.addContainer(ship.unloadContainer());
			notify();
			
		}
		System.out.println("Разгрузка корабля окончена " + ship + " в доке " + this.toString() + " Порт:" +port.getContainersList().size() + " из " + port.getCapacity());

	}
	
	

	public synchronized boolean isOccupied() {
		return isOccupied;
	}

	public synchronized void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public synchronized Ship getShip() {
		return ship;
	}

	public synchronized void setShip(Ship ship) {
		this.ship = ship;
	}

	@Override
	public String toString() {
		return "#" + id;
	}

}
