package LFDCompany.view;

import LFDCompany.controller.LFDCompanyController;

/**
 * Interface for the view in the MVC { @see LFDCompanyController , @see LFDCompanyModel }
 *
 */
public interface LFDCompanyView {
	/**
	 * Used to make the view request input from the user
	 * 
	 * @param string What I want the user to write
	 * @return string The input from the user
	 */
	String get(String string);
	/**
	 * Used for displaying information to the user
	 * 
	 * @param text What I want to show the user
	 */
	void show(String text);
	void startView(LFDCompanyController controller);
}
