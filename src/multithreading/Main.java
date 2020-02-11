package multithreading;

import java.util.ArrayList;

/*Задание 4. Многопоточность. Порт . Корабли заходят в порт для
разгрузки/загрузки контейнеров. Число контейнеров, находящихся в текущий момент
в порту и на корабле, должно быть неотрицательным и превышающим заданную
грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
У одного причала может стоять один корабль. Корабль может загружаться у причала
или разгружаться.*/
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Port port = new Port(4, 20);
		
		for (int i = 0; i < 20; i++) {
			int capacity = (int) (Math.random()*10);
			ArrayList<Container> containersList = new ArrayList<Container>();
			for (int j = 0; j < (int) (Math.random()*capacity); j++) {
				containersList.add(new Container());
			}
			Ship ship = new Ship(capacity);
			ship.setContainersList(containersList);
			System.out.println("Прибыл корабль " + ship);
			port.recieveShip(ship);
			Thread.sleep(5000);
		}

	}

}
