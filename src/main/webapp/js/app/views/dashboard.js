define(function(require, d3) {

	"use strict";

	// require library
	var $ = require('jquery'), _ = require('underscore'), Backbone = require('backbone'), Epoch = require('epoch');

	// require template
	var tpl = require('text!tpl/dashboard.html'), template = _.template(tpl);

	return Backbone.View.extend({
		render : function() {
			this.$el.empty();
			this.$el.removeClass();
			this.$el.addClass('container-fluid');

			this.$el.html(template());

			// area chart
			var data1 = [ {
				label : 'Sqrt',
				values : []
			}, {
				label : 'Cbrt',
				values : []
			}, {
				label : '4th',
				values : []
			} ];

			for (var i = 0; i <= 128; i++) {
				var x2 = 20 * (i / 128);
				data1[0].values.push({
					x : x2,
					y : Math.sqrt(x2)
				});
				data1[1].values.push({
					x : x2,
					y : Math.pow(x2, (1 / 3))
				});
				data1[2].values.push({
					x : x2,
					y : Math.pow(x2, (1 / 4))
				});
			}

			// Categorical colors are based directly on those used by d3, for more information see
			// the d3 categorical color docs.
			// https://github.com/mbostock/d3/wiki/Ordinal-Scales#categorical-colors
			$('#area').addClass('epoch').addClass('category10');
			// $('#area').addClass('epoch').addClass('category20');
			// $('#area').addClass('epoch').addClass('category20b');
			// $('#area').addClass('epoch').addClass('category20c');
			$('#area').css({
				'height' : '200px'
			});
			var areaChartInstance = $('#area').epoch({
				type : 'area',
				data : data1,
				axes : [ 'left', 'right', 'bottom' ]
			});

			// line chart
			var data2 = [ {
				label : 'Layer 1',
				values : []
			}, {
				label : 'Layer 2',
				values : []
			}, {
				label : 'Layer 3',
				values : []
			} ];

			for (var i = 0; i < 256; i++) {
				var x = 40 * (i / 256) - 20;
				data2[0].values.push({
					x : x,
					y : Math.sin(x) * (x / 4)
				});
				data2[1].values.push({
					x : x,
					y : Math.cos(x) * (x / Math.PI)
				});
				data2[2].values.push({
					x : x,
					y : Math.sin(x) * (x / 2)
				});
			}

			$('#line').addClass('epoch').addClass('category10');
			$('#line').css({
				'height' : '200px'
			});
			var line = $('#line').epoch({
				type : 'line',
				data : data2
			});

			// bubble chart
			var DATA_LENGTH = 24;

			var data = [ {
				label : 'A',
				values : []
			}, {
				label : 'B',
				values : []
			} ];

			for (var i = 0; i < DATA_LENGTH; i++) {
				for (var j = 0; j < data.length; j++) {
					data[j].values.push({
						x : (Math.random() * 1000),
						y : (Math.random() * 200),
						r : Math.random() * 15 + 1
					});
				}
			}

			$('#bubble').addClass('epoch').addClass('category10');
			$('#bubble').css({
				'height' : '200px'
			});
			var chart = $('#bubble').epoch({
				type : 'scatter',
				data : data,
				axes : [ 'top', 'bottom', 'left', 'right' ]
			});

			return this;
		}
	});

});