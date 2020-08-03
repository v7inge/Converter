
function handleInput() {
	
	let value1 = $("#currency1").val();
	let value2 = $("#currency2").val();
	let sum = $("#inp").val().replace(",", ".");
	
	if (value1 != "" && value2 != "" && sum != "") {
		
		if (inputIsCorrect(sum)) {
		
			sum = parseFloat(sum);
			let result = value1 / value2 * sum;	
			console.log("result: " + result);
			
			// Notify the server
			let data = {"currency1": $("#currency1 option:selected").text(), "currency2": $("#currency2 option:selected").text(), "sum": sum, "result": result};
		    let url = "/save";
		    let userJson = JSON.stringify(data);
			
			$.ajax
		    ({
		        type: "POST",
		        data: userJson,
		        url: url,
		        contentType: "application/json; charset=utf-8",
		        success: function(data)
		    	{
		        	//outputMessageHistory(data);
		    	}
		    });
			
		}
	}
}


function inputIsCorrect(inp) {
	
	$("#message").text("");
	
	for (let i=0; i < inp.length; i++) {
		if (!symbolIsCorrect(inp[i])) {
			$("#message").text("Пожалуйста, введите число");
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