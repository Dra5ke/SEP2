package view;

import controller.LFDUserController;

/**
 * Interface for the view in the MVC { @see LFDUserController , @see LFDUserModel }
 *
 */
public interface LFDUserView {
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
	/**
	 * Used to put the view in a thread and start it and to also assign the controller to it
	 * @param controller The controller which contains both the model and the view
	 * @see LFDUserModel
	 * @see LFDUserController
	 */
	void startView(LFDUserController controller);
}
