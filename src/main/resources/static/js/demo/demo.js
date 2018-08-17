(function() {
	if(window.krt == undefined || window.krt == null) {
		window.krt = {};
	}
	krt.demo = {
		init : function() {
			$("#demo1").click(function() {
				alert(1234);
			});
		},
	};
	
})();

$(document).ready(function() {
	krt.demo.init();
});