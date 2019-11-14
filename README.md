# simple-logger-app
simple-logger-app

This is very basic maven app that logs rundom log messages (including stacktrace) with json format to stdout.

To build your own image execute this command:
```
mvn clean package docker:build
```

To run simple-logger-app container execute this command:
```
docker run --rm -it dawidmalina/simple-logger-app:0.0.4-SNAPSHOT
```

By default log messages are generated each 1000 ms. You can change this by SLEEP environment
```
docker run --rm -it -e SLEEP=100 dawidmalina/simple-logger-app:0.0.4-SNAPSHOT
```

To run simple-logger-app container with flat log messages format
```
docker run --rm -it -e SLEEP=100 dawidmalina/simple-logger-app:0.0.4-SNAPSHOT -Dlogback.configurationFile=/opt/app/config/logback-no-json.xml -jar /opt/app/simple-logger-app-0.0.4-SNAPSHOT.jar -d
```

Kubernetes deployments:
```
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: logger-deployment
  labels:
    app: logger
spec:
  replicas: 1
  selector:
    matchLabels:
      app: logger
  template:
    metadata:
      labels:
        app: logger
    spec:
      containers:
      - name: logger
        image: dawidmalina/simple-logger-app:0.0.3-SNAPSHOT
        env:
        - name: SLEEP
          value: "100"
```

```
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: flat-logger-deployment
  labels:
    app: flat-logger
spec:
  replicas: 1
  selector:
    matchLabels:
      app: flat-logger
  template:
    metadata:
      labels:
        app: flat-logger
    spec:
      containers:
      - name: flat-logger
        image: dawidmalina/simple-logger-app:0.0.3-SNAPSHOT
        env:
        - name: SLEEP
          value: "100"
        args: ["-Dlogback.configurationFile=/opt/app/config/logback-no-json.xml", "-jar", "/opt/app/simple-logger-app-0.0.3-SNAPSHOT.jar", "-d"]
```
