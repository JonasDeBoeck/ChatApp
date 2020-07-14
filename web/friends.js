let xhr = new XMLHttpRequest();

window.addEventListener("load", initPage);

function initPage() {
    document.getElementById("status").addEventListener("change", changeStatus);
    document.getElementById("addFriendButton").addEventListener("click", addFriend);
    getFriends();
    getFriendRequests()
}

function changeStatus(evt) {
    let status = evt.target.value;
    if (status === "Other") {
        //p element aanmaken
        let p = document.createElement("p");
        p.id = "other";
        //input element aanmaken en juiste attributen zetten
        let input = document.createElement("input");
        input.type = "text";
        input.name = "custom";
        input.id = "custom";
        input.className = "form-control mt-2";
        p.appendChild(input);
        //button aanmaken en juiste attributen zetten
        let button = document.createElement("button");
        button.innerText = "Change";
        button.id = "verzendStatus";
        button.className = "btn btn-primary mt-3";
        button.addEventListener("click", changeOther);
        p.appendChild(button);
        evt.target.parentElement.appendChild(p);
    } else {
        if (document.getElementById("other")) {
            document.getElementById("other").parentNode.removeChild(document.getElementById("other"));
        }
        xhr.open("POST", "Servlet?command=ChangeStatus&status=" + status, true);
        xhr.onreadystatechange = getStatus;
        xhr.send();
    }
}

//Handelt alles af van de statussen buiten bij Status = Other
function getStatus() {
    if (xhr.status === 200) {
        if (xhr.readyState === 4) {
            let status = xhr.responseText;
            document.querySelector("p:nth-of-type(2)").innerText = document.querySelector("p:nth-of-type(2)").innerText.slice(0, 13) + status;
        }
    }
}

//Zorgt voor de afhandeling van Status = Other
function changeOther() {
    let status = document.getElementById("custom").value;
    xhr.open("POST", "Servlet?command=ChangeOther&status=" + status, true);
    xhr.onreadystatechange = getStatus;
    xhr.send();
}

//Wordt opgeroepen om vriendenlijst te vernieuwen
function getFriends() {
    xhr.open("GET", "Servlet?command=GetFriends", true);
    xhr.onreadystatechange = updateTable;
    xhr.send();
    setTimeout(getFriends, 20000)
}

//Update de vriendenlijst
function updateTable() {
    if (xhr.status === 200) {
        if (xhr.readyState === 4) {
            clearTable("friends");
            let json = JSON.parse(xhr.responseText);
            for (let person in json) {
                let friend = createTr(person, json[person].status);
                document.getElementById("friendsBody").appendChild(friend);
            }
        }
    }
}

//Maakt table rows met values username en status
function createTr (username, status) {
    let friend = document.createElement("tr");
    let friendName = document.createElement("td");
    let friendStatus = document.createElement("td");
    let showChat = document.createElement("td");
    let button = document.createElement("button");
    button.className = "btn btn-primary openChat w-100";
    button.innerText = "Open chat";
    button.addEventListener("click", goToChat);
    friendName.innerText = username;
    friendStatus.innerText = status;
    showChat.appendChild(button);
    friend.appendChild(friendName);
    friend.appendChild(friendStatus);
    friend.appendChild(showChat);
    return friend;
}

function goToChat(evt) {
    let username = evt.target.parentNode.parentNode.firstElementChild.innerText;
    window.location = "http://localhost:8080/Servlet?command=Chat&receiver=" + username;
}

//Maakt de body van de tabel leeg
function clearTable (tablename) {
    document.getElementById(tablename + "Body").innerHTML = "";
}

//Handelt het toevoegen van vrienden af
function addFriend() {
    let email = document.getElementById("addFriend").value;
    xhr.open("POST", "Servlet?command=AddFriend&email=" + email, true);
    xhr.onreadystatechange = confirmRequest;
    xhr.send();
}

function confirmRequest() {
    if (xhr.status === 200) {
        if (xhr.readyState === 4) {
            let confirmation = $("<div class='alert alert-success' role='alert'></div>");
            let text = $("<p class='mb-0'></p>").text("Succesfuly send a friend request to: " + xhr.responseText);
            confirmation.append(text);
            $("#mainContent").prepend(confirmation);
            setTimeout(function () {
                confirmation.hide('fade', { to: { width: 200, height: 60 } }, 500)
            }, 5000)
        }
    }
}

function getFriendRequests() {
    $.get("Servlet?command=GetFriendRequests", function (data) {
        clearTable("friendRequests");
        let requests = jQuery.parseJSON(data);
        for (let person in requests) {
            let request = $("<tr>" +
                "<td>" + person + "</td>" +
                "<td><button class='btn btn-success w-100 requests accept' id='" + person + "Accept'>Accept</button></td>" +
                "<td><button class='btn btn-danger w-100 requests deny' id='" + person + "Deny'>Deny</button></td>" +
                "</tr>");
            $("#friendRequestsBody").append(request);
        }
        $(".requests").on("click", function (evt) {
            let person = evt.target.parentNode.parentNode.firstElementChild.innerText;
            let action = evt.target.id.replace(person, '');
            $.post("Servlet?command=HandleFriendRequest&action=" + action + "&email=" + person, function (data) {
                let confirmation = $("<div class='alert alert-success' role='alert'></div>");
                let text = $("<p class='mb-0'></p>").text(data);
                confirmation.append(text);
                $("#mainContent").prepend(confirmation);
                setTimeout(function () {
                    confirmation.hide('fade', { to: { width: 200, height: 60 } }, 500)
                }, 5000)
            });
            getFriends();
        })
    });
    setTimeout(getFriendRequests, 5000)
}