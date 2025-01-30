function escribeCiudad(idSelect, idCiudad){
    var s= document.getElementById(idSelect);
    var c= document.getElementById(idCiudad);
    s.innerHTML=c.value;
}

function DNICorrecto(DNI){
    var s= DNI.length;
    if(s!=9) return false;
    for(i=0;i<8;i++)
        if(DNI[i]<'0' || DNI[i]>'9') return false;
    if(DNI[8]<'A' || DNI[8]>'Z') return false;
    return true;
}

function CompruebaDNI(pDNI,DNI){
    var pdni=document.getElementById(pDNI);
    var dni= document.getElementById(DNI);
    dni.value=dni.value.toUpperCase();

    if(DNICorrecto(dni.value)){
        pdni.style='color:black';
        dni.style.color='black';
        return true;
    }else{
        pdni.style='color:red';
        dni.style.color='red';
        return false;
    }
}

function cambiarColor(elemento) {
    var e= document.getElementById(elemento);
    e.style.backgroundColor='pink'; 
}
function restaurarColor(elemento) {
    var e= document.getElementById(elemento);
    e.style.backgroundColor = '';
}
//ReqJ14
function AMayusc(elemento){
    var e= document.getElementById(elemento);
    e.value=e.value.toUpperCase();
}
//ReqJ18
function AbrirCorreo(){
    window.location.href="mailto: uo294340@uniovi.es";
}
//REqJ16
function dobleClickMensaje(Sid){
    var s= document.getElementById(Sid);
    //s.textContent="Se ha realizado doble click.";
    s.innerHTML='Se ha realizado doble click.'  ;
}

function CheckTodo(){
    var nom=document.getElementById('name').value;
    var ape=document.getElementById('apellido').value;
   
    
    var cMas=document.getElementById('M').checked;
    var cFem=document.getElementById('F').checked;
    
    
    if(!CompruebaDNI('pDNI','DNI')) return false;
    if(!cMas && !cFem) return false;
    if(nom.length===0) return false;
    if(ape.length===0) return false;
     return true;
}


function validarAdjunto(input) {
    var archivo = input.files[0];
    if (archivo) {
        var extension = archivo.name.split('.').pop().toLowerCase();
        if (extension !== 'pdf') {
            document.getElementById('mensaje-aviso').style.display = 'block'; //se muestra como elemento de bloque
            input.value = ''; // Limpiar el valor del input
        } else {
            document.getElementById('mensaje-aviso').style.display = 'none';
        }
    }
}

function ImprimeSel(IdS,name){
    var s= document.getElementById(IdS);
    var vectorA= document.getElementsByName(name);
    var selected= [];
    vectorA.forEach(el => {
        if(el.checked)
         selected.push(el.value);
    });
    

    s.innerHTML= selected.join(',');
}
/*ReqJ10 en el js"I18n.js*/


//AboutUs botones
function irAFormulario() {
    window.location.href = "Principal.html#formulario";
    
}
function MostrarAviso() {
    alert("No mientas, todos querrían suscribirse");
    
}
