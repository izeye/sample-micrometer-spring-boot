package com.izeye.sample.support.metrics;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

/**
 * Custom {@link org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint}.
 *
 * @author Johnny Lim
 */
@Component
@WebEndpoint(id = "customPrometheus")
public class CustomPrometheusScrapeEndpoint {

	private final CollectorRegistry collectorRegistry;

	public CustomPrometheusScrapeEndpoint(CollectorRegistry collectorRegistry) {
		this.collectorRegistry = collectorRegistry;
	}

	@ReadOperation(produces = TextFormat.CONTENT_TYPE_004)
	public String scrape() {
		try {
			Writer writer = new StringWriter();
			TextFormat.write004(writer, this.collectorRegistry.filteredMetricFamilySamples(Collections.singleton("jvm_gc_max_data_size_bytes")));
			return writer.toString();
		}
		catch (IOException ex) {
			// This actually never happens since StringWriter::write() doesn't throw any
			// IOException
			throw new RuntimeException("Writing metrics failed", ex);
		}
	}

}
