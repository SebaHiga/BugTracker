package Panels;

import DataBase.EntityAbstract;
import DataBase.Projects.TicketService;

import javax.swing.*;
import java.util.List;

public class PanelList<T> extends JPanel {
    private JList<String> labelList;
    private List<T> list;

    public void build(List<T> list) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        var defList = new DefaultListModel<String>();

        this.list = list;

        for (T t : this.list) {
            defList.addElement(((EntityAbstract) t).getLabel());
        }

        this.labelList = new JList<String>(defList);

        this.add(this.labelList);
    }

    public JList<String> getLabelList() {
        return this.labelList;
    }

    public T getSelectedItem() {
        var selectedItem = this.labelList.getSelectedValue();

        var selected = this.list.stream().filter(item -> ((EntityAbstract) item).getLabel().equals(selectedItem)).toList();

        return selected.getFirst();
    }
}
