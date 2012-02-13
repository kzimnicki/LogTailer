function start() {
    $("#startButton").attr("disabled", "true");
    $("#log").text("STARTED\n");
    intervalId = setInterval("tailFromServer()", 1000);
}

function stop() {
    clearInterval(intervalId);
    $("#log").append("STOPPED\n");
    $("#startButton").removeAttr("disabled");
}

function tailFromServer() {
    $.get('/d', function(data) {

        $("#log").append(data);
        var textarea = $("#log");
        var total = (textarea.text()
            ? textarea.text() + "\n"
            : "").split("\n");

        var maxLines = 1000;
        if (total.length > maxLines)
            total = total.slice(total.length - maxLines);
        textarea.text(total.join("\n"));
        $("#log").scrollTop(99999);
    });
}

//git test