# Micrometer for InfluxDB

* Run InfluxDB as follows:

```
$ docker run -p 8086:8086 --name my-influxdb influxdb
```

* Check metrics in InfluxDB as follows:

```
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
9c56d4ea99d8        influxdb            "/entrypoint.sh infl…"   18 seconds ago      Up 17 seconds       0.0.0.0:8086->8086/tcp   my-influxdb
$ docker exec -it my-influxdb bash
root@9c56d4ea99d8:/# influx  
Connected to http://localhost:8086 version 1.8.0
InfluxDB shell version: 1.8.0
> show databases
name: databases
name
----
_internal
mydb
> use mydb
Using database mydb
> show series
key
---
jvm_buffer_count,id=direct,metric_type=gauge
jvm_buffer_count,id=mapped,metric_type=gauge
jvm_buffer_memory_used,id=direct,metric_type=gauge
jvm_buffer_memory_used,id=mapped,metric_type=gauge
jvm_buffer_total_capacity,id=direct,metric_type=gauge
jvm_buffer_total_capacity,id=mapped,metric_type=gauge
jvm_classes_loaded,metric_type=gauge
jvm_classes_unloaded,metric_type=counter
jvm_gc_live_data_size,metric_type=gauge
jvm_gc_max_data_size,metric_type=gauge
jvm_gc_memory_allocated,metric_type=counter
jvm_gc_memory_promoted,metric_type=counter
jvm_gc_pause,action=end\ of\ minor\ GC,cause=Metadata\ GC\ Threshold,metric_type=histogram
jvm_memory_committed,area=heap,id=G1\ Eden\ Space,metric_type=gauge
jvm_memory_committed,area=heap,id=G1\ Old\ Gen,metric_type=gauge
jvm_memory_committed,area=heap,id=G1\ Survivor\ Space,metric_type=gauge
jvm_memory_committed,area=nonheap,id=CodeHeap\ 'non-nmethods',metric_type=gauge
jvm_memory_committed,area=nonheap,id=CodeHeap\ 'non-profiled\ nmethods',metric_type=gauge
jvm_memory_committed,area=nonheap,id=Compressed\ Class\ Space,metric_type=gauge
jvm_memory_committed,area=nonheap,id=Metaspace,metric_type=gauge
jvm_memory_max,area=heap,id=G1\ Eden\ Space,metric_type=gauge
jvm_memory_max,area=heap,id=G1\ Old\ Gen,metric_type=gauge
jvm_memory_max,area=heap,id=G1\ Survivor\ Space,metric_type=gauge
jvm_memory_max,area=nonheap,id=CodeHeap\ 'non-nmethods',metric_type=gauge
jvm_memory_max,area=nonheap,id=CodeHeap\ 'non-profiled\ nmethods',metric_type=gauge
jvm_memory_max,area=nonheap,id=Compressed\ Class\ Space,metric_type=gauge
jvm_memory_max,area=nonheap,id=Metaspace,metric_type=gauge
jvm_memory_used,area=heap,id=G1\ Eden\ Space,metric_type=gauge
jvm_memory_used,area=heap,id=G1\ Old\ Gen,metric_type=gauge
jvm_memory_used,area=heap,id=G1\ Survivor\ Space,metric_type=gauge
jvm_memory_used,area=nonheap,id=CodeHeap\ 'non-nmethods',metric_type=gauge
jvm_memory_used,area=nonheap,id=CodeHeap\ 'non-profiled\ nmethods',metric_type=gauge
jvm_memory_used,area=nonheap,id=Compressed\ Class\ Space,metric_type=gauge
jvm_memory_used,area=nonheap,id=Metaspace,metric_type=gauge
jvm_threads_daemon,metric_type=gauge
jvm_threads_live,metric_type=gauge
jvm_threads_peak,metric_type=gauge
jvm_threads_states,metric_type=gauge,state=blocked
jvm_threads_states,metric_type=gauge,state=new
jvm_threads_states,metric_type=gauge,state=runnable
jvm_threads_states,metric_type=gauge,state=terminated
jvm_threads_states,metric_type=gauge,state=timed-waiting
jvm_threads_states,metric_type=gauge,state=waiting
logback_events,level=debug,metric_type=counter
logback_events,level=error,metric_type=counter
logback_events,level=info,metric_type=counter
logback_events,level=trace,metric_type=counter
logback_events,level=warn,metric_type=counter
process_cpu_usage,metric_type=gauge
process_files_max,metric_type=gauge
process_files_open,metric_type=gauge
process_start_time,metric_type=gauge
process_uptime,metric_type=gauge
system_cpu_count,metric_type=gauge
system_cpu_usage,metric_type=gauge
system_load_average_1m,metric_type=gauge
tomcat_sessions_active_current,metric_type=gauge
tomcat_sessions_active_max,metric_type=gauge
tomcat_sessions_alive_max,metric_type=gauge
tomcat_sessions_created,metric_type=counter
tomcat_sessions_expired,metric_type=counter
tomcat_sessions_rejected,metric_type=counter
```
