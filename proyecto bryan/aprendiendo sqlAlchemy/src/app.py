from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from flask_marshmallow import Marshmallow


app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root@localhost/alchemy'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False


ma = Marshmallow(app)
db = SQLAlchemy(app)


class Task(db.Model):
    id = db.Column(db.Integer, primary_key = True)
    title = db.Column(db.String(70), unique=True)
    description = db.Column(db.String(144))
    
    def __init__(self, title, description):
        self.title = title
        self.description = description
        
with app.app_context():
    db.create_all()

class TaskSchema(ma.Schema):
    class Meta:
        fields = ('id', 'title', 'description')
        
task_schema = TaskSchema()
tasks_schema = TaskSchema(many=True)


@app.route('/tasks', methods = ['POST'])
def create_task():
    title = request.json["title"]
    description = request.json["description"]
    new_task = Task(title, description)
    db.session.add(new_task)
    db.session.commit()
    
    return task_schema.jsonify(new_task)


@app.route('/tasks', methods = ['GET'])
def get_task():
    all_tasks = Task.query.all()
    result = tasks_schema.dump(all_tasks)
    return jsonify(result)

if __name__ == '__main__':
    app.run(debug=True)