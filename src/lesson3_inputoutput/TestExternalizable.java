package lesson3_inputoutput;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/*
данный интерфейс позволяем производить серализацию и десерализацию 'руками'
 */
public class TestExternalizable implements Externalizable {


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
