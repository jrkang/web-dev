define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'),
		_ = require('underscore'),
		Backbone = require('backbone');

	// require template
	var tpl = require('text!tpl/layout.html'),
		template = _.template(tpl);

	return Backbone.View.extend({
		initialize: function(options) {},
		render: function() {
			this.$el.empty();
			this.$el.removeClass();
			this.$el.addClass('container-fluid');

			this.$el.html(template());

			return this;
		}
	});
});