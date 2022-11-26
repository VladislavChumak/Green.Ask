window.onload = showMainPanel();
var models_buttons = document.querySelector(".models_buttons");
document.getElementById("Main").onclick = function showMain(){
	models_buttons.style.visibility = 'hidden';
	showMainPanel();
}
function showMainPanel(){
	var xhr = new XMLHttpRequest();
    xhr.overrideMimeType("application/json");
	xhr.open("GET", "ShowMainPanal", false);
	xhr.send();
	if (xhr.status != 200) {
		console.log( xhr.status + ': ' + xhr.statusText ); 
		console.log('didn\'t work');
	} else {
		document.getElementById("rezult").innerHTML = xhr.responseText;
	}
}
document.getElementById("Models").onclick = function showModels(){
	models_buttons.style.visibility = 'visible';
}
document.querySelector(".model_button1").onclick = function showModel1(){
	console.log("Yes1");
	showModel(1);
}
document.querySelector(".model_button2").onclick = function showModel2(){
	console.log("Yes2");
	showModel(2);
}
document.querySelector(".model_button3").onclick = function showModel3(){
	console.log("Yes3");
	showModel(3);
}
function showModel(number){
	var xhr = new XMLHttpRequest();
    xhr.overrideMimeType("application/json");
	xhr.open("GET", "ShowModel"+number, false);
	xhr.send();
	if (xhr.status != 200) {
		console.log( xhr.status + ': ' + xhr.statusText ); 
		console.log('didn\'t work');
	} else {
		document.getElementById("rezult").innerHTML = xhr.responseText;
	}
}
document.getElementById("Contact").onclick = function showContact(){
	models_buttons.style.visibility = 'hidden';
	showFeedback();
}
function showFeedback(){
	var xhr = new XMLHttpRequest();
    xhr.overrideMimeType("application/json");
	xhr.open("GET", "showFeedback", false);
	xhr.send();
	if (xhr.status != 200) {
		console.log( xhr.status + ': ' + xhr.statusText ); 
		console.log('didn\'t work');
	} else {
		document.getElementById("rezult").innerHTML = xhr.responseText;
	}
}