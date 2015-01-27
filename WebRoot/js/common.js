
function go(action, pageNo, sort) {
  document.getElementById("currentPage").value = pageNo;
  if(sort != '') {
  	document.getElementById("sort").value = sort;
  }
  document.getElementById("qform").action = action;
  document.getElementById("qform").submit();
}

function toReset() {
	$('input:text').val('');
	$('input:password').val('');
	$('#first_input').focus();
}

function add(action) {
	location.href = action;
}

function objectEval(text) {
    text = text.replace(/\n/g, ' ');
    text = text.replace(/\r/g, ' ');
    if (text.match(/^\s*\{.*\}\s*$/))
    {
      text = '[' + text + '][0]';
    }
    return eval(text);
  }