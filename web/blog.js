let webSocket;

window.addEventListener("load", initPage);

function initPage() {
    openSocket();
    let buttons = document.getElementsByClassName("comment");
    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener("click", comment)
    }
}

function openSocket() {
    webSocket = new WebSocket("ws://localhost:8080/comment");
    webSocket.onopen = function(event) {

    };
    webSocket.onmessage = function (event) {
        addComment(event.data)
    };
    webSocket.onclose = function (event) {

    };
}

function comment(evt) {
    let sectie = evt.target.id.replace("Button", "");
    let naam = document.getElementById(sectie+ "Name").value;
    let comment = document.getElementById(sectie + "Comment").value;
    let rating = document.getElementById(sectie + "Rating").value;
    let contentInfo = {contentId: sectie, senderNaam: naam, content: comment, commentRating: rating};
    webSocket.send(JSON.stringify(contentInfo));
}

function addComment(event) {
    let contentInfo = JSON.parse(event);
    let li = document.createElement("li");
    li.className = "list-group-item";
    let content = document.createElement("p");
    content.className = "list-group-item";
    content.innerText = "Naam: " + contentInfo['senderNaam'] + "\nComment: \n" + contentInfo['content'] + "\nRating: " + contentInfo['commentRating'];
    li.appendChild(content);
    document.getElementById(contentInfo['contentId']).appendChild(li);
}