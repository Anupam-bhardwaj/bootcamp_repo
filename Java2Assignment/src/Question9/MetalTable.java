package Question9;

public class MetalTable extends Table {
    @Override
    public String TableType() {
        return "Metal table";
    }

    @Override
    public void stressTest() {
        System.out.println("Passed the Stress Test");

    }

    @Override
    public void fireTest() {
        System.out.println("Passed the fire Test");

    }
}
