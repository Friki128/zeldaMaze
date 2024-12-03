import { draw } from "./roomDraw.js"
let rightInput = document.getElementById("right")
let leftInput = document.getElementById("left")
let upInput = document.getElementById("up")
let downInput = document.getElementById("down")
let coinInput = document.getElementById("coinPosition")
let keyInput = document.getElementById("keyPosition")
let canvas = document.getElementById("canvas")
let loadInput = document.getElementById("load")
let canvasBound = canvas.getBoundingClientRect();
const Yoffset = canvasBound.top;
const Xoffset = canvasBound.left;
loadInput.onclick=function(){
    draw(canvas, leftInput.value, rightInput.value, upInput.value, downInput.value, coinInput.value, keyInput.value)
}

canvas.onclick = function(event){
    let x = getPercentage(event.clientX - Xoffset);
    let y = getPercentage(event.clientY - Yoffset);
    switch(coinInput.value){
        case "0":
            if(collided([25, 25], [x, y], 30, 30))console.log("coin")
            break
        case "1":
            if(collided([75, 25], [x, y], 30, 30))console.log("coin")
            break
        case "2":
            if(collided([25, 75], [x, y], 30, 30))console.log("coin")
            break
        case "3":
            if(collided([75, 75], [x, y], 30, 30))console.log("coin")
            break       
    }
    switch(keyInput.value){
        case "0":
            if(collided([25, 25], [x, y], 30, 30))console.log("key")
            break
        case "1":
            if(collided([75, 25], [x, y], 30, 30))console.log("key")
            break
        case "2":
            if(collided([25, 75], [x, y], 30, 30))console.log("key")
            break
        case "3":
            if(collided([75, 75], [x, y], 30, 30))console.log("key")
            break        
    }

}

function collided(position, mousePosition, width, height){
    return ((mousePosition[0] > position[0] - width/2) && (mousePosition[0] < position[0] + width/2)) && ((mousePosition[1] > position[1] - height/2) && (mousePosition[1] < position[1] + height/2))
}

function getPercentage(value){
    return (value * 100) / canvas.width;
}