	<div class="grid_12" style="margin-bottom: 15px;">
		<div class="genericContainer">
			<h3>Beneficiario: <c:out value="${beneficiario.nombre}"/></h3>
			<div class="content">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td class="formLabel">EPS:</td>
							<td class="dataLabel" colspan="2"><span id="eps"><c:out value="${beneficiario.eps}"/></span></td>
							<td class="formLabel">Tipo Id:</td>
							<td class="dataLabel"><span id="tipoDocumento"><c:out value="${beneficiario.tipoDocumento}"/></span></td>
							<td class="formLabel">Num. Id:</td>
							<td class="dataLabel"><span id="numeroDocumento"><c:out value="${beneficiario.numeroDocumento}"/></span></td>
						</tr>
						<tr>
							<td class="formLabel">Genero:</td>
							<td class="dataLabel" colspan="2"><span id="genero"><c:out value="${beneficiario.sexo}"/></span></td>
							<td class="formLabel">Edad:</td>
							<td class="dataLabel"><span id="edad"><c:out value="${beneficiario.edad}"/></span></td>
							<td class="formLabel">Nivel:</td>
							<td class="dataLabel"><span id="nivel"><c:out value="${beneficiario.nivelSisben}"/></span></td>
						</tr>
						<tr>
							<td class="formLabel">Dirección:</td>
							<td class="dataLabel" colspan="4"><span id="direccion"><c:out value="${beneficiario.direccion}"/></span></td>
							<td class="formLabel">Estado:</td>
							<td class="dataLabel"><span id="estado"><c:out value="${beneficiario.estado}"/></span></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>