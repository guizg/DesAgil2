package br.pro.hashi.ensino.desagil.lucianogic.model;

public class MuxGate extends Gate {
	private NandGate nandGate;
	private NandGate nandGate2;
	private NandGate nandGate3;
	private NandGate nandGate4;
	private Emitter a;
	private Emitter b;
	private Emitter sel;

	public MuxGate() {
		super(3);
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
			a = emitter;	
		}
		if(index == 1){
			b = emitter;
		}
		if(index == 2){
			sel = emitter;
			
			nandGate.connect(sel, 0);
			nandGate.connect(sel, 1);
			
			nandGate2.connect(a, 0);
			nandGate2.connect(nandGate, 1);
			
			nandGate3.connect(sel, 0);
			nandGate3.connect(b, 1);
			
			nandGate4.connect(nandGate2, 0);
			nandGate4.connect(nandGate3, 1);
			
			
		}
		
		
	}
}
