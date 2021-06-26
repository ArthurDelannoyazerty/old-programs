function setup() {
	createCanvas(4000,4000);
    background(200);	
	var x=0;
	var y=0;
	var a=50;
	var b=0;
	for(var grey=0; grey<3600; grey++) {
		fill(100,100,100);
		rect(x,y,50,50);
		x=x+100;
		if(x>4000){
			y=y+50;
			x=x-4550;
		};
	};
	for(var red=0; red<3600; red++) {
		fill(255,0,0);
		rect(a,b,50,50);
		a=a+100;
		if(a>4000) {
			b=b+50;
			a=a-4550;
		};
	};
	

	
	
	
}

function draw() {
	
}