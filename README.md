# StatsD meter registry sample with war packaging

## Issues

* [Micrometer #657](https://github.com/micrometer-metrics/micrometer/issues/657)
  * Deploying this application in a standalone Tomcat and then stopping it causes the following logs:

```
2019-05-30 17:14:53.700  INFO 28101 --- [io-8080-exec-14] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
30-May-2019 17:14:53.732 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-1] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.733 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-2] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.734 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-3] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.734 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-4] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.734 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-5] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.735 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-6] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.736 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-7] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.736 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-8] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.737 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-9] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.737 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-10] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.738 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-11] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.738 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [udp-nio-12] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.nio.ch.KQueueArrayWrapper.kevent0(Native Method)
 sun.nio.ch.KQueueArrayWrapper.poll(KQueueArrayWrapper.java:198)
 sun.nio.ch.KQueueSelectorImpl.doSelect(KQueueSelectorImpl.java:117)
 sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
 sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
 io.micrometer.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:737)
 io.micrometer.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:392)
 io.micrometer.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.739 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [parallel-1] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.misc.Unsafe.park(Native Method)
 java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
 java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1093)
 java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
 java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
 java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
 java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.739 WARNING [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [sample-micrometer-spring-boot] appears to have started a thread named [parallel-2] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 sun.misc.Unsafe.park(Native Method)
 java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
 java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1093)
 java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
 java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
 java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
 java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 java.lang.Thread.run(Thread.java:748)
30-May-2019 17:14:53.739 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@6ca4e35c]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.740 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@90e0f8f]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.740 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@64d3fc14]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.740 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@39d3ef1a]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.740 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@72a6457f]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.740 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@bad4ed3]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.740 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@706520ee]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.741 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@56e8aac7]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.741 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@162580d8]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.741 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@779f9f19]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.741 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@2e24b354]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.741 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@3d099ac5]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.741 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@790eefc]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.741 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@cfcde40]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
30-May-2019 17:14:53.741 SEVERE [http-nio-8080-exec-14] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [sample-micrometer-spring-boot] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@264f7256]) and a value of type [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap] (value [io.micrometer.shaded.io.netty.util.internal.InternalThreadLocalMap@7497298]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
```
