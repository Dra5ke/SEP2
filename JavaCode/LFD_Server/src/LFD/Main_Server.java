package LFD;

import LFD.controller.LFDController;
import LFD.domain.mediator.LFDModel;
import LFD.domain.mediator.LFDModelManager;
import LFD.domain.model.Init;
import LFD.view.LFDConsole;
import LFD.view.LFDView;
import utility.persistence.MyDatabase;
public class Main_Server {

	public static void main(String[] args) {
		
		Init.getInstance().getData();
		
		LFDModel model = new LFDModelManager();
		LFDView view = new LFDConsole();
		
		LFDController controller = new LFDController(model, view);
		
		view.startView(controller);

	}

}
