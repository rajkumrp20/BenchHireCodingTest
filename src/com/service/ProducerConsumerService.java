package com.service;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.bean.Cycleprop;
import com.costCalculation.Costcalculater;
import com.costPreCalculation.Costprecalculater;

class ProducerConsumerService {

	public static void main(String[] args) throws IOException {
		// Creating BlockingQueue of size 10

		BlockingQueue<Cycleprop> queue = new ArrayBlockingQueue<>(10);
		Costcalculater objCalc = null;
		Costprecalculater objpreCalc = null;

		objpreCalc = new Costprecalculater(queue);
		objCalc = new Costcalculater(queue);

		new Thread(objpreCalc).start();
		new Thread(objCalc).start();

	}

}

