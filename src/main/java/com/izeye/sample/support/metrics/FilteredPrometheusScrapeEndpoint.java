package com.izeye.sample.support.metrics;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Set;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

/**
 * Filter-supported {@link org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint}.
 *
 * @author Johnny Lim
 */
@Component
@WebEndpoint(id = "filtered-prometheus")
public class FilteredPrometheusScrapeEndpoint  {

	private final CollectorRegistry collectorRegistry;

	public FilteredPrometheusScrapeEndpoint(CollectorRegistry collectorRegistry) {
		this.collectorRegistry = collectorRegistry;
	}

	@ReadOperation(produces = TextFormat.CONTENT_TYPE_004)
	public String scrape(@Nullable Set<String> includedNames) {
		try {
			Writer writer = new StringWriter();
			TextFormat.write004(writer, getSamples(includedNames));
			return writer.toString();
		}
		catch (IOException ex) {
			// This actually never happens since StringWriter::write() doesn't throw any
			// IOException
			throw new RuntimeException("Writing metrics failed", ex);
		}
	}

	private Enumeration<Collector.MetricFamilySamples> getSamples(@Nullable Set<String> includedNames) {
		if (includedNames == null) {
			return this.collectorRegistry.metricFamilySamples();
		}
		return this.collectorRegistry.filteredMetricFamilySamples(includedNames);
	}

}
