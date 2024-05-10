#Aqui se va a contener la configuracion de la aplicación 

from flask import Flask
from routes.empleados import empleados

app = Flask(__name__)

app.register_blueprint(empleados)

