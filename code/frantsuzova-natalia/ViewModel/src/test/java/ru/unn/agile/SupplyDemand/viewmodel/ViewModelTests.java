package ru.unn.agile.SupplyDemand.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import ru.unn.agile.SupplyDemand.viewmodel.ViewModel.Operation;
import ru.unn.agile.SupplyDemand.viewmodel.ViewModel.Status;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getq1());
        assertEquals("", viewModel.getI1());
        assertEquals("", viewModel.getq2());
        assertEquals("", viewModel.getI2());
        assertEquals("", viewModel.getResult());
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    private void fillInputFields() {
        viewModel.setq1("1");
        viewModel.setI1("1");
        viewModel.setq2("3");
        viewModel.setI2("3");
    }

    @Test
    public void canReportBadFormat() {
        viewModel.setq1("a");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setq1("a");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        viewModel.setq1("1.0");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        fillInputFields();
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(true, viewModel.isCalculateButtonEnabled());

        viewModel.setq1("trash");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWithIncompleteInput() {
        viewModel.setq1("1");
        viewModel.setI1("1");

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(true, viewModel.isCalculateButtonEnabled());
    }


    @Test
    public void canPerformCalcAction() {
        viewModel.setq1("1");
        viewModel.setI1("2");
        viewModel.setq2("-10");
        viewModel.setI2("-20");
        viewModel.calculate();

        assertEquals("1", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessage() {
        fillInputFields();

        viewModel.calculate();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setq1("a");

        viewModel.calculate();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ENTER);

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void part2() {
        viewModel.setq1("1");
        viewModel.setI1("0");
        viewModel.setq2("2");
        viewModel.setI2("2");
               viewModel.calculate();

        assertEquals((double)(1/3)+"", viewModel.getResult());
    }

    @Test
    public void canPerformAddWithArbitraryNumbers() {
        viewModel.setq1("1.2");
        viewModel.setI1("2.3");
        viewModel.setq2("-2.4");
        viewModel.setI2("-4.6");

        viewModel.calculate();

        assertEquals("9", viewModel.getResult());
    }
}
