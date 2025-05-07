from flask import Flask, redirect
app = Flask(__name__)

@app.route("/")
def index():
    return '''
    <h1>Frontend (Python Flask)</h1>
    <a href="http://java-svc.app.svc.cluster.local:8080/">Go to Application Tier</a>
    '''

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
