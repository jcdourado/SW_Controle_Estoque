package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import utilities.EstoqueException;
import controller.ControllerProduto;
import model.Produto;
import model.Saida;

public class JanelaSaida implements ActionListener{
	private JTextField codSai = new JTextField(10);
	private ControllerProduto ctrProduto = new ControllerProduto();
	private JFormattedTextField dataSai;
	private JTextField descSaida = new JTextField(30);
	private JTextField precoGeral = new JTextField(30);
	private JTextField pesoGeral = new JTextField(30);
	private JTextField contaisDoc = new JTextField(30);
	private JTextField isDoc = new JTextField(30);
	private JTextField qtdProDoados = new JTextField(30);
	private JFrame frame = new JFrame("Saida");
	private JPanel panelSai;
	private JPanel panelBtn;
	private JPanel panelPrinc;
	private Saida s;
	SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public JanelaSaida(Saida s) throws ParseException{
		this.s = s;
		frame.setSize(1000, 600);
		panelPrinc = new JPanel(new BorderLayout());
		panelSai = new JPanel(new GridLayout(8,2));
		 
		MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		
		dataSai = new JFormattedTextField(mascaraData);
		codSai.setText(String.valueOf(s.getIdSaida()));
		dataSai.setText(in.format(s.getData()));
		descSaida.setText(s.getDescricao());
		if(s.isDoacao()){
			isDoc.setText("Sim");			
		}
		else{
			isDoc.setText("Não");
		}
		if(s.containsDoacao()){
			contaisDoc.setText("Sim");			
		}
		else{
			contaisDoc.setText("Não");
		}
		qtdProDoados.setText(String.valueOf(s.qtdProdutosDoadosTotal()));
		precoGeral.setText(String.valueOf(s.calcPrecoGeral()));
		pesoGeral.setText(String.valueOf(s.calcPesoGeral()));
		
		codSai.setEditable(false);
		dataSai.setEditable(false);
		descSaida.setEditable(false);
		isDoc.setEditable(false);
		contaisDoc.setEditable(false);
		qtdProDoados.setEditable(false);
		precoGeral.setEditable(false);
		pesoGeral.setEditable(false);
		
		panelSai.add(new JLabel("Código"));
		panelSai.add(codSai);
		panelSai.add(new JLabel("Data"));
		panelSai.add(dataSai);
		panelSai.add(new JLabel("Descrição"));
		panelSai.add(descSaida);
		panelSai.add(new JLabel("É uma doação"));
		panelSai.add(isDoc);
		panelSai.add(new JLabel("Tem doação"));
		panelSai.add(contaisDoc);
		panelSai.add(new JLabel("Quantidade de produtos doados"));
		panelSai.add(qtdProDoados);
		panelSai.add(new JLabel("Preço geral"));
		panelSai.add(precoGeral);
		panelSai.add(new JLabel("Peso geral"));
		panelSai.add(pesoGeral);
		panelPrinc.add(panelSai,BorderLayout.NORTH);

		panelBtn = new JPanel(new FlowLayout());
		
		JButton qtdTotalItemProduto = new JButton("Calcular a quantidade de items por produto");
		JButton qtdProdutosDoadosProduto = new JButton("Calcular a quantidade de produtos doados");
		JButton calcPesoPorProduto = new JButton("Calcular o peso por produto");
		JButton calcPrecoPorProduto = new JButton("Calcular o preço por produto");
		panelBtn.add(qtdTotalItemProduto);
		panelBtn.add(qtdProdutosDoadosProduto);
		panelBtn.add(calcPesoPorProduto);
		panelBtn.add(calcPrecoPorProduto);
		
		qtdProdutosDoadosProduto.addActionListener(this);
		qtdTotalItemProduto.addActionListener(this);
		calcPesoPorProduto.addActionListener(this);
		calcPrecoPorProduto.addActionListener(this);
		panelPrinc.add(panelBtn,BorderLayout.SOUTH);
		
		frame.setContentPane(panelPrinc);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd.contains("items por")){
			try{
				int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto"));
				Produto prod = new Produto();
				prod.setId(idProduto);
				try {
					List<Produto> list = ctrProduto.consultar(prod);
					JOptionPane.showMessageDialog(null, s.qtdTotalItemProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
				
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.contains("doados")){
			int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto"));
			Produto prod = new Produto();
			prod.setId(idProduto);
			try {
				List<Produto> list = ctrProduto.consultar(prod);
				JOptionPane.showMessageDialog(null, s.qtdProdutosDoadosProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
			} catch (EstoqueException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.contains("peso")){
			int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto"));
			Produto prod = new Produto();
			prod.setId(idProduto);
			try {
				List<Produto> list = ctrProduto.consultar(prod);
				JOptionPane.showMessageDialog(null, s.calcPesoPorProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
			} catch (EstoqueException e) {
				e.printStackTrace();
			}
		}
		else{
			int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto"));
			Produto prod = new Produto();
			prod.setId(idProduto);
			try {
				List<Produto> list = ctrProduto.consultar(prod);
				JOptionPane.showMessageDialog(null, s.calcPrecoPorProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
			} catch (EstoqueException e) {
				e.printStackTrace();
			}
		}
	}
}
