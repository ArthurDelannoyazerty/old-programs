function setup() {
	createCanvas(400,400);
    background(200);
	var iter = 0;
	var x = 0;
	var y = 0;
	var r = 255;
	var g = 255;
	var b = 255;
	rect(x,y,4,4);
	

function draw() {
	for (var iter = 0; iter<1; iter-1) {
	fill(r,g,b);
	rect(x,y,4,4);
	x=Math.random()*400;
	y=Math.random()*400;
	};
}
}