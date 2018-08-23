package LFDCompany;

import java.util.Scanner;

import LFDCompany.controller.LFDCompanyController;
import LFDCompany.domain.mediator.LFDCompanyModel;
import LFDCompany.domain.mediator.LFDCompanyModelManager;
import LFDCompany.domain.model.Init;
import LFDCompany.view.LFDCompanyConsole;
import LFDCompany.view.LFDCompanyView;

public class Main_Company {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String[] data = scan.nextLine().split(" ");
		Init.getInstance().setIp(data[0]);
		Init.getInstance().setPort(Integer.parseInt(data[1]));
		LFDCompanyModel model = new LFDCompanyModelManager();
		LFDCompanyView view = new LFDCompanyConsole();
		
		LFDCompanyController controller = new LFDCompanyController(model, view);
		
		view.startView(controller);
	}
}
