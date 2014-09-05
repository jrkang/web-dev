define(function(require, d3) {

	"use strict";

	// require library
	var $ = require('jquery'),
		_ = require('underscore'),
		Backbone = require('backbone'),
		Epoch = require('epoch'),
		d3 = require('d3'),
		nv = require('nv');

	// require template
	var tpl = require('text!tpl/dashboard.html'),
		template = _.template(tpl);

	return Backbone.View.extend({
		render: function() {
			this.$el.empty();
			this.$el.removeClass();
			this.$el.addClass('container-fluid');

			this.$el.html(template());

			// area chart
			var data1 = [{
				key: 'Sqrt',
				values: []
			}, {
				key: 'Cbrt',
				values: []
			}, {
				key: '4th',
				values: []
			}];

			for (var i = 0; i <= 128; i++) {
				var x2 = 20 * (i / 128);
				data1[0].values.push({
					x: x2,
					y: Math.sqrt(x2)
				});
				data1[1].values.push({
					x: x2,
					y: Math.pow(x2, (1 / 3))
				});
				data1[2].values.push({
					x: x2,
					y: Math.pow(x2, (1 / 4))
				});
			}
			$('#area').css({
				'height': '200px'
			});
			nv.addGraph(function() {
				var chart = nv.models.stackedAreaChart().margin({
						left: 100
					}) // Adjust chart margins to give the x-axis some breathing room.
					.useInteractiveGuideline(true) // We want nice looking tooltips and a guideline!
					.transitionDuration(350) // how fast do you want the lines to transition?
					.showLegend(true) // Show the legend, allowing users to turn on/off line series.
					.showYAxis(true) // Show the y-axis
					.showXAxis(true) // Show the x-axis
					.showControls(false) // Allow user to choose 'Stacked', 'Stream', 'Expanded' mode.
				;

				chart.xAxis // Chart x-axis settings
					.axisLabel('Time (ms)').tickFormat(d3.format(',r'));

				chart.yAxis // Chart y-axis settings
					.axisLabel('Voltage (v)').tickFormat(d3.format('.02f'));

				d3.select('#area svg') // Select the <svg> element you want to render the chart in.
					.datum(data1) // Populate the <svg> element with chart data...
					.call(chart); // Finally, render the chart!

				// Update the chart when window resizes.
				nv.utils.windowResize(function() {
					chart.update()
				});
				return chart;
			});

			// line chart
			var data2 = [{
				label: 'Layer 1',
				values: []
			}, {
				label: 'Layer 2',
				values: []
			}, {
				label: 'Layer 3',
				values: []
			}];

			for (var i = 0; i < 256; i++) {
				var x = 40 * (i / 256) - 20;
				data2[0].values.push({
					x: x,
					y: Math.sin(x) * (x / 4)
				});
				data2[1].values.push({
					x: x,
					y: Math.cos(x) * (x / Math.PI)
				});
				data2[2].values.push({
					x: x,
					y: Math.sin(x) * (x / 2)
				});
			}

			$('#line').addClass('epoch').addClass('category10');
			$('#line').css({
				'height': '200px'
			});
			var line = $('#line').epoch({
				type: 'line',
				data: data2
			});

			// bubble chart
			var DATA_LENGTH = 24;

			var data = [{
				label: 'A',
				values: []
			}, {
				label: 'B',
				values: []
			}];

			for (var i = 0; i < DATA_LENGTH; i++) {
				for (var j = 0; j < data.length; j++) {
					data[j].values.push({
						x: (Math.random() * 1000),
						y: (Math.random() * 200),
						r: Math.random() * 15 + 1
					});
				}
			}

			$('#bubble').addClass('epoch').addClass('category10');
			$('#bubble').css({
				'height': '200px'
			});
			var chart = $('#bubble').epoch({
				type: 'scatter',
				data: data,
				axes: ['top', 'bottom', 'left', 'right']
			});

			return this;
		},
		
	});

});