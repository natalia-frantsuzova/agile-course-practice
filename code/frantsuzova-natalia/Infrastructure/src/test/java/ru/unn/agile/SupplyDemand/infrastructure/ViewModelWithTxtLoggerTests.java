package ru.unn.agile.SupplyDemand.infrastructure;

import ru.unn.agile.SupplyDemand.viewmodel.*;
import ru.unn.agile.SupplyDemand.viewmodel.*;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./ViewModel_with_TxtLogger_Tests.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
