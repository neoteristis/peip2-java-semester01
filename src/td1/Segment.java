package td1;

/**
 * Cette classe représente les segments de droite entre 2 points du plan. Ces
 * deux points doivent être différents.
 *
 * @author V. Granet vg@unice.fr
 */

public class Segment {
    private Point orig;
    private Point fin;
    private static final double EPSILON = 1E-15;

    /**
     * Rôle : initialise le Segment de droite d'orgine O et de fin a Antécédent : a
     * != 0
     */
    public Segment(Point a) {
        this(new Point(), a);
    }

    /**
     * Rôle : initialise le Segment de droite d'orgine a et de fin b Antécédent : a
     * != b
     */
    public Segment(Point a, Point b) {
        if (!(a.equals(b))) {
            this.orig = a;
            this.fin = b;
        } else {
            System.out.println("Couldn't initialize because A = B.");
        }
    }

    /**
     * Rôle : renvoie le point d'origne du Segment courant
     */
    public Point getOrig() {
        return this.orig;
    }

    /**
     * Rôle : renvoie le point de fin du Segment courant
     */
    public Point getFin() {
        return this.fin;
    }

    /**
     * Rôle : modifie le point d'origine du Semgent courant
     */
    public void setOrig(Point orig) {
        this.orig = orig;
    }

    /**
     * Rôle : modifie le point de fin du Semgent courant
     */
    public void setFin(Point fin) {
        this.fin = fin;
    }

    /**
     * Rôle : renvoie la représentation du Segment courant sous forme d'une chaîne
     * de caratères
     */
    @Override
    public String toString() {
        return "[(" + this.getOrig() + ") ; (" + this.getFin() + ")]";
    }

    /**
     * Rôle : renvoie la longueur du Segment courant sous forme d'un double
     */
    public double longueur() {
        return this.getOrig().distance(this.getFin());
    }

    /**
     * rôle : teste si le Point p appartient au Segment courant
     */
    public boolean belongsTo(Point p) {

        Vector2 vectorAB = new Vector2(this.getOrig(), this.getFin());
        Vector2 vectorAP = new Vector2(this.getOrig(), p);

        if (vectorAB.collinear(vectorAP)) {
            double k = vectorAP.getX() / vectorAB.getX();
            return ((k <= 1) && (k >= 0));
        }
        return false;
    }

    /**
     * rôle : renvoie le Point d'intersection entre le Segment courant et le Segment
     * s. Si pas de point d'intersaction, la fonction renvoie null
     */
    public Point intersection(Segment s) {

        double xA = this.getOrig().getX();
        double xB = this.getFin().getX();
        double xC = s.getOrig().getX();
        double xD = s.getFin().getX();

        double yA = this.getOrig().getY();
        double yB = this.getFin().getY();
        double yC = s.getOrig().getY();
        double yD = s.getFin().getY();

        double kABNumerator = (yA - yC) * (xD - xC) - (xA - xC) * (yD - yC);
        double kABDenominator = (xB - xA) * (yD - yC) - (yB - yA) * (xD - xC);
        double kCDNumerator = (xC - xA) * (yB - yA) + (yA - yC) * (xB - xA);
        double kCDDenominator = (yD - yC) * (xB - xA) - (xD - xC) * (yB - yA);

        double kAB = kABNumerator / kABDenominator;
        double kCD = kCDNumerator / kCDDenominator;

        if ((kAB >= 0 && kAB <= 1) && (kCD >= 0 && kCD <= 1)) {
            double xIab = kAB * (xB - xA) + xA;
            double yIab = kAB * (yB - yA) + yA;

            double xIcd = kCD * (xD - xC) + xC;
            double yIcd = kCD * (yD - yC) + yC;

            if (Vector2.almostEqual(xIab, xIcd, EPSILON) && (Vector2.almostEqual(yIab, yIcd, EPSILON))) {
                return new Point(xIab, yIab, "I");
            }
        }

        return null;
    }
}