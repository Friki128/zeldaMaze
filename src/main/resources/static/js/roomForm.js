import { draw } from "./roomDraw.js"
let down = document.getElementById("down")
let up = document.getElementById("up")
let right = document.getElementById("right")
let left = document.getElementById("left")
let coin = document.getElementById("coin")
let key = document.getElementById("key")
let canvas = document.getElementById("canvas")

function loadMap(){
    draw(canvas, left.value, right.value, up.value, down.value, coin.value, key.value)
}

down.onchange = function(){
    loadMap()
}

up.onchange = function(){
    loadMap()
}

left.onchange = function(){
    loadMap()
}

right.onchange = function(){
    loadMap()
}

coin.onchange = function(){
    loadMap()
}

key.onchange = function(){
    loadMap()
}

loadMap()