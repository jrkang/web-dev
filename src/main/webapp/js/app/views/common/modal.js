/**
 * Alert View
 */
define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'), _ = require('underscore'), Backbone = require('backbone');

	// require template
	var tpl = require('text!tpl/common/modal.html');

	// require i18n
	var locale = require('i18n!nls/str');

	var ModalView = Backbone.View.extend({
		className : "modal backbone-modal",
		template : _.template(tpl),
		buttonTemplate : _
				.template('<button type="button" class="btn <%=className%>"><%=label%></button>'),
		buttonDefaults : {
			className : "",
			label : "",
			close : false
		},
		defaults : {
			title : "Info",
			backdrop : true,
			body : "",
			buttons : [ {
				className : "btn-primary",
				label : locale.close,
				close : true
			} ]
		},
		initialize : function(options) {
			options || (options = {});
			_.defaults(this, this.defaults);
			_.extend(this, _.pick(options, _.keys(this.defaults)));
			_.bindAll(this, "close");
		},
		render : function() {
			var view = this;

			this.$el.html(this.template({
				title : this.title,
				body : this.body
			}));
			this.$header = this.$el.find('.modal-header');
			this.$body = this.$el.find('.modal-body');
			this.$footer = this.$el.find('.modal-footer');

			_.each(this.buttons, function(button) {
				_.defaults(button, view.buttonDefaults);
				var $button = $(view.buttonTemplate(button));
				view.$footer.append($button);
				if (button.close)
					$button.on("click", view.close);
			});

			this.$el.modal({
				keyboard : false,
				backdrop : this.backdrop
			});

			this.$header.find("a.close").click(view.close);

			if (this.backdrop === true) {
				$('.modal-backdrop').off().click(view.close);
			}

			this.postRender();

			return this;
		},
		postRender : function() {
			return this;
		},
		close : function(e) {
			if (e)
				e.preventDefault();
			var view = this;
			this.trigger("close", this);
			setTimeout(function() {
				view.$el.modal("hide");
				view.remove();
			}, 25);
		}
	});

	ModalView.msg = function(options) {
		var modal = new Backbone.ModalView({
			title : options.title,
			body : options.body
		}).render();
		return modal;
	};

	ModalView.msgWithOkBtn = function(options) {
		var modal = new Backbone.ModalView({
			title : options.title,
			body : options.body,
			buttons : [ {
				className : "btn-info",
				label : locale.ok,
				close : true
			} ]
		}).render();
		return modal;
	};

	ModalView.msgWithOkCancelBtn = function(options) {
		var modal = new Backbone.ModalView({
			title : options.title,
			body : options.body,
			buttons : [ {
				className : "btn-info",
				label : locale.ok,
				close : true
			}, {
				className : "btn-default",
				label : locale.cancel,
				close : true
			} ]
		}).render();
		return modal;
	};

	return ModalView;
});