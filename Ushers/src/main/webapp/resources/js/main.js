function confirmDelete(msg){
	var agree=confirm(msg);
	if (agree)
	     return true ;
	else
	     return false ;
}
//add object method
function addObject(msg)
{
	if(validateName(msg))
	{
		return true;
	}
	return false;
}
//validate name
function validateName(msg)
{
	var name = document.getElementById('name').value;
	if(name.length == 0 ){
		alert(msg);
	    return false;
	}
	return true;
}
