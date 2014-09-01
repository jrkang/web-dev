define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'),
		Backbone = require('backbone');

	// require i18n
	var locale = require('i18n!nls/str');

	var AccountModel = Backbone.Model.extend({
		defaults: {
			"seq": "",
			"username": "",
			"password": "",
			"role": "",
			"enabled": "",
			"mail": "",
			"sms": ""
		},
		url: "api/account/selectAccountByUsername",
		validate: function(attrs, options) {
			if (!attrs.username) {
				return locale.requireUsername;
			}
			if (!attrs.password) {
				return locale.requirePassword;
			}
			if (!attrs.role) {
				return locale.requireRole;
			}
		}
	});

	var AccountCollection = Backbone.Collection.extend({
		model: AccountModel
	});

	return AccountModel;

});