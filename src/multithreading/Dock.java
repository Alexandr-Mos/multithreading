package multithreading;

public class Dock extends Thread {
	private volatile static Port port;
	private volatile boolean isOccupied;
	private volatile Ship ship;
	//private volatile static int dockCount = 0;
	//private volatile int id ;

	public Dock(Port port) {
		Dock.port = port;
		isOccupied = false;
		ship = null;
		//if (++dockCount > 4) {
		//	dockCount = 1;
		//	id = dockCount; 
		//} else {
		//	id = dockCount;
		//}
		 
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
			System.out.println("Произошло ЧП в порту");
			e.printStackTrace();
		}

		setOccupied(false);
	}

	public synchronized void load() throws InterruptedException {
		while (!ship.isFull()) {
			//while (port.isEmpty()) {
			//	System.out.println("ПУСТОЙ ПОРТ КОРАБЛЬ ОЖИДАЕТ " + ship);
			//	wait();
			//}
			
			ship.addContainer(port.unloadContainer());
			System.out.println(ship + "<-----");
			
			Thread.sleep(1500);
			Thread.yield();
	
			
		}
		System.out.println("ЗАГРУЗКА окончена " + ship + " в доке " + this.toString() + " " + port);

	}

	public synchronized void unload() throws InterruptedException {
		while (!ship.isEmpty()) {
			//while (port.isFull()) {
			//	System.out.println("ПОЛНЫЙ ПОРТ КОРАБЛЬ ОЖИДАЕТ " + ship);
			//	port.wait();
			//}
			
			port.addContainer(ship.unloadContainer());
			System.out.println(ship + "----->");
			
			Thread.sleep(1500);
			Thread.yield();
			
			
		}
		System.out.println("РАЗГРУЗКА окончена " + ship + " в доке " + this.toString() + " " + port);

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
		return this.getName();
	}

}
