package com.devquinchi.proyecto_webservices_cliente.service;

import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.devquinchi.proyecto_webservices_cliente.client.EmpleadoDTO;

/**
 * @author Julio
 * Clase cliente que permite consumir los webservice de Empleado
 */
public class EmpleadoWSClient {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// GET
		// clase principal de jersey "Client"
//		Client cliente=ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
//		Client cliente=ClientBuilder.newClient(); //construir un cliente
//		WebTarget webtarget=cliente.target("http://localhost:8080/proyecto-webservices/devquinchi/empleadoWS").path("listaEmpleado").path("123456"); //url de comunicacion
//		// aqui configuro el tipo de peticion que deseo hacer como json,html 
//		Invocation.Builder invocationBuilder=webtarget.request(MediaType.APPLICATION_JSON);
//		Response response=invocationBuilder.get();// respuesta que me va optener el webservice
//		
//		//validaciones de paginas
//		if(response.getStatus()==204) {
//			System.out.println("No existe ese empleado");
//		}if(response.getStatus()==200) {
//			//Va buscar como mapear las propiedades de la clase EmpleadoDTO
//			EmpleadoDTO empleadoDTO=response.readEntity(EmpleadoDTO.class);
//			System.out.println("Nombre Empleado "+empleadoDTO.getNombre());
//			System.out.println("Fecha :" +empleadoDTO.getFechaCreacion());
//		}
//		
		
		Client cliente=ClientBuilder.newClient(); //construir un cliente
		WebTarget webtarget=cliente.target("http://localhost:8080/proyecto-webservices/devquinchi/empleadoWS").path("guardarEmpleado"); //url de comunicacion
		// creo mi objeto DTO
		EmpleadoDTO empleado=new EmpleadoDTO();
		empleado.setNumeroEmpleado("1234567");
		empleado.setNombre("");
		empleado.setPrimerApellido("cajas");
		empleado.setSegundoApellido("cuyago");
		empleado.setEdad(38);
		empleado.setFechaCreacion(LocalDateTime.now());			
		
		// aqui configuro el tipo de peticion que deseo hacer como json,html 
		Invocation.Builder invocationBuilder=webtarget.request(MediaType.APPLICATION_JSON);
		Response response=invocationBuilder.post(Entity.entity(empleado,MediaType.APPLICATION_JSON));// respuesta que me va enviar al webservice
		
		if(response.getStatus()==400) {
			String error =response.readEntity(String.class);
			System.out.println(error);
		}
//		System.out.println(response.getStatus());
//		System.out.println(response.readEntity(String.class));
		if (response.getStatus()==200) {
		EmpleadoDTO empleadoDTO=	response.readEntity(EmpleadoDTO.class);
		System.out.println(empleadoDTO.getNombre()+"\n"+
		empleadoDTO.getNumeroEmpleado());
		
		}
	}
}
