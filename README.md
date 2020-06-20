# Micrometer for Stackdriver

## Create a new project
* Go to the "New Project" page: https://console.cloud.google.com/projectcreate
* Fill "Project name" as follows:

```
Project name: micrometer-spring-boot-tests
```

* Click "CREATE" button.

## Create a service account key
* Go to the "Create service account key" page: https://console.cloud.google.com/apis/credentials/serviceaccountkey?project=micrometer-spring-boot-tests
* Select "New service account" in the "Service account" drop-down list.
* Fill "Service account name" as follows:

```
Service account name: metric-writer
```

* Select "Role" as follows:

```
Role: Monitoring/Monitoring Metric Writer
```

* Click "Create" button, and then a generated service account key file will be downloaded.

## Set `GOOGLE_APPLICATION_CREDENTIALS` environment variable

* Set `GOOGLE_APPLICATION_CREDENTIALS` environment variable with the downloaded file from the previous step as follows:

```
export GOOGLE_APPLICATION_CREDENTIALS=/Users/izeye/workspaces/micrometer/stackdriver/micrometer-spring-boot-tests-x.json
```

## Note

* I didn't check any further as I didn't set up billing for the project, and I got the following exception:

```
Caused by: io.grpc.StatusRuntimeException: PERMISSION_DENIED: This API method requires billing to be enabled. Please enable billing on project #695044409890 by visiting https://console.developers.google.com/billing/enable?project=695044409890 then retry. If you enabled billing for this project recently, wait a few minutes for the action to propagate to our systems and retry.
        at io.grpc.Status.asRuntimeException(Status.java:533) ~[grpc-api-1.28.1.jar:1.28.1]
```
