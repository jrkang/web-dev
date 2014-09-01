define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'),
		_ = require('underscore'),
		Backbone = require('backbone');

	// require model
	var LoginModel = require('models/login'),
		loginModel = new LoginModel();

	// require template
	var tpl = require('text!tpl/login.html'),
		template = _.template(tpl);

	// require i18n
	var locale = require('i18n!nls/str');

	return Backbone.View.extend({
		model: loginModel,
		initialize: function(options) {
			// avoid 'change' event, because model.set method trigger 'change'
			// event. If this use and 'change' event don't need, use
			// {silent:true} option.
			// this.listenTo(this.model, 'change', this.success);
		},
		render: function() {
			this.$el.empty();
			this.$el.removeClass();

			this.$el.html(template());
			$('#userid').prop('placeholder', locale.id);
			$('#password').prop('placeholder', locale.password);
			$('#loginBtn').html(locale.sign_in);
			return this;
		},
		events: {
			"click #loginBtn": "login"
		},
		login: function(event) {
			loginModel.set({
				username: $('#userid').val(),
				password: $('#password').val()
			}, {
				// Silent option is do everything as normal, but just don't
				// trigger the event.
				silent: true

				// Check validate
				// validate : true
			});

			// fetch here.
			// When model does not change, 'change' event is not trigger and
			// does not run success function.
			/**
			 * <pre>
			 * loginModel.fetch({
			 * 	method : &quot;POST&quot;,
			 * 	contentType : 'application/json',
			 * 	data : JSON.stringify(loginModel.attributes),
			 * 	error : function(model, response) {
			 * 		console.log('fetch error');
			 * 		console.log(model);
			 * 		console.log(response);
			 * 	}
			 * });
			 * </pre>
			 */

			// fetch in model. use listenTo().
			// When model does not change, 'change' event is not trigger and
			// does not run success function.
			// loginModel.obtainCertification();
			// fetch in model. not use listenTo
			// When model does not change, run success function.
			if (loginModel.isValid()) {
				loginModel.obtainCertification({
					success: this.success
				});
			} else {
				Backbone.AlertView.msg($('#alert'), {
					alert: "warning",
					message: loginModel.validationError
				});
			}
		},
		success: function() {
			console.log(loginModel.toJSON());
			if (loginModel.get('successLogin') == 'Y') {
				location.hash = '#dashboard';
			} else {
				Backbone.AlertView.msg($('#alert'), {
					alert: loginModel.get('returnType'),
					message: loginModel.get('errorMessage')
				});
				Backbone.ModalView.msg({
					title: loginModel.get('returnType'),
					body: loginModel.get('errorMessage')
				});
			}
		},
		error: function() {
			console.log("error");
		}
	});
});