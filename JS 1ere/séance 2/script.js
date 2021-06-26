function setup() {
	createCanvas(800,600);
    background(150);
	line(0,800,600,0);
	line(200,0,200,400);
	rect(200,200,100,60);
	fill(0,0,0);
	arc(100,300,100,100,radians(90),radians(-90));
	arc(100,300,100,100,radians(-90),radians(90));
	for ( var x=0 ; x<200 ; x++ ){
		line(x,0,200-x,200);
		x=x+3;
	};
	translate(201,0);
	rotate(radians(90));
	for ( var x=0 ; x<200 ; x++ ){
		line(x,0,200-x,200);
		x=x+3;
	};
}

function draw() {

}