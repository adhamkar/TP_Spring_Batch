package org.exemple.tp_spring_batch.lisntener;

import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

@Component
public class HospitalItemReaderListener {
    @OnReadError
    public void onReadError(Exception ex) {
        if (ex instanceof FlatFileParseException) {
            FlatFileParseException ffpe = (FlatFileParseException) ex;
            System.err.println("Parsing error at line: " + ffpe.getLineNumber() + ", input: " + ffpe.getInput());
        } else {
            System.err.println("Error during reading: " + ex.getMessage());
        }
    }
}
