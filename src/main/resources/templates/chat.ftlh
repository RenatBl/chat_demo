<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="chat.js"></script>
</head>

<style>
    .chat {
        font-family: 'Avenir', serif;
        width: 900px;
        margin-left: 10%;
        margin-top: 2%;
        border: 6px rgba(212,228,239,1) solid;
        border-radius: 6px;
    }
    .chat-box {
        overflow-y: scroll;
        background: #D3D3D3;
        height: 600px;
    }
    .no-msg {
        margin-top: 40px;
        margin-right: auto;
        margin-left: auto;
        text-align: center;
        font-family: 'Avenir',sans-serif;
        font-size: 16pt;
    }
    .bubbleWrapper {
        padding: 10px 10px;
        display: flex;
        justify-content: flex-end;
        flex-direction: column;
        align-self: flex-end;
        color: #fff;
    }
    .inlineContainer {
        display: inline-flex;
    }
    .inlineContainer.own {
        flex-direction: row-reverse;
    }
    .ownBubble {
        min-width: 60px;
        max-width: 700px;
        padding: 14px 18px;
        margin: 6px 8px;
        background-color: #5b5377;
        border-radius: 16px 16px 0 16px;
        border: 1px solid #443f56;

    }
    .otherBubble {
        min-width: 60px;
        max-width: 700px;
        padding: 14px 18px;
        margin: 6px 8px;
        background-color: #6C8EA4;
        border-radius: 16px 16px 16px 0;
        border: 1px solid #54788e;

    }
    .own {
        align-self: flex-end;
    }
    .other {
        align-self: flex-start;
    }
    span.own,
    span.other{
        font-size: 14px;
        color: grey;
    }
    .msg-input {
        border-top: 5px rgba(212,228,239,1) solid;
        min-height: 70px;
        vertical-align: center;
        display: flex;
    }

    .inp-box {
        display: inline-block;
        float: left
    }

    .inp {
        padding-left: 8px;
        border-radius: 8px 0 0 8px;
        border:1px solid #ccc;
        width: 810px;
        height: 30px;
        position: inherit;
        outline: none;
        -webkit-appearance: none;
        vertical-align: middle;
    }
    .send-box {
        display: inline-block;
        float: left;
        border: 1px #ccc solid;
        width: 30px;
        height: 32px;
        border-radius: 0 8px 8px 0;
    }
    .send {
        width: 30px;
        height: 30px;
        border: none;
        background: url("https://img.icons8.com/ios-glyphs/30/000000/telegram-app.png");
        outline: none;
        -webkit-appearance: none;
    }
    .send:hover {
        background: url("https://img.icons8.com/material-two-tone/30/000000/telegram-app.png");
    }
    .send:active {
        transform: translateY(4px);
    }
</style>

<body onload="receiveMessage('${username}'); scroll()">

<div class="chat" id="chat" onload="scroll()">
    <div class="chat-box" id="box">
        <#if DBmessages??>
            <#list DBmessages as message>
                <#if message.owner == username>
                    <div class="bubbleWrapper">
                        <div class="inlineContainer own">
                            <div class="ownBubble own">
                                <strong>${message.owner}</strong>
                                <br>
                                ${message.content}
                            </div>
                        </div>
                        <span class="own">${message.date}</span>
                    </div>
                <#else >
                    <div class="bubbleWrapper">
                        <div class="inlineContainer">
                            <div class="otherBubble other">
                                <strong>${message.owner}</strong>
                                <br>
                                ${message.content}
                            </div>
                        </div>
                        <span class="other">${message.date}</span>
                    </div>
                </#if>
            </#list>
        <#else >
            <div class="no-msg">
                No messages yet...
            </div>
        </#if>
    </div>
    <div class="msg-input">
        <div style="margin: 20px">
            <div class="inp-box">
                <input type="text" class="inp" id="message">
            </div>
            <div class="send-box">
                <input onclick="sendMessage('${username}',
                $('#message').val()); cleanAndScroll();" type="button" class="send">
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    function scroll() {
        var block = document.getElementById("box");
        block.scrollTop = block.scrollHeight;
    }

    function cleanAndScroll() {
        document.getElementById("message").value = "";
    }
</script>

</html>