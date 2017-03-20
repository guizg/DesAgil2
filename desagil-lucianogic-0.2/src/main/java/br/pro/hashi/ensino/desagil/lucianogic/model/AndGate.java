package br.pro.hashi.ensino.desagil.lucianogic.model;

public class AndGate extends Gate {
	private NandGate nandGate;
	private NandGate nandGate2;
	

	public AndGate() {
		super(2);
		nandGate = new NandGate();
		nandGate2 = new NandGate();
	}

	@Override
	public boolean read() {
		return nandGate2.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		if(index == 0){
			nandGate.connect(emitter, 0);
		}
		if(index == 1){
			nandGate.connect(emitter, 1);
			nandGate2.connect(nandGate, 0);
			nandGate2.connect(nandGate, 1);
		}
		
		
	}
}
