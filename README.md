# Micrometer for Elasticsearch

## Run with Docker

* Run Elasticsearch:

```
$ docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.9.0
```

## Elasticsearch APIs

* Get documents from metrics index:

```
$ curl http://localhost:9200/micrometer-metrics-2020-11/_search?pretty
```

* Get all indices:

```
$ curl http://localhost:9200/_cat/indices?v
```

* Get mapping for a specific index:

```
$ curl http://localhost:9200/metrics-2019-05/_mapping?pretty
```

* Delete a specific index:

```
$ curl -XDELETE http://localhost:9200/metrics-2019-05
```

* Bulk index:

```
$ curl -XPOST --header "Content-Type: application/json" --data-binary "@request_body.txt" http://localhost:9200/metrics-2019-05/doc/_bulk
```

* Get all index templates:

```
$ curl http://localhost:9200/_template?pretty
```

* Get a specific index template:

```
$ curl http://localhost:9200/_template/metrics_template?pretty
```

* Delete a specific index template:

```
$ curl -XDELETE http://localhost:9200/_template/metrics_template
```
