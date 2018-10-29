$(document).ready(function(){
	$('.message a').click(function(){
	   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	});
	$('.message').click(function(){
		console.log("Hello");
	})
})

function login() {
	alert($('.message'));
}