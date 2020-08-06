define([
    'dojo/_base/declare',
    'dojo/_base/lang',
    'dojo',
    'dijit/Dialog',
    'dijit/_WidgetsInTemplateMixin',
    'dojo/text!./addBlog.html',
    'dijit/form/Button',
    'dijit/form/Form',
    'dijit/form/TextBox',
    'dijit/form/ValidationTextBox',
    'dojox/layout/TableContainer'
], function (declare, lang, dojo, Dialog, _WidgetsInTemplateMixin, template) {
    return declare('app.dialog.addBlog', [Dialog, _WidgetsInTemplateMixin], {

        title: 'Добавить новый блог!',
        style: 'width:400px;' +
            'height:350px',
        templateString: template,

        constructor: function (options) {
            lang.mixin(this, options);
        },

        onSubmit: function () {
            var widget = this;
            var x = dojo.byId("textForAddingInBlog").value;
            if (x === "" || (x.replace(/\s/g, '').length === 0)) {
                widget.onErrorInTextBox("Нельзя создать пустой блог!")
                return false;
            } else if (x.length > 200) {
                widget.onErrorInTextBox("Максимальное число символов - 200!")
                return false;
            }
            dojo.xhrPost({
                url: this.url,
                form: this.loginForm.id,
                handleAs: 'json',
                load: function (response) {
                    if (response.message == 'BAD REQUEST') {
                        widget.onFailure(response);
                    } else {
                        widget.onSuccess(response);
                    }
                }
            });
        },

        onSuccess: function (response) {

        },

        onFailure: function (response) {

        },

        onErrorInTextBox: function (text) {

        }

    });
});