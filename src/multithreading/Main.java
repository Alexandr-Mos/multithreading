package multithreading;

import java.util.ArrayList;
import java.util.Scanner;

/*Задание 4. Многопоточность. Порт . Корабли заходят в порт для
разгрузки/загрузки контейнеров. Число контейнеров, находящихся в текущий момент
в порту и на корабле, должно быть неотрицательным и превышающим заданную
грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
У одного причала может стоять один корабль. Корабль может загружаться у причала
или разгружаться.*/
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Port port = new Port(20, 20);
		
		for (int i = 0; i < 20; i++) {
			//int capacity = (int) (Math.random()*10 + 10);
			int capacity = scanner.nextInt();
			ArrayList<Container> containersList = new ArrayList<Container>();
			int count = scanner.nextInt();
			for (int j = 0; j < count; j++) {
				containersList.add(new Container());
			}
			//if (((int)(Math.random()*4)) != 0) {
			//	for (int j = 0; j < (int) (Math.random()*capacity); j++) {
			//	containersList.add(new Container());
			//}
			//}
			
			Ship ship = new Ship(capacity);
			ship.setContainersList(containersList);
			System.out.println("ПРИБЫЛ корабль " + ship);
			port.recieveShip(ship);
			Thread.sleep(3000);
		}

	}

}
