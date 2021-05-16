package com.example.jumptestdemo.bean

class Scheme {
    private val tag: String
    private val parameters: List<Parameter>

    constructor(tag: String, parameters: List<Parameter>){
        this.tag = tag
        this.parameters = parameters
    }


}