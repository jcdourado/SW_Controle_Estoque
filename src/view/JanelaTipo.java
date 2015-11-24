package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import utilities.EstoqueException;
import controller.ControllerTipoRestricao;
import model.Tipo;

public class JanelaTipo implements ActionListener{
	private JFrame janela;
	private JTextField codTipo = new JTextField(10);
	private JTextField nomeTipo = new JTextField(30);
	private JPanel panelTipo;
	private JPanel panelCentral;
	private JPanel panelBtn;
	private JScrollPane scrollTipo = new JScrollPane();
	private ControllerTipoRestricao ctr = new ControllerTipoRestricao();
	private JTable tableTipo = new JTable(ctr);
	private Tipo t;
	
	public JanelaTipo(Tipo t) throws EstoqueException{
		this.t = t;
		janela = new JFrame("Tipo");
		janela.setSize(500,400);
		panelCentral = new JPanel(new BorderLayout());
		panelTipo = new JPanel(new GridLayout(2,2));
		
		codTipo.setText(String.valueOf(t.getId()));
		nomeTipo.setText(t.getNome());
		
		codTipo.setEditable(false);
		nomeTipo.setEditable(false);
		
		panelTipo.add(new JLabel("Codigo"));
		panelTipo.add(codTipo);
		
		panelTipo.add(new JLabel("Nome"));
		panelTipo.add(nomeTipo);

		scrollTipo.setViewportView(tableTipo);
		panelCentral.add(scrollTipo,BorderLayout.CENTER);
		
		JButton btnAdicionar = new JButton("Adicionar");
		JButton btnRemover = new JButton("Remover");
		btnAdicionar.addActionListener(this);
		btnRemover.addActionListener(this);
		panelBtn = new JPanel(new FlowLayout());
		panelBtn.add(btnAdicionar);
		panelBtn.add(btnRemover);
		
		panelCentral.add(panelTipo,BorderLayout.NORTH);
		panelCentral.add(panelBtn,BorderLayout.SOUTH);
		
		janela.setContentPane(panelCentral);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ctr.consultar(t);
		tableTipo.revalidate();
		scrollTipo.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent eve) {
		String cmd = eve.getActionCommand();
		if(cmd.equals("Adicionar")){
			try{
				int cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do tipo a ser restringido"));
				Tipo t1 = new Tipo();
				t1.setId(cod);
				ctr.adicionar(t, t1);
				ctr.consultar(t);
				tableTipo.revalidate();
				scrollTipo.repaint();
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else{
			int linha = tableTipo.getSelectedRow();
			Tipo t1 = ctr.getTipos().get(linha);
			try {
				ctr.remover(t, t1);
				ctr.consultar(t);
				tableTipo.revalidate();
				scrollTipo.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
}
