package decorator;

import model.Transponder;

public class TransponderPlus extends TransponderDecorator {

    public TransponderPlus(Device device) {
        super(device);
    }

    @Override
    public Device assemble() {
        Device d = super.assemble();

        System.out.println("Assembling plus transponder");

        if(d instanceof Transponder) {
            Transponder t = (Transponder) d;
            t.setPlus(1);
        }

        return d;
    }
}
