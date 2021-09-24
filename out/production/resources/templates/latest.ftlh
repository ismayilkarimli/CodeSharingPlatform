<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>

    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<table>
    <tr>
        <th>Code</th>
        <th>Time</th>
    </tr>
    <#list codes as code>
        <tr>
            <td><pre><code>${code.code}</code></pre></td>
            <td><span>${code.date}</span></td>
        </tr>
    </#list>
</table>
</body>
</html>