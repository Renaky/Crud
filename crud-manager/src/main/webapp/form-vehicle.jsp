<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<%@include file="base-head.jsp"%>
	</head>
	<body>
		<%@include file="nav-menu.jsp"%>
			
		<div id="container" class="container-fluid">
			<h3 class="page-header">Adicionar Veículo</h3>

			<form action="${pageContext.request.contextPath}/vehicle/${action}" method="POST">
				<input type="hidden" value="${vehicle.getVehicleid()}" name="vehicleId">
				<div class="row">
					<div class="form-group col-md-4">
					<label for="name">Marca</label>
						<input type="text" class="form-control" id="brand" name="brand" 
							   autofocus="autofocus" placeholder="Marca do Veículo" 
							   required oninvalid="this.setCustomValidity('Por favor, informe a marca.')"
							   oninput="setCustomValidity('')"
							   value="${vehicle.getBrand()}">
					</div>

					
					
					<div class="form-group col-md-4">
					<label for="mail">Modelo</label>
						<input type="text" class="form-control" id="model" name="model" 
							   autofocus="autofocus" placeholder="Modelo do veículo" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o modelo do veículo.')"
							   oninput="setCustomValidity('')"
							   value="${vehicle.getModel()}">
					</div>
					
					<div class="form-group col-md-4">
					<label for="mail">Cor</label>
						<input type="text" class="form-control" id="color" name="color" 
							   autofocus="autofocus" placeholder="Cor do veículo" 
							   required oninvalid="this.setCustomValidity('Por favor, informe a cor do veículo.')"
							   oninput="setCustomValidity('')"
							   value="${vehicle.getColor()}">	   
					</div>
					
					
					
					<div class="form-group col-md-4">
						<label for="type">Tipo de Veículo</label>
						<select id="type" class="form-control selectpicker" name="type" 
								required oninvalid="this.setCustomValidity('Por favor, selecione o tipo de veículo')"
								oninput="setCustomValidity('')">
							<option value="" disabled ${not empty vehicle ? "" : "selected"}>Selecione o tipo de veículo</option>
							<option value="truck" ${vehicle.getType() == 'truck' ? "selected" : ""}>Caminhão</option>
							<option value="car" ${vehicle.getType() == 'car' ? "selected" : ""}>Carro</option>
							<option value="van" ${vehicle.getType() == 'van' ? "selected" : ""}>Van</option>
							<option value="bike" ${vehicle.getType() == 'bike' ? "selected" : ""}>Motocicleta</option>
						</select>
					</div>
					
					<div class="form-group col-md-4">
					<label for="mail">Placa</label>
						<input type="text" class="form-control" id="plate" name="plate" 
							   autofocus="autofocus" placeholder="Numeração da Placa" 
							   required oninvalid="this.setCustomValidity('Por favor, informe a placa do veículo.')"
							   oninput="setCustomValidity('')"
							   value="${vehicle.getNumber_plate()}">
					</div>
					
					<div class="form-group col-md-4">
					<label for="company">Proprietário</label> <select id="company"
						class="form-control selectpicker" name="company" required
						oninvalid="this.setCustomValidity('Por favor, informe o proprietário.')"
						oninput="setCustomValidity('')">

						<option value="" ${not empty vehicle ? "" : 'selected'}>Selecione um proprietário</option>

						<c:forEach var="company" items="${companies}">
							<option value="${company.getId()}"
								${vehicle.getCompany().getId() == company.getId() ?'selected' : ''}>${company.getName()}</option>
						</c:forEach>
					</select>
				</div>
				
					
				
				</div>
				<hr />
				<div id="actions" class="row pull-right">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/vehicles" class="btn btn-default">Cancelar</a>
						<button type="submit" class="btn btn-primary">${not empty user ? "Alterar Veículo" : "Cadastrar Veículo"}</button>
					</div>
				</div>
			</form>
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>
