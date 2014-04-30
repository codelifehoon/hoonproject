package ref.sample.FormTemplateMethod;

public class Rental {

	
	private String movieTitle;
	private int charge;
	
	
	public Rental(String movieTitle, int charge) {
		super();
		this.movieTitle = movieTitle;
		this.charge = charge;
	}
	
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	
}
