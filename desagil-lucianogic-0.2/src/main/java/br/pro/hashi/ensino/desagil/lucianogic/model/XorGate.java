package br.pro.hashi.ensino.desagil.lucianogic.model;

public class XorGate extends Gate {
	private NandGate nandGate;
	private NandGate nandGate2;
	private NandGate nandGate3;
	private NandGate nandGate4;
	private Emitter a;
	private Emitter b;

	public XorGate() {
		super(2);
		nandGate = new NandGate();
		nandGate2 = new NandGate();
		nandGate3 = new NandGate();
		nandGate4 = new NandGate();
	}

	@Override
	public boolean read() {
		return nandGate4.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		if(index == 0){
			nandGate.connect(emitter, 0);
			a = emitter;
			
		}
		if(index == 1){
			nandGate.connect(emitter, 1);
			b = emitter;
			
			nandGate2.connect(a, 0);
			nandGate2.connect(new MockEmitter(nandGate.read()), 1);
			
			nandGate3.connect(new MockEmitter(nandGate.read()), 0);
			nandGate3.connect(b, 1);
			
			nandGate4.connect(new MockEmitter(nandGate2.read()), 0);
			nandGate4.connect(new MockEmitter(nandGate3.read()), 1);
		}
		
		
	}
}