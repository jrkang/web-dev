define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'),
		_ = require('underscore'),
		Backbone = require('backbone'),
		tpl = require('text!tpl/system/account/accountDetail.html'),
		template = _.template(tpl);

	// require model
	var AccountModel = require('models/system/account'),
		accountModel = new AccountModel();

	return Backbone.View.extend({

		initialize: function() {},

		render: function(options) {
			var self = this;
			this.$el.empty();

			if (options.username === null) {
				this.$el.html('<h1>No selected item.</h1>');
			} else {
				accountModel.set({
					username: options.username
				}, {
					silent: true,
					validate: false
				});
				accountModel.fetch({
					method: "POST",
					contentType: 'application/json',
					data: JSON.stringify(accountModel.toJSON()),
					success: function(model, response, options) {
						self.$el.html(template(model.attributes));
					}
				});

				if (options.listView !== undefined) {
					options.listView.selectAccountItem(options.username);
				}
			}

			return this;
		}
	});

});