package ru.unn.agile.SupplyDemand.model;

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
                "1. ������� ������� � ��������;\n"
                + "2. ������� �����;\n"
                + "3. ����, ����������;\n"
                + "4. ������� �������-����������� (�����������);\n"
                + "5. ������������ ��������;\n"
                + "";
    }

    public String PrintSupplyFactors()
    {

        return
                "1. ������� �������-����������� (�����������);\n"
                 + "2. ������� �������-������������ (�����������);\n"
                 + "3. ������� ����������;\n"
                 + "4. ����� � ����������� ��������;\n"
                 + "5. ������ � �������;\n"
                 + "6. ��������� �������;\n"
                 + "7. �������� (������������, ���������-������������);\n"
                 + "8. ������� �����;\n"
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
