<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<form method="get">
    <pre><code><label for="code_snippet">Code</label><textarea id="code_snippet"></textarea></code></pre><br>
    <label for="view_restriction">View limit: </label><input id="view_restriction" type="text"/><br><br>
    <label for="time_restriction">Time limit: </label><input id="time_restriction" type="text"/><br>
<button id="send_snippet" type="submit" onclick="send();">Submit</button>
</form>
</body>
<script>
    function send() {
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": document.getElementById("view_restriction").value,
        "views": document.getElementById("time_restriction").value,
    };

    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status == 200) {
      alert("Success!");
    }
}
</script>
</html>