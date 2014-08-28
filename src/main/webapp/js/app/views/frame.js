define(function(require) {

	"use strict";

	var $ = require('jquery'),
		_ = require('underscore'),
		Backbone = require('backbone'),
		tpl = require('text!tpl/frame.html'),
		template = _.template(tpl),
		$menuItems;

	return Backbone.View.extend({

		initialize : function() {
		},

		render : function() {
			this.$el.html(template());
			$menuItems = $('.navbar .nav li', this.el);
			return this;
		},

		events : {},

		selectMenuItem : function(menuItem) {
			$menuItems.removeClass('active');
			if (menuItem) {
				$('.' + menuItem + '-menu').addClass('active');
			}
		}

	});

});