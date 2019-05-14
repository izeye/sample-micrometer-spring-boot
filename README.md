# Micrometer for Elasticsearch

## Elasticsearch APIs

* Get all indices:

```
$ curl http://localhost:9200/_cat/indices?v
```

* Get mapping for index:

```
$ curl http://localhost:9200/metrics-2019-05/_mapping?pretty
```

* Delete index:

```
$ curl -XDELETE http://localhost:9200/metrics-2019-05
```

* Bulk index:

```
$ curl -XPOST --header "Content-Type: application/json" --data-binary "@request_body.txt" http://localhost:9200/metrics-2019-05/doc/_bulk
```
