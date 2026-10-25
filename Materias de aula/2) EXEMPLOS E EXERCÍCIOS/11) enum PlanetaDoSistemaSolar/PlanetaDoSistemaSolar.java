public enum PlanetaDoSistemaSolar
{
    // teluricos
    MERCURIO (3303e+20,  24397e+2),
    VENUS    (4869e+21,  60518e+2),
    TERRA    (5976e+21, 637814e+1),
    MARTE    (6421e+20,  33972e+2),

    //gasosos
    JUPITER  (  19e+26,  71492e+3),
    SATURNO  (5688e+23,  60268e+3),

    URANO    (8686e+23,  25559e+3),
    NETUNO   (1024e+23,  24746e+3),

    //anoes
    CERES    (8958e+17,  475),
    PLUTAO   (  13e+21,  1180),
    HAUMEA   (4006e+18,  718),
    ERIS     (1651e+20,  1163);

    private final double massa; // em kilogramas
    private final double raio;  // em metros

    private PlanetaDoSistemaSolar (double m, double r)
    {
        this.massa = m;
        this.raio  = r;
    }

    private double getMassa ()
    {
        return this.massa;
    }

    private double getRaio ()
    {
        return this.raio;
    }

    // constante gravitational universal ou constante de Newton
    // expressa em m3 kg-1 s-2, ou seja,
    // metro cubico por kilograma por segundo ao quadrado
    public static final double G = 6.67300E-11;

    private double getMassaNaTerra (double pnt)
    {
        return pnt / PlanetaDoSistemaSolar.TERRA.getGravidadeNaSuperficie();
    }

    public double getGravidadeNaSuperficie ()
    {
        return PlanetaDoSistemaSolar.G * this.massa / Math.pow(this.raio,2);
    }

    public double getPesoNaSuperficie (double pnt)
    {
        return getMassaNaTerra (pnt) * getGravidadeNaSuperficie ();
    }
}
