

class Event {
	
	constructor(currency1, currency2, sum, result, date) {
	  
		this.currency1 = currency1;
		this.currency2 = currency2;
		this.sum = sum;
		this.result = result;
		this.date = date;
	}
}


function handleInput() {
	
	let value1 = $("#currency1").val();
	let value2 = $("#currency2").val();
	let sum = $("#inp").val().replace(",", ".");
	
	if (value1 != "" && value2 != "" && sum != "") {
		
		if (inputIsCorrect(sum)) {
		
			sum = parseFloat(sum).toFixed(4);
			let result = (value1 / value2 * sum).toFixed(4);
			$("#result").val(result);
			
			let event = new Event($("#currency1 option:selected").text(), $("#currency2 option:selected").text(), sum, result, new Date());
			outputNewEvent(event);
			
			// Notify the server
			let data = {"currency1": event.currency1, "currency2": event.currency2, "sum": event.sum, "result": event.result};
		    let url = "/save";
		    let userJson = JSON.stringify(data);
			
			$.ajax
		    ({
		        type: "POST",
		        data: userJson,
		        url: url,
		        contentType: "application/json; charset=utf-8",
		        success: function(data) { }
		    });	
		}
	}
}


function inputIsCorrect(inp) {
	
	$("#message").text("");
	
	for (let i=0; i < inp.length; i++) {
		if (!symbolIsCorrect(inp[i])) {
			$("#message").text("Пожалуйста, введите число!");
			return false;
		}
	}
	
	return true;
}


function toFloat(sum) {
	
	return parseFloat(str.replace(",", "."));
}


function symbolIsCorrect(symbol) {
	let numbers = "0123456789.";
	return numbers.includes(symbol);
}


function outputNewEvent(event) {
	
	let text = "<tr>" + 
		"<td>" + event.currency1 + "</td>" +
		"<td>" + event.currency2 + "</td>" +
		"<td>" + event.sum + "</td>" +
		"<td>" + event.result + "</td>" +
		"<td>" + formatDate(event.date) + "</td>" +
		"</tr>";
	
	$("#history tr:last").after(text);
}


function formatDate(date) {

	  let day = date.getDate();
	  if (day < 10) day = "0" + day;

	  let month = date.getMonth() + 1;
	  if (month < 10) month = "0" + month;

	  let year = date.getFullYear();
	  
	  return day + "." + month + "." + year;
}
