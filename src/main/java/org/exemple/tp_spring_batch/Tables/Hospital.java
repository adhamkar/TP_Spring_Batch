package org.exemple.tp_spring_batch.Tables;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

public record Hospital(Long id, String nom, String service, String dateAdmission, String dateSortie) {

}
