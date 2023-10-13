package com.designclub.ultimate.fc24

class ModelNews {
    var id: Int = 0
    var category: String = ""
    var new: Boolean = false
    var image: String = ""
    var title: String = ""
    var text1: String = ""
    var text2: String = ""
    var text3: String = ""

    constructor(id: Int, category: String, new: Boolean, image: String, title: String) {
        this.id = id
        this.category = category
        this.new = new
        this.image = image
        this.title = title
    }
}