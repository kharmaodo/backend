<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script src="<c:url value="js/nicEdit.js"/>" type="text/javascript"></script>
<script type="text/javascript">
var first_input, contactContent;
var taskContents = new Array(10);
var resourceContents = new Array(10);
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
	$('#result').html('${subCategory}');
	taskContents[0] = new nicEditor({fullPanel : true, maxHeight : 200}).panelInstance('taskContents_0');
	resourceContents[0] = new nicEditor({fullPanel : true, maxHeight : 200}).panelInstance('resourceContents_0');
	first_input = new nicEditor({fullPanel : true, maxHeight : 200}).panelInstance('first_input');
	contactContent = new nicEditor({fullPanel : true, maxHeight : 200}).panelInstance('contactContent');
	
	$("#add_button_task").click
	  (
	    function()
	    {
	      var task_length = $("input[name^=taskDescriptions]").length;
	      $("#task_area").append('<table id="task_'+task_length+'"><tr><td class="label" style="width:60px"><spring:message code="text.project.description"/></td><td class="field"><input type="text" name="taskDescriptions" id="taskDescriptions_'+task_length+'" value="" size="50"/></td></tr><tr><td class="label"><spring:message code="text.project.content"/></td><td class="field"><textarea name="taskContents" id="taskContents_'+task_length+'" style="width:820px; height:200px;"></textarea></td></tr></table>');
	      taskContents[task_length] = new nicEditor({fullPanel : true, maxHeight : 200}).panelInstance('taskContents_' + task_length);
	    }
	  );
	
	$("#add_button_resource").click
	  (
	    function()
	    {
	      var resource_length = $("input[name^=resourceDescriptions]").length;	
	      $("#resource_area").append('<table id="resource_'+resource_length+'"><tr><td class="label" style="width:60px"><spring:message code="text.project.description"/></td><td class="field"><input type="text" name="resourceDescriptions" id="resourceDescriptions_'+resource_length+'" value="" size="50"/></td></tr><tr><td class="label"><spring:message code="text.project.content"/></td><td class="field"><textarea name="resourceContents" id="resourceContents_'+resource_length+'" style="width:820px; height:200px;"></textarea></td></tr></table>');
	      resourceContents[resource_length] = new nicEditor({fullPanel : true, maxHeight : 200}).panelInstance('resourceContents_' + resource_length);
	    }
	  );
});

function del_task() {
	var task_length = $("input[name^=taskDescriptions]").length;
	if(task_length == 1) {
		$('#taskDescriptions_0').val('');
		nicEditors.findEditor('taskContents_0').setContent('');
		//alert('無法再移除!');
		exit();
	}
	task_length--;
	var parent = document.getElementById('task_area');
	var child = document.getElementById('task_' + task_length);
	parent.removeChild(child);
}

function del_resource() {
	var task_length = $("input[name^=resourceDescriptions]").length;
	if(task_length == 1) {
		$('#resourceDescriptions_0').val('');
		nicEditors.findEditor('resourceContents_0').setContent('');
		//alert('無法再移除!');
		exit();
	}
	task_length--;
	var parent = document.getElementById('resource_area');
	var child = document.getElementById('resource_' + task_length);
	parent.removeChild(child);
}

function switchEditor() {
	if(!first_input) {
		first_input = new nicEditor({fullPanel : true, maxHeight : 200}).panelInstance('first_input');
	} else {
		first_input.removeInstance('first_input');
		first_input = null;
	}
	
	if(!contactContent) {
		contactContent = new nicEditor({fullPanel : true, maxHeight : 200}).panelInstance('contactContent');
	} else {
		contactContent.removeInstance('contactContent');
		contactContent = null;
	}
	
	for(i=0; i<$("input[name^=taskDescriptions]").length; i++) {
		if(!taskContents[i]) {
			taskContents[i] = new nicEditor({fullPanel : true, maxHeight : 200}).panelInstance('taskContents_' + i);
		} else {
			taskContents[i].removeInstance('taskContents_' + i);
			taskContents[i] = null;
		}
	}
	
	for(i=0; i<$("input[name^=resourceDescriptions]").length; i++) {
		if(!resourceContents[i]) {
			resourceContents[i] = new nicEditor({fullPanel : true, maxHeight : 200}).panelInstance('resourceContents_' + i);
		} else {
			resourceContents[i].removeInstance('resourceContents_' + i);
			resourceContents[i] = null;
		}
	}
}

function checkSubmit() {
	if(!first_input) {
		switchEditor();
		alert('<spring:message code="project.before.submit"/>');
		return false;
	}
	if(nicEditors.findEditor('first_input').getContent() == '' || nicEditors.findEditor('first_input').getContent() == '<br>' || nicEditors.findEditor('first_input').getContent() == '<BR>') {
		alert('<spring:message code="project.required"/>');
		return false;
	} else {
		var task_length = $("input[name^=taskDescriptions]").length;
		var resource_length = $("input[name^=resourceDescriptions]").length;
		
		document.getElementById('first_input').value = nicEditors.findEditor('first_input').getContent();
		
		if(nicEditors.findEditor('contactContent').getContent() == '' ||
		   nicEditors.findEditor('contactContent').getContent() == '<BR>' ||
		   nicEditors.findEditor('contactContent').getContent() == '<br>') {
			document.getElementById('contactContent').value = '';
		} else {
			document.getElementById('contactContent').value = nicEditors.findEditor('contactContent').getContent();
		}
		
		for(i=0; i<task_length; i++) {
			document.getElementById('taskContents_'+i).value = (nicEditors.findEditor('taskContents_'+i).getContent());
		}
		
		for(i=0; i<resource_length; i++) {
			document.getElementById('resourceContents_'+i).value = (nicEditors.findEditor('resourceContents_'+i).getContent());
		}
		
		document.getElementById('projectEditForm').submit();
		return true;
	}
}
</script>
<center>
<table style="width:900px">
	<tr>
		<td align="right">
			<input type="button" value="<spring:message code="project.switch"/>" onClick="switchEditor()">
		</td>
	</tr>
  </table>
  <form name="projectEditForm" method="POST" action="<c:url value="project.htm?act=doInsert"/>" id="projectEditForm">
  <spring:bind path="command.menuId"><input type="hidden" name="menuId" value="${status.value}"></spring:bind>
  <spring:bind path="command.locale"><input type="hidden" name="locale" value="${status.value}"></spring:bind>
  <div class="login_L">
  	<table style="width:900px">
  		<tr>
  			<td colspan="3" align="left"><b><spring:message code="text.project.title.introdution"/></b></td>
  		</tr>
  		<tr>
  			<spring:bind path="command.introContent">
  			<td class="label" style="width:36px;"><spring:message code="text.project.content"/></td>
  			<td class="field"><textarea name="introContent" id="first_input" style="width:850px; height:150px;">${status.value}</textarea></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  	</table>		
  	<br/>
  	<div id="task_area">
  			<div style="position:absolute; left:3px;"><b><spring:message code="text.project.title.task"/></b></div><br>
  			<table style="width:900px">
  				<tr>
  					<td class="label" style="width:60px"><spring:message code="text.project.description"/></td>
	      			<td class="field"><input type="text" name="taskDescriptions" id="taskDescriptions_0" value="" size="50"/></td>
	      		</tr>
	      		<tr>
	      			<td class="label"><spring:message code="text.project.content"/></td>
	      			<td class="field"><textarea name="taskContents" id="taskContents_0" style="width:820px; height:200px;"></textarea></td>
	      		</tr>
	      	</table>
  	</div>
  	<div><input type="button" id="add_button_task" name="add_button_task" value="<spring:message code="project.add.button"/>">&nbsp;&nbsp;<input type="button" value="<spring:message code="project.remove.button"/>" onclick="del_task()"></div>
  	
  	
  	<div id="resource_area">
  			<div style="position:absolute; left:3px;"><b><spring:message code="text.project.title.resource"/></b></div><br>
  			<table style="width:900px">
  				<tr>
  					<td class="label" style="width:60px"><spring:message code="text.project.description"/></td>
	      			<td class="field"><input type="text" name="resourceDescriptions" id="resourceDescriptions_0" value="" size="50"/></td>
	      		</tr>
	      		<tr>
	      			<td class="label"><spring:message code="text.project.content"/></td>
	      			<td class="field"><textarea name="resourceContents" id="resourceContents_0" style="width:820px; height:200px;"></textarea></td>
	      		</tr>
	      	</table>
  	</div>
  	<div><input type="button" id="add_button_resource" name="add_button_resource" value="<spring:message code="project.add.button"/>">&nbsp;&nbsp;<input type="button" value="<spring:message code="project.remove.button"/>" onclick="del_resource()"></div>
  	
  	
  	<div id="contack_area">
  	<table>
  		<tr>
  			<td colspan="3" align="left"><b><spring:message code="text.project.title.contact"/></b></td>
  		</tr>
  		<tr>
  			<spring:bind path="command.contactContent">
  			<td class="label" style="width:36px;"><spring:message code="text.project.content"/></td>
  			<td class="field"><textarea name="contactContent" id="contactContent" style="width:850px; height:150px;">${status.value}</textarea></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  	</table>			
  </div>
  </div>	
  <br/>
  <table>
	<tr>
		<td align="right">
			<input type="button" id="dirty" value="<spring:message code="project.text.submit"/>" onclick="checkSubmit()">
			<input type="button" value="<spring:message code="text.back"/>" onClick="window.history.back()">
		</td>
	</tr>
  </table>
  </form>
</center>
