package es.ucm.bany.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> description = new ArrayList<String>();
        description.add("India");

        List<String> schedule = new ArrayList<String>();
        schedule.add("Brazil");

        List<String> direction = new ArrayList<String>();
        direction.add("Calle del Prof. José García Santesmases, 9, 28040 Madrid");

        List<String> transport = new ArrayList<String>();
        transport.add("United States");


        expandableListDetail.put("TRANSPORTE PÚBLICO", transport);
        expandableListDetail.put("DIRECCIÓN", direction);
        expandableListDetail.put("HORARIOS", schedule);
        expandableListDetail.put("DESCRIPCIÓN", description);

        return expandableListDetail;
    }
}