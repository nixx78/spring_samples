var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function schedulerStarted(started) {
    $("#startScheduler").prop("disabled", started);
    $("#stopScheduler").prop("disabled", !started);
}

function connect() {
    var socket = new SockJS('/connect');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        schedulerStarted(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe('/app/topic/message', function (message) {
            var jMessage = JSON.parse(message.body);
            showMessage(jMessage.action, jMessage.content);
        });
        
        stompClient.subscribe('/topic/message', function (message) {
            var jMessage = JSON.parse(message.body);
            showMessage(jMessage.action, jMessage.content);
        });
    });
}

function showMessage(action, message) {
    var _class = getClassForAction(action);
    $("#messages").append("<tr><td class=\"" + _class + "\">" + message + "</td></tr>");
}

function getClassForAction(action) {
    var _class;
    switch (action)
    {
       case "MESSAGE":
            _class = "message-class";
            break;
       case "START":
            _class = "start-class";
            break;
       case "STOP":
            _class = "stop-class";
            break;
       default:
           console.log('No CSS class for":', action);
    }
    return _class;
}

function disconnect() {
    if (stompClient !== null) {
    	stompClient.unsubscribe('/app/topic/message');
    	stompClient.unsubscribe('/topic/message');
        stompClient.disconnect();
    }
    setConnected(false);

    $("#startScheduler").prop("disabled", true);
    $("#stopScheduler").prop("disabled", true);

    console.log("Disconnected");
}

function startScheduler() {
    console.log('Start scheduler');
    var r = stompClient.send("/app/scheduler", {}, JSON.stringify({'action': 'start'}));
    console.log(r);
    schedulerStarted(true);
}

function stopScheduler() {
    console.log('Stop scheduler');
    var r = stompClient.send("/app/scheduler", {}, JSON.stringify({'action': 'stop'}));
    console.log(r);
    schedulerStarted(false);
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#startScheduler" ).click(function() { startScheduler(); });
    $( "#stopScheduler" ).click(function() { stopScheduler(); });
    $( "#clearMessages" ).click(function() {     $("#messages").html(""); });
});

