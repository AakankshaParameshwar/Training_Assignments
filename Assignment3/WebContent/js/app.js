$(document).ready(function(){
	$insertForm=$('#insert');
	$insertForm.hide('fast');
	console.log("yaayy");
	$('.add-button').click(function(){
		$insertForm.show('fast');
	});
	$('.edit-button').click(function(){
		$('#insert input').each(function(){
			$(this).css("color","red");
			console.log("ENTERS");
			if($(this).attr('disabled')){
				$(this).removeAttr('disabled');
			}
		});
	});
});