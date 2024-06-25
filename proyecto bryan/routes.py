from app import app, db
from models import *
from schemas import *
from flask import jsonify, request
import jwt 
import datetime
from flask_jwt_extended import jwt_required
from jwt.exceptions import ExpiredSignatureError


@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    username = data['username']
    password = data['password']
    
    user = Usuario.query.filter_by(username=username).first()
    
    if user is None:
        return jsonify({"message": "el usuario no ha sido encontrado"}), 401

    if not user.check_password(password):
        return jsonify({"message": "Contraseña invalida"}), 401


    token = jwt.encode({
        'sub': user.id,
        'iat': datetime.datetime.utcnow(),
        'exp': datetime.datetime.utcnow() + datetime.timedelta(hours=1) #timedelta se utiliza para representar la diferencia entre dos fechas o tiempos
    }, app.config['JWT_SECRET_KEY'], algorithm='HS256')
    print(token)

    

    return jsonify({'access_token': token}), 200



@app.errorhandler(jwt.InvalidTokenError)
def handle_invalid_token_error(error):
    return jsonify({"message": "Acceso denegado, token JWT inválido"}), 401

#ROLES

@app.route('/rol', methods=['GET'])
@jwt_required()
def get_rol():
    roles = Rol.query.all()
    return roles_schema.jsonify(roles)

@app.route('/rol', methods=['POST'])
@jwt_required()
def add_rol():
    nombre = request.json["nombre"]
    descripcion = request.json["descripcion"]
    nuevo_rol = Rol(nombre=nombre, descripcion=descripcion)
    db.session.add(nuevo_rol)
    db.session.commit()
    return rol_schema.jsonify(nuevo_rol)



#Cliente
@app.route('/cliente', methods=['GET'])
@jwt_required()
def get_cliente():
    cliente = Cliente.query.all()
    return clientes_schema.jsonify(cliente)

@app.route('/cliente', methods=['POST'])
@jwt_required()
def add_cliente():
    nombre = request.json["nombre"]
    telefono = request.json["telefono"]
    direccion = request.json["direccion"]
    email = request.json["email"]
    nuevo_cliente = Cliente(nombre=nombre, telefono=telefono, direccion = direccion, email = email)
    db.session.add(nuevo_cliente)
    db.session.commit()
    return rol_schema.jsonify(nuevo_cliente)

@app.route('/cliente/<int:id>', methods=['PUT'])
@jwt_required()
def eliminacion_logica_cliente(id):
    cliente = Cliente.query.get_or_404(id)
    cliente.estado_eliminado = request.json.get('estado_eliminado', cliente.estado_eliminado)
    
    db.session.commit()
    return jsonify({"message": "Estado actualizado con éxito", "cliente": rol_schema.dump(cliente)})


#Personal
# @app.route('/personal', methods=['GET'])
# @jwt_required()
# def get_personal():
#     personal = Personal.query.all()
#     return personales_schema.jsonify(personal)

# @app.route('/personal', methods=['POST'])
# @jwt_required()
# def add_personal():
#     nombre = request.json["nombre"]
#     edad = request.json["edad"]
#     direccion = request.json["direccion"]
#     salario = request.json["salario"]
#     rol_id = request.json["rol_id"]
#     nuevo_personal = Personal(nombre=nombre, edad=edad, direccion = direccion, salario = salario, rol_id = rol_id)
#     db.session.add(nuevo_personal)
#     db.session.commit()
#     return rol_schema.jsonify(nuevo_personal)

# @app.route('/personal/<int:id>', methods=['PUT'])
# @jwt_required()
# def eliminacion_logica_personal(id):
#     personal = Personal.query.get_or_404(id)
#     personal.estado_eliminado = request.json.get('estado_eliminado', personal.estado_eliminado)
    
#     db.session.commit()
#     return jsonify({"message": "Estado actualizado con éxito", "cliente": rol_schema.dump(personal)})

# #Equipos
# @app.route('/equipo', methods=['GET'])
# @jwt_required()
# def get_equipo():
#     equipo = Equipo.query.all()
#     return personales_schema.jsonify(equipo)

# @app.route('/equipo', methods=['POST'])
# @jwt_required()
# def add_equipo():
#     nombre = request.json["nombre"]
#     marca = request.json["marca"]
#     modelo = request.json["modelo"]
#     salario = request.json["salario"]
#     nuevo_equipo = Equipo(nombre=nombre, marca=marca, modelo = modelo, salario = salario)
#     db.session.add(nuevo_equipo)
#     db.session.commit()
#     return rol_schema.jsonify(nuevo_equipo)

#Clases 
@app.route('/clase', methods=['GET'])
@jwt_required()
def get_clase():
    clase = Clase.query.all()
    return personales_schema.jsonify(clase)

@app.route('/clase', methods=['POST'])
@jwt_required()
def add_clase():
    nombre = request.json["nombre"]
    description = request.json["description"]
    horario = request.json["horario"]
    personal_id = request.json["personal_id"]
    nuevo_clase = Equipo(nombre=nombre, description=description, horario = horario, personal_id = personal_id)
    db.session.add(nuevo_clase)
    db.session.commit()
    return rol_schema.jsonify(nuevo_clase)

# #Registro
# @app.route('/register', methods=['POST'])
# def register():
#     data = request.get_json()
#     username = data['username']
#     password = data['password']
    
#     if Usuario.query.filter_by(username=username).first():
#         return jsonify({"mensaje": "El usuario ya existe"}), 400
    
#     new_user = Usuario(username=username, password=password)
#     # new_user.set_password(password)
    
#     db.session.add(new_user)
#     db.session.commit()
    
#     return usuario_schema.jsonify(new_user), 201

