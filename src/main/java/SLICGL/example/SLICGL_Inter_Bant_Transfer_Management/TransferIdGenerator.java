package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management;

import java.time.LocalDate;

public class TransferIdGenerator {
    private final String prefix;
    private int numericPart;

    public TransferIdGenerator(String lastTransferId) {
        String currentYear = String.valueOf(LocalDate.now().getYear());
        String currentMonth = String.format("%02d", LocalDate.now().getMonthValue());
        this.prefix = "TFR-" + currentYear + currentMonth + "-";

        if (lastTransferId != null && lastTransferId.startsWith(this.prefix.substring(0, 10))) {
            this.numericPart = Integer.parseInt(lastTransferId.substring(11, 15)) + 1;
        } else {
            this.numericPart = 1; // Start from 1 if no previous ID or month/year changed
        }
    }
    public String getNextId() {
        return prefix + String.format("%04d", numericPart++);
    }
}
