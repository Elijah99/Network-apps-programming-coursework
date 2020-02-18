package contoller;

import interfaces.BaseController;
import constants.ServerActions;
import view.MenuWindow;
import view.ShowCoeffsWindow;

import java.util.Arrays;
import java.util.Vector;

public class ShowCoefficientsController extends BaseController<ShowCoeffsWindow> {

    private int id , role;

    public void setIdAndRole(int id, int role) {
        this.id = id;
        this.role = role;
    }

    public void getStatisticsFromDB() {
        Vector<Vector> rowData = new Vector<>();
        sendDataToServer(ServerActions.CALC_COEFFICIENTS);
        sendDataToServer(String.valueOf(id));

        int rows = Integer.parseInt(getDataFromServer());
        for (int i = 0; i < rows; i++) {
            String row = getDataFromServer();

            String[] arr = row.split(" ");

            Vector<Object> object = new Vector<>(Arrays.asList(arr));
            rowData.add(object);
        }
        view.onStatisticsLoaded(rowData);
    }

    public void navigateBack() {
        new MenuWindow(role, id).setVisible(true);
        view.setVisible(false);
    }
}
