from app import app, db
from models import Rol
from schemas import rol_schema, roles_schema
from flask import jsonify, request

@app.route('/rol', methods=['GET'])
def get_rol():
    roles = Rol.query.all()
    return roles_schema.jsonify(roles)

@app.route('/rol', methods=['POST'])
def add_rol():
    nombre = request.json["nombre"]
    descripcion = request.json["descripcion"]
    nuevo_rol = Rol(nombre=nombre, descripcion=descripcion)
    db.session.add(nuevo_rol)
    db.session.commit()
    return rol_schema.jsonify(nuevo_rol)