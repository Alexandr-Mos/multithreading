package my.home.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Port extends Loadable {
	private ExecutorService executor; 
	
	
	public Port(int docksCount, int capacity) {  // количество доков, вместимость порта
		super(capacity); 
		
		executor = Executors.newFixedThreadPool(docksCount);
		
	}
	
	//отправляем корабль в док
	public void recieveShip(Ship ship) throws InterruptedException {
		Dock dock = new Dock(this);
		dock.setShip(ship);
		executor.execute(dock);
	}



	@Override
	public String toString() {
		return "Port [" + getContainersList().size() + " из " + getCapacity() + "]";
	}

	
	
	

}
