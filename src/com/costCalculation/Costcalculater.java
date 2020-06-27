package com.costCalculation;

import java.util.concurrent.BlockingQueue;

import org.json.JSONException;
import org.json.JSONObject;

import com.bean.Cycleprop;

public class Costcalculater implements Runnable {

	private BlockingQueue<Cycleprop> queue;

	public Costcalculater(BlockingQueue<Cycleprop> q) {
		this.queue = q;
		// this.cycledata = data;
		// this.i = i;

	}

	@Override
	public void run() {

		Cycleprop prop;
		// consuming messages until exit message is received
		try {
			
			while ((prop = queue.take()).getMsg() != "exit") {
				int frameCost = 0;
				int handleBarCost = 0;
				int seatingCost = 0;
				int wheelsCost = 0;
				int chainAssCost = 0;

				JSONObject cycledata = prop.getCycleprop();
				int i = prop.getCount();
				Thread.sleep(i*2);
				frameCost = cycledata.getString("frame").equals("yes") ? +300 : 0;

				handleBarCost = cycledata.getJSONObject("hbwithb").getString("bell").equals("yes") ? 120 : 0;
				handleBarCost += cycledata.getJSONObject("hbwithb").getString("rubbergrip").equals("yes") ? 60 : 0;
				handleBarCost += cycledata.getJSONObject("hbwithb").getString("ledlight").equals("yes") ? 170 : 0;

				wheelsCost = cycledata.getJSONObject("wheel").getString("tyre").equals("yes") ? 200 : 0;
				wheelsCost += cycledata.getJSONObject("wheel").getString("rim").equals("yes") ? 1300 : 0;
				wheelsCost += cycledata.getJSONObject("wheel").getString("spokes").equals("yes") ? 500 : 0;

				seatingCost = cycledata.getJSONObject("seat").getString("cover").equals("yes") ? 30 : 0;
				seatingCost += cycledata.getJSONObject("seat").getString("cycleSeat").equals("yes") ? 80 : 0;

				chainAssCost = cycledata.getJSONObject("chainassembly").getString("cover").equals("yes") ? 20 : 0;
				chainAssCost += cycledata.getJSONObject("chainassembly").getString("backrest").equals("yes") ? 100 : 0;
				chainAssCost += cycledata.getJSONObject("chainassembly").getString("removable").equals("yes") ? 120 : 0;

				System.out.println("Cost Of High Level Components");
				System.out.println("Frame Cost : " + frameCost);
				System.out.println("Handle Bar with Breaks Cost : " + handleBarCost);
				System.out.println("Seating Cost : " + seatingCost);
				System.out.println("Wheels Cost : " + wheelsCost);
				System.out.println("Chain Assembly Cost : " + chainAssCost);
				System.out.println("Total Cost For Cycle " + (i) + " : "
						+ (handleBarCost + frameCost + seatingCost + wheelsCost + chainAssCost));
				System.out.println("queue size-->" + this.queue.size());
				System.out.println();
			}
		} catch (JSONException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
