from flask import Flask, jsonify, request #jsonify nos permite convertir un objeto a JSON tipico del navegador 
#request lo que hace es poder guardar los datos que se envien a traves del cliente 
from products import products


app = Flask(__name__)


@app.route('/ping')
def ping():
    return jsonify({"message": "pong"})


@app.route('/products')
def getProducts():
    return jsonify(products)

@app.route('/products', methods = ['POST'])
def addProducts():
    newProduct = {
        "name": request.json["name"],
        "price": request.json["price"],
        "quantity": request.json["quantity"]
    }
    products.append(newProduct)
    return jsonify({"messaje": "Producto agregado satisfactoriamente"},{"products" : products})
    
    

@app.route('/products/<string:product_name>', methods = ['PUT'])
def updateProduct(product_name):
    productsFound = [product for product in products if product["name"] == product_name]
    if len(productsFound) > 0:
        productsFound[0]['name'] = request.json["name"]
        productsFound[0]['price'] = request.json["price"]
        productsFound[0]['quantity'] = request.json["quantity"]
        return jsonify({"messaje": "Producto Actualizado", "product" : productsFound[0]})
    return jsonify({"message":"producto no encontrado"})
    

@app.route('/products/<string:product_name>')
def getProduct(product_name):
    productsFound = [product for product in products if product["name"] == product_name]
    
    if len(productsFound) > 0:
        return jsonify({"products": productsFound[0]})
    return jsonify({"messaje" : "Producto no encontrado"}) 

@app.route('/products/<string:product_name>', methods = ['DELETE'])
def deleteProduct(product_name):
    productsFound = [product for product in products if product["name"] == product_name]

    if len(productsFound)>0:
        remove(products[productsFound[0]])
        return jsonify({"message" : products})
    return jsonify({"message": "Producto no encontrado"})

if __name__ == '__main__':
    app.run(debug = True, port = 5000)
    
