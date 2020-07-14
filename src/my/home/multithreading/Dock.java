package my.home.multithreading;

public class Dock extends Thread {
	private volatile static Port port;
	private volatile Ship ship;


	public Dock(Port port) {
		Dock.port = port;
		ship = null;	 
	}

	@Override
	public void run() {
		if (ship == null) {
			return;
		}


		try {
			if (ship.isGoingToLoad()) {
				load();
			} else {
				unload();
			}
		} catch (InterruptedException e) {
			System.out.println("Произошло ЧП в порту");
			e.printStackTrace();
		}

	}

	//загрузка корабля
	public synchronized void load() throws InterruptedException {
		while (!ship.isFull()) {
			ship.addContainer(port.unloadContainer());
			System.out.println(ship + "<-----" + port);
			Thread.sleep(1500);  //скорость загрузки контейнера(мешьше - быстрее)
		}
		System.out.println("ЗАГРУЗКА окончена " + ship + " в доке " + this.toString() + " " + port);

	}

	//Разгрузка корабля
	public synchronized void unload() throws InterruptedException {
		while (!ship.isEmpty()) {
			System.out.println(ship + "----->" + port);
			port.addContainer(ship.unloadContainer());
			Thread.sleep(1500);	//скорость разгрузки контейнера(мешьше - быстрее)	
		}
		System.out.println("РАЗГРУЗКА окончена " + ship + " в доке " + this.toString() + " " + port);

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
