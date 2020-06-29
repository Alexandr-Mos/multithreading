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
		Port port = new Port(2, 20);  //создаем порт на 2 причала и вместимостью 20 контейнеров
		
		for (int i = 0; i < 20; i++) {
			
			//ручное управление
			System.out.print("ВВЕДИТЕ вместимость прибывшего корабля >> ");
			int capacity = scanner.nextInt();               //задаем вместимость корабля
			System.out.print("ВВЕДИТЕ количество контейнеров на корабле >> ");
			int count = scanner.nextInt();                  // задаем количество контейнеров на корабле              
			ArrayList<Container> containersList = new ArrayList<Container>();
			
			for (int j = 0; j < count; j++) {
				containersList.add(new Container());
			}
			
			/* генерация случайных кораблей
			int capacity = (int) (Math.random()*10 + 10);
			ArrayList<Container> containersList = new ArrayList<Container>();
			if (((int)(Math.random() * 4)) != 0) {
				for (int j = 0; j < (int) (Math.random() * capacity); j++) {
				containersList.add(new Container());
				}
			}
			*/
			
			Ship ship = new Ship(capacity, containersList);
			System.out.println("ПРИБЫЛ корабль " + ship);
			port.recieveShip(ship);
			//Thread.sleep(3000);
		}

	}

}
