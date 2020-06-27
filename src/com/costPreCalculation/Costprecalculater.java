package com.costPreCalculation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bean.Cycleprop;

public class Costprecalculater implements Runnable {

	private BlockingQueue<Cycleprop> queue;

	public Costprecalculater(BlockingQueue<Cycleprop> q) {
		this.queue = q;

	}

	public static String readafile(File fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		String content = stringBuilder.toString();
		return content;

	}

	@Override
	public void run() {

		File filecycle = new File("src/cycle.json");
		String jsondata = null;
		try {
			jsondata = Costprecalculater.readafile(filecycle);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		final JSONObject obj = new JSONObject(jsondata);
		final JSONArray geodata = obj.getJSONArray("cycle");
		final int n = geodata.length();

		for (int i = 1; i < n; i++) {

			final JSONObject cycledata = geodata.getJSONObject(i);

			Cycleprop calci = new Cycleprop(cycledata, i, "no");

			try {
				//Thread.sleep(i);
				queue.put(calci);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
