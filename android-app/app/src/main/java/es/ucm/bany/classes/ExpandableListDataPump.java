package es.ucm.bany.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> director = new ArrayList<String>();
        director.add("José Manuel Mendías Cuadros");

        List<String> phone = new ArrayList<String>();
        phone.add("+34 913 94 75 13 / 11 16 / 11 17");

        List<String> mail = new ArrayList<String>();
        mail.add("migs@ucm.es / museos@ucm.es");

        List<String> address = new ArrayList<String>();
        address.add("Facultad de Informática, Profesor José García Santesmases, 9, " +
                "Ciudad Universitaria, 28040 Madrid");

        List<String> transport = new ArrayList<String>();
        transport.add("Autobuses: F, G, U" +
                "\n" +
                "Metro: Moncloa, Ciudad Universitaria (Línea 6)");

        List<String> schedule = new ArrayList<String>();
        schedule.add("9:00 a.m - 21:00 p.m");

        List<String> description = new ArrayList<String>();
        description.add("Inaugurado en noviembre de 2013, el Museo de Informática García " +
                "Santesmases (MIGS) se encuentra situado en los pasillos de la Facultad de " +
                "Informática de la Universidad Complutense de Madrid, conformado por un \"paseo " +
                "histórico\" por siete décadas de Informática. Se denomina así en memoria del " +
                "Profesor José García Santesmases, catedrático de esta Universidad que fue pionero " +
                "en la investigación y docencia de la Informática en España.\n" +
                "\n" +
                "En él se exponen máquinas desarrolladas en la UCM entre los años 1950 y 1975, así " +
                "como computadoras comerciales que desde 1968 estuvieron en uso en el Centro de " +
                "Cálculo de esta Universidad y equipos donados por Departamentos, particulares y " +
                "otras entidades. Las piezas, organizadas cronológicamente y por categorías, se " +
                "complementan con paneles explicativos que muestran sus características y modo de " +
                "funcionamiento, todo ello con el propósito de ofrecer una visión didáctica y global " +
                "de la evolución de la Informática desde sus inicios.\n" +
                "\n" +
                "El Museo de Informática García Santesmases se complementa con un amplio fondo " +
                "bibliográfico histórico que se encuentra accesible en la biblioteca de la Facultad " +
                "de Informática.");



        expandableListDetail.put("DIRECTOR", director);
        expandableListDetail.put("TELÉFONO", phone);
        expandableListDetail.put("E-MAIL", mail);
        expandableListDetail.put("DIRECCIÓN", address);
        expandableListDetail.put("TRANSPORTE PÚBLICO", transport);
        expandableListDetail.put("HORARIOS", schedule);
        expandableListDetail.put("DESCRIPCIÓN", description);

        return expandableListDetail;
    }
}