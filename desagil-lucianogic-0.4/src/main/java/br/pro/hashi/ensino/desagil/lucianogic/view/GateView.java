package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.LED;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;
import javax.swing.border.Border;


// Esta classe representa a interface de uma porta logica.
public class GateView extends FixedPanel implements ItemListener, ActionListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;
	
	private JCheckBox[] inBoxes;
	private JCheckBox outBox;
	private LED zeppelin;
	private JButton button;
	private boolean flag;
	
	private Switch[] switches;
	private Gate gate;


	public GateView(Gate gate) {
		super(370, 220);
		this.gate = gate;
		this.image = loadImage(gate.toString());
		// A componente JLabel representa simplesmente um texto fixo.
		// https://docs.oracle.com/javase/tutorial/uiswing/components/label.html
		

		int size = gate.getSize();

		inBoxes = new JCheckBox[size];
		zeppelin = new LED(255,255,255);
		
		switches = new Switch[size];

		for(int i = 0; i < size; i++) {
			inBoxes[i] = new JCheckBox();

			// Esta linha garante que, sempre que o usuario clicar
			// na checkbox, o metodo itemStateChanged abaixo sera chamado.
			// https://docs.oracle.com/javase/tutorial/uiswing/components/button.html#checkbox
			inBoxes[i].addItemListener(this);

			switches[i] = new Switch();

			gate.connect(switches[i], i);
		}

		outBox = new JCheckBox();

		// Esta linha garante que outBox nao seja clicavel.
		outBox.setEnabled(false);

		// Esta linha garante que os componentes sejam simplesmente
		// colocados em linha reta, mais especificamente na vertical.
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		/* A PARTIR DESTE PONTO VOCE DEVE ENTENDER SOZINHO */

		
		//for(JCheckBox inBox: inBoxes) {
			//add(inBox);
		//}
		
		int tambas = 20;
		
		button = new JButton();
		button.addActionListener(this);
		button.setOpaque(true);
		button.setToolTipText("Click to change the color of the LED");

		
		if(inBoxes.length == 2){
			add(inBoxes[0], 92, 145, tambas, tambas);
			add(inBoxes[1], 92, 82, tambas, tambas);
			add(button, 285, 110, tambas, tambas);
		}
		if(inBoxes.length == 1){
			add(inBoxes[0], 92, 110, tambas, tambas);
			add(button, 277, 110, tambas, tambas);
		}
		if(inBoxes.length == 3){
			add(inBoxes[0], 92, 50, tambas, tambas);
			add(inBoxes[1], 92, 110, tambas, tambas);
			add(inBoxes[2], 130, 180, tambas, tambas);
			add(button, 285, 100, tambas, tambas);
		}
		//add(outBox, 277, 110, tambas, tambas);

		//outBox.setSelected(gate.read());
		zeppelin.connect(gate, 0);
		
				
		if (zeppelin.isOn() == false){
			Color color = new Color(0,0,0);
			button.setBackground(color);
		}else{
			zeppelin.changeColor(255, 255, 255);
			Color color = new Color(zeppelin.getR(),zeppelin.getG(),zeppelin.getB());
			button.setBackground(color);
		}
	}


	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 100, 50, 200, 150, null);

		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Color color = JColorChooser.showDialog(this, null, null);

		if(color != null && zeppelin.isOn()) {
			button.setBackground(color);
			zeppelin.changeColor(color.getRed(), color.getGreen(), color.getBlue());
			flag = true;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}
		
		switches[i].setOn(inBoxes[i].isSelected());

		outBox.setSelected(gate.read());
		
		if (zeppelin.isOn() == false){
			Color color = new Color(0,0,0);
			button.setBackground(color);
		}else{
			if(flag == false){
				zeppelin.changeColor(255, 255, 255);
			}
			Color color = new Color(zeppelin.getR(),zeppelin.getG(),zeppelin.getB());
			button.setBackground(color);
		}

	}
}

