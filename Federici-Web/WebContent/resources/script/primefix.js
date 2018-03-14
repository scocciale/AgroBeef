$(document).ready(function() {

	// funzione per aggiornare le label dopo una chiamata JSF AJAX da
	// utilizzare
	// come metodo onconplete dei command button
	updateLabel = function() {
		$('input[type=text]').each(function() {
			if ($(this).val()) {
				console.log($(this).val());
				$(this).parent().find('label').addClass("active");
			}
		});
		PF('statusDialog').hide();
	};

	/** ****** GESTIONE MENU ******* */
	// apertura menu
	toggleMenuOpen = function() {
		$('#slide-out').addClass('fixed');
		$('#content').css('padding-left', '10px');
		$('main').css('padding-left', '240px');
		$('#toggle-menu-open').hide();
		$('#toggle-menu-close').show();
	};

	// chiusura menu
	toggleMenuClose = function() {
		$('#slide-out').removeClass('fixed');
		$('#content').css('padding-left', '17px');
		$('main').css('padding-left', '0px');
		$('#toggle-menu-close').hide();
		$('#toggle-menu-open').show();
	};

	// Init componenti di materialize css
	$('#toggle-menu-open').hide();
	$('.modal-trigger').leanModal();
});