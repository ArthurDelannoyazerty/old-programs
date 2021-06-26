window.open('E:/ICN/index.html','nom_de_ma_popup','menubar=no, scrollbars=no, top=100, left=100, width=300, height=200');
var v1="12";
var v2=5;
document.write("La variable v1 contient : ",v1,"<BR>");
document.write("La variable v2 contient : ",v2,"<BR>");
document.write("Avec v1+v2 on obtient :",v1+v2,"<BR>"); 
document.write("Avec eval(v1)+v2 on obtient : ",eval(v1)+v2,"<BR>");

var nom;
document.write("<BIG><B>B</B></BIG>onjour "+nom+" !<BR>");
var maintenant=new Date();
document.write(maintenant);

var maintenant=new Date();
var jour=maintenant.getDate();
var mois=maintenant.getMonth()+1;
var an=maintenant.getFullYear();
document.write("<BR>","Nous sommes le ",jour,"/",mois,"/",an,".");

