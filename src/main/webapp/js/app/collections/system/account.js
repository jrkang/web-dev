define(function(require) {

	"use strict";

	// require library
	var _ = require('underscore'),
		Backbone = require('backbone'),
		AccountModel = require('models/system/account');

	var AccountCollection = Backbone.Collection.extend({
		model : AccountModel,
		url : "api/account/selectAccounts",
	});

	return AccountCollection;

});