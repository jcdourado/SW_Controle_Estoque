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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import utilities.EstoqueException;
import model.Departamento;
import model.Produto;
import model.SolicitacaoDepartamento;
import controller.ControllerProduto;
import controller.ControllerSolicitacaoDepartamento;

public class JanelaDepartamento implements ActionListener{
	private ControllerProduto ctrProduto = new ControllerProduto();
	private JTextField codDep = new JTextField(10);
	private JTextField nomeDep = new JTextField(30);
	private JTextField andarDep = new JTextField(10);
	private JTextField predioDep = new JTextField(10);
	private JTextField telDep = new JTextField(10);
	private JTextField codRespDep = new JTextField(10);
	private JTextField calcConsumoGeral = new JTextField(10);
	private JTextField calcQtdRecebidaGeral = new JTextField(30);
	private JTextField restaAlgoGeral = new JTextField(10);
	private JTextField qtdRestanteGeral = new JTextField(10);
	private JTextField calcPesoGeral = new JTextField(10);
	private JTextField calcPrecoGeral = new JTextField(10);
	private JFrame janela = new JFrame("Departamento");
	private JPanel panelPrincipal;
	private JPanel panelBtn;
	private JPanel panelDep;
	private JScrollPane scroll;
	private ControllerSolicitacaoDepartamento ctrSolDpto = new ControllerSolicitacaoDepartamento();
	private JTable tableSolicitacoes = new JTable(ctrSolDpto);
	private Departamento d;
	
	public JanelaDepartamento(Departamento d) throws EstoqueException{
		this.d = d;
		janela.setSize(1200, 600);
		panelDep = new JPanel(new GridLayout(13,2));
		panelPrincipal = new JPanel(new BorderLayout());
		panelBtn = new JPanel(new FlowLayout());
		scroll = new JScrollPane();
		scroll.setViewportView(tableSolicitacoes);
		
		codDep.setText(String.valueOf(d.getId()));
		nomeDep.setText(d.getNome());
		andarDep.setText(d.getAndar());
		predioDep.setText(d.getPredio());
		telDep.setText(d.getTelefone());
		codRespDep.setText(String.valueOf(d.getCodResponsavel()));
		calcConsumoGeral.setText(String.valueOf(d.calcConsumoGeral()));
		calcQtdRecebidaGeral.setText(String.valueOf(d.calcQtdRecebidaGeral()));
		if(d.restaAlgoGeral()){
			restaAlgoGeral.setText("Sim");
		}
		else{
			restaAlgoGeral.setText("Não");			
		}
		qtdRestanteGeral.setText(String.valueOf(d.qtdRestanteGeral()));
		calcPesoGeral.setText(String.valueOf(d.calcPesoGeral()));
		calcPrecoGeral.setText(String.valueOf(d.calcPrecoGeral()));
		
		codDep.setEditable(false);
		nomeDep.setEditable(false);
		andarDep.setEditable(false);
		predioDep.setEditable(false);
		telDep.setEditable(false);
		codRespDep.setEditable(false);
		calcConsumoGeral.setEditable(false);
		calcQtdRecebidaGeral.setEditable(false);
		restaAlgoGeral.setEditable(false);
		qtdRestanteGeral.setEditable(false);
		calcPesoGeral.setEditable(false);
		calcPrecoGeral.setEditable(false);
		
		panelDep.add(new JLabel("Código"));
		panelDep.add(codDep);
		panelDep.add(new JLabel("Nome"));
		panelDep.add(nomeDep);
		panelDep.add(new JLabel("Andar"));
		panelDep.add(andarDep);
		panelDep.add(new JLabel("Prédio"));
		panelDep.add(predioDep);
		panelDep.add(new JLabel("Telefone"));
		panelDep.add(telDep);
		panelDep.add(new JLabel("Código do Responsável"));
		panelDep.add(codRespDep);
		panelDep.add(new JLabel("Consumo geral"));
		panelDep.add(calcConsumoGeral);
		panelDep.add(new JLabel("Quantidade recebida geral"));
		panelDep.add(calcQtdRecebidaGeral);
		panelDep.add(new JLabel("Resta algo"));
		panelDep.add(restaAlgoGeral);
		panelDep.add(new JLabel("Quantidade restante geral"));
		panelDep.add(qtdRestanteGeral);
		panelDep.add(new JLabel("Peso geral"));
		panelDep.add(calcPesoGeral);
		panelDep.add(new JLabel("Preço geral"));
		panelDep.add(calcPrecoGeral);
		panelPrincipal.add(panelDep,BorderLayout.NORTH);
		
		panelPrincipal.add(scroll,BorderLayout.CENTER);
				
		JButton calcConsumoProduto = new JButton("Calcular consumo Produto");
		JButton calcQtdRecebidaPorProduto = new JButton("Calcular qtd recebida Produto");
		JButton getRestante = new JButton("Calcular qtd restante Produto");
		JButton calcPesoPorProduto = new JButton("Calcular peso consumido Produto");
		JButton calcPrecoPorProduto = new JButton("Calcular preço consumido Produto");
		
		calcConsumoProduto.addActionListener(this);
		calcQtdRecebidaPorProduto.addActionListener(this);
		getRestante.addActionListener(this);
		calcPesoPorProduto.addActionListener(this);
		calcPrecoPorProduto.addActionListener(this);
		
		panelBtn.add(calcConsumoProduto);
		panelBtn.add(calcQtdRecebidaPorProduto);
		panelBtn.add(getRestante);
		panelBtn.add(calcPesoPorProduto);
		panelBtn.add(calcPrecoPorProduto);
		
		panelPrincipal.add(panelBtn,BorderLayout.SOUTH);
		SolicitacaoDepartamento solDp = new SolicitacaoDepartamento();
		solDp.setIdDepartamento(d.getId());
 		ctrSolDpto.consultar(solDp);
		tableSolicitacoes.revalidate();
		scroll.repaint();
		
		janela.setContentPane(panelPrincipal);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd.contains("consumo Produto")){
			try{
				int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(indexPro);
				try {
					List<Produto> list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					d.calcConsumoProduto(rs);
				} catch (EstoqueException e) {
					e.printStackTrace();
				}		
				
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.contains("qtd recebida Produto")){
			try{
				int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(indexPro);
				try {
					List<Produto> list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					d.calcQtdRecebidaPorProduto(rs);
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
	
		}
		else if(cmd.contains("qtd restante Produto")){
			try{
				int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(indexPro);
				try {
					List<Produto> list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					d.getRestante(rs);
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
	
		}
		else if(cmd.contains("peso consumido Produto")){
			try{
				int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(indexPro);
				try {
					List<Produto> list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					d.calcPesoPorProduto(rs);
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
	
		}
		else{
			try{
				int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(indexPro);
				try {
					List<Produto> list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					d.calcPrecoPorProduto(rs);
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
		}
		tableSolicitacoes.revalidate();
		scroll.repaint();
	}
}
