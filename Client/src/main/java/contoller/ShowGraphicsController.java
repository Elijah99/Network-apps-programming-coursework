package contoller;

import interfaces.BaseController;
import constants.ServerActions;
import view.MenuWindow;
import view.ShowGraphicsWindow;

import java.util.ArrayList;

public class ShowGraphicsController extends BaseController<ShowGraphicsWindow> {
    private int id , role;

    public void setId(int id,int role) {
        this.id = id;
        this.role = role;
    }

    public ArrayList<String[]> getStatisticsFromDB() {
        ArrayList<String[]> rowData = new ArrayList<>();
        sendDataToServer(ServerActions.CALC_COEFFICIENTS);
        sendDataToServer(String.valueOf(id));
        int rows = Integer.parseInt(getDataFromServer());

        for (int i = 0; i < rows; i++) {
            String row = getDataFromServer();
            String[] arr = row.split(" ");
            rowData.add(arr);
        }
        view.onStatisticsLoaded(rowData);
        return rowData;
    }

    public void navigateBack() {
        new MenuWindow(role, id).setVisible(true);
        view.setVisible(false);
    }



}
