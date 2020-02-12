package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Port extends Loadable {
	private DockService dockService;
	private ExecutorService executor; 
	
	
	public Port(int docksCount, int capacity) {
		super(capacity);
		dockService = new DockService(docksCount, this);
		executor = Executors.newFixedThreadPool(docksCount);
		
	}
	
	public void recieveShip(Ship ship) throws InterruptedException {
		//Dock dock = null;
		//while (dock == null) {
		//	dock = dockService.getFreeDock();
		//	Thread.sleep(100); //с определенной частотой опрашиваем порт на свободный док 
		//}
		//dock.setShip(ship);
		//dockService.execute(dock);
		Dock dock = new Dock(this);
		dock.setShip(ship);
		executor.execute(dock);
	}



	@Override
	public String toString() {
		return "Port [" + getContainersList().size() + " из " + getCapacity() + "]";
	}

	
	
	

}
