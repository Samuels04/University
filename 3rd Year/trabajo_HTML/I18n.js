/*ReqI12*/
function CalculoAniversario(IdFund,IdS){
 var fund=document.getElementById(IdFund);
 var s= document.getElementById(IdS);
 var fechaFund= fund.value; //cadena de texto que representa una fecha

 //dateFormatS espera un objeto Date como entrada para formatearlo en una cadena legible;
 var FechaFundacion= new Date(fechaFund);
 var añoAniv= FechaFundacion.getFullYear()+100;
 FechaFundacion.setFullYear(añoAniv);

 //ReqI15
 var FechaFormatS= dateFormatS.format(FechaFundacion);
 //ReqI16
 var FechaFormatL= dateFormat.format(FechaFundacion);
 s.innerHTML= "("+FechaFormatS+")"+"= ("+FechaFormatL+")";

}

function CalculoCapacidad(IdCapacidad,Idmembers,IdS){
    var cap= document.getElementById(IdCapacidad).value;
    var mem= document.getElementById(Idmembers).value;
    var s= document.getElementById(IdS);
  if(mem !=""){
    var capacidad= new Number(cap);
    var miembros= new Number(mem);
    var capTotal= capacidad-miembros;
    //ReqI13
    s.innerHTML= numberFormat.format(capTotal);
  }else
     s.innerHTML = ""; 
}

//ReqJ10
function CalculoPrecioTotal(Idprecio, Idmembers, IdS){
    var p= document.getElementById(Idprecio).value;
    var mem=document.getElementById(Idmembers).value;
    var s= document.getElementById(IdS);
    if(mem !=""){
        var precio= new Number(p);
        var miembros= new Number(mem);
        var precioTotal= precio*miembros;
        //ReqI14
        s.innerHTML= currencyFormat.format(precioTotal);
      }else
         s.innerHTML = "";

    
}

function AbrirIngles() {
    window.location.href = "AboutUs_en.html";
}
function AbrirEs() {
    window.location.href = "AboutUs_es.html";
}
