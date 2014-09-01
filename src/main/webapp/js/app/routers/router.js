define(function(require) {

	"use strict";

	// require library
	var $ = require('jquery'),
		Backbone = require('backbone');

	// require view
	var FrameView = require('views/frame'),
		frameView = new FrameView({
			el: $('body')
		}).render(),
		$container = $('#container', frameView.el);

	var BaseRouter = require('routers/baseRouter'),
		baseRouter = new BaseRouter();
	baseRouter.setOptions({
		$container: $container,
		frameView: frameView
	});

	var SystemRouter = require('routers/systemRouter'),
		systemRouter = new SystemRouter();
	systemRouter.setOptions({
		$container: $container,
		frameView: frameView
	});

	var PolicyRouter = require('routers/policyRouter'),
		policyRouter = new PolicyRouter();
	policyRouter.setOptions({
		$container: $container,
		frameView: frameView
	});
});