package contoller;

import interfaces.BaseController;
import constants.ServerActions;
import view.ShowUsersWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ShowUsersController extends BaseController<ShowUsersWindow> {
    public void getAllUsers() {
        Vector<Vector> rowData = new Vector<>();
        sendDataToServer(ServerActions.GET_ALL_USERS);
        int count = Integer.parseInt(getDataFromServer());
        List<String> rows = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String res = getDataFromServer();
            rows.add(res);
        }
        for (String row : rows) {
            String[] arr = row.split(" ");
            Vector<Object> object = new Vector<>(Arrays.asList(arr));
            rowData.add(object);
        }
        view.setTable(rowData);
    }
}
