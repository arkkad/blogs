define([
    'require',
    'dojo/ready',
    'dijit/registry',
    "dojo/dom",
], function (require, ready, registry,) {

    var app = {};

    ready(function () {
        dojo.connect(registry.byId('btnClickMe'), 'onClick', app.onButtonClick);
        dojo.connect(registry.byId('btnClickBack'), 'onClick', app.prevPage);
        dojo.connect(registry.byId('btnClickNext'), 'onClick', app.nextPage);
        console.log('loaded!');
    });

    /**
     * Display alert dialog.
     */
    app.alert = function (message) {
        require(['dijit/Dialog'], function (Dialog) {
            var dialog = new Dialog({title: 'Alert', content: message});
            dialog.show();
        });
    };

    /**
     * Display login dialog.
     */
    app.login = function (options) {
        require(['app/dialog/addBlog'], function (Dialog) {
            var dialog = new Dialog(options);
            dialog.show();
        });
    };

    /**
     * Button click action.
     */
    app.onButtonClick = function () {
        app.login({
            url: appConfig.baseUrl + 'api/v1/addBlog',
            onSuccess: function (response) {
                console.log(response.message);
                app.alert('Блог успешно добавлен!!');
            },
            onFailure: function (response) {
                console.log(response.message);
                app.alert('Что-то пошло не так!!');
            },
            onErrorInTextBox: function (text) {
                app.alert(text);
            }
        });
    };

    app.nextPage = function () {
        require(["dojo/request", "dojo/json",
            "dojo/_base/array"], function (request, JSON, arrayUtil) {
            currPage = parseInt(localStorage.getItem("currPage"));
            totalPages = localStorage.getItem("totalPages")
            let s = parseInt(currPage) + 1;
            if (s < totalPages) {
                request.get("http://localhost:8080/api/v1/getBlogs?page=" + s, {
                    // Parse data from JSON to a JavaScript object
                    handleAs: "json"
                }).then(function (data) {
                    console.log(data);
                    var html = "";
                    if (data.content.length > 0) {
                        arrayUtil.forEach(data.content, function (data, i) {
                            html += "<label>" + data.createdDate + "</label>" +
                                "<h3>" + data.text + "</h3>";
                        });
                        html += "</dl>";
                    }
                    localStorage.setItem("currPage", data.number)
                    localStorage.setItem("totalPages", data.totalPages)
                    dojo.ready(function () {
                        var one = dojo.byId("resultDiv");
                        one.innerHTML = html;
                    });
                })
            }
        })
    }

    app.prevPage = function () {
        require(["dojo/request", "dojo/json",
            "dojo/_base/array"], function (request, JSON, arrayUtil) {
            currPage = parseInt(localStorage.getItem("currPage"));
            totalPages = localStorage.getItem("totalPages")
            if (currPage >= 1) {
                request.get("http://localhost:8080/api/v1/getBlogs?page=" + (currPage - 1), {
                    // Parse data from JSON to a JavaScript object
                    handleAs: "json"
                }).then(function (data) {
                    console.log(data);
                    var html = "";
                    if (data.content.length > 0) {
                        arrayUtil.forEach(data.content, function (data, i) {
                            html += "<label>" + data.createdDate + "</label>" +
                                "<h3>" + data.text + "</h3>";
                        });
                        html += "</dl>";
                    }
                    localStorage.setItem("currPage", data.number)
                    localStorage.setItem("totalPages", data.totalPages)
                    dojo.ready(function () {
                        var one = dojo.byId("resultDiv");
                        one.innerHTML = html;
                    });
                })
            }
        })
    }


    return app;
});