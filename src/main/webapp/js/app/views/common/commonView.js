/**
 * Alert View
 */
define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'),
		_ = require('underscore'),
		Backbone = require('backbone');

	Backbone.View.prototype.close = function() {
		this.$el.empty();
		this.$el.removeClass();
		this.stopListening();
		this.unbind();
		if (this.onClose) {
			this.onClose();
		}
	};

	var ModalView = require('views/common/modal'),
		AlertView = require('views/common/alert');

	Backbone.ModalView = ModalView;
	Backbone.AlertView = AlertView;
});