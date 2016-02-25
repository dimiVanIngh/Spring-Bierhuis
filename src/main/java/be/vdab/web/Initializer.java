package be.vdab.web;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import be.vdab.dao.CreateDAOBeans;
import be.vdab.datasource.CreateDataSourceBean;
import be.vdab.services.CreateServiceBeans;
//TODO apart jsp invulform ; resourcebundle for all text
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { CreateDAOBeans.class, CreateServiceBeans.class, CreateDataSourceBean.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { CreateControllerBeans.class };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter utf8Filter = new CharacterEncodingFilter();
	    utf8Filter.setEncoding("UTF-8");
		return new Filter[] { new OpenEntityManagerInViewFilter(), utf8Filter };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("dispatchOptionsRequest", "true");
	}
	@Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet ds = new DispatcherServlet(servletAppContext);
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
}