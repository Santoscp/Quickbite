package com.example.api.model;

import com.example.api.ENUM.EstadoPedido;
import com.example.api.ENUM.PagoPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPedido estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rep", nullable = false)
    private Repartidor idRep;

    @Enumerated(EnumType.STRING)
    @Column(name = "pago", nullable = false)
    private PagoPedido pago;

    @Column(name = "recogida", nullable = false)
    private Boolean recogida = false;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public Repartidor getIdRep() {
		return idRep;
	}

	public void setIdRep(Repartidor idRep) {
		this.idRep = idRep;
	}

	public PagoPedido getPago() {
		return pago;
	}

	public void setPago(PagoPedido pago) {
		this.pago = pago;
	}

	public Boolean getRecogida() {
		return recogida;
	}

	public void setRecogida(Boolean recogida) {
		this.recogida = recogida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}