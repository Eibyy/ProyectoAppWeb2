from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config["SQLALCHEMY_DATABASE_URI"] = 'mysql+pymysql://root@localhost/alchemy'
app.config["SQLALCHEMY_TRACK_MODIFICTACIONS"] = False
db = SQLAlchemy(app)

#probando