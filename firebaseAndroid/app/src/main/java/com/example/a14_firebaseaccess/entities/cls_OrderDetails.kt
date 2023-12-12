package com.example.a14_firebaseaccess.entities

class cls_OrderDetails {
    var ProductID: String = ""
    var Quantity: String = ""
    var UnitPrice: String = ""
    var Discount: String = ""

    constructor() {}

    constructor(ProductID: String, Quantity: String, UnitPrice: String, Discount: String) {
        this.ProductID = ProductID
        this.Quantity = Quantity
        this.UnitPrice = UnitPrice
        this.Discount = Discount
    }
}