var workDays = [];

$(document).ready(function () {
	$("#input-file").change(loadFileContent);
	$("#lazy-loading-form").submit(sendData);
	clear();
});

function clear() {
	$("#file-content").empty();
	$("#results").empty().text("No results.");
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
		for (var weight of day.weights) {
			details.append(weight + " ");
		}
		$("#file-content").append(details);
	}
}

function sendData(event) {
	event.preventDefault();
	var minWeight = $("#min-weight").val();
	var data = {"minWeight": minWeight, "workDays": workDays};
	$.ajax({
			url: "http://localhost:8080/processData",
			type: "POST",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(data)
		}).done(showResults);
}

function showResults(data) {
	var results = $("#results").empty();
	for (var result of data) {
		results.append($("<p>").text(result));
	}
}