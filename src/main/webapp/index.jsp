<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>USUARIOS NUEVOS</title>
<script>
    function deleteRecord(id) {
        if (confirm('Estas seguro de Eliminar el registro...')) {
            fetch('PersonaController', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    'accion': 'Delete',
                    'id': id
                })
            }).then(response => {
                if (response.ok) {
                    window.location.reload(); // Recarga la página para actualizar la lista
                } else {
                    alert('Failed to delete record');
                }
            }).catch(error => {
                console.error('Error:', error);
            });
        }
    }
    
    // Función para habilitar el formulario para edición
    function editRecord(id, nombres, correo, telefono) {
        document.getElementById('id').value = id;
        document.getElementById('nombres').value = nombres;
        document.getElementById('correo').value = correo;
        document.getElementById('telefono').value = telefono;
        document.getElementById('editMode').value = "true"; // Indicamos que estamos en modo edición
        document.getElementById('submitButton').value = "Actualizar"; // Cambiamos el texto del botón
    }
    
    function validateForm() {
        var nombre = document.getElementById('nombres').value;
        var correo = document.getElementById('correo').value;
        var telefono = document.getElementById('telefono').value;

        // Validar campos vacíos
        if (nombre === '' || correo === '' || telefono === '') {
            alert('Todos los campos son obligatorios.');
            return false;
        }

        // Validar longitud del nombre
        if (nombre.length > 50) {
            alert('El nombre no debe exceder los 50 caracteres.');
            return false;
        }

        // Validar formato de correo
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(correo)) {
            alert('Por favor, introduce un correo electrónico válido.');
            return false;
        }

        // Validar teléfono
        var phonePattern = /^[0-9]{10}$/;
        if (!phonePattern.test(telefono)) {
            alert('Por favor, introduce un número de teléfono válido de 10 dígitos.');
            return false;
        }

        // Si todas las validaciones pasan
        return true;
    }
</script>
</head>
<body>
	
		<h2>Formulario de Registro</h2>
		<input type="hidden" id="editMode" name="editMode" value="false"> <!-- Campo oculto para modo edición -->
	    <form action="PersonaController" method="POST" id="miFormulario" onsubmit="return validateForm()">
	        <label for="id">ID:</label>
	        <input type="text" id="id" name="id" required><br><br>
	        
	        <label for="nombres">NOMBRES:</label>
	        <input type="text" id="nombres" name="nombres"><br><br>
	        
	        <label for="correo">CORREO:</label>
	        <input type="email" id="correo" name="correo" ><br><br>
	        
	        <label for="telefono">TELEFONO:</label>
	        <input type="tel" id="telefono" name="telefono"><br><br>
	        
	        <input type="submit" id="submitButton" name="accion" value="Registrar">
	    </form>
	    <center>
		<div>
			<h3>Personas</h3>
		</div>
		<div>
			<table border="1">
				<thead>
					<tr>
						<th>ID</th>
						<th>NOMBRES</th>
						<th>CORREO</th>
						<th>TELEFONO</th>
						<th>ACCIONES</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dato" items="${datos}">
					<tr>
						<td>${dato.id}</td>
						<td>${dato.nom}</td>
						<td>${dato.correo}</td>
						<td>${dato.tel}</td>
						<td>
                            <button onclick="editRecord('${dato.id}', '${dato.nom}', '${dato.correo}', '${dato.tel}')">Edit</button>
                            <button onclick="deleteRecord('${dato.id}')">Delete</button>
                        </td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</center>
</body>
</html>