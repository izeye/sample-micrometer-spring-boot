package com.izeye.sample;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/**
 * A sample for {@link Timer}.
 *
 * @author Johnny Lim
 */
@Component
public class TimerSample {

	private final Timer timer;
	private long startTimeInMillis;

	public TimerSample(MeterRegistry meterRegistry) {
		this.timer = meterRegistry.timer("app.event", "type", "ping");
		this.startTimeInMillis = System.currentTimeMillis();
	}

	@PostConstruct
	public void start() {
		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				this.timer.record(getElapsedTimeInMillis(), TimeUnit.MILLISECONDS);
				sleep();
			}
		}).start();
	}

	private long getElapsedTimeInMillis() {
		return System.currentTimeMillis() - this.startTimeInMillis;
	}

	private void sleep() {
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("I slept for 1 second.");
		}
		catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

}
