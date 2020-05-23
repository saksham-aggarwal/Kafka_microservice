package com.cwctravel.microservice.kafka.utilities;

import java.util.concurrent.TimeUnit;

public class KafkaUtil {

	public static void delayExecution(long duration) {
		try {
			TimeUnit.MILLISECONDS.sleep(duration);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
