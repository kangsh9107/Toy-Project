const main = document.querySelector(".main");
const imgs = ["../img/img1.jpg","../img/img2.jpg","../img/img3.jpg","../img/img4.jpg","../img/img5.jpg","../img/img6.jpg"];
let imageCount = 0;

for(let i=0; i<5; i++) {
    appendCard();
}

let current = main.querySelector(".card:last-child");
let startX = 0, startY = 0, moveX = 0, moveY = 0;
addEventListener(current);

document.querySelector(".againBtn").onclick = () => {
    moveX = -1;
    moveY = 0;
    complete();
}

document.querySelector(".completeBtn").onclick = () => {
    moveX = 1;
    moveY = 0;
    complete();
}

function appendCard() {
    const firstCard = main.children[0];
    const newCard = document.createElement("div");
    newCard.className = "card";
    newCard.style.backgroundImage = `url(${imgs[imageCount++ % imgs.length]})`
    if(firstCard) main.insertBefore(newCard, firstCard);
    else main.appendChild(newCard);
}

function addEventListener(card) {
    card.addEventListener("pointerdown", onPointerDown);
}

function setTransform(x, y, deg, duration) {
    current.style.transform = `translate3d(${x}px, ${y}px, 0) rotate(${deg}deg)`
    if(duration) current.style.transition = `transform ${duration}ms`
}

function onPointerDown(e) {
    startX = e.clientX;
    startY = e.clientY;
    current.addEventListener("pointermove", onPointerMove);
    current.addEventListener("pointerup", onPointerUp);
    current.addEventListener("pointerleave", onPointerUp);
}

function onPointerMove(e) {
    moveX = e.clientX - startX;
    moveY = e.clientY - startY;

    setTransform(moveX, moveY, moveX / innerWidth * 50);
}

function onPointerUp() {
    current.removeEventListener("pointermove", onPointerMove);
    current.removeEventListener("pointerup", onPointerUp);
    current.removeEventListener("pointerleave", onPointerUp);
    if(Math.abs(moveX) > main.clientWidth / 2) {
        current.removeEventListener("pointerleave", onPointerUp);
        complete();
    } else {
        cancelAnimationFrame();
    }
}

function complete() {
    const flyX = Math.abs(moveX) / moveX * innerWidth * 1.1;
    const flyY = (moveY / moveX) * flyX;
    setTransform(flyX, flyY, flyX / innerWidth *50, innerWidth * 0.5);

    const prev = current;
    const next = current.previousElementSibling;
    current = next;
    addEventListener(next);
    appendCard();
    setTimeout(() => main.removeChild(prev), 500);
}

function cancel() {
    setTransform(0, 0, 0, 100);
    setTimeout(() => current.style.transition = "", 100);
}