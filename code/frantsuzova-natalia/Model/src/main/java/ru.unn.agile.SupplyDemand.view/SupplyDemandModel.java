package main.java.ru.unn.agile.SupplyDemand;

public class SupplyDemandModel {
    private static final double DELTA = 1e-10;

    public double FindPrice(double a1, double b1, double a2, double b2)
    {

        if ((a1-a2)*(a1-a2) < DELTA)
            throw new ArithmeticException("Division by zero!");

        double x = (b2-b1)/(a1-a2);
        return x;
    }
    public String PrintDemandFactors()
    {
        return
                "1. Уровень доходов в обществе;\n"
                + "2. Размеры рынка;\n"
                + "3. Мода, сезонность;\n"
                + "4. Наличие товаров-субститутов (заменителей);\n"
                + "5. Инфляционные ожидания;\n"
                + "";
    }

    public String PrintSupplyFactors()
    {

        return
                "1. Наличие товаров-субститутов (заменителей);\n"
                 + "2. Наличие товаров-комплементов (дополняющих);\n"
                 + "3. Уровень технологий;\n"
                 + "4. Объём и доступность ресурсов;\n"
                 + "5. Налоги и дотации;\n"
                 + "6. Природные условия;\n"
                 + "7. Ожидания (инфляционные, социально-политические);\n"
                 + "8. Размеры рынка;\n"
                 + "";
    }

    public double DemandElasticity(double Q1, double Q2, double I1, double I2)
    {


        if ((I2-I1)*(I2-I1) < DELTA)
            throw new ArithmeticException("Division by zero!");

        double x = ((Q2-Q1)/(Q2+Q1))/((I2-I1)/(I2+I1));
        return x;
//        return -1.0;
    }

    public double SupplyElasticity(double G, double F)
    {
        if (F*F < DELTA)
            throw new ArithmeticException("Division by zero!");

        double x = G/F;
        return x;
    }
}
