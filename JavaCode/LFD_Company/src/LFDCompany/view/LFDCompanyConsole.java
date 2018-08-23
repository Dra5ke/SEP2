package LFDCompany.view;

import java.util.Scanner;

import LFDCompany.controller.LFDCompanyController;
/**
 * The console with which the company will interact with the system
 *
 */
public class LFDCompanyConsole implements LFDCompanyView, Runnable{
	private LFDCompanyController controller;
	private Scanner input;

	public LFDCompanyConsole() {
		input = new Scanner(System.in);
	}
	@Override
	public void startView(LFDCompanyController controller) {
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
			System.out.println("LFD Company");
			System.out.println("--------------");
			System.out.println("1) Add announcement");
			System.out.println("2) Remove announcement");
			System.out.println("3) List my announcements");
			System.out.println("0) Quit");
			System.out.print("Select an item 0 - 3: ");
			
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
