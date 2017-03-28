package br.pro.hashi.ensino.desagil.lucianogic.model;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;


import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

//Esta classe representa a interface de uma calculadora de densidade, com
//os dois campos de entrada (peso e raio) e o campo de saida (resultado).
public class GateView extends JPanel implements ItemListener{

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	// A componente JTextField representa um campo para digitacao de texto.
	// https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
	private	JCheckBox entrada1;
	private	JCheckBox entrada2;
	private JCheckBox saida;
	private Switch pino1;
	private Switch pino2;

	private Gate gate;


	public GateView(Gate gate) {
		this.gate = gate;
		
	    pino1 = new Switch();
	    pino2 = new Switch();

		// A componente JLabel representa simplesmente um texto fixo.
		// https://docs.oracle.com/javase/tutorial/uiswing/components/label.html
		JLabel entradasLabel = new JLabel("Entrada:");
		JLabel saidasLabel = new JLabel("Saida:");

		entrada1 = new JCheckBox();
		entrada1.setMnemonic(KeyEvent.VK_C); 
	    entrada1.setSelected(false);
	    entrada1.addItemListener(this);
	    
		entrada2 = new JCheckBox();
		entrada2.setMnemonic(KeyEvent.VK_G); 
	    entrada2.setSelected(false);
	    entrada2.addItemListener(this);
	    
		saida = new JCheckBox();
		saida.setMnemonic(KeyEvent.VK_H); 
	    saida.setSelected(false);
	    saida.addItemListener(this);

		// Esta linha garante que, sempre que o usuario digitar algo
		// em weightField, o metodo keyPressed abaixo sera chamado.
		// Voce usou a interface KeyListener no Projeto 1, lembra?
		//weightField.addKeyListener(this);

		// Esta linha garante que, sempre que o usuario digitar algo
		// em radiusField, o metodo keyPressed abaixo sera chamado.
		// Voce usou a interface KeyListener no Projeto 1, lembra?
		//radiusField.addKeyListener(this);

		// Esta linha garante que resultField nao seja digitavel.
		//resultField.setEnabled(false);

		// Esta linha garante que os componentes sejam simplesmente
		// colocados em linha reta, mais especificamente na vertical.
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		/* A PARTIR DESTE PONTO VOCE DEVE ENTENDER SOZINHO */

		add(entradasLabel);
		add(entrada1);
		add(entrada2);
		add(saidasLabel);
		add(saida);

	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
	    Object source = e.getItemSelectable();
	    
	    
	    if (e.getStateChange() == ItemEvent.DESELECTED){
	    	if (source == entrada1) {
		    	//entrada1.setSelected(true);
		    	pino1.setOn(false);
		    } else if (source == entrada2) {
		    	//entrada2.setSelected(true);
		    	pino2.setOn(false);
		    }
	    }else{
	    	if (source == entrada1) {
		    	//entrada1.setSelected(false);
		    	pino1.setOn(true);
		    } else if (source == entrada2) {
		    	//entrada2.setSelected(false);
		    	pino2.setOn(true);
		    }
	    }
	    
	    System.out.println(pino1.read());
	    System.out.println(pino2.read());
	    
	    gate.connect(pino1, 0);
	    gate.connect(pino2, 1);
	    
	    if(gate.read() == true){
	    	saida.setSelected(true);
	    }else{
	    	saida.setSelected(false);
	    }
	    
	    	
	    //updatePicture();
	}



}
