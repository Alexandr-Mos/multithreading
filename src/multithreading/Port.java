package multithreading;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Port extends Loadable {
	private DockService dockService;
	
	
	public Port(int docksCount, int capacity) {
		super(capacity);
		dockService = new DockService(docksCount, this);
		
	}
	
	public void recieveShip(Ship ship) throws InterruptedException {
		Dock dock = null;
		while (dock == null) {
			dock = dockService.getFreeDock();
			Thread.sleep(1000);
		}
		dock.setShip(ship);
		dockService.execute(dock);
	}



	@Override
	public String toString() {
		return "Port [docks=" + getCapacity() + "]";
	}

	
	
	

}
