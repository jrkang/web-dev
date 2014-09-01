define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'),
		_ = require('underscore'),
		Backbone = require('backbone');

	// require template
	var tpl = require('text!tpl/system/account/accountList.html'),
		template = _.template(tpl),
		listItemTemplate = _
		.template('<li class="<%= username %>-item"><a href="#sys/acc/<%= username %>"><p class="list-item"><%= username %></p></a></li>');

	// require collection
	var AccountCollection = require('collections/system/account'),
		accountCollection = new AccountCollection();

	var $list, $detail;
	http: //localhost:8080/#sys/acc/idess

		return Backbone.View.extend({

			initialize: function() {
				this.$el.empty();
				this.$el.html(template());

				$list = $('#list', this.$el);

				this.listenTo(accountCollection, 'all', this.addAll);
				// this.listenTo(accountCollection, 'reset', this.addAll);
			},
			listItemTemplate: listItemTemplate,
			render: function() {
				accountCollection.fetch({
					method: 'POST',
					contentType: 'application/json'
				}, {
					reset: true
				});

				return this;
			},
			addOne: function(model) {
				$list.append(listItemTemplate(model.toJSON()));
			},
			addAll: function() {
				$list.empty();
				accountCollection.each(this.addOne);
			},
			selectAccountItem: function(username) {
				var $items = $('#list li', this.el);
				$items.removeClass('active');
				if (username) {
					$('.' + username + '-item').addClass('active');
				}
			}
		});

});