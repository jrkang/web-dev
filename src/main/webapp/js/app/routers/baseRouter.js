define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'),
		Backbone = require('backbone');

	// require View
	var frameView, $container;

	var currentView;

	return Backbone.Router.extend({

		routes: {
			"": "login",
			"login": "login",
			"dashboard": "dashboard",
			"realtime": "realtime",
			"sessionExpire": "sessionExpire"
		},
		setOptions: function(options) {
			$container = options.$container;

			frameView = options.frameView;
		},
		login: function() {
			$.when(this.closeCurrentView()).done(function() {
				var LoginView = require('views/login');
				currentView = new LoginView({
					el: $container
				}).render();
			});
		},
		dashboard: function() {
			$.when(this.closeCurrentView()).done(function() {
				require(['views/dashboard'], function(Dashboard) {
					currentView = new Dashboard({
						el: $container
					}).render();
				});

				frameView.selectMenuItem('');
			});
		},
		realtime: function() {
			$.when(this.closeCurrentView()).done(function() {
				require(['views/realtime/usingSockjs'], function(UsingSockjs) {
					currentView = new UsingSockjs({
						el: $container
					}).render();
				});

				frameView.selectMenuItem('');
			});
		},
		sessionExpire: function() {
			$('body').html('session expired.');
		},
		closeCurrentView: function() {
			if (currentView !== undefined) {
				currentView.close();
				currentView = undefined;
			}
		}

	});

});