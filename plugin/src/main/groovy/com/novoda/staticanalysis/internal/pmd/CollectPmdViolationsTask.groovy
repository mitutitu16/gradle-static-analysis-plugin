package com.novoda.staticanalysis.internal.pmd

import com.novoda.staticanalysis.Violations
import com.novoda.staticanalysis.internal.CollectViolationsTask

class CollectPmdViolationsTask extends CollectViolationsTask {

    @Override
    void collectViolations(File xmlReportFile, File htmlReportFile, Violations violations) {
        PmdViolationsEvaluator evaluator = new PmdViolationsEvaluator(xmlReportFile)
        int errors = 0
        int warnings = 0
        evaluator.collectViolations().each { PmdViolationsEvaluator.PmdViolation violation ->
            if (violation.isError()) {
                errors += 1
            } else {
                warnings += 1
            }
        }
        violations.addViolations(errors, warnings, htmlReportFile ?: xmlReportFile)
    }

}
