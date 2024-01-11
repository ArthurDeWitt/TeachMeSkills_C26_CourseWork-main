package com.teachmeskills.mycourse.controller.pars_document;

import com.teachmeskills.mycourse.exception.NullSummaryException;
import com.teachmeskills.mycourse.logger.Logger;
import com.teachmeskills.mycourse.documents.CheckDocument;
import com.teachmeskills.mycourse.documents.InvoiceDocument;
import com.teachmeskills.mycourse.documents.OrderDocument;


import java.io.*;
import java.util.Date;
import java.util.List;

public class Statistic {
    FileReader fileReader;

    public static void stats(List<File> files) throws NullSummaryException {
        // проверить лист на null
        if (files != null) {
            double total = InvoiceDocument.statsInvoice(files)
                    + CheckDocument.statsCheck(files)
                    + OrderDocument.statsOrder(files);
            System.out.println("total turnover: " + total);
            System.out.println("total turnover by invoice: " + InvoiceDocument.statsInvoice(files));
            System.out.println("total turnover by order: " + OrderDocument.statsOrder(files));
            System.out.println("total turnover by check: " + CheckDocument.statsCheck(files));
            Logger.logExecutionInfo(new Date(), "[INFO Statistic accesses]");
        }

    }
}
