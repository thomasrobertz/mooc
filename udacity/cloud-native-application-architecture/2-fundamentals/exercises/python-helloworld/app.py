from flask import Flask, redirect
app = Flask(__name__)

HTTP_STATUS_OK = 200

@app.route("/")
def index():
    return redirect('/status')

@app.route("/status")
def status():
    return {
        "result": "OK - healthy"
    }, HTTP_STATUS_OK

@app.route("/metrics")
def metrics():
    return { "data" : {
        "UserCount": 100,
        "UserCountActive": 17
    }}, HTTP_STATUS_OK

if __name__ == "__main__":
    app.run(host='0.0.0.0', debug=True)
