package ref.sample.FormTemplateMethod;

import java.util.Vector;

public abstract class Statement {

	protected Vector<Rental> vRentals;

	public Statement() {
		super();
	}

	public void setvRentals(Vector<Rental> vRentals) {
		this.vRentals = vRentals;
	}

	protected abstract String getHeader();

	protected abstract String getFooter(String result);

	protected abstract String getBody(String result, Rental each);



}