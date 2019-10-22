# simple-logger-app
simple-logger-app

This is very basic maven app that logs rundom log messages (including stacktrace) with json format to stdout.

To build your own image execute this command:
```
mvn clean package docker:build
```

To run simple-logger-app container execute this command:
```
docker run --rm -it dawidmalina/simple-logger-app:0.0.3-SNAPSHOT
```

By default log messages are generated each 1000 ms. You can change this by SLEEP environment
```
docker run --rm -it -e SLEEP=100 dawidmalina/simple-logger-app:0.0.3-SNAPSHOT
```

To run simple-logger-app container with flat log messages format
```
docker run --rm -it -e SLEEP=100 dawidmalina/simple-logger-app:0.0.3-SNAPSHOT -Dlogback.configurationFile=/opt/app/config/logback-no-json.xml -jar /opt/app/simple-logger-app-0.0.3-SNAPSHOT.jar -d
```
