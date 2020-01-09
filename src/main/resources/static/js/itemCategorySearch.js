/**
 * 
 */
$(function () {

	$('#big-category').on('change', function () {
		var grandChildHtml = '<select  id=\"grandChildCategory\" class=\"form-control\"><option>- grandChild -</option>'
		var html = '<select  id=\"childCategory\" class=\"form-control\"><option>- childCategory -</option>'
		if ($(this).val().length < 4) {
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/childCategory",
				data: $(this).val()
			}).done(function (data) {

				for (var i in data) {
					var h = '<option value=\"' + data[i].id + '\">' + data[i].name + '</option>';
					html += h;

				};
				html += '</select>'
				$('#childCategory').html(html);
				$('#grandChildCategory').html(grandChildHtml)
			}).fail(function (jqXHR, textStatus, errorThrown) {
			});
		} else {
			html += '</select>'
			grandChildHtml += '</select>'
			$('#childCategory').html(html);
			$('#grandChildCategory').html(grandChildHtml);
		}
	});
	$('#childCategory').on('change', function () {
		var html = '<select  id=\"grandChildCategory\" class=\"form-control\"><option>- grandChild -</option>'
		console.log($(this).val())
		if ($(this).val().length < 5) {
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/childCategory",
				data: $(this).val()
			}).done(function (data) {
				for (var i in data) {
					var h = '<option value=\"' + data[i].id + '\">' + data[i].name + '</option>';
					html += h;

				};
				html += '</select>'
				$('#grandChildCategory').html(html);
			}).fail(function (jqXHR, textStatus, errorThrown) { });
		} else {
			html += '</select>'
			$('#grandChildCategory').html(html);
		}
	});
});