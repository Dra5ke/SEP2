package LFD.view;

import java.util.Observable;
import java.util.Scanner;

import LFD.controller.LFDController;
/**
 * The console with which the user will interact with the system
 *
 */
public class LFDConsole implements LFDView, Runnable {

	private LFDController controller;
	private Scanner input;

	public LFDConsole() {
		input = new Scanner(System.in);
	}

	@Override
	public void startView(LFDController controller) {
		this.controller = controller;
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * Run method that displays the menu
	 * @see startView
	 */
	@Override
	public void run() {
		boolean continueWorking = true;
		while (continueWorking) {
			// Read input from user input.
			System.out.println("LFD System");
			System.out.println("--------------");
			System.out.println("1) List all announcements");
			System.out.println("2) Add an announcement");
			System.out.println("3) Sign a member for an announcement");
			System.out.println("4) Remove announcement");
			System.out.println("0) Quit");
			System.out.print("Select an item 1-4: ");
			
			int choice = input.nextInt();
			input.nextLine();

			controller.execute(choice);
			if (choice == 0) {
				continueWorking = false;
			}
		}
	}

	@Override
	public String get(String text) {
		System.out.print("Enter " + text + ": ");
		return input.nextLine();
	}

	@Override
	public void show(String text) {
		System.out.println(text);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof String)
		{
			this.show((String)arg1);
		}
		
	}	
}
