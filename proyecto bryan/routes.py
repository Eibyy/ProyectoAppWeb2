from flask import jsonify, request
from app import app
from models import Personal, Rol, Clase, Cliente, Equipo
from database import db
from flask_marshmallow import Marshmallow

ma = Marshmallow()

class PersonalSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Personal
        include_fk = True

class RolSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Rol

class ClaseSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Clase
        include_fk = True
    personal = ma.Nested(PersonalSchema, only=['id', 'nombre'])
    clientes = ma.Nested('ClienteSchema', many=True, exclude=('clases',))
    equipos = ma.Nested('EquipoSchema', many=True, exclude=('clases',))

class ClienteSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Cliente
        include_relationships = True
    clases = ma.Nested(ClaseSchema, many=True, exclude=('clientes',))

class EquipoSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Equipo
    clases = ma.Nested(ClaseSchema, many=True, exclude=('equipos',))
personal_schema = PersonalSchema()
personales_schema = PersonalSchema(many=True)

rol_schema = RolSchema()
roles_schema = RolSchema(many=True)

clase_schema = ClaseSchema()
clases_schema = ClaseSchema(many=True)

cliente_schema = ClienteSchema()
clientes_schema = ClienteSchema(many=True)

equipo_schema = EquipoSchema()
equipos_schema = EquipoSchema(many=True)

@app.route('/clases', methods=['GET'])
def get_clases():
    clases = Clase.query.all()
    return clases_schema.jsonify(clases)

@app.route('/clientes', methods=['GET'])
def get_clientes():
    clientes = Cliente.query.all()
    return clientes_schema.jsonify(clientes)

@app.route('/rol', methods=['GET'])
def get_rol():
    rol = Rol.query.all()
    return equipos_schema.jsonify(rol)

@app.route('/rol', methods = ['POST'])
def add_rol():
    nombre = request.json["nombre"]
    descripcion = request.json["descripcion"]
    nuevo_rol = Rol(nombre=nombre, descripcion=descripcion)
    db.session.add(nuevo_rol)
    db.session.commit()
    return rol_schema.jsonify(nuevo_rol)