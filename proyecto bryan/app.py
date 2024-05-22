from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from flask_marshmallow import Marshmallow


app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root@localhost/gimnasio'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

    
ma = Marshmallow(app)
db = SQLAlchemy(app)

class Personal(db.Model):
    id = db.Column(db.Integer, primary_key = True)
    nombre = db.Column(db.String(64), nullable = False)
    edad = db.Column(db.Integer, nullable = False)
    direccion = db.Column(db.String(144), nullable = False)
    salario = db.Column(db.Double, nullable = False)
    rol_id = db.Column(db.Integer, db.ForeignKey('rol.id'), nullable = False)
    rol = db.relationship('Rol', backref='personal', lazy=True)

class Rol(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(44), nullable = False)
    descripcion = db.Column(db.String(50), nullable=False)

clase_cliente = db.Table('clase_cliente',
    db.Column('clase_id', db.Integer, db.ForeignKey('clase.id'), primary_key=True),
    db.Column('cliente_id', db.Integer, db.ForeignKey('cliente.id'), primary_key=True)
)

clase_equipo = db.Table('clase_equipo',
    db.Column('clase_id', db.Integer, db.ForeignKey('clase.id'), primary_key = True),
    db.Column('equipo_id', db.Integer, db.ForeignKey('equipo.id'), primary_key = True)
    
)

class Clase(db.Model):
    id = db.Column(db.Integer, primary_key = True)
    nombre = db.Column(db.String(44), nullable = False)
    description = db.Column(db.String(44), nullable = False)
    horario = db.Column(db.String(44), nullable = False)
    personal_id = db.Column(db.Integer, db.ForeignKey('personal.id'),nullable = False)
    personal = db.relationship('Personal', backref = 'clase', lazy = True)
    clientes = db.relationship('Cliente', secondary=clase_cliente, back_populates='clases')
    equipos = db.relationship('Equipo', secondary=clase_equipo, back_populates='clases')



class Cliente(db.Model):
    id = db.Column(db.Integer, primary_key = True)
    nombre = db.Column(db.String(44), nullable = False)
    telefono = db.Column(db.Integer, nullable = False)
    direccion = db.Column(db.String(144), nullable = False)
    email = db.Column(db.String(144), nullable = False)
    clases = db.relationship('Clase', secondary=clase_cliente, back_populates='clientes')
    

class Equipo(db.Model):
    id = db.Column(db.Integer, primary_key = True)
    nombre = db.Column(db.String(44), nullable = False)
    marca = db.Column(db.String(44))
    modelo = db.Column(db.String(44))
    clases = db.relationship('Clase', secondary=clase_equipo, back_populates='equipos')
    

with app.app_context():
    db.create_all()

# Definición de los esquemas con Marshmallow
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

# Crear instancias de los esquemas
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

# Rutas de ejemplo para probar los esquemas
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


if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True)