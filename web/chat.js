let selectedEffect = "blind";

$(function () {
    $(".moreInfo").on("click", runEffect);
    $("#sendMessage").on("click", sendMessage);
    $(".openChat").on("click", changeChat);
    setTimeout(getMessages, 5000);
});

function changeChat(evt) {
    let email = evt.target.id;
    console.log(email);
    window.location = "http://localhost:8080/Servlet?command=Chat&receiver=" + email;
    getMessages();
}

function runEffect(evt) {
    let options = { to: { width: 200, height: 60 } };
    let id = evt.target.id + "MoreInfo";
    $("#" + id).toggle(selectedEffect, options, 500);
}

function sendMessage (evt) {
    let message = $("#message").val();
    let receiver = $("#receiver").text().replace("Now chatting with: ", "");
    $("#message").val("");
    $.ajax({
        type: "POST",
        url: "Servlet?command=SendMessage&receiver=" + receiver + "&message=" + message,
        dataType: "json",
        success: updateMessages
    });
}

function updateMessages(json) {
    $(".message").remove();
    $("br").remove();
    $.each(json, function (i, val) {
        let wrapper = val.role === "sender" ? $("<div class='message d-flex justify-content-end mb-2'></div>") : $("<div class='message d-flex justify-content-start mb-2'></div>");
        let bericht = val.role === "sender" ? $("<div class='p-2 bg-primary text-white' style='border-radius: 10px; width: fit-content'></div>").text(val.content) : $("<div class='p-2' style='border-radius: 10px; background-color: #f1f0f0; width: fit-content'></div>").text(val.content);
        wrapper.append(bericht);
        $("#messages").append(wrapper);
        });
    setTimeout(getMessages, 5000);
}

function getMessages() {
    let receiver = $("#receiver").text().replace("Now chatting with: ", "");
    $.ajax({
        type: "GET",
        url: "Servlet?command=GetMessages&receiver=" + receiver,
        dataType: "json",
        success: updateMessages
    });
}