package Question9;

public class MetalChair extends Chair {
    @Override
    public String Chairtype() {
        return "Metal Chair";
    }

    @Override
    public void stressTest() {
        System.out.println("Passed the stress test");
    }

    @Override
    public void fireTest() {
        System.out.println("Passed the fire test");

    }
}
