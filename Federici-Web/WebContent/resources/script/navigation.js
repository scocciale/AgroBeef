$(".button-collapse").sideNav();
$("#close-menu").parent().hide();

var toggleMenu = function(action) {
	console.log("action " + action);
	if (action == 'show') {
		//$("#content").addClass("padding-menu");
		$("#open-menu").parent().hide();
		$("#close-menu").parent().show();
	} else {
		//$("#content").removeClass("padding-menu");
		$(this).parent().hide();
		$("#open-menu").parent().show();
		$("#close-menu").parent().hide();
	}
	$(".button-collapse").sideNav(action);
}
