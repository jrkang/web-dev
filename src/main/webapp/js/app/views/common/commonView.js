/**
 * Alert View
 */
define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'),
		_ = require('underscore'),
		Backbone = require('backbone'),
		ModalView = require('views/common/modal'),
		AlertView = require('views/common/alert');

	Backbone.ModalView = ModalView;
	Backbone.AlertView = AlertView;
});