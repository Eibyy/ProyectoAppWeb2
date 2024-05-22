from app import db

class Personal(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(64), nullable=False)
    edad = db.Column(db.Integer, nullable=False)
    direccion = db.Column(db.String(144), nullable=False)
    salario = db.Column(db.Float, nullable=False)
    rol_id = db.Column(db.Integer, db.ForeignKey('rol.id'), nullable=False)
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
