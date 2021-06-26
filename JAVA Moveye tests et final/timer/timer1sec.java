package timer;

public class timer1sec {

	public static void main(String[] args) {
		class MyObj {
		    private final long createdMillis = System.currentTimeMillis();

		    public int getAgeInSeconds() {
		        long nowMillis = System.currentTimeMillis();
		        return (int)((nowMillis - this.createdMillis) / 1000);
		    }
		}

	}

}
