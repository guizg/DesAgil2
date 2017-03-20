package br.pro.hashi.ensino.desagil.lucianogic.model;

public class AndGate extends Gate {
	private NandGate nandGate;
	private NandGate nandGate2;
	//private NotGate notGate;
	

	public AndGate() {
		super(2);
		nandGate = new NandGate();
		nandGate2 = new NandGate();
		//notGate = new NotGate();
	}

	@Override
	public boolean read() {
		//return notGate.read();
		return nandGate2.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		if(index == 0){
			nandGate.connect(emitter, 0);
		}
		if(index == 1){
			nandGate.connect(emitter, 1);
			//notGate.connect(new MockEmitter(nandGate.read()), 0);
			nandGate2.connect(new MockEmitter(nandGate.read()), 0);
			nandGate2.connect(new MockEmitter(nandGate.read()), 1);
		}
		
		
	}
}
