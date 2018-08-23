package LFD.view;

import java.util.Observer;

import LFD.controller.LFDController;

/**
 * Interface for the view in the MVC { @see LFDController , @see LFDModel }
 *
 */
public interface LFDView extends Observer {
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
	 * 
	 * @param controller The controller which contains both the model and the view
	 * @see LFDModel
	 * @see LFDController
	 */
	void startView(LFDController controller);
}
