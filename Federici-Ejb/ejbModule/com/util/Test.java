package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date firstDate = sdf.parse("2019-02-03");
			Date secondDate = sdf.parse("2020-02-03");

			System.out.println((int) (TimeUnit.MILLISECONDS.toDays(new Date().getTime() - firstDate.getTime()) / 30));

		} catch (Exception e) {
			System.out.println("cast error<---");
		}

	}

}
