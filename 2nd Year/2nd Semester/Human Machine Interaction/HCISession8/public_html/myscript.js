/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function writeNumber(selectNumberId, paragraphID){
    var myNumber = document.getElementById(selectNumberId).value;
    document.getElementById(paragraphID).innerHTML = "Selected number: " + myNumber;
}

function writeColour(selectColourId, paragraphID){
    var myColour = document.getElementById(selectColourId).value;
    var rgb = "XXXXXX";
    switch(myColour){
        case 'red': 
            rgb='FF0000'; 
            break;
        case 'green': 
            rgb='00FF00'; 
            break;
        case 'blue': 
            rgb='0000FF'; 
            break;
        case 'white': 
            rgb='FFFFFF'; 
            break;
        case 'grey': 
            rgb='7F7F7F';
            break;
        case 'black': 
            rgb='000000'; 
            break;
    }
    document.getElementById(paragraphID).innerHTML = "Selected Colour: " + myColour;
}

function checkRadioOption(radioSelectionId){
    document.getElementById(radioSelectionId).checked = true;
}

function validateID(id){
    var myId = id.toString();
    if(myId.length !== 9)
        return false;
    for(i = 0; i < 8; i++)
        if(myId[i] < '0' || myId[i] > '9')
            return false;
    
    if(myId[8] < 'A' || myId[8] > 'Z')
        return false;
    
    
    return true;
}


function checkId(inputId){
    var idElement = document.getElementById(inputId);
    var id = idElement.value.toUpperCase();
    if(validateID(id))
        idElement.style.color = 'black';
    else 
        idElement.style.color = 'red';
    

}
