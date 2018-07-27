
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>商务线进展</title>
    </head>

    <style type="text/css">
        table {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            width: 100%;
            border-collapse: collapse;
        }

        td, th {
            font-size: 1em;
            border: 1px solid #5B4A42;
            padding: 3px 7px 2px 7px;
        }

        th {
            font-size: 1.1em;
            text-align: center;
            padding-top: 5px;
            padding-bottom: 4px;
            background-color: #ffffff;
            color: #24A9E1;
        }
    </style>
    <body>
        <div>
            <div>
                <h3>当前商务线今日进展</h3>

                <table id="customers">
                    <tr>
                        <th>序号</th>
                        <th>商务线</th>
                        <th>更新前状态</th>
                        <th>更新前Todo</th>
                        <th>今日进展</th>
                        <th>更新后Todo</th>
                        <th>更新后状态</th>
                    </tr>

                <#list bizLines as item>
                    <tr>
                        <td>${item.bizCode}</td>
                        <td>${item.topic}</td>
                        <td>${item.status}</td>
                        <td>

                            <#if item.todoList?size != 0>
                                <ul>
                                    <#list item.todoList as todo>
                                        <li>${todo}</li>
                                    </#list>
                                </ul>
                            <#else >
                                -
                            </#if>

                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </#list>

                </table>
            </div>

            <div>
                <h3>新增商务线今日进展</h3>
                <table id="customers">
                    <tr>
                        <th>商务线</th>
                        <th>状态</th>
                        <th>今日进展</th>
                        <th>Todo</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
            </div>

        </div>
    </body>
</html>

