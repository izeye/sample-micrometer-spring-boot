# Micrometer for StatsD (Telegraf)

* Run StatsD (Telegraf), InfluxDB, and Grafana as follows:

```
$ docker run --ulimit nofile=66000:66000 \
  -d \
  --name my-statsd \
  -p 3003:3003 \
  -p 3004:8888 \
  -p 8086:8086 \
  -p 8125:8125/udp \
  samuelebistoletti/docker-statsd-influxdb-grafana
```

* Check metrics in Influx as follows:

```
$ docker ps
CONTAINER ID        IMAGE                                              COMMAND                  CREATED             STATUS              PORTS                                                                                            NAMES
2cc94abd299d        samuelebistoletti/docker-statsd-influxdb-grafana   "/usr/bin/supervisord"   8 seconds ago       Up 6 seconds        0.0.0.0:3003->3003/tcp, 0.0.0.0:8086->8086/tcp, 0.0.0.0:8125->8125/udp, 0.0.0.0:3004->8888/tcp   my-statsd
$ docker exec -it my-statsd bash
root@2cc94abd299d:~# influx
Connected to http://localhost:8086 version 1.7.10
InfluxDB shell version: 1.7.10
> show databases
name: databases
name
----
_internal
telegraf
> use telegraf
Using database telegraf
> show series
key
---
cpu,cpu=cpu-total,host=392677e895bc
cpu,cpu=cpu0,host=392677e895bc
cpu,cpu=cpu1,host=392677e895bc
cpu,cpu=cpu2,host=392677e895bc
cpu,cpu=cpu3,host=392677e895bc
cpu,cpu=cpu4,host=392677e895bc
cpu,cpu=cpu5,host=392677e895bc
disk,device=overlay,fstype=overlay,host=392677e895bc,mode=rw,path=/
disk,device=vda1,fstype=ext4,host=392677e895bc,mode=rw,path=/etc/hostname
disk,device=vda1,fstype=ext4,host=392677e895bc,mode=rw,path=/etc/hosts
disk,device=vda1,fstype=ext4,host=392677e895bc,mode=rw,path=/etc/resolv.conf
diskio,host=392677e895bc,name=sr0
diskio,host=392677e895bc,name=sr1
diskio,host=392677e895bc,name=sr2
diskio,host=392677e895bc,name=vda
diskio,host=392677e895bc,name=vda1
jvm_buffer_count,host=392677e895bc,id=direct,metric_type=gauge,statistic=value
jvm_buffer_count,host=392677e895bc,id=mapped,metric_type=gauge,statistic=value
jvm_buffer_memory_used,host=392677e895bc,id=direct,metric_type=gauge,statistic=value
jvm_buffer_memory_used,host=392677e895bc,id=mapped,metric_type=gauge,statistic=value
jvm_buffer_total_capacity,host=392677e895bc,id=direct,metric_type=gauge,statistic=value
jvm_buffer_total_capacity,host=392677e895bc,id=mapped,metric_type=gauge,statistic=value
jvm_classes_loaded,host=392677e895bc,metric_type=gauge,statistic=value
jvm_classes_unloaded,host=392677e895bc,metric_type=counter,statistic=count
jvm_gc_live_data_size,host=392677e895bc,metric_type=gauge,statistic=value
jvm_gc_max_data_size,host=392677e895bc,metric_type=gauge,statistic=value
jvm_memory_committed,area=heap,host=392677e895bc,id=G1_Eden_Space,metric_type=gauge,statistic=value
jvm_memory_committed,area=heap,host=392677e895bc,id=G1_Old_Gen,metric_type=gauge,statistic=value
jvm_memory_committed,area=heap,host=392677e895bc,id=G1_Survivor_Space,metric_type=gauge,statistic=value
jvm_memory_committed,area=nonheap,host=392677e895bc,id=CodeHeap_'non-nmethods',metric_type=gauge,statistic=value
jvm_memory_committed,area=nonheap,host=392677e895bc,id=CodeHeap_'non-profiled_nmethods',metric_type=gauge,statistic=value
jvm_memory_committed,area=nonheap,host=392677e895bc,id=Compressed_Class_Space,metric_type=gauge,statistic=value
jvm_memory_committed,area=nonheap,host=392677e895bc,id=Metaspace,metric_type=gauge,statistic=value
jvm_memory_max,area=heap,host=392677e895bc,id=G1_Eden_Space,metric_type=gauge,statistic=value
jvm_memory_max,area=heap,host=392677e895bc,id=G1_Old_Gen,metric_type=gauge,statistic=value
jvm_memory_max,area=heap,host=392677e895bc,id=G1_Survivor_Space,metric_type=gauge,statistic=value
jvm_memory_max,area=nonheap,host=392677e895bc,id=CodeHeap_'non-nmethods',metric_type=gauge,statistic=value
jvm_memory_max,area=nonheap,host=392677e895bc,id=CodeHeap_'non-profiled_nmethods',metric_type=gauge,statistic=value
jvm_memory_max,area=nonheap,host=392677e895bc,id=Compressed_Class_Space,metric_type=gauge,statistic=value
jvm_memory_max,area=nonheap,host=392677e895bc,id=Metaspace,metric_type=gauge,statistic=value
jvm_memory_used,area=heap,host=392677e895bc,id=G1_Eden_Space,metric_type=gauge,statistic=value
jvm_memory_used,area=heap,host=392677e895bc,id=G1_Old_Gen,metric_type=gauge,statistic=value
jvm_memory_used,area=heap,host=392677e895bc,id=G1_Survivor_Space,metric_type=gauge,statistic=value
jvm_memory_used,area=nonheap,host=392677e895bc,id=CodeHeap_'non-nmethods',metric_type=gauge,statistic=value
jvm_memory_used,area=nonheap,host=392677e895bc,id=CodeHeap_'non-profiled_nmethods',metric_type=gauge,statistic=value
jvm_memory_used,area=nonheap,host=392677e895bc,id=Compressed_Class_Space,metric_type=gauge,statistic=value
jvm_memory_used,area=nonheap,host=392677e895bc,id=Metaspace,metric_type=gauge,statistic=value
jvm_threads_daemon,host=392677e895bc,metric_type=gauge,statistic=value
jvm_threads_live,host=392677e895bc,metric_type=gauge,statistic=value
jvm_threads_peak,host=392677e895bc,metric_type=gauge,statistic=value
jvm_threads_states,host=392677e895bc,metric_type=gauge,state=blocked,statistic=value
jvm_threads_states,host=392677e895bc,metric_type=gauge,state=new,statistic=value
jvm_threads_states,host=392677e895bc,metric_type=gauge,state=runnable,statistic=value
jvm_threads_states,host=392677e895bc,metric_type=gauge,state=terminated,statistic=value
jvm_threads_states,host=392677e895bc,metric_type=gauge,state=timed-waiting,statistic=value
jvm_threads_states,host=392677e895bc,metric_type=gauge,state=waiting,statistic=value
kernel,host=392677e895bc
logback_events,host=392677e895bc,level=info,metric_type=counter,statistic=count
mem,host=392677e895bc
process_cpu_usage,host=392677e895bc,metric_type=gauge,statistic=value
process_files_max,host=392677e895bc,metric_type=gauge,statistic=value
process_files_open,host=392677e895bc,metric_type=gauge,statistic=value
process_start_time,host=392677e895bc,metric_type=gauge,statistic=value
process_uptime,host=392677e895bc,metric_type=gauge,statistic=value
processes,host=392677e895bc
swap,host=392677e895bc
system,host=392677e895bc
system_cpu_count,host=392677e895bc,metric_type=gauge,statistic=value
system_cpu_usage,host=392677e895bc,metric_type=gauge,statistic=value
system_load_average_1m,host=392677e895bc,metric_type=gauge,statistic=value
tomcat_sessions_active_current,host=392677e895bc,metric_type=gauge,statistic=value
tomcat_sessions_active_max,host=392677e895bc,metric_type=gauge,statistic=value
tomcat_sessions_alive_max,host=392677e895bc,metric_type=gauge,statistic=value
tomcat_sessions_created,host=392677e895bc,metric_type=counter,statistic=count
tomcat_sessions_expired,host=392677e895bc,metric_type=counter,statistic=count
tomcat_sessions_rejected,host=392677e895bc,metric_type=counter,statistic=count
> 
```
