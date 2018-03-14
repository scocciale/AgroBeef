/**
 * 
 */

function overrideXAxis() {
	this.cfg.axes.yaxis.tickOptions = {
		formatString : "%'i"
	};

	this.cfg.highlighter = {
		show : true,
		sizeAdjust : 3,
		tooltipContentEditor : function(str, seriesIndex, pointIndex, jqPlot) {

			return jqPlot['data'][0][pointIndex][0] + " - " + str.split(",")[1];

		}
	};
	this.cfg.grid = {
		background : 'transparent'
	};

	$.jqplot.sprintf.thousandsSeparator = '.';

};

function overrideXAxisFortTaxGap() {
	this.cfg.axes.yaxis.tickOptions = {
		formatString : "%.2f %"
	};

	this.cfg.highlighter = {
		show : true,
		sizeAdjust : 3,
		tooltipContentEditor : function(str, seriesIndex, pointIndex, jqPlot) {
			return str.split(",")[1];

		}
	};
	this.cfg.grid = {
		background : 'transparent'
	};
	/*
	 * this.cfg.seriesDefaults= { fill: true, fillToZero: true, rendererOptions: {
	 * highlightMouseDown: true } }
	 */
};

function andamentoGraph() {

	this.cfg.axes.yaxis.tickOptions = {
		formatString : "%'i"
	};

	this.cfg.legend = {
		show : true,
		location : 'ne',
		renderer : $.jqplot.EnhancedLegendRenderer,
		placement : 'outsideGrid',
		rendererOptions : {
			numberColumns : 1,
			seriesToggle : 'normal',
			seriesToggleReplot : {
				resetAxes : true
			},
			disableIEFading : true,

		}
	};

	this.cfg.highlighter = {
		show : true,
		sizeAdjust : 3,
		tooltipContentEditor : function(str, seriesIndex, pointIndex, jqPlot) {
			return str.split(",")[1];

		}
	};
	this.cfg.grid = {
		background : 'transparent'
	};

	this.cfg.series = [ {
		label : 'Riscosso effettivo',
		showLine : true,
		color : '#8e001c'
	}, {
		label : 'Riscosso simulato',
		showLine : true,
		linePattern : 'dashed',
		color : '#8e001c'
	}, {
		label : 'Gettito modellato',
		showLine : true,
		color : '#009688'
	}, {
		label : 'Gettito simulato',
		showLine : true,
		linePattern : 'dashed',
		color : '#009688'
	} ]

}