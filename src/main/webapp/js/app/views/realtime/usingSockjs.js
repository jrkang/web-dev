define(function(require, d3) {

	"use strict";

	// require library
	var $ = require('jquery'),
		_ = require('underscore'),
		Backbone = require('backbone'),
		sockjs = require('sockjs');

	var ws;

	return Backbone.View.extend({
		events: {
			'remove' : 'onClose'
		},
		render: function() {
			this.$el.empty();
			this.$el.removeClass();
			this.$el.addClass('container-fluid');

			this.realtime();

			return this;
		},
		realtime: function() {
			var url = "/socketjs/echo";
			if (url.indexOf('socketjs') == -1) {
				if (window.location.protocol == 'http:') {
					url = 'ws://' + window.location.host + url;
				} else {
					url = 'wss://' + window.location.host + url;
				}
			}
			var timer;
			var transports = [];
			ws = (url.indexOf('socketjs') != -1) ? new SockJS(url, undefined, {
				protocols_whitelist: transports
			}) : new WebSocket(url);

			ws.onopen = function(event) {
				console.log('open');
				console.log(timer);
				if (timer != null) {
					clearInterval(timer);
				}
				ws.send('message!');
			};
			ws.onclose = function(event) {
				console.log('close');
			};
			ws.onmessage = function(event) {
				console.log(event.data);
			};
			ws.onerror = function(event) {
				console.log('error');
			};

			timer = setInterval(function() {
				if (ws && ws.readyState === SockJS.OPEN) {
					ws.send('message!');
				}
			}, 1000);
		},
		onClose: function() {
			if (ws != null) {
				ws.close();
				ws = null;
			}
		}
	});

});