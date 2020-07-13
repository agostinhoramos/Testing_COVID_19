package pt.ipg.application.testingcovid_19.other;

import android.view.View;

public class ViewWithData {
    private View view = null;
    private String[] data = {};

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
