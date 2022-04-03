import json

from kafka import KafkaProducer
from flask import Flask, jsonify, request, g, Response

from .services import retrieve_orders, create_order

app = Flask(__name__)

TOPIC_NAME = 'udacity-items'

@app.before_request
def before_request():
    # Set up a Kafka producer
    KAFKA_SERVER = 'localhost:9092'
    producer = KafkaProducer(bootstrap_servers = KAFKA_SERVER)
    # Setting Kafka to g enables us to use this
    # in other parts of our application
    g.kafka_producer = producer

@app.route('/api/orders/computers', methods = ['GET', 'POST'])
def computers():
    if request.method == 'GET':
        return jsonify(retrieve_orders())
    elif request.method == 'POST':
        request_body = request.json
        create_order(request_body)
        g.kafka_producer.send(TOPIC_NAME, b"Created an order")
        g.kafka_producer.flush()
        return Response(status=202)
    else:
        raise Exception('Unsupported HTTP request type.')


if __name__ == '__main__':
    app.run()
