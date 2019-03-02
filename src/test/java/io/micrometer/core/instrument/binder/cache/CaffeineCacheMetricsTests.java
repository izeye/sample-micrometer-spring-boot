package io.micrometer.core.instrument.binder.cache;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CaffeineCacheMetricsTests {

	@Test
	public void monitorWithAsyncLoadingCache() {
		LoadingCache loadingCache = mock(LoadingCache.class);
		CacheStats stats = new CacheStats(0L, 0L, 0L, 0L, 0L, 0L, 0L);
		when(loadingCache.stats()).thenReturn(stats);

		AsyncLoadingCache asyncLoadingCache = mock(AsyncLoadingCache.class);
		when(asyncLoadingCache.synchronous()).thenReturn(loadingCache);
		CaffeineCacheMetrics.monitor(new SimpleMeterRegistry(), asyncLoadingCache, "test");
	}

	@Test
	public void monitorWithAsyncCache() {
		Cache cache = mock(Cache.class);
		CacheStats stats = new CacheStats(0L, 0L, 0L, 0L, 0L, 0L, 0L);
		when(cache.stats()).thenReturn(stats);

		AsyncCache asyncCache = mock(AsyncCache.class);
		when(asyncCache.synchronous()).thenReturn(cache);
		CaffeineCacheMetrics.monitor(new SimpleMeterRegistry(),asyncCache, "test");
	}

}
