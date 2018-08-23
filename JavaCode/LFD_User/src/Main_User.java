import java.util.Scanner;

import domain.model.Init;
import controller.LFDUserController;
import domain.mediator.LFDUserModel;
import domain.mediator.LFDUserModelManager;
import view.LFDUserConsole;
import view.LFDUserView;

public class Main_User {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String[] data = scan.nextLine().split(" ");
		Init.getInstance().setIp(data[0]);
		Init.getInstance().setPort(Integer.parseInt(data[1]));
		
		LFDUserModel model = new LFDUserModelManager();
		LFDUserView view = new LFDUserConsole();
		
		LFDUserController controller = new LFDUserController(model, view);
		
		view.startView(controller);
	}
}
