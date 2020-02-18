package contoller;

import interfaces.BaseController;
import constants.ServerActions;
import view.AddCompanyStatistics;

public class AddCompanyStatisticsController extends BaseController<AddCompanyStatistics> {

    int id,role;

    public AddCompanyStatisticsController(AddCompanyStatistics window, int id, int role) {
        attachView(window);
        this.id = id;
        this.role = role;
    }

    public void addRecord() {
        sendDataToServer(ServerActions.ADD_STATISTICS);
        String[] values = view.getTFValues();
        String result = "";
        for(String s: values)
        {
            result+= s + " ";
        }
        sendDataToServer(result);
    }
}
