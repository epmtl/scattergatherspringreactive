package epmtl.github.resource.outbound;

import epmtl.github.resource.inbound.Data1;
import epmtl.github.resource.inbound.Data2;

@SuppressWarnings("unused")
public class Data {
    private Data1 data1;
    private Data2 data2;

    public Data() {}

    public Data(Data1 data1, Data2 data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    public Data1 getData1() {
        return data1;
    }

    public void setData1(Data1 data1) {
        this.data1 = data1;
    }

    public Data2 getData2() {
        return data2;
    }

    public void setData2(Data2 data2) {
        this.data2 = data2;
    }
}
