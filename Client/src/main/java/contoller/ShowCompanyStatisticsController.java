package contoller;

import interfaces.BaseController;
import constants.ServerActions;
import view.MenuWindow;
import view.ShowCompanyStatistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ShowCompanyStatisticsController extends BaseController<ShowCompanyStatistics> {
        private int id , role;

        public void setId(int id,int role) {
                this.id = id;
                this.role = role;
        }

        public void getStatisticsFromDB() {
                Vector<Vector> rowData = new Vector<>();
                sendDataToServer(ServerActions.GET_STATISTICS);
                sendDataToServer(String.valueOf(id));
                int rows = Integer.parseInt(getDataFromServer());

                for (int i = 0; i < rows; i++) {
                        String row = getDataFromServer();
                        String[] arr = row.split(" ");
                        arr = convertPeriod(arr);
                        Vector<Object> object = new Vector<>(Arrays.asList(arr));
                        rowData.add(object);
                }
                view.onStatisticsLoaded(rowData);
        }

        public void navigateBack() {
                view.setVisible(false);
                new MenuWindow(role, id).setVisible(true);
        }

        private String[] convertPeriod(String[] arr)
        {
                List<String> list = new ArrayList<>(Arrays.asList(arr));
                list.set(1, list.get(1) + " - " + list.get(2));
                list.remove(2);
                String[] result =  list.toArray(new String[list.size()]);
                return result;
        }
}
