package EmpresaBBDD;

public class Dieta {
    private int id;
    private String empleado;
    private String dptmt;
    private float euros;
    private String concepto;

    public Dieta(int id, String empleado, String dptmt, float euros, String concepto) {
        this.id = id;
        this.empleado = empleado;
        this.dptmt = dptmt;
        this.euros = euros;
        this.concepto = concepto;
    }

    @Override
    public String toString() {
        return "Dieta{" +
                "id=" + id +
                ", empleado:'" + empleado + '\'' +
                ", departamento:'" + dptmt + '\'' +
                ", euros:" + euros +
                ", concepto:'" + concepto + '\'' +
                '}';
    }

}
