package multithreading;

public class Dock extends Thread {
	private volatile static Port port;
	//private volatile boolean isOccupied;
	private volatile Ship ship;


	public Dock(Port port) {
		Dock.port = port;
		//isOccupied = false;
		ship = null;	 
	}

	@Override
	public void run() {
		if (ship == null) {
			return;
		}

		//setOccupied(true);

		try {
			if (ship.isEmpty()) {
				load();
			} else {
				unload();
			}
		} catch (InterruptedException e) {
			System.out.println("Произошло ЧП в порту");
			e.printStackTrace();
		}

		//setOccupied(false);
	}

	//загрузка корабля
	public synchronized void load() throws InterruptedException {
		while (!ship.isFull()) {
			ship.addContainer(port.unloadContainer());
			System.out.println(ship + "<-----");
			Thread.sleep(1500);
		}
		System.out.println("ЗАГРУЗКА окончена " + ship + " в доке " + this.toString() + " " + port);

	}

	//Разгрузка корабля
	public synchronized void unload() throws InterruptedException {
		while (!ship.isEmpty()) {
			System.out.println(ship + "----->");
			port.addContainer(ship.unloadContainer());
			Thread.sleep(1500);		
		}
		System.out.println("РАЗГРУЗКА окончена " + ship + " в доке " + this.toString() + " " + port);

	}
	
	

	//public synchronized boolean isOccupied() {
	//	return isOccupied;
	//}

	//public synchronized void setOccupied(boolean isOccupied) {
	//	this.isOccupied = isOccupied;
	//}

	public synchronized Ship getShip() {
		return ship;
	}

	public synchronized void setShip(Ship ship) {
		this.ship = ship;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
