package multithreading;

import java.util.ArrayList;

public class DockService {
	private ArrayList<Dock> list;
	
	public DockService(int docksCount, Port port) {
		list = new ArrayList<Dock>();
		for (int i = 0; i < docksCount; i++) {
			list.add(new Dock(port));
		}
	}
	
	public Dock getFreeDock() {
		for (Dock d : list) {
			if (!d.isAlive()) {
				return d;
			}
		}
		return null;
	}
	
	public void execute(Dock dock) {
		dock.interrupt();
		dock.start();
	}

}
