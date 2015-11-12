package model;

public class Item {
	private int idItem;
	private int idProduto;
	private int idSaida;
	private int idEntrada;
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getIdSaida() {
		return idSaida;
	}
	public void setIdSaida(int idSaida) {
		this.idSaida = idSaida;
	}
	public int getIdEntrada() {
		return idEntrada;
	}
	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}
	public boolean verSaida(){
		if(idSaida == 0){
			return false;
		}
		return true;
	}
}