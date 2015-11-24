package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.EstoqueException;
import controller.ControllerFornecedor;
import model.Fornecedor;
import model.Produto;

public class JanelaProduto implements ActionListener{
	private JPanel panelPro = new JPanel(new GridLayout(14, 2));
	private ControllerFornecedor ctrForn = new ControllerFornecedor();
	
	private JTextField codPro = new JTextField(10);
	private JTextField nomePro = new JTextField(30);
	private JTextField usoPro = new JTextField(30);
	private JTextField qtdMinima = new JTextField(10);	
	private JTextField qtdSeguranca = new JTextField(10);
	private JTextField qtdMaxima = new JTextField(10);
	private JTextField consumoPrevisto = new JTextField(10);
	private JTextField preco = new JTextField(10);
	private JTextField peso = new JTextField(10);
	private JTextField codTipoPro = new JTextField(10);
	private JTextField qtdTotalSolFor = new JTextField(10);
	private JTextField qtdTotalSolDep = new JTextField(10);
	private JTextField qtdEstoque = new JTextField(10);
	private JTextField qtdTotal = new JTextField(10);

	private JFrame janela;
	private JPanel panelCentral;
	private JPanel panelBtn;
	private Produto p;
	
	public JanelaProduto(Produto p){
		this.p = p;
		janela = new JFrame("Produto");
		janela.setSize(700,400);
		panelCentral = new JPanel(new BorderLayout());

		codPro.setText(String.valueOf(p.getTipo()));
		nomePro.setText(p.getNome());
		usoPro.setText(p.getUso());
		qtdMinima.setText(String.valueOf(p.getQtdMinima()));
		qtdMaxima.setText(String.valueOf(p.getQtdMaxima()));
		qtdSeguranca.setText(String.valueOf(p.getQtdSeguranca()));
		consumoPrevisto.setText(p.getConsumoPrevisto());
		preco.setText(String.valueOf(p.getPreco()));
		peso.setText(String.valueOf(p.getPeso()));
		codTipoPro.setText(String.valueOf(p.getTipo()));
		qtdTotalSolFor.setText(String.valueOf(p.getQtdPedidoFornecedor()));
		qtdTotalSolDep.setText(String.valueOf(p.getQtdPedidoDepartamento()));
		qtdEstoque.setText(String.valueOf(p.getQtdEmEstoque()));
		qtdTotal.setText(String.valueOf(p.qtdTotal()));
				
		codPro.setEditable(false);
		nomePro.setEditable(false);
		usoPro.setEditable(false);
		qtdMaxima.setEditable(false);
		qtdMinima.setEditable(false);
		qtdSeguranca.setEditable(false);
		consumoPrevisto.setEditable(false);
		preco.setEditable(false);
		peso.setEditable(false);
		codTipoPro.setEditable(false);
		qtdTotal.setEditable(false);
		qtdEstoque.setEditable(false);
		qtdTotalSolFor.setEditable(false);
		qtdTotalSolDep.setEditable(false);
		
		panelPro.add(new JLabel("Código"));
		panelPro.add(codPro);
		panelPro.add(new JLabel("Nome"));
		panelPro.add(nomePro);
		panelPro.add(new JLabel("Uso"));
		panelPro.add(usoPro);
		panelPro.add(new JLabel("Quantidade mínima"));
		panelPro.add(qtdMinima);
		panelPro.add(new JLabel("Quantidade de segurança"));
		panelPro.add(qtdSeguranca);
		panelPro.add(new JLabel("Quantidade máxima"));
		panelPro.add(qtdMaxima);
		panelPro.add(new JLabel("Consumo previsto"));
		panelPro.add(consumoPrevisto);
		panelPro.add(new JLabel("Preço"));
		panelPro.add(preco);
		panelPro.add(new JLabel("Peso"));
		panelPro.add(peso);
		panelPro.add(new JLabel("Tipo do Produto"));
		panelPro.add(codTipoPro);
		panelPro.add(new JLabel("Quantidade total solicitada p/ fornecedor"));
		panelPro.add(qtdTotalSolFor);
		panelPro.add(new JLabel("Quantidade total solicitada p/ departamento"));
		panelPro.add(qtdTotalSolDep);
		panelPro.add(new JLabel("Quantidade total já rodada"));
		panelPro.add(qtdTotal);
		panelPro.add(new JLabel("Quantidade total em estoque"));
		panelPro.add(qtdEstoque);		
		panelCentral.add(panelPro,BorderLayout.NORTH);
		
		JButton btnTempoPorFornecedor = new JButton("Calcular tempo por fornecedor");
		JButton btnQtdPorFornecedor = new JButton("Calcular quantidade por fornecedor");
		btnQtdPorFornecedor.addActionListener(this);
		btnTempoPorFornecedor.addActionListener(this);
		panelBtn = new JPanel(new FlowLayout());
		panelBtn.add(btnQtdPorFornecedor);
		panelBtn.add(btnTempoPorFornecedor);
		panelCentral.add(panelBtn,BorderLayout.SOUTH);
		
		janela.setContentPane(panelCentral);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd.equals("Calcular tempo por fornecedor")){
			int indexFor = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do fornecedor"));
			Fornecedor f = new Fornecedor();
			f.setId(indexFor);
			try {
				List<Fornecedor> list = ctrForn.consultar(f);
				JOptionPane.showMessageDialog(null, p.tempoPorFornecedor(list.get(0)), "Quantidade!",JOptionPane.INFORMATION_MESSAGE);
			} catch (EstoqueException e) {
				e.printStackTrace();
			}
		}
		else{
			int indexFor = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do fornecedor"));
			Fornecedor f = new Fornecedor();
			f.setId(indexFor);
			try {
				List<Fornecedor> list = ctrForn.consultar(f);
				JOptionPane.showMessageDialog(null, p.qtdPorFornecedor(list.get(0)), "Quantidade!",JOptionPane.INFORMATION_MESSAGE);
			} catch (EstoqueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
