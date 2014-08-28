define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'), Backbone = require('backbone');

	// require View
	var LayoutView = require('views/layout'), layoutView, frameView;

	var $container, $list, $main;

	return Backbone.Router.extend({

		routes : {
			'pol/vdp(/:ruleNo)' : 'detection'
		},
		setOptions : function(options) {
			$container = options.$container;

			layoutView = new LayoutView({
				el : $container
			});
			
			frameView = options.frameView;
		},
		detection : function(ruleNo) {
			layoutView.render();
			$list = $('.sidebar', layoutView.el);
			$main = $('.main', layoutView.el);
			
			$list.append('policy list');
			$main.append('victim detection policy main');
			
			frameView.selectMenuItem('policy');
		}

	});

});