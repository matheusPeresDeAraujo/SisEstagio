package br.edu.granbery.sisestagio.relatorio;

public class RelRelatorioMensal {

	private Double totalHorasRegistradas;
	private Double totalHorasPenalizadas;
	private Double totalHorasAproveitadas;
	private String nome;

	public RelRelatorioMensal() {

	}

	public Double getTotalHorasRegistradas() {
		return totalHorasRegistradas;
	}

	public void setTotalHorasRegistradas(Double totalHorasRegistradas) {
		this.totalHorasRegistradas = totalHorasRegistradas;
	}

	public Double getTotalHorasPenalizadas() {
		return totalHorasPenalizadas;
	}

	public void setTotalHorasPenalizadas(Double totalHorasPenalizadas) {
		this.totalHorasPenalizadas = totalHorasPenalizadas;
	}

	public Double getTotalHorasAproveitadas() {
		return totalHorasAproveitadas;
	}

	public void setTotalHorasAproveitadas(Double totalHorasAproveitadas) {
		this.totalHorasAproveitadas = totalHorasAproveitadas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
