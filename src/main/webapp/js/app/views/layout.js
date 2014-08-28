define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery');
	var _ = require('underscore');
	var Backbone = require('backbone');

	// require template
	var tpl = require('text!tpl/layout.html');
	var template = _.template(tpl);

	return Backbone.View.extend({
		initialize : function(options) {
		},
		render : function() {
			this.$el.empty();
			this.$el.removeClass();
			this.$el.addClass('container-fluid');
			
			this.$el.html(template());
			
			return this;
		}
	});
});