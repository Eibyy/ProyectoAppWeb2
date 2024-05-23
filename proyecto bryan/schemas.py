from app import ma
from models import Personal, Rol, Clase, Cliente, Equipo

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
