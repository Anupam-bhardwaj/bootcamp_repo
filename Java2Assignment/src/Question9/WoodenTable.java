package Question9;

public class WoodenTable extends Table{
    @Override
    public String TableType() {
        return "Wooden Table";
    }

    @Override
    public void stressTest() {
        System.out.println("Passed the Stress Test");

    }

    @Override
    public void fireTest() {
        System.out.println("Failed the fire Test");

    }
}
