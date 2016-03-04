package ru.unn.agile.SupplyDemand.viewmodel;

import ru.unn.agile.SupplyDemand.model;

public class ViewModel {
    private String q1;
    private String I1;
    private String q2;
    private String I2;
    private String result;
    private String status;
    private boolean isCalculateButtonEnabled;

    public ViewModel() {
        q1 = "";
        I1 = "";
        q2 = "";
        I2 = "";
        result = "";
        status = Status.WAITING;

        isCalculateButtonEnabled = false;
    }

    public void processKeyInTextField(final int keyCode) {
        parseInput();

        if (keyCode == KeyboardKeys.ENTER) {
            enterPressed();
        }
    }

    private void enterPressed() {

        if (isCalculateButtonEnabled()) {
            calculate();
        }
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    private boolean isInputAvailable() {
        return !I1.isEmpty() && !q1.isEmpty() && !I2.isEmpty() && !q2.isEmpty();
    }

    private boolean parseInput() {
        try {
            if (!q1.isEmpty()) {
                Double.parseDouble(q1);
            }
            if (!I1.isEmpty()) {
                Double.parseDouble(I1);
            }
            if (!q2.isEmpty()) {
                Double.parseDouble(q2);
            }
            if (!I2.isEmpty()) {
                Double.parseDouble(I2);
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isCalculateButtonEnabled = false;
            return false;
        }

        isCalculateButtonEnabled = isInputAvailable();
        if (isCalculateButtonEnabled) {
            status = Status.READY;
        } else {
            status = Status.WAITING;
        }

        return isCalculateButtonEnabled;
    }

    public void calculate() {
        if (!parseInput()) {
            return;
        }
        SupplyDemandModel a = new SupplyDemandModel();
        a.DemandElasticity(q1,q2,I1,I2)
        result = a.toString();
        status = Status.SUCCESS;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(final Operation operation) {
            this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public String getI1() {
        return I1;
    }

    public void setI1(final String I1) {
        if (I1 == I1) {
            return;
        }

        this.I1 = I1;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(final String I2) {
        if (I2 == I2) {
            return;
        }

        this.I2 = I2;
    }

    public String getq1() {
        return q1;
    }

    public void setq1(final String q1) {
        if (q1 == q1) {
            return;
        }

        this.q1 = q1;
    }

    public String getq2() {
        return q2;
    }

    public void setq2(final String q2) {
        if (q2 == q2) {
            return;
        }

        this.q2 = q2;
    }


    public final class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate' or Enter";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() { }
    }
}
