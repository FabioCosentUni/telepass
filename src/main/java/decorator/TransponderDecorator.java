package decorator;

public class TransponderDecorator implements Device{

    protected Device device;

    public TransponderDecorator(Device device) {
        this.device = device;
    }

    @Override
    public Device assemble() {
        return this.device.assemble();
    }
}
