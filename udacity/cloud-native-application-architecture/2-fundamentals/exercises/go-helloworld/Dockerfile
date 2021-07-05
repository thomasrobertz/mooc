FROM golang:alpine
WORKDIR /go/src/app
COPY ./main.go /go/src/app
EXPOSE 6111/tcp
RUN go mod init helloworld
RUN go build -o helloworld
CMD ./helloworld

