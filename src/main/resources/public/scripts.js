var workDays = [];

$(document).ready(function () {
	$("#input-file").change(loadFileContent);
	$("#lazy-loading-form").submit(sendData);
	clear();
});

function clear() {
	$("#file-content").empty();
}

function loadFileContent(event) {
	clear();
	var inputFile = event.target.files[0];
	var formData = new FormData();
	formData.append("inputFile", inputFile);
	$.ajax({
			url: "http://localhost:8080/fileContent",
			type: "POST",
			contentType: false,
			processData: false,
			data: formData
		}).done(showContent);
}

function showContent(data) {
	workDays = data;
	$("#file-content").append($("<p>").text("Working days: " + workDays.length));
	for (var day of workDays) {
		var details = $("<p>").text("Day #" + day.day + ": ");
		for (var element of day.elements) {
			details.append(element.weight + " ");
		}
		$("#file-content").append(details);
	}
}

function sendData(event) {
	event.preventDefault();
	var minWeight = $("#min-weight").val();
	var customerId = $("#customer-id").val();
	workDays.forEach(function (day) {
			day.customerId = customerId;
		});
	var data = {"minWeight": minWeight, "workDays": workDays};
	alert("from client: " + JSON.stringify(data));
	$.ajax({
			url: "http://localhost:8080/processData",
			type: "POST",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(data)
		}).done(showResults);
}

function showResults(data) {
	var results = $("#results");
	alert("from server: " + JSON.stringify(data));
	results.append($("<h3>").text(data[0].customerId));
	for (var day of data) {
		results.append($("<p>").text("Case #" + day.day + ": " + day.travels));
	}
}