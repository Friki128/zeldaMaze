let keyImage = new Image()
keyImage.src = "/img/key.webp"
let coinImage = new Image()
coinImage.src = "/img/coin.gif"
export function draw(canvas, left, right, up, down, coin, key){
    let context = canvas.getContext("2d")
    let size = canvas.width
    context.fillStyle = "#000000"
    context.strokeStyle = "#000000"
    context.fillRect(adaptSize(size, 0), adaptSize(size, 0), adaptSize(size, 100), adaptSize(size, 100))
    drawDoor(left, [adaptSize(size, 0), adaptSize(size, 35)], adaptSize(size, 30), context)
    drawDoor(right, [adaptSize(size,80), adaptSize(size, 35)], adaptSize(size, 30), context)
    drawDoor(up, [adaptSize(size, 35), adaptSize(size, 0)], adaptSize(size, 30), context)
    drawDoor(down, [adaptSize(size, 35), adaptSize(size, 80)], adaptSize(size, 30), context)
    context.clearRect(adaptSize(size, 10), adaptSize(size, 10), adaptSize(size, 80), adaptSize(size, 80)) 
    if(key != -1){ 
        let keyPosition = getSpritePosition(size, key)
        context.drawImage(keyImage, keyPosition[0], keyPosition[1])
    }
    if(coin != -1){ 
        let coinPosition = getSpritePosition(size, coin)
        context.drawImage(coinImage, coinPosition[0], coinPosition[1])
    }
}

function getSpritePosition(size,value){
    switch(value){
        case "0":
            return [adaptSize(size, 20), adaptSize(size, 20)]
            break
        case "1":
            return [adaptSize(size, 70), adaptSize(size, 20)]
            break
        case "2":
            return [adaptSize(size, 20), adaptSize(size, 70)]
            break
        case "3":
            return [adaptSize(size, 70), adaptSize(size, 70)]
            break            
    }
}

function drawDoor(direction, startPosition, size, context){
    switch(direction){
        case "Exit":
        case "Open":
            context.clearRect(startPosition[0], startPosition[1], size, size)
        case "Wall":
            return
        default:
            context.fillStyle = "#f54242"
            context.strokeStyle = "#f54242"
            context.fillRect(startPosition[0], startPosition[1], size, size) 
    }
}

function adaptSize(size, value){
    return (size*value)/100
}