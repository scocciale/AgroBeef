/*$(document).ready(function(){
  $(".boxmenu").mouseover(function(){
	  $(this).find('.boxmenu_testo').stop().slideDown("slow");
  });
  $(".boxmenu").mouseout(function(){
	  $(this).find('.boxmenu_testo').slideUp("slow");
  });
 });*/

function pieExtender() {

	this.cfg.seriesDefaults.shadow = true;
	this.cfg.grid = {
		drawBorder : false,
		shadow : false,
		background : 'transparent'
	};

	this.cfg.highlighter = {
		show : true,
		useAxesFormatters : false,
		tooltipFormatString : "%'d",
		tooltipContentEditor : function(str, seriesIndex, pointIndex, jqPlot) {

			return jqPlot['data'][0][pointIndex][0] + " " + str;

		}
	// show:true,
	// useAxesFormatters: false,
	// tooltipFormatString: '%d',
	// tooltipAxes: 'both'
	};

	this.cfg.legend = {
		show : true,
		location : 'n',
		renderer : $.jqplot.EnhancedLegendRenderer,
		placement : 'outsideGrid',
		rendererOptions : {
			numberColumns : 11,
			seriesToggle : 'normal',
			disableIEFading : true,

		}
	};

	this.cfg.redraw;

	this.cfg.seriesDefaults.rendererOptions = {
		showDataLabels : true,
		diameter : 200
	};
	$.jqplot.sprintf.thousandsSeparator = '.';

}

function metroExtender() {

	this.cfg.axes.yaxis.tickOptions.showGridline = false;
	this.cfg.axes.xaxis.tickOptions.showGridline = false;

	this.cfg.seriesDefaults.shadow = false;
	this.cfg.grid = {
		drawBorder : false,
		shadow : false,
		background : "white"
	};
	/* this.cfg.seriesDefaults.rendererOptions.varyBarColor = true; */
	this.cfg.redraw;

}

function metroExtenderNoXAxis() {

	this.cfg.axes.yaxis.tickOptions.showGridline = false;
	this.cfg.axes.xaxis.tickOptions.showGridline = false;
	this.cfg.axes.xaxis.tickOptions.show = false;

	this.cfg.seriesDefaults.shadow = false;
	this.cfg.grid = {
		drawBorder : false,
		shadow : false,
		background : "white"
	};

	/* this.cfg.seriesDefaults.rendererOptions.varyBarColor = true; */
	this.cfg.redraw;

}

function metroExtenderSimulazioneSintesiBar() {

	this.cfg.axes.yaxis.tickOptions.showGridline = false;
	this.cfg.axes.xaxis.tickOptions.showGridline = false;
	this.cfg.axes.xaxis.tickOptions.show = false;

	this.cfg.seriesDefaults.shadow = false;
	this.cfg.grid = {
		drawBorder : false,
		shadow : false,
		background : "white"
	};
	this.cfg.legend = {
		show : true,
		renderer : $.jqplot.EnhancedLegendRenderer,
		placement : 'outsideGrid',
		rendererOptions : {
			seriesToggle : 'normal'
		}

	};

	this.cfg.redraw;
}

function barRegoleModelCustom() {

	this.cfg.axes.yaxis.tickOptions = {
		formatString : "%'i"
	};

	this.cfg.grid = {
		background : 'transparent'
	};


	$.jqplot.sprintf.thousandsSeparator = '.';
	
}

function barGruppiModelCustom() {

	this.cfg.axes.yaxis.tickOptions = {
		formatString : "%'i"
	};

	this.cfg.grid = {
		background : 'transparent'
	};
	
	this.cfg.highlighter = {
			show : true,
			sizeAdjust : 3,
			tooltipContentEditor : function(str, seriesIndex, pointIndex, jqPlot) {			
				return jqPlot.axes.xaxis.ticks[pointIndex] + ", " + str.split(",")[1];	
			}
		};
	
	$.jqplot.sprintf.thousandsSeparator = '.';
	
}



function metroExtenderSimulazioneSintesiBar_New() {

	this.cfg.axes.yaxis.tickOptions.showGridline = false;
	this.cfg.axes.xaxis.tickOptions.showGridline = false;
	// this.cfg.axes.xaxis.tickOptions.show = false;

	this.cfg.seriesDefaults.shadow = false;
	this.cfg.grid = {
		drawBorder : false,
		shadow : false,
		background : "white"
	};
	this.cfg.legend = {
		show : true,
		renderer : $.jqplot.EnhancedLegendRenderer,
		placement : 'outsideGrid',
		rendererOptions : {
			seriesToggle : 'normal'
		}

	};

	this.cfg.redraw;
}