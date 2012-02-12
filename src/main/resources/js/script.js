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
        $("#log").text(data);
    });
}

//git test