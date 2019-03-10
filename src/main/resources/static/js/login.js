$(document).ready(function(){
	$('.message a').click(function(){
	   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	});
	$('.message').click(function(){
		console.log("Hello");
	})
})

function login() {
    var params = {
    username: $("#username")[0].value,
    password: $("#pwd")[0].value
    };
    $.post("/login", params,function(data){
    alert("Data Loaded: " + data);
    window.location = "/home";
    });

}