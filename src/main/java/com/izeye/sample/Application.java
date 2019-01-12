package com.izeye.sample;

import java.lang.management.ManagementFactory;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sample application.
 *
 * @author Johnny Lim
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void initialize() {
		MBeanServer mBeanServer = getMBeanServer();
		try {
			String objectNamePattern = "Tomcat:type=ThreadPool,*";
			System.out.println("# Object name pattern: " + objectNamePattern);
			mBeanServer.queryNames(new ObjectName(objectNamePattern), null).forEach(System.out::println);

			objectNamePattern = "Tomcat:type=ThreadPool,name=*";
			System.out.println("# Object name pattern: " + objectNamePattern);
			mBeanServer.queryNames(new ObjectName(objectNamePattern), null).forEach(System.out::println);
		} catch (MalformedObjectNameException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static MBeanServer getMBeanServer() {
		List<MBeanServer> mBeanServers = MBeanServerFactory.findMBeanServer(null);
		if (!mBeanServers.isEmpty()) {
			return mBeanServers.get(0);
		}
		return ManagementFactory.getPlatformMBeanServer();
	}

}
