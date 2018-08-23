package view;

import java.util.Scanner;

import controller.LFDUserController;
/**
 * The console with which the user will interact with the system
 *
 */
public class LFDUserConsole implements LFDUserView, Runnable{
	private LFDUserController controller;
	private Scanner input;

	public LFDUserConsole() {
		input = new Scanner(System.in);
	}
	@Override
	public void startView(LFDUserController controller) {
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
		System.out.println("Welcome to LFD");
		System.out.println("1) Login");
		System.out.println("2) Register");
		System.out.println("0) Quit");
		System.out.println("Select an item 0 - 2: ");
		int choose = input.nextInt(); input.nextLine();
		if(choose == 1)
		{
			controller.execute(-1);
		}
		else if(choose == 2)
		{
			controller.execute(-2);
		}
		else
		{
			controller.execute(0);
		}
		while (continueWorking) {
			// Read input from user input.
			System.out.println("LFD User");
			System.out.println("--------------");
			System.out.println("1) List all announcements");
			System.out.println("2) Apply to announcement");
			System.out.println("0) Quit");
			System.out.print("Select an item 0 - 2: ");
			
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
}
