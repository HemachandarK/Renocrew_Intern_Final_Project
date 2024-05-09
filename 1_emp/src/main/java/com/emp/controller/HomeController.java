package com.emp.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.emp.entity.emp;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HomeController {
	
	String user;
	@RequestMapping(path="/home")
	public String home(){
		return "home";
	}
	
	@RequestMapping(path="/logf")
	public String logf(){
		return "logf";
	}
	
	@RequestMapping(path="/admin")
	public String admin(){
		return "admin";
	}
	
	@RequestMapping(path="/rel")
	public String rel(){
		return "rel";
	}

	
	@RequestMapping(path="/adlog")
	public String adlog(){
		return "adlog";
	}
	
	@RequestMapping(path="/emlog")
	public String emlog(){
		return "emlog";
	}
	
	@RequestMapping(path="/reademp")
	public String reademp(){
		return "reademp";
	}
	
	@RequestMapping(path="/addemp")
	public String addemp(){
		return "addemp";
	}
	
	@RequestMapping(path="/rem")
	public String rem(){
		return "rem";
	}
	
	@RequestMapping(path="/updateemp")
	public String updateemp(){
		return "updateemp";
	}
	
	//to see all the tables which are reserved
	
	@RequestMapping(path="/mytab")
	public void mytab(HttpServletResponse response) throws IOException{
		
		 ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
		    JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
		    
		    // Select query
		    String query = "SELECT * FROM reservation where emp_email=?";
		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    try {
		        // Fetch data from the database
		        List<Map<String, Object>> empDetailsList = template.queryForList(query,user);
		        
		        out.println("<!DOCTYPE html>");
		        out.println("<html>");
		        out.println("<head>");
		        out.println("<meta charset=\"UTF-8\">");
		        out.println("<title>Employee List</title>");
		        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"");
		        out.println("integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
		        out.println("</head>");
		        out.println("<body>");
		        out.println("<nav class=\"navbar navbar-expand-lg navbar-light bg-primary\">");
		        out.println("<div class=\"container-fluid\">");
		        out.println("<a class=\"navbar-brand text-white\" href=\"home\">Emp Management System</a>");
		        out.println("<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\"");
		        out.println("data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"");
		        out.println("aria-label=\"Toggle navigation\">");
		        out.println("<span class=\"navbar-toggler-icon\"></span>");
		        out.println("</button>");
		        out.println("</div>");
		        out.println("</nav>");
		        // Write employee details
		        out.println("<div class=\"container\">");
		        out.println("<h1>Employee List</h1>");
		        out.println("<table class=\"table\">");
		        out.println("<thead>");
		        out.println("<tr>");
		        out.println("<th>TABLE</th>");
		        out.println("<th>LOCATION</th>");
		        out.println("</tr>");
		        out.println("</thead>");
		        out.println("<tbody>");

		        // Loop through each employee details and display them in a table row
		        for (Map<String, Object> empDetails : empDetailsList) {
		            out.println("<tr>");
		            out.println("<td>" + empDetails.get("table_number") + "</td>");
		            out.println("<td>" + empDetails.get("location") + "</td>");
		            out.println("</tr>");
		        }
		        
		        out.println("</tbody>");
		        out.println("</table>");
		        out.println("</div>");

		        // Write HTML footer
		        out.println("</body>");
		        out.println("</html>");

		        // Flush the output stream
		        out.flush();
		    } catch (EmptyResultDataAccessException e) {
		        out.println("<h1>No Tables Reserved</h1>");
		    }
	}
	
	//to release the tables
	
	@RequestMapping(path="/release")
	public void release(@RequestParam("tab") String tab, HttpServletResponse response) throws IOException {
	    ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
	    JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);

	    // Update query
	    String query = "UPDATE reservation SET status ='not occupied', emp_email=NULL WHERE table_number = ? AND emp_email=?";
	    int t = template.update(query, tab, user);

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    if (t == 1) {
	        out.println("<h1>Table " + tab + " successfully Released.</h1>");
	    } else {
	        out.println("<h1>Table not Reserved.</h1>");
	    }
	}

	
	//accept the notification
	@RequestMapping(path="/notfaccept")
	public String notfaccept(){
		ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
		 JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
		    
		    // Insert query
		 String query = "UPDATE msgs SET notification ='No New Notifications' WHERE emp_email = ?";
		 int t=template.update(query, user);
		return "emphome";
	}
	
	//selection of tables
	
	@RequestMapping(path="/tabsel")
	public void tabsel(@RequestParam("tab") String tab, HttpServletResponse response) throws IOException {
	    ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
	    JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);

	    // Update query
	    String query = "UPDATE reservation SET status ='occupied', emp_email = ? WHERE table_number = ?";
	    int t = template.update(query, user, tab);

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    if (t == 1) {
	        out.println("<h1>Table " + tab + " successfully Reserved.</h1>");
	    } else {
	        out.println("<h1>Table not Available.</h1>");
	    }
	}

	//reservation of tables
	
	@RequestMapping(path="/reserv")
	public void reserv(HttpServletResponse response) throws IOException{
			
		ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
	    JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
	    
	    // Select query
	    String query = "SELECT * FROM reservation where status='not occupied'";
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    try {
	        // Fetch data from the database
	        List<Map<String, Object>> empDetailsList = template.queryForList(query);
	        
	        out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<meta charset=\"UTF-8\">");
	        out.println("<title>Table Availability</title>");
	        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"");
	        out.println("integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<nav class=\"navbar navbar-expand-lg navbar-light bg-primary\">");
	        out.println("<div class=\"container-fluid\">");
	        out.println("<a class=\"navbar-brand text-white\" href=\"home\">Emp Management System</a>");
	        out.println("<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\"");
	        out.println("data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"");
	        out.println("aria-label=\"Toggle navigation\">");
	        out.println("<span class=\"navbar-toggler-icon\"></span>");
	        out.println("</button>");
	        out.println("<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">");
	        out.println("    <ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">");
	        out.println("        <li class=\"nav-item\">");
	        out.println("            <a class=\"nav-link text-white\" href=\"reserv\">Reserve Table</a>");
	        out.println("        </li>");
	        out.println("        <li class=\"nav-item\">");
	        out.println("            <a class=\"nav-link text-white\" href=\"prof\">My Profile</a>");
	        out.println("        </li>");
	        out.println("        <li class=\"nav-item\">");
	        out.println("            <a class=\"nav-link text-white\" href=\"notf\">Notifications</a>");
	        out.println("        </li>");
	        out.println("        <li class=\"nav-item\">");
	        out.println("            <a class=\"nav-link text-white\" href=\"rel\">Release Table</a>");
	        out.println("        </li>");
	        out.println("        <li class=\"nav-item\">");
	        out.println("            <a class=\"nav-link text-white\" href=\"mytab\">My Tables</a>");
	        out.println("        </li>");
	        out.println("        <li class=\"nav-item\">");
	        out.println("            <a class=\"nav-link active text-white\" style=\"position: absolute;bottom: 0;right: 0;padding:20px\" aria-current=\"page\" href=\"home\">Log Out</a>");
	        out.println("        </li>");
	        out.println("    </ul>");
	        out.println("</div>");

	        out.println("</div>");
	        out.println("</nav>");
	        // Write employee details
	        out.println("<div class=\"container\">");
	        out.println("<h1>Table Availability</h1>");
	        out.println("<table class=\"table\">");
	        out.println("<thead>");
	        out.println("<tr>");
	        out.println("<th>TABLE</th>");
	        out.println("<th>STATUS</th>");
	        out.println("<th>LOCATION</th>");
	        out.println("</tr>");
	        out.println("</thead>");
	        out.println("<tbody>");

	        // Loop through each employee details and display them in a table row
	        for (Map<String, Object> empDetails : empDetailsList) {
	            out.println("<tr>");
	            out.println("<td>" + empDetails.get("table_number") + "</td>");
	            out.println("<td>" + empDetails.get("status") + "</td>");
	            out.println("<td>" + empDetails.get("location") + "</td>");
	            out.println("</tr>");
	        }
	        
	        out.println("</tbody>");
	        out.println("</table>");
	        out.println("</div>");
	        out.println("<div class=\"container p-4\">");
	        out.println("    <div class=\"row\">");
	        out.println("        <div class=\"col-md-6 offset-md-3\">");
	        out.println("            <div class=\"card\">");
	        out.println("                <div class=\"card-header text-center\">");
	        out.println("                    <h3>Enter Table</h3>");
	        out.println("                </div>");
	        out.println("                <div class=\"card-body\">");
	        out.println("                    <form action=\"tabsel\" method=\"post\">");
	        out.println("                        <div class=\"mb-3\">");
	        out.println("                            <label>Enter Table</label>");
	        out.println("                            <input type=\"number\" name=\"tab\" class=\"form-control\" required>");
	        out.println("                        </div>");
	        out.println("                        <div style=\"display: flex; justify-content: center;\">");
	        out.println("                            <button class=\"btn btn-primary\">Submit</button>");
	        out.println("                        </div>");
	        out.println("                    </form>");
	        out.println("                </div>");
	        out.println("            </div>");
	        out.println("        </div>");
	        out.println("    </div>");
	        out.println("</div>");

	        // Write HTML footer
	        out.println("</body>");
	        out.println("</html>");

	        // Flush the output stream
	        out.flush();
	    } catch (EmptyResultDataAccessException e) {
	        out.println("<h1>No Tables Found</h1>");
	    }
		
	}
	
	//shows list of employee
	
	@RequestMapping(path="/empl")
	public void empl(HttpServletResponse response) throws IOException {
	    ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
	    JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
	    
	    // Select query
	    String query = "SELECT * FROM emp_det";
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    try {
	        // Fetch data from the database
	        List<Map<String, Object>> empDetailsList = template.queryForList(query);
	        
	        out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<meta charset=\"UTF-8\">");
	        out.println("<title>Employee List</title>");
	        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"");
	        out.println("integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<nav class=\"navbar navbar-expand-lg navbar-light bg-primary\">");
	        out.println("<div class=\"container-fluid\">");
	        out.println("<a class=\"navbar-brand text-white\" href=\"home\">Emp Management System</a>");
	        out.println("<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\"");
	        out.println("data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"");
	        out.println("aria-label=\"Toggle navigation\">");
	        out.println("<span class=\"navbar-toggler-icon\"></span>");
	        out.println("</button>");
	        out.println("</div>");
	        out.println("</nav>");
	        // Write employee details
	        out.println("<div class=\"container\">");
	        out.println("<h1>Employee List</h1>");
	        out.println("<table class=\"table\">");
	        out.println("<thead>");
	        out.println("<tr>");
	        out.println("<th>ID</th>");
	        out.println("<th>Fullname</th>");
	        out.println("<th>DESIGNATION</th>");
	        out.println("<th>CREDIT POINTS</th>");
	        out.println("</tr>");
	        out.println("</thead>");
	        out.println("<tbody>");

	        // Loop through each employee details and display them in a table row
	        for (Map<String, Object> empDetails : empDetailsList) {
	            out.println("<tr>");
	            out.println("<td>" + empDetails.get("id") + "</td>");
	            out.println("<td>" + empDetails.get("fullname") + "</td>");
	            out.println("<td>" + empDetails.get("designation") + "</td>");
	            out.println("<td>" + empDetails.get("credit_points") + "</td>");
	            out.println("</tr>");
	        }
	        
	        out.println("</tbody>");
	        out.println("</table>");
	        out.println("</div>");

	        // Write HTML footer
	        out.println("</body>");
	        out.println("</html>");

	        // Flush the output stream
	        out.flush();
	    } catch (EmptyResultDataAccessException e) {
	        out.println("<h1>No Employees Found</h1>");
	    }
	}

	
	//profile view
	
	@RequestMapping(path="/prof")
	public void prof(HttpServletResponse response) throws IOException{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        
        // Select query
        String query = "SELECT * FROM emp_det WHERE email=?";
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        try {
        // Fetch data from the database
        Map<String, Object> empDetails = template.queryForMap(query, user);
        
        	
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Profile</title>");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"");
        out.println("integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<nav class=\"navbar navbar-expand-lg navbar-light bg-primary\">");
        out.println("<div class=\"container-fluid\">");
        out.println("<a class=\"navbar-brand text-white\" href=\"home\">Emp Management System</a>");
        out.println("<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\"");
        out.println("data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"");
        out.println("aria-label=\"Toggle navigation\">");
        out.println("<span class=\"navbar-toggler-icon\"></span>");
        out.println("</button>");
        out.println("</div>");
        out.println("</nav>");
        // Write employee details
        out.println("<div class=\"container\">");
        out.println("<h1>Details</h1>");
        out.println("<p><strong>ID:</strong> " + empDetails.get("id") + "</p>");
        out.println("<p><strong>Fullname:</strong> " + empDetails.get("fullname") + "</p>");
        out.println("<p><strong>Address:</strong> " + empDetails.get("address") + "</p>");
        out.println("<p><strong>Email:</strong> " + empDetails.get("email") + "</p>");
        out.println("<p><strong>Designation:</strong> " + empDetails.get("designation") + "</p>");
        out.println("<p><strong>Salary:</strong> " + empDetails.get("salary") + "</p>");
        out.println("<p><strong>Credit Points:</strong> " + empDetails.get("credit_points") + "</p>");
        out.println("</div>");

        // Write HTML footer
        out.println("</body>");
        out.println("</html>");

        // Flush the output stream
        out.flush();
        }
        catch (EmptyResultDataAccessException e){
        	out.println("<h1>No Such Employee</h1>");
        }
	}
	
	//notification display
	
	@RequestMapping(path="/notf")
	public void notf(HttpServletResponse response) throws IOException{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        
        // Select query
        String query = "SELECT * FROM msgs WHERE emp_email=?";
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        try {
        // Fetch data from the database
        Map<String, Object> empDetails = template.queryForMap(query, user);
        
        	
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Profile</title>");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"");
        out.println("integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<nav class=\"navbar navbar-expand-lg navbar-light bg-primary\">");
        out.println("<div class=\"container-fluid\">");
        out.println("<a class=\"navbar-brand text-white\" href=\"home\">Emp Management System</a>");
        out.println("<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\"");
        out.println("data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"");
        out.println("aria-label=\"Toggle navigation\">");
        out.println("<span class=\"navbar-toggler-icon\"></span>");
        out.println("</button>");
        out.println("</div>");
        out.println("</nav>");
        // Write employee details
        out.println("<div class=\"container\">");
        out.println("<h1>Notifications</h1>");
        out.println("<p>" + empDetails.get("notification") + "</p>");
        out.println("</div>");
        out.println("                <div class=\"card-body\">");
        out.println("                    <form action=\"notfaccept\" method=\"post\">");
        out.println("                        <div style=\"display: flex; justify-content: center;\">");
        out.println("                            <button class=\"btn btn-primary\">Ok</button>");
        out.println("                        </div>");
        out.println("                    </form>");
        out.println("                </div>");
        // Write HTML footer
        out.println("</body>");
        out.println("</html>");

        // Flush the output stream
        out.flush();
        }
        catch (EmptyResultDataAccessException e){
        	out.println("<h1>No Such Employee</h1>");
        }
	}
	
	//creation of new employee

	@RequestMapping(path="/createemp", method=RequestMethod.POST)
	public String createemp(@ModelAttribute emp em) {
	    ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
	    JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
	    
	    // Insert query
	    String query = "insert into emp_det(fullname, address, email, password, designation, salary, credit_points) values(?,?,?,?,?,?,?)";
	    String query1 = "insert into msgs(emp_email,notification) values(?,?)";

	    // Fire query
	    template.update(query, em.getFullname(), em.getAddress(), em.getEmail(), em.getPassword(), em.getDesignation(), em.getSalary(), 0);
	    template.update(query1,em.getEmail(),"No Notifications");

	    
	    return "addemp";
	}
	
	//delete an employee record
	
	@RequestMapping(path="/delemp", method=RequestMethod.POST)
	public String delemp(@RequestParam("email") String email) {
	    ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
	    JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
	    
	    // Insert query
	    String query = "DELETE FROM emp_det WHERE email=?";
	    
	    // Fire query
	    template.update(query, email);
	    System.out.println("deleted");
	    return "rem";
	}
	
	//update the details of employee
	
	@RequestMapping(path="/chemp", method=RequestMethod.POST)
	public String updateEmpFields(@RequestParam("email") String email, 
	                              @RequestParam(value="salary", required=false) String salary,
	                              @RequestParam(value="address", required=false) String address,
	                              @RequestParam(value="designation", required=false) String designation,
	                              @RequestParam(value="credit_points", required=false) String credit_points) {
	    ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
	    JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
	    
	    // Start building the update query
	    StringBuilder queryBuilder = new StringBuilder("UPDATE emp_det SET ");
	    List<Object> params = new ArrayList<>();
	    
	    // Update only if the field is not null or empty
	    if (!StringUtils.isEmpty(salary)) {
	        queryBuilder.append("salary=?, ");
	        params.add(salary);
	        String query="UPDATE msgs SET notification = 'Your Salary Have Been Changed Please Refer to that.' WHERE emp_email=?";
	        template.update(query, email);
	    }
	    if (!StringUtils.isEmpty(address)) {
	        queryBuilder.append("address=?, ");
	        params.add(address);
	        String query="UPDATE msgs SET notification = 'Your Address Have Been Changed Please Refer to that.' WHERE emp_email=?";
	        template.update(query, email);
	    }
	    if (!StringUtils.isEmpty(designation)) {
	        queryBuilder.append("designation=?, ");
	        params.add(designation);
	        String query="UPDATE msgs SET notification = 'Your Designation Have Been Changed Please Refer to that.' WHERE emp_email=?";
	        template.update(query, email);
	    }
	    if (!StringUtils.isEmpty(credit_points)) {
	        queryBuilder.append("credit_points=?, ");
	        params.add(credit_points);
	        String query="UPDATE msgs SET notification = 'Your Credit Points Have Been Changed Please Refer to that(Based on your Performance).' WHERE emp_email=?";
	        template.update(query, email);
	    }
	    
	    // Remove the trailing comma and space
	    queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
	    
	    // Add the WHERE clause
	    queryBuilder.append(" WHERE email=?");
	    params.add(email);
	    
	    // Execute the update query
	    template.update(queryBuilder.toString(), params.toArray());
	    System.out.println("Updated");
	    return "updateemp";
	}

	//profile view by the admin

	@RequestMapping(path="/seedet", method=RequestMethod.POST)
    public void seedet(@RequestParam("email") String email,HttpServletResponse response) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        
        // Select query
        String query = "SELECT * FROM emp_det WHERE email=?";
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        try {
        // Fetch data from the database
        Map<String, Object> empDetails = template.queryForMap(query, email);
        
        	
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Employee Profile</title>");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"");
        out.println("integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<nav class=\"navbar navbar-expand-lg navbar-light bg-primary\">");
        out.println("<div class=\"container-fluid\">");
        out.println("<a class=\"navbar-brand text-white\" href=\"home\">Emp Management System</a>");
        out.println("<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\"");
        out.println("data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"");
        out.println("aria-label=\"Toggle navigation\">");
        out.println("<span class=\"navbar-toggler-icon\"></span>");
        out.println("</button>");
        out.println("<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">");
        out.println("<ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">");
        out.println("<li class=\"nav-item\">");
        out.println("<a class=\"nav-link active text-white\" aria-current=\"page\" href=\"home\">Home</a>");
        out.println("</li>");
        out.println("<li class=\"nav-item\">");
        out.println("<a class=\"nav-link text-white\" href=\"addemp\">Add Employee</a>");
        out.println("</li>");
        out.println("<li class=\"nav-item\">");
        out.println("<a class=\"nav-link text-white\" href=\"rem\">Remove Employee</a>");
        out.println("</li>");
        out.println("<li class=\"nav-item\">");
        out.println("<a class=\"nav-link text-white\" href=\"updateemp\">Update Employee</a>");
        out.println("</li>");
        out.println("<li class=\"nav-item\">");
        out.println("<a class=\"nav-link text-white\" href=\"reademp\">Employee Profile</a>");
        out.println("</li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("</div>");
        out.println("</nav>");

        // Write employee details
        out.println("<div class=\"container\">");
        out.println("<h1>Employee Details</h1>");
        out.println("<p><strong>ID:</strong> " + empDetails.get("id") + "</p>");
        out.println("<p><strong>Fullname:</strong> " + empDetails.get("fullname") + "</p>");
        out.println("<p><strong>Address:</strong> " + empDetails.get("address") + "</p>");
        out.println("<p><strong>Email:</strong> " + empDetails.get("email") + "</p>");
        out.println("<p><strong>Designation:</strong> " + empDetails.get("designation") + "</p>");
        out.println("<p><strong>Salary:</strong> " + empDetails.get("salary") + "</p>");
        out.println("<p><strong>Credit Points:</strong> " + empDetails.get("credit_points") + "</p>");
        out.println("</div>");

        // Write HTML footer
        out.println("</body>");
        out.println("</html>");

        // Flush the output stream
        out.flush();
        }
        catch (EmptyResultDataAccessException e){
        	out.println("<h1>No Such Employee</h1>");
        }
        
    }
	
	//login of aadmin
	
	@RequestMapping(path="/adin", method=RequestMethod.POST)
    public String adin(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        // Load the Spring application context
        ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
        
        // Get the JdbcTemplate bean from the application context
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        
        // Select query
        String query = "SELECT fullname FROM admin_det WHERE email=? AND password=?";
        
        // Fetch data from the database
        String fullName;
        try {
            fullName = jdbcTemplate.queryForObject(query, String.class, email, password);
        } catch (EmptyResultDataAccessException ex) {
            // User not found, redirect to login page with an error message
            return "logf";
        }
        
        // User found, set user information in session
        session.setAttribute("loggedInUser", email);
        return "admin";
    }
	
	//login of employee
	
	@RequestMapping(path="/empin", method=RequestMethod.POST)
    public String empin(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        // Load the Spring application context
        ApplicationContext context = new ClassPathXmlApplicationContext("com/emp/config/config.xml");
        
        // Get the JdbcTemplate bean from the application context
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        
        // Select query
        String query = "SELECT fullname FROM emp_det WHERE email=? AND password=?";
        
        // Fetch data from the database
        String fullName;
        try {
            fullName = jdbcTemplate.queryForObject(query, String.class, email, password);
        } catch (EmptyResultDataAccessException ex) {
            // User not found, redirect to login page with an error message
            return "logf";
        }
        
        // User found, set user information in session
        session.setAttribute("loggedInUser", email);
        this.user=(String)session.getAttribute("loggedInUser");
        return "emphome";
    }

}
