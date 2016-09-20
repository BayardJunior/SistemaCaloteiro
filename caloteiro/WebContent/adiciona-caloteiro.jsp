<%@ include file="includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adiciona Caloteiro</title>  
</head>
<body>
	<c:import url="cabecalho.jsp"/>
	<form action="sistema" method="post">
	<input type="hidden" name="logica" value="AdicionaCaloteiro"/>
		Nome:<input type="text" name="nome"> </br>
		Email:<input type="text"name="email"> </br>
		Devendo R$:<input type="text" name="devendo"></br>
		Data:<input type="text" name="dataDivida"> </br>
		
		<input type="submit" value="Adicionar">
	</form>
	<c:import url="rodape.jsp"/>
</body>
</html>