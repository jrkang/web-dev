define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'), Backbone = require('backbone');

	// require View
	var frameView;
	
	var $container;

	return Backbone.Router.extend({

		routes : {
			"" : "login",
			"login" : "login",
			"dashboard" : "dashboard",
			"sessionExpire" : "sessionExpire"
		},
		setOptions : function(options) {
			$container = options.$container;
			
			frameView = options.frameView;
		},
		login : function() {
			$container.empty();
			$container.removeClass();
			var LoginView = require('views/login');
			var loginView = new LoginView({
				el : $container
			}).render();
		},
		dashboard : function() {
			require([ 'views/dashboard' ], function(Dashboard) {
				var dashboard = new Dashboard({
					el : $container
				}).render();
			});
			
			frameView.selectMenuItem('');
		},
		sessionExpire : function() {
			$('body').html('session expired.');
		}

	});

});