from flask import Flask, redirect
app = Flask(__name__)

@app.route("/")
def index():
    return '''
    <h1>Frontend (Python Flask)</h1>
    <a href="https://ingress.kubernetes/app">Go to Application Tier</a>
    '''

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
