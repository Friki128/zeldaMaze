import { draw } from "./roomDraw.js"
let rightInput = document.getElementById("right")
let leftInput = document.getElementById("left")
let upInput = document.getElementById("up")
let downInput = document.getElementById("down")
let coinInput = document.getElementById("coinPosition")
let keyInput = document.getElementById("keyPosition")
let canvas = document.getElementById("canvas")
let canvasBound = canvas.getBoundingClientRect();
const Yoffset = canvasBound.top;
const Xoffset = canvasBound.left;
function load(){
    draw(canvas, leftInput.value, rightInput.value, upInput.value, downInput.value, coinInput.value, keyInput.value)
}

load()

canvas.onclick = function(event){
    let x = getPercentage(event.clientX - Xoffset);
    let y = getPercentage(event.clientY - Yoffset);
    switch(coinInput.value){
        case "0":
            if(collided([25, 25], [x, y], 30, 30)) window.location.replace("/coin")
            break
        case "1":
            if(collided([75, 25], [x, y], 30, 30)) window.location.replace("/coin")
            break
        case "2":
            if(collided([25, 75], [x, y], 30, 30)) window.location.replace("/coin")
            break
        case "3":
            if(collided([75, 75], [x, y], 30, 30)) window.location.replace("/coin")
            break       
    }
    switch(keyInput.value){
        case "0":
            if(collided([25, 25], [x, y], 30, 30)) window.location.replace("/key")
            break
        case "1":
            if(collided([75, 25], [x, y], 30, 30)) window.location.replace("/key")
            break
        case "2":
            if(collided([25, 75], [x, y], 30, 30)) window.location.replace("/key")
            break
        case "3":
            if(collided([75, 75], [x, y], 30, 30)) window.location.replace("/key")
            break        
    }
    if((leftInput.value != "Open" && leftInput.value != "Wall") && collided([0, 50], [x,y], 30, 30)) window.location.replace("/open?dir=W")
    if((rightInput.value != "Open" && rightInput.value != "Wall") && collided([100, 50], [x,y], 30, 30)) window.location.replace("/open?dir=E")
    if((upInput.value != "Open" && upInput.value != "Wall") && collided([50, 0], [x,y], 30, 30)) window.location.replace("/open?dir=N")
    if((downInput.value != "Open" && downInput.value != "Wall") && collided([50, 100], [x,y], 30, 30)) window.location.replace("/open?dir=S")

}

function collided(position, mousePosition, width, height){
    return ((mousePosition[0] > position[0] - width/2) && (mousePosition[0] < position[0] + width/2)) && ((mousePosition[1] > position[1] - height/2) && (mousePosition[1] < position[1] + height/2))
}

function getPercentage(value){
    return (value * 100) / canvas.width;
}

document.onkeydown = function (event) {
    let dir = ""
    switch(event.key){
        case "ArrowDown":
            dir = "S";
            break;
        case "ArrowLeft":
            dir = "W";
            break;
        case "ArrowRight":
            dir = "E";
            break;
        case "ArrowUp":
            dir = "N";
            break;            
    }
    if(dir != ""){
        window.location.replace("/move?dir=" + dir);
    }
};