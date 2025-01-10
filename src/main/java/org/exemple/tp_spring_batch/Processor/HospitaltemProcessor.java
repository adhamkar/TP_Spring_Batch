package org.exemple.tp_spring_batch.Processor;


import org.exemple.tp_spring_batch.Tables.Hospital;
import org.exemple.tp_spring_batch.Tables.Order;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

public class HospitaltemProcessor implements ItemProcessor<Hospital,Hospital> {
    //private static final Logger LOG = LoggerFactory.getLogger(ProductItemProcessor.class);

    @Override
    public Hospital process(Hospital hospitalItem) throws Exception {
        if (hospitalItem.dateSortie() == null) {
            System.out.println("Ignorer l'enregistrement : Date de sortie absente pour " + hospitalItem.nom());
            return null; // Ignorer cet enregistrement
        }
        LocalDate dateAdmission = LocalDate.parse(hospitalItem.dateAdmission());
        LocalDate dateSortie = LocalDate.parse(hospitalItem.dateSortie());
        if (dateAdmission != null && dateSortie != null) {
            Long dureeSejour = ChronoUnit.DAYS.between(dateAdmission, dateSortie);
            System.out.println("Calculated duration of stay for patient :"+
                    hospitalItem.nom()+" days :"+ dureeSejour);
            return new Hospital(hospitalItem.id(), hospitalItem.nom(), hospitalItem.service(), hospitalItem.dateAdmission(), hospitalItem.dateSortie());

        } else {
            System.out.println("Missing admission or discharge date for patient "+hospitalItem.nom());
            return null; // Skip this record
        }


    }
}
