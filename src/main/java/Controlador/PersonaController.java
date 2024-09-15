package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Persona;
import Modelo.PersonaDao;

@WebServlet("/PersonaController")
public class PersonaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    PersonaDao dao = new PersonaDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la lista de personas
        List<Persona> datos = dao.listar();
        
        // Establecer los datos en el request
        request.setAttribute("datos", datos);
        
        // Redirigir a la página JSP para mostrar los datos
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String id = request.getParameter("id");
        String nombres = request.getParameter("nombres");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        Persona persona = new Persona(id,nombres,correo,telefono);
        switch (accion) {        
            case "Delete":
                dao.eliminar(id); // Llama al método de eliminar en tu DAO
                response.sendRedirect("PersonaController"); // Redirige a la misma URL para actualizar la lista
                break;

            case "Actualizar":
            	dao.ActualiarDatos(persona);
                response.sendRedirect("PersonaController"); // Redirige a la misma URL para actualizar la lista
                break;

            case "Registrar":
                dao.insertarDatos(persona);
                response.sendRedirect("PersonaController");
                break;
                
            default:
                throw new AssertionError();
        }
    }
}
