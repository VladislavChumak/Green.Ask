var arrayOfDebtors;
var operation=0;
document.getElementById("Continue").onclick=function Continue(){
	if(arrayOfDebtors==null)getArrayOfDebtors();
	if(operation==1)addDebtor();
	else if(operation==2)deleteDebtor();
	else if(operation==3)selectEditDebtor();
	else if(operation==4)editDebtor();
	else if(operation==5)findDebtor();
}
document.getElementById("ShowAddPanel").onclick = function showAddPanel(){
	if(arrayOfDebtors==null)getArrayOfDebtors();
	var rez = document.querySelector(".rez");
	var table="<table> <tbody>";
	for(var i=0;i<arrayOfDebtors[0].length-2;i++){
		table+="<tr><td>"+arrayOfDebtors[0][i]+"</td><td><input type=\"text\" id=\"text "+i+"\"/></td></tr>";
	}
	table+="</tbody></table>";
	rez.innerHTML=table;
	operation=1;
	document.getElementById("Continue").style.visibility = "visible";
}
function addDebtor(){
	var xhr = new XMLHttpRequest();
    var kol=arrayOfDebtors[0].length-2;
	let arr = new Array();
	for(var i=0;i<kol;i++){
		var rezult = document.getElementById("text "+i);
		if(rezult.value.length!=0)arr.push(rezult.value);
		else {
			alert('Введены не все данные');
			return;
		}
	}
	xhr.open("POST", 'AddDebtor', false);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.send(JSON.stringify(arr));
	if (xhr.status != 201) {
		console.log(xhr.status + ': ' + xhr.statusText ); 
	}
	document.getElementById("Continue").style.visibility = "hidden";
	document.querySelector(".rez").innerHTML="";
	document.getElementById("ShowDebtors").click();
}
document.getElementById("DeleteDebtors").onclick=function deleteDebtors(){
	if(arrayOfDebtors==null)getArrayOfDebtors();
	var table="<table> <tbody>";
	table+="<tr>";
	for(var y=0;y<arrayOfDebtors[0].length-1;y++){
		table+="<th>"+arrayOfDebtors[0][y]+"</th>";
	}
	table+="<th>Delete</th>";
	table+="</tr>";
	for(var i=1;i<arrayOfDebtors.length;i++){
		table+="<tr>";
		for(var y=0;y<arrayOfDebtors[0].length-1;y++){
			table+="<td>"+arrayOfDebtors[i][y]+"</td>";
		}
		table+="<td>"+"<input type=\"checkbox\" id=\"checkbox "+i+"\">"+"</td>";
		table+="</tr>";
	}
	table+="</tbody></table>";
	document.querySelector(".rez").innerHTML=table;
	operation=2;
	document.getElementById("Continue").style.visibility = "visible";
}
function deleteDebtor(){
	var xhr = new XMLHttpRequest();
    var kol=arrayOfDebtors.length-1;
	let arr = new Array();
	for(var i=1;i<=kol;i++){
		var rezult = document.getElementById("checkbox "+i);
		if(rezult.checked)arr.push(arrayOfDebtors[i][arrayOfDebtors[0].length-1]);
	}
	xhr.open("POST", 'DeleteDebtor', false);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.send(JSON.stringify(arr));
	if (xhr.status != 201) {
		console.log(xhr.status + ': ' + xhr.statusText ); 
	}
	document.getElementById("Continue").style.visibility = "hidden";
	document.querySelector(".rez").innerHTML="";
	document.getElementById("ShowDebtors").click();
}
document.getElementById("ShowDebtors").onclick = function showDebtors(){
	document.getElementById("Continue").style.visibility = "hidden";
	getArrayOfDebtors();
	var table="<table id=\"sortable\">";
	table+="<thead><tr>";
	var znach;
	for(var y=0;y<arrayOfDebtors[0].length-1;y++){
		try {
			znach=arrayOfDebtors[1][y];
			if(isNaN(znach))table+="<th>"+arrayOfDebtors[0][y]+"</th>"
			else table+="<th data-type=\"number\">"+arrayOfDebtors[0][y]+"</th>"
		} catch (err) {
			table+="<th>"+arrayOfDebtors[0][y]+"</th>"
		}
	}
	table+="</tr></thead><tbody>";
	for(var i=1;i<arrayOfDebtors.length;i++){
		table+="<tr>";
		for(var y=0;y<arrayOfDebtors[0].length-1;y++){
			table+="<td>"+arrayOfDebtors[i][y]+"</td>";
		}
		table+="</tr>";
	}
	table+="</tbody></table>";
	document.querySelector(".rez").innerHTML=table;
	Sort();
}

window.onload = getArrayOfDebtors();
function getArrayOfDebtors(){
	var xhr = new XMLHttpRequest();
    xhr.overrideMimeType("application/json");
	xhr.open("GET", "ShowDebtors", false);
	xhr.send();
	if (xhr.status != 200) {
		console.log( xhr.status + ': ' + xhr.statusText ); 
	} else {
		arrayOfDebtors=JSON.parse(xhr.responseText);
	}
}

//Сортировка (не моя)
function Sort() {
    const table = document.getElementById('sortable');
    const headers = table.querySelectorAll('th');
    const tableBody = table.querySelector('tbody');
    const rows = tableBody.querySelectorAll('tr');

    // Направление сортировки
    const directions = Array.from(headers).map(function(header) {
        return '';
    });

    // Преобразовать содержимое данной ячейки в заданном столбце
    const transform = function(index, content) {
        // Получить тип данных столбца
        const type = headers[index].getAttribute('data-type');
        switch (type) {
            case 'number':
                return parseFloat(content);
            case 'string':
            default:
                return content;
        }
    };

    const sortColumn = function(index) {
        // Получить текущее направление
        const direction = directions[index] || 'asc';

        // Фактор по направлению
        const multiplier = (direction === 'asc') ? 1 : -1;

        const newRows = Array.from(rows);

        newRows.sort(function(rowA, rowB) {
            const cellA = rowA.querySelectorAll('td')[index].innerHTML;
            const cellB = rowB.querySelectorAll('td')[index].innerHTML;

            const a = transform(index, cellA);
            const b = transform(index, cellB);    

            switch (true) {
                case a > b: return 1 * multiplier;
                case a < b: return -1 * multiplier;
                case a === b: return 0;
            }
        });

        // Удалить старые строки
        [].forEach.call(rows, function(row) {
            tableBody.removeChild(row);
        });

        // Поменять направление
        directions[index] = direction === 'asc' ? 'desc' : 'asc';

        // Добавить новую строку
        newRows.forEach(function(newRow) {
            tableBody.appendChild(newRow);
        });
    };

    [].forEach.call(headers, function(header, index) {
        header.addEventListener('click', function() {
            sortColumn(index);
        });
    });
}

document.getElementById("EditDebtors").onclick = function editDebtors(){
	if(arrayOfDebtors==null)getArrayOfDebtors();
	var table="<table> <tbody>";
	table+="<tr>";
	for(var y=0;y<arrayOfDebtors[0].length-1;y++){
		table+="<th>"+arrayOfDebtors[0][y]+"</th>";
	}
	table+="<th>Edit</th>";
	table+="</tr>";
	for(var i=1;i<arrayOfDebtors.length;i++){
		table+="<tr>";
		for(var y=0;y<arrayOfDebtors[0].length-1;y++){
			table+="<td>"+arrayOfDebtors[i][y]+"</td>";
		}
		table+="<td>"+"<input type=\"checkbox\" id=\"checkbox "+i+"\" name="+arrayOfDebtors[i][arrayOfDebtors[0].length-1]+"\>"+"</td>";
		table+="</tr>";
	}
	table+="</tbody></table>";
	document.querySelector(".rez").innerHTML=table;
	for(var y=1;y<arrayOfDebtors.length;y++){
		document.getElementById("checkbox "+y).addEventListener('change', function() {
			var thisid=this.id.replace(/checkbox /gi,'');
			for(var i=1;i<arrayOfDebtors.length;i++){
				var id = document.getElementById("checkbox "+i).id.replace(/checkbox /gi,'');
				if(this.checked&&thisid!=id)document.getElementById("checkbox "+i).checked=false;
			}
		});
	}
	operation=3;
	document.getElementById("Continue").style.visibility = "visible";
}

function selectEditDebtor(){
	var table="";
	table+=document.querySelector(".rez").innerHTML;
	var kol=arrayOfDebtors.length-1;
	for(var i=1;i<=kol;i++){
		var rezult = document.getElementById("checkbox "+i);
		if(rezult.checked)editSelectedDebtor(i,table);
	}
}
var SelectedId;
function editSelectedDebtor(checkBoxId,table){
	var addTable="<table> <tbody>";
	for(var i=0;i<arrayOfDebtors[0].length-1;i++){
		addTable+="<tr><td>"+arrayOfDebtors[0][i]+"</td><td><input type=\"text\" id=\"text "+i+"\"/></td></tr>";
	}
	addTable+="</tbody></table>";
	document.querySelector(".rez").innerHTML+=addTable;
	SelectedId=checkBoxId;
	document.getElementById("checkbox "+checkBoxId).checked=true;
	operation=4;
}
function editDebtor(){
	var xhr = new XMLHttpRequest();
    var kol=arrayOfDebtors[0].length-2;
	let arr = new Array();
	for(var i=0;i<=kol;i++){
		var rezult = document.getElementById("text "+i);
		if(rezult.value.length!=0)arr.push(rezult.value);
		else {
			alert('Введены не все данные');
			return;
		}
	}
	console.log(SelectedId);
	arr.push(document.getElementById("checkbox "+SelectedId).name);
	xhr.open("POST", 'EditDebtor', false);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.send(JSON.stringify(arr));
	if (xhr.status != 201) {
		console.log(xhr.status + ': ' + xhr.statusText ); 
	}
	document.getElementById("Continue").style.visibility = "hidden";
	document.querySelector(".rez").innerHTML="";
	document.getElementById("ShowDebtors").click();
}
document.getElementById("FindDebtors").onclick = function findDebtors(){
	if(arrayOfDebtors==null)getArrayOfDebtors();
	var table="<table> <tbody>";
	table+="<tr><td>Введите значение для поиска</td><td><input type=\"text\" id=\"text 0\"/></td>";
	table+="</tbody></table>";
	document.querySelector(".rez").innerHTML=table;
	operation=5;
	document.getElementById("Continue").style.visibility = "visible";
}
function findDebtor(){
	var table="<table> <tbody>";
	var value=document.getElementById("text 0").value;
	for(var i=1;i<arrayOfDebtors.length;i++){
		if(arrayOfDebtors[i].indexOf(value)!=-1){
			table+="<tr>";
			for(var y=0;y<arrayOfDebtors[0].length-1;y++){
				table+="<td>"+arrayOfDebtors[i][y]+"</td>";
			}
			table+="</tr>";
		}
		else console.log(value+" "+arrayOfDebtors[0].indexOf(value));
	}
	table+="</tbody></table>";
	document.querySelector(".rez").innerHTML=table;
	document.getElementById("Continue").style.visibility = "hidden";
}
document.getElementById("ShowActiveDebtors").onclick = function showActiveDebtors(){
	if(arrayOfDebtors==null)getArrayOfDebtors();
	var table="<table><thead>";
	for(var y=0;y<arrayOfDebtors[0].length-1;y++){
		table+="<th>"+arrayOfDebtors[0][y]+"</th>"
	}
	table+="</tr></thead><tbody>";
	for(var i=1;i<arrayOfDebtors.length;i++){
		if(arrayOfDebtors[i][arrayOfDebtors[0].length-2]=="Active"){
			table+="<tr>";
			for(var y=0;y<arrayOfDebtors[0].length-1;y++){
				table+="<td>"+arrayOfDebtors[i][y]+"</td>";
			}
			table+="</tr>";
		}
		else console.log(arrayOfDebtors[i][arrayOfDebtors[0].length-2]);
	}
	table+="</tbody></table>";
	document.querySelector(".rez").innerHTML=table;
}