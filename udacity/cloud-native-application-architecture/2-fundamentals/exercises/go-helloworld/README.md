# Go Hello World

This is a simple Go web application that prints a "Hello World" message.

## Run the application

This application listens on port `6111`

To run the application, use the following command:
```
go run main.go 
```

Access the application on: http://127.0.0.1:6111/

## Run the docker image

```
docker run -p 6111:6111 thomasrobertz/helloworld-go:0.0.1
```