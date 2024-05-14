from flask import Flask, jsonify #jsonify nos permite convertir un objeto a JSON tipico del navegador 
from products import products


app = Flask(__name__)


@app.route('/ping')
def ping():
    return jsonify({"message": "pong"})


@app.route('/products')
def getProducts():
    return jsonify(products)

@app.route('/products/:product_name')
def getProduct():
    productsFound = [product for product in products if product["name"] == product_name]
    return jsonify({"products": productsFound[0]})


if __name__ == '__main__':
    app.run(debug = True, port = 5000)
