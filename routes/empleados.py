from flask import Blueprint, render_template

empleados = Blueprint('empleados', __name__)

@empleados.route('/empleados')
def home():
    return render_template('index.html')


@empleados.route('/new_empleado')
def add_contact():
    return "saving a contact"

@empleados.route('/update_empleado')
def update():
    return "update un empleado"

@empleados.route('/delete_empleado')
def delete():
    return "delete un empleado"
