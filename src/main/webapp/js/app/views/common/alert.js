/**
 * Alert View
 */
define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'),
		_ = require('underscore'),
		Backbone = require('backbone'),
		bootstrap = require('bootstrap');

	// require template
	var tpl = require('text!tpl/common/alert.html');

	var AlertView = Backbone.View.extend({
		alerts : [ 'success', 'error', 'warning', 'info' ],
		template : _.template(tpl),

		/**
		 * @param {String}
		 *            [options.alert] alert. Default: none
		 * @param {String}
		 *            [options.message] message. Default: none
		 */
		initialize : function(options) {
			var message = options.message || '';
			var alert = options.hasOwnProperty('alert') ? options.alert : 'info';

			if (_.indexOf(this.alerts, alert) === -1) {
				throw new Error('Invalid alert: [' + alert + '] Must be one of: '
						+ this.alerts.join(', '));
			}

			this.alert = alert;
			this.message = message;
		},
		render : function() {
			var output = this.template({
				alert : this.alert,
				message : this.message
			});
			this.$el.addClass('alert-' + this.alert).html(output).alert();
			return this;
		}
	});

	AlertView.msg = function($el, options) {
		var alert = new AlertView(options);
		$el.html(alert.render().el);
		return alert;
	};

	return AlertView;
});