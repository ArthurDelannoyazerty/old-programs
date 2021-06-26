
var r;
var v;
var x;
function setup(){
    createCanvas(4000,400);
    noStroke();
    fill(0);
	r=2;
	v=2;
	x=10;
	frameRate(1);
}
function draw(){
	background(240);
	ellipse(x,200,8*r,8*r);
	while (x<500){
		background(240);
		x=x+10;
		ellipse(x,200,2*r,2*r);
	};
	
	while (x>500) {
		background(240);
		x=x-10;
		ellipse(x,200,2*r,2*r);
	};
}