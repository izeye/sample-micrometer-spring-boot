# Micrometer for Elasticsearch

## Elasticsearch APIs

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
