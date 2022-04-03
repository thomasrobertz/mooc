import json

import flask
from flask import Flask, jsonify, request, Blueprint

from .services import retrieve_orders, create_order

app = Flask(__name__)

v1 = Blueprint("version1", "version1")
v2 = Blueprint('version2', "version2")

@v2.route('/orders/computers', methods=['GET', 'POST'])
def computers():
    return versionedService(2)

@v1.route('/orders/computers', methods=['GET', 'POST'])
def computers():
    return versionedService(1)

def versionedService(version):
    if request.method == 'GET':
        resp = jsonify(retrieve_orders())
    elif request.method == 'POST':
        resp = jsonify(create_order(request.json))
    else:
        raise Exception('Unsupported HTTP request type.')
    resp.headers['API_VERSION'] = str(version)
    return resp

app.register_blueprint(v1, url_prefix="/api/")
app.register_blueprint(v2, url_prefix="/api/v2")

if __name__ == '__main__':
    app.run()
