package csvReader;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Enroll> enrollList = new CsvToBeanBuilder(new FileReader("list.csv"))
                .withType(Enroll.class).build().parse();

        Map<String, Enroll> mapped = new HashMap<>();

        for (Enroll e: enrollList) {

            if (mapped.containsKey(e.getUserid())){
                if(Integer.parseInt(mapped.get(e.getUserid()).getVersion()) < Integer.parseInt(e.getVersion())){
                    mapped.put(e.getUserid(), e);

                }
            }
            else {
                mapped.put(e.getUserid(), e);
            }

        }

        enrollList.forEach(System.out::println);
        Map<String, List<Enroll>> sorted = mapped.values().stream().collect(Collectors.
                groupingBy(Enroll::getInsuranceCompany, Collectors.collectingAndThen(Collectors.toList(), l -> l.stream()
                        .sorted(Comparator.comparing(e -> (e.getLastName() + e.getFirstName()))).collect(Collectors.toList()))));


        for (Map.Entry<String, List<Enroll>> e: sorted.entrySet()  ) {

            Writer second = new FileWriter(e.getKey() + ".csv");
            StatefulBeanToCsv toCsv = new StatefulBeanToCsvBuilder(second).build();
            toCsv.write(e.getValue());
            second.close();
            System.out.println(e.getKey() + e.getValue());
        }

    }
}
