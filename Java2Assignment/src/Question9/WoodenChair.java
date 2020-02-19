package Question9;

public class WoodenChair extends Chair{
    @Override
    public String Chairtype() {
        return "Wooden Chair";
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
