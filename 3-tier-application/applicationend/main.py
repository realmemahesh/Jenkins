from flask import Flask
import mysql.connector
from mysql.connector import Error

app = Flask(__name__)

@app.route("/")
def home():
    result = ""
    try:
        connection = mysql.connector.connect(
            host='mysql.backend.svc.cluster.local',
            database='appdb',
            user='root',
            password='rootpass'
        )

        if connection.is_connected():
            cursor = connection.cursor()
            cursor.execute("SELECT * FROM users")
            rows = cursor.fetchall()
            for row in rows:
                result += f"{row[0]}: {row[1]}<br/>"

    except Error as e:
        result = f"DB Error: {e}"

    finally:
        if 'cursor' in locals():
            cursor.close()
        if 'connection' in locals() and connection.is_connected():
            connection.close()

    return f"<h1>Application Tier (Python)</h1>{result}"

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000)
