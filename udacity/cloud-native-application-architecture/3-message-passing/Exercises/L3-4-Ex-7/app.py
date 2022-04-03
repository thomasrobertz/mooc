import json

import flask
from flask import Flask, jsonify, request

from .services import retrieve_orders, create_order

app = Flask(__name__)

@app.route('/orders/computers', methods=['GET', 'POST'])
def computers():
    if request.method == 'GET':
        return jsonify(retrieve_orders())
    elif request.method == 'POST':
        return jsonify(create_order(request.json))
    else:
        raise Exception('Unsupported HTTP request type.')
    return resp

if __name__ == '__main__':
    app.run()
