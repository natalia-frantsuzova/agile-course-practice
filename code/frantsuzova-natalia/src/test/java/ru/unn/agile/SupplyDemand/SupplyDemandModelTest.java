package ru.unn.agile.SupplyDemand;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SupplyDemandModelTest {
    private static final double DELTA = 1e-10;
    private SupplyDemandModel supplyDemand;

    @Before
    public void setUp() {
        supplyDemand = new SupplyDemandModel();
    }

    @Test(expected = ArithmeticException.class)
    public void FindPriceZeroDivisionTest()
    {
        supplyDemand.FindPrice(10, 100, 10, 100);
    }

    @Test
    public void FindPriceTest()
    {
        assertEquals(30, supplyDemand.FindPrice(-2, 100, 1, 10), DELTA);
    }

    @Test
    public void PrintDemandFactors()
    {
       // String expectation ="";
       // String result=PrintDemandFactors();
       // assertEquals(expectation, result);
    }

    @Test
    public void PrintSupplyFactors()
    {
       // String expectation ="";
       // String result=PrintSupplyFactors();
       // assertEquals(expectation, result);
    }

    @Test
    public void DemandElasticity()
    {

    }
    @Test(expected = ArithmeticException.class)
    public void DemandElasticityZeroDivisionTest()
    {
        supplyDemand.DemandElasticity(10, 13, 500, 500);
    }

    @Test
    public void DemandElasticityTest()
    {
        assertEquals(1.4347826087, supplyDemand.DemandElasticity(10, 13, 500, 600), DELTA);
    }


    @Test
    public void SupplyElasticity()
    {


    }
    @Test

    public void SupplyElasticityZeroDivisionTest()
    {
        supplyDemand.SupplyElasticity(100, 10);
    }
    @Test
    public void SupplyElasticityTest()
    {
        assertEquals(0.7692307692307693, supplyDemand.SupplyElasticity(10.0, 13.0), DELTA);
    }

}
